package edu.isen.fhgd.fft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * \file Fft.java
 * Class contenant les diffèrentes fft
 */
public class Fft {
    private static final Logger LOGGER = LoggerFactory.getLogger(Fft.class);
    private int tailleP2;
    private float tabComplexes[][];
    private float tabReel[];

    /**
     * Constructeur Complexe
     *
     * @param tailleP2
     * @param tabComplexes
     */
    public Fft(int tailleP2, float tabComplexes[][]) {
        this.tailleP2 = tailleP2;
        this.tabComplexes = tabComplexes;
    }

    /**
     * Constructeur Reel
     *
     * @param tailleP2
     * @param tabReel
     */
    public Fft(int tailleP2, float[] tabReel) {
        if (testPuissance2(tailleP2)) this.tailleP2 = tailleP2;
        else LOGGER.error("La taille doit être une puissance de 2 !");
        this.tabReel = tabReel;
    }

    /**
     * Cette méthode calcule une fft réel
     */
    public float[] fftRapideReel() {
        return this.tabReel;
    }

    /**
     * Cette méthode calcule une fft complèxe
     */
    public float[][] fftRapideComplexe() {
        return this.tabComplexes;
    }

    /**
     * Test si la valeur est bien une puissance de 2
     *
     * @param value
     * @return
     */
    public boolean testPuissance2(int value) {
        return Long.bitCount(value) == 1;
        //bitCount compte le nombre de bits à l'état haut
        //Pour la représentation d'une puissance de 2 en binaire, seul 1 bit est à l'état haut
    }

    /**
     * @return tailleP2
     */
    public int getTailleP2() {
        return tailleP2;
    }

    /**
     * @param tailleP2
     */
    public void setTailleP2(int tailleP2) {
        this.tailleP2 = tailleP2;
    }

    /**
     * @return tabComplexes
     */
    public float[][] getTabComplexes() {
        return tabComplexes;
    }

    /**
     * @param tabComplexes
     */
    public void setTabComplexes(float[][] tabComplexes) {
        this.tabComplexes = tabComplexes;
    }

    public float[] getTabReel() {
        return tabReel;
    }

    public void setTabReel(float[] tabReel) {
        this.tabReel = tabReel;
    }
}
