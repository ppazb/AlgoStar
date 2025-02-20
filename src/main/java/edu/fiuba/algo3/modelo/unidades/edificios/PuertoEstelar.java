package edu.fiuba.algo3.modelo.unidades.edificios;

import edu.fiuba.algo3.exceptions.*;
import edu.fiuba.algo3.modelo.casillero.Casillero;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.unidades.moviles.Dragon;
import edu.fiuba.algo3.modelo.unidades.moviles.Scout;
import edu.fiuba.algo3.modelo.unidades.moviles.UnidadMovil;

public class PuertoEstelar extends EdificioProtoss {
    private static int COSTO_GASEOSO = 150;
    private static int COSTO_MINERAL = 150;
    private static int VIDA = 600;
    private static int ESCUDO = 600;
    private static int TURNOS_PARA_CONSTRUIR = 10;
    private static final int NIVEL_DE_CONSTRUCCION = 0;
    private static final int NIVEL_DE_CONSTRUCCION_REQUERIDO = 1;
    private boolean estaEvolucionando;
    private Engendradora engendradora;
    public PuertoEstelar(Casillero casillero, Inventario inventario) {
        super(casillero, inventario, VIDA, ESCUDO);
        casillero.ocupar(this);
        inventario.pagarMateriales(COSTO_GASEOSO,COSTO_MINERAL);
        this.estaEvolucionando = false;
        this.engendradora = null;
    }
    public void ubicarEnInventario(){
        inventario.subirNivelConstruccion(NIVEL_DE_CONSTRUCCION);
    }

    public void pasarTurno() {
        super.pasarTurno();
        if(estaEvolucionando){
            engendradora.pasarTurno();
            this.estaEvolucionando = (!engendradora.estaListo());
            //acaba habria que chequear que si el casillero que da al obtener adyacentes es nulo (porque no hay ninguno libre)
        }
    }

    public int turnosParaConstruir() {
        return TURNOS_PARA_CONSTRUIR;
    }

    public static EdificioEnConstruccion construir(Casillero casillero, Inventario inventario) {
        if(!casillero.tieneEnergia()){
            throw new UbicacionInvalida("Ubicacion invalida");
        }
        if(!inventario.tieneRecursos(COSTO_GASEOSO, COSTO_MINERAL)){
            throw new RecursosInsuficientes("No tiene recursos");
        }
        if(!inventario.puedeConstruir(NIVEL_DE_CONSTRUCCION_REQUERIDO)){
            throw new CorrelativasInsuficientes("Aún no se puede \ncontruir este edificio,\n requiere Acceso");
        }
        PuertoEstelar puertoEstelar = new PuertoEstelar(casillero, inventario);
        return new EdificioEnConstruccion(puertoEstelar, casillero, inventario);
    }

    public static int getNivelDeConstruccionRequerido() {
        return NIVEL_DE_CONSTRUCCION_REQUERIDO;
    }

    public void engendrarScout() throws CasilleroNoCompatible{
        this.chequeoCasillero();
        if(this.estaEvolucionando){
            throw new EdificioOcupado("Ya hay una unidad en creación.");
        }
        Scout scout = new Scout(inventario);
        this.engendradora = new Engendradora(this.casillero, this.inventario, scout);
        this.estaEvolucionando = true;
    }
}
