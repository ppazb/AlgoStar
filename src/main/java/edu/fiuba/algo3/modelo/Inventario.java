package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.exceptions.PoblacionMaximaAlcanzada;
import edu.fiuba.algo3.exceptions.RecursosInsuficientes;
import edu.fiuba.algo3.modelo.unidades.moviles.AmoSupremo;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private int cantidadGas;
    private int cantidadMineral;
    List<Unidad> unidades;
    List<Unidad> edificios;

    private static int POBLACION_MAXIMA =200;
    private static int SUMINISTROS_MAXIMOS = 200;
    private int suministrosDisponibles;
    int suministrosEmpleados;

    private int nivelConstruccion;
    public Inventario(){
        this.cantidadGas = 0;
        this.cantidadMineral = 200;
        this.suministrosDisponibles = 0;
        this.suministrosEmpleados = 0;
        this.unidades = new ArrayList<>();
        this.edificios = new ArrayList<>();
        this.nivelConstruccion = 0;
    }
    public boolean puedeConstruir(int nivelDificultadConstruccion) {
        return (nivelDificultadConstruccion <= this.nivelConstruccion);
    }

    public void subirNivelConstruccion(int nivelOtorga){
        //Si puedo construir algo que otorga mayor nivel al mio, sube mi nivel de construccion
        if(nivelOtorga > this.nivelConstruccion){
            this.nivelConstruccion = nivelOtorga;
        }
    }
    public void agregarGas(int i) {
        this.cantidadGas += i;
    }
    public void agregarMineral(int i) {
        this.cantidadMineral += i;
    }
    public void agregarUnidad(Unidad unidad) {
        this.unidades.add(unidad);
    }
    public void agregarEdifico(Unidad edificio) {
        this.edificios.add(edificio);
    }

    public void agregarSuministro(int cantidad){
        this.suministrosDisponibles += cantidad;
        if(this.suministrosDisponibles > SUMINISTROS_MAXIMOS){this.suministrosDisponibles = 200;}
    }
    public void perderSuministro(int cantidad){
        this.suministrosDisponibles -= cantidad;
    }
    public boolean tieneSuministros(int cantidad){
        return this.suministrosDisponibles >= cantidad;
    }
    public boolean puedeCrecerPoblacion(int cantidad){
        return (this.suministrosEmpleados+cantidad) <= POBLACION_MAXIMA;
    }
    public void suministrarUnidad(int cantidad) throws PoblacionMaximaAlcanzada {
        if (tieneSuministros(cantidad) && puedeCrecerPoblacion(cantidad)){ //Puedo pagarlo y tengo capacidad de poblacion
            this.suministrosEmpleados += cantidad;
            perderSuministro(cantidad);
        }
    }

    public boolean tieneRecursos(int cantidadGas, int cantMineral) {
        return ((this.cantidadMineral >= cantMineral) && (this.cantidadGas >= cantidadGas));
    }
    public void pagarMateriales(int cantidadGas, int cantidadMineral){
        if(!this.tieneRecursos(cantidadGas,cantidadMineral)){
            throw new RecursosInsuficientes("Recursos insuficientes");
        }
        this.cantidadMineral -= cantidadMineral;
        this.cantidadGas -= cantidadGas;
    }

    public boolean tieneEdificios(){
        return !(unidades.isEmpty());
        //TODO cuando esten separados los edificios se manda a edificios
    }
    public void pasarTurno(){
        for(Unidad unidad :unidades){
            unidad.pasarTurno();
        }
    }
    public List<AmoSupremo> obtenerAmosSupremos(){
        List<AmoSupremo> amosSupremos = new ArrayList<>();
        for (Unidad unidad : this.unidades) {
            if (unidad instanceof AmoSupremo) {
                amosSupremos.add((AmoSupremo) unidad);
            }
        }
        return amosSupremos;
    }
    public List<Unidad> getUnidades(){
        return this.unidades;
    }
    public List<Unidad> getEdificios(){
        return this.edificios;
    }
    public int getGas(){
        return this.cantidadGas;
    }
    public int getMineral(){
        return this.cantidadMineral;
    }

    public int getSuministros(){
        return this.suministrosDisponibles;
    }
    public int getSuministrosMaximos(){
        return SUMINISTROS_MAXIMOS;
    }
    public int getSuministrosEmpleados(){
        return this.suministrosEmpleados;
    }
    public int getPoblacionMaxima(){
        return POBLACION_MAXIMA;
    }
}
