package edu.isen.fhgd.fft;

import edu.isen.fhgd.fft.complexe.Complexe;
import org.junit.Test;

import static org.junit.Assert.*;

public class FFTTest {
    @Test
    public void FFTCompexeExpo() throws Exception {
        Complexe[] signal = new Complexe[8];
        for (int i = 0; i < 8; i++) {
            signal[i] = new Complexe((float) (2 * Math.PI * i));
        }
        FFT tranformee = new FFT(8, signal);
        Complexe[] sortie = tranformee.FFTComplexe();

        for (int i = 0; i < 8; i++) {
            System.out.println(sortie[i]);
        }
    }

    @Test
    public void FFTComplexeDirac() throws Exception {
        Complexe[] valeurDirac = new Complexe[8];
        valeurDirac[0]=new Complexe(1,0);
        for (int i = 1; i < 8; i++) {
            valeurDirac[i]=new Complexe(0,0);
        }
        FFT dirac= new FFT(8, valeurDirac);
        Complexe[] sortie = dirac.FFTComplexe();
        for(int i=0;i<valeurDirac.length;i++)
        {
            System.out.println("i = " + i + " : " + sortie[i]);
        }
    }

    @Test
    public void FFTRelle() {
        float[] signal = new float[8];
        for (int i = 0; i < 8; i++) {
            System.out.println(signal[i]);
        }
        FFT tranformee = new FFT(8, signal);
        Complexe[] sortie = tranformee.FFTRelle();

        for (int i = 0; i < 8; i++) {
            System.out.println(sortie[i]);
        }
    }

    @Test
    public void FFT() throws Exception
    {
        try {
            float test[] = {0,0};
            FFT objet = new FFT(51, test);
            fail("Expected an IllegalArgumentException to be thrown");
        }catch(IllegalArgumentException aExp){
            assert(aExp.getMessage().contains("La taille n'est pas une puissance de 2"));
        }

        try {
            float test[] = {0,0};
            FFT objet = new FFT(64, test);
        }catch(IllegalArgumentException aExp){
            assert(aExp.getMessage().contains("La taille n'est pas une puissance de 2"));
            fail("No error expected");
        }
    }
}