package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.exceptions.EstaDestruido;

public class VidaProtoss extends Vida {

    private Escudo escudo;

    public VidaProtoss(int vida, int escudo){
        super(vida);
        this.escudo = new Escudo(escudo);
    }
    public void sufrirAtaque(int danio) throws EstaDestruido {
        try{
            this.escudo.sufrirAtaque(danio);
        } catch (Exception EstaDestruido) {
            this.vida -= danio;
            super.chequeoDeVida();
        }
    }
    public void pasarTurno(){
        this.escudo.regenerar();
    }

    public int getEscudo(){
        return this.escudo.getEscudo();
    }
    public int getEscudoMaximo(){
        return this.escudo.getEscudoMaximo();
    }
}
