package edu.isen.fhgd.fft;

import edu.isen.fhgd.fft.complexe.Complexe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * \file Fft.java
 * Class contenant les diffèrentes fft
 */
public class Fft {
    private static final Logger LOGGER = LoggerFactory.getLogger(Fft.class);

    private int tailleP2;
    private Complexe[] signal;
    private Complexe[] sortie;

    private float[] signalR;
    private float[] sortieR;

    /**
     * @param tailleP2
     * @param signal
     */
    public Fft(int tailleP2, Complexe[] signal) throws IllegalArgumentException {
        if (testPuissance2(tailleP2)) {
            this.tailleP2 = tailleP2;
            this.signal = signal;
            LOGGER.info("Création d'un objet FFT");
        } else {
            LOGGER.error("La taille doit être une puissance de 2 !");
            throw new IllegalArgumentException("La taille n'est pas une puissance de 2");
        }
    }

    /**
     * @param tailleP2
     * @param tabReel
     */
    public Fft(int tailleP2, float[] tabReel) throws IllegalArgumentException {
        if (testPuissance2(tailleP2)) {
            this.tailleP2 = tailleP2;
            this.signalR = tabReel;
            LOGGER.info("Création d'un objet FFT");
        } else {
            LOGGER.error("La taille doit être une puissance de 2 !");
            throw new IllegalArgumentException("La taille n'est pas une puissance de 2");
        }
    }

    /**
     * Cette méthode calcule une fft réel
     */
    public float[] fftRapideReel() {
        return this.sortieR;
    }

    /**
     * Cette méthode calcule une fft complèxe
     */
    public Complexe[] fftRapideComplexe() {
        this.sortie = new Complexe[tailleP2];
        if (tailleP2 == 1) {
            this.sortie[0] = this.signal[0];
        } else {
            Complexe[] tabPairs = new Complexe[tailleP2 / 2];
            Complexe[] tabImpairs = new Complexe[tailleP2 / 2];

            for (int i = 0; i < this.tailleP2 / 2; i++) {
                tabPairs[i] = this.signal[2 * i];
                tabImpairs[i] = this.signal[2 * i + 1];
            }

            Fft paire = new Fft(this.tailleP2 / 2, tabPairs);
            Fft impaire = new Fft(this.tailleP2 / 2, tabImpairs);
            //On calcule la FFT des deux listes
            paire.fftRapideComplexe();
            impaire.fftRapideComplexe();

            for (int k = 0; k <= (tailleP2 / 2) - 1; k++) {
                Complexe M = new Complexe((float) (-2 * Math.PI * k) / tailleP2);
                this.sortie[k] = (paire.sortie[k]).addition(impaire.sortie[k].multiplication(M));
                this.sortie[k + (tailleP2 / 2)] = (paire.sortie[k]).soustraction(impaire.sortie[k].multiplication(M));
            }
        }
        return this.sortie;
    }

    /**
     *
     */
    public Complexe[] inverseFFT() {
        for (int i = 0; i < signal.length; i++) {
            signal[i] = signal[i].conjugue();
        }
        this.fftRapideComplexe();
        for (int i = 0; i < this.sortie.length; i++) {
            this.sortie[i] = this.sortie[i].conjugue();
            this.sortie[i] = this.sortie[i].multiplication(new Complexe(1 / (float) this.sortie.length, 0));
        }
        return this.sortie;
    }

    /**
     * Test si la valeur est bien une puissance de 2
     *
     * @param value
     * @return
     */
    private boolean testPuissance2(int value) {
        //bitCount compte le nombre de bits à l'état haut
        //Pour la représentation d'une puissance de 2 en binaire, seul 1 bit est à l'état haut
        return Long.bitCount(value) == 1;
    }

    /**
     * @return tailleP2
     */
    public int getTailleP2() {
        return tailleP2;
    }
}
