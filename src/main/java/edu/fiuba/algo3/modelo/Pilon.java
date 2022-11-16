package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exceptions.RecursosInsuficientes;
import edu.fiuba.algo3.exceptions.UbicacionInvalida;

public class Pilon extends EdificioProtoss {
    public Pilon(Casillero casillero, Inventario inventario) {
        super(casillero, inventario, 300, 300);
    }

    public void pasarTurno() {
        super.pasarTurno();
    }

    int turnosParaConstruir() {
        return 5;
    }

    public static EdificioEnConstruccion construir(Casillero casillero, Inventario inventario) {
        EdificioConcreto pilon = new Pilon(casillero, inventario);
        if(!casillero.esDelTipo(new CasilleroVacio())){
            throw new UbicacionInvalida("Ubicacion invalida");
        }
        if(!inventario.tieneRecursos(0, 100)){
            throw new RecursosInsuficientes("No tiene recursos");
        }
        return new EdificioEnConstruccion(pilon, casillero, inventario);
    }
}
