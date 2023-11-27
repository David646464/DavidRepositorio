import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestorInmueblesTest {
    private GestorInmuebles gestor;
    private Piso piso1, piso2;
    private Casa casa1, casa2;

    @Before
    public void setUp() {
        piso1 = new Piso("Calle Mayor 10, 5º", 50, 2, 1, 500, 100000, 1);
        piso2 = new Piso("Calle Mayor 11, 3A", 75, 3, 2, 0, 150000, 2);
        casa1 = new Casa("Calle Ancha 20", 100, 3, 2, 1000, 200000, 500);
        casa2 = new Casa("Calle Ancha 21", 150, 4, 3, 0, 300000, 1000);
        gestor = new GestorInmuebles();
        gestor.agregar(piso1);
        gestor.agregar(piso2);
        gestor.agregar(casa1);
        gestor.agregar(casa2);
    }

    @Test
    public void testAgregar() {
        Piso piso3 = new Piso("Calle Nueva 1, 2A", 1, 1, 30, 300, 50000, 1);
        assertTrue(gestor.agregar(piso3));
        assertEquals(gestor.inmuebles.size(), 5);
        assertTrue(gestor.inmuebles.contains(piso3));
    }

    @Test
    public void testEliminar() {
        assertTrue(gestor.eliminar(piso2));
        assertEquals(gestor.inmuebles.size(), 3);
        assertFalse(gestor.inmuebles.contains(piso2));
    }

    @Test
    public void testOrdenarNatural() {
        GestorInmuebles gestor = new GestorInmuebles();
        gestor.agregar(piso1);
        gestor.agregar(piso2);
        gestor.agregar(casa1);
        gestor.agregar(casa2);

        gestor.ordenar();
        List<Inmueble> inmueblesOrdenados = new ArrayList<>();
        inmueblesOrdenados.add(casa1);
        inmueblesOrdenados.add(casa2);
        inmueblesOrdenados.add(piso1);
        inmueblesOrdenados.add(piso2);

        assertEquals(inmueblesOrdenados, gestor.inmuebles);
    }

    @Test
    public void testOrdenarInverso() {
        gestor.ordenarInverso();
        List<Inmueble> inmueblesOrdenados = new ArrayList<>();
        inmueblesOrdenados.add(piso2);
        inmueblesOrdenados.add(piso1);
        inmueblesOrdenados.add(casa2);
        inmueblesOrdenados.add(casa1);

        assertEquals(inmueblesOrdenados, gestor.inmuebles);
    }

    @Test
    public void testOrdenarMetros() {
        gestor.ordenarMetros();
        List<Inmueble> inmueblesOrdenados = new ArrayList<>();
        inmueblesOrdenados.add(piso1);
        inmueblesOrdenados.add(piso2);
        inmueblesOrdenados.add(casa1);
        inmueblesOrdenados.add(casa2);

        assertEquals(inmueblesOrdenados, gestor.inmuebles);
    }

    @Test
    public void testOrdenarHabMetrosDesc() {
        gestor.ordenarHabMetrosDesc();
        List<Inmueble> inmueblesOrdenados = new ArrayList<>();
        inmueblesOrdenados.add(casa2);
        inmueblesOrdenados.add(casa1);
        inmueblesOrdenados.add(piso2);
        inmueblesOrdenados.add(piso1);

        assertEquals(inmueblesOrdenados, gestor.inmuebles);
    }

    @Test
    public void testOrdenarPersonalizado() {
        Comparator<Inmueble> comparador = new Comparator<Inmueble>() {
            @Override
            public int compare(Inmueble inmueble1, Inmueble inmueble2) {
                return Double.compare(inmueble1.getPrecioAlquiler(), inmueble2.getPrecioAlquiler());
            }
        };
        gestor.ordenar(comparador);
        List<Inmueble> inmueblesOrdenados = new ArrayList<>();
        inmueblesOrdenados.add(piso2);
        inmueblesOrdenados.add(casa2);
        inmueblesOrdenados.add(piso1);
        inmueblesOrdenados.add(casa1);

        assertEquals(inmueblesOrdenados, gestor.inmuebles);
    }

    @Test
    public void testGetEnAlquiler() {
        List<Inmueble> inmueblesEnAlquiler = gestor.getEnAlquiler().inmuebles;
        assertEquals(2, inmueblesEnAlquiler.size());
        assertTrue(inmueblesEnAlquiler.contains(piso1));
        assertTrue(inmueblesEnAlquiler.contains(casa1));
    }

    @Test
    public void testGetCasas() {
        List<Inmueble> casas = gestor.getCasas().inmuebles;
        assertEquals(2, casas.size());
        assertTrue(casas.contains(casa1));
        assertTrue(casas.contains(casa2));
    }
}