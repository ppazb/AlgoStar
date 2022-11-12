package edu.fiuba.algo3.modelo;

public class Casillero extends EdificioZerg{
    private TipoCasillero tipoCasillero;
    private int energia;
    private int coordenadaX;
    private int coordenadaY;

    

    public void setTipoCasillero(TipoCasillero unTipoCasillero){
        tipoCasillero = unTipoCasillero;
    }

    public boolean sonDelMismoTipoDeCasillero(Casillero unCasillero){
        return (this.suTipoDeCasillero() == unCasillero.suTipoDeCasillero());
    }
    public String suTipoDeCasillero(){
        return tipoCasillero.nombreDelCasillero();
    }

    //revisar esto, sino rompia encapsulamiento
    public void extraerMineral(Inventario inventario, int cantidad){
        this.tipoCasillero.extraerMineral(Inventario inventario, int cantidad);
    }

}
