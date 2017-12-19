package edu.isen.fhgd.fft;

import org.junit.Test;

import static org.junit.Assert.*;

public class FftTest {

    @Test
    public void testPuissance2() {
        float test[] = {0,0};
        Fft fft = new Fft(8, test);
        assertEquals(true,fft.testPuissance2(8));
        assertEquals(false,fft.testPuissance2(9));
        assertEquals(false,fft.testPuissance2(11));
        assertEquals(false,fft.testPuissance2(14));
    }
}