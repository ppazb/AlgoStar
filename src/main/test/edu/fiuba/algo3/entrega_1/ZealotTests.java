package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class ZealotTests {
    @Test
    public void test01ZealotAtacaAUnidadVoladorYNoLeHaceDanio() {
        //ARRANGE
        Inventario inventarioMock = mock(Inventario.class);
        when(inventarioMock.tieneRecursos(anyInt(),anyInt())).thenReturn(true);
        UnidadMovil unidadMock = mock(UnidadMovil.class);
        when(unidadMock.esVoladora()).thenReturn(true);
        Zealot zealot = new Zealot(inventarioMock);
        //ACT
        try {
            zealot.atacar(unidadMock);
            //ASSERT
            verify(unidadMock, times(1)).recibirDanio(0);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void test02ZealotAtacaAUnidadTerrestreYLeHace8DeDanio() {
        //ARRANGE
        Inventario inventarioMock = mock(Inventario.class);
        when(inventarioMock.tieneRecursos(anyInt(),anyInt())).thenReturn(true);
        UnidadMovil unidadMock = mock(UnidadMovil.class);
        when(unidadMock.esVoladora()).thenReturn(false);
        Zealot zealot = new Zealot(inventarioMock);
        //ACT
        try {
            zealot.atacar(unidadMock);
            //ASSERT
            verify(unidadMock, times(1)).recibirDanio(8);
        } catch (Exception e) {
            fail();
        }
    }
}
