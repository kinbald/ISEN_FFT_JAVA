package edu.isen.fhgd.fft;

import edu.isen.fhgd.fft.complexe.Complexe;
import org.junit.Test;

import static org.junit.Assert.*;

public class FftTest {
    @Test
    public void fftRapideComplexe() throws Exception {
        Complexe[] signal = new Complexe[8];
        for (int i = 0; i < 8; i++) {
            signal[i] = new Complexe((float) (2 * Math.PI * i));
        }
        Fft tranformee = new Fft(8, signal);
        Complexe[] sortie = tranformee.fftRapideComplexe();

        for (int i = 0; i < 8; i++) {
            System.out.println(sortie[i]);
        }
    }

    @Test
    public void Fft() throws Exception
    {
        try {
            float test[] = {0,0};
            Fft objet = new Fft(51, test);
            fail("Expected an IllegalArgumentException to be thrown");
        }catch(IllegalArgumentException aExp){
            assert(aExp.getMessage().contains("La taille n'est pas une puissance de 2"));
        }

        try {
            float test[] = {0,0};
            Fft objet = new Fft(64, test);
        }catch(IllegalArgumentException aExp){
            assert(aExp.getMessage().contains("La taille n'est pas une puissance de 2"));
            fail("No error expected");
        }
    }
}