package edu.fiuba.algo3.modelo;

public class Inventario {
    int cantidadGas;
    int cantidadMineral;


    public Inventario(int cantidadInicialGas, int cantidadInicialMineral){
        this.cantidadGas = cantidadInicialGas;
        this.cantidadMineral = cantidadInicialMineral;
    }

    private boolean puedePagar(int pagoDeMineral, int pagoDeGas){
        if((this.cantidadMineral < pagoDeMineral) || (this.cantidadGas < pagoDeGas)){
            return false;
        }
        return true;
    }
    public void pagar(int pagoDeGas, int pagoDeMineral) throws Exception {
        if (!this.puedePagar(pagoDeGas, pagoDeMineral)) {
            throw new Exception("Materiales insuficientes");
        }
        this.cantidadGas -= pagoDeGas;
        this.cantidadMineral -= pagoDeMineral;
    }

    public void recibirMineral(int cantidad){
        this.cantidadMineral += cantidad;
    }
    public void recibirGas(int cantidad){
        this.cantidadGas += cantidad;
    }

    public void agregar(EdificioConcreto edificioConcreto) {
    }

    public boolean tieneRecursos(int cantidadDeGas, int cantidadDeMineral){
        return (this.cantidadGas >= cantidadDeGas) && (this.cantidadMineral >= cantidadDeMineral);
    }

    public boolean tieneAcceso() {
        return true;
    }
}
