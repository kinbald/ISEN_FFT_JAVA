package edu.isen.fhgd.fft.complexe;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexeTest {

    @Test
    public void addition() throws Exception {
        Complexe a = new Complexe(1,1);
        Complexe b = new Complexe(10, 5);
        Complexe c = new Complexe(11, 6);
        assertEquals(c, a.addition(b));
    }

    @Test
    public void minus() throws Exception {
        Complexe a = new Complexe(1,1);
        Complexe b = new Complexe(10, 5);
        Complexe c = new Complexe(-9, -4);
        assertEquals(c, a.soustraction(b));
    }

    @Test
    public void conjugue() throws Exception {
        Complexe a = new Complexe(5,1);
        Complexe b = new Complexe(5, -1);
        Complexe c = new Complexe(5, 0);
        assertEquals(b, a.conjugue());
        assertEquals(c, c.conjugue());
    }

    @Test
    public void multiplication() throws Exception {
    }

    @Test
    public void re() throws Exception {
        Complexe a = new Complexe(1,1);
        Complexe b = new Complexe(10, 5);
        assertEquals(1, a.Re());
        assertEquals(10, b.Re());
    }

    @Test
    public void im() throws Exception {
        Complexe a = new Complexe(1,1);
        Complexe b = new Complexe(10, 5);
        assertEquals(1, a.Im());
        assertEquals(5, b.Im());
    }

}