package edu.isen.fhgd.fft;

import edu.isen.fhgd.fft.complexe.Complexe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * \file FFT.java
 * Class contenant les diffèrentes fft
 */
public class FFT {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FFT.class);

    /**
     * Taille du tableau (correspond à une puissance de 2)
     */
    private int tailleP2;

    /**
     * Signal complexe d'entrée
     */
    private Complexe[] signal;

    /**
     * Sortie de l'algorithme
     */
    private Complexe[] sortie;

    /**
     * Signal d'entrée réel
     */
    private float[] signalR;

    /**
     * Constructeur d'objet à partir d'un signal complexe
     *
     * @param tailleP2
     * @param signal
     */
    public FFT(int tailleP2, Complexe[] signal) throws IllegalArgumentException {
        if (estPuissance2(tailleP2)) {
            this.tailleP2 = tailleP2;
            this.signal = signal;
            LOGGER.debug("Création d'un objet FFT de taille : " + tailleP2);
        } else {
            LOGGER.error("La taille " + tailleP2 + " doit être une puissance de 2 !");
            throw new IllegalArgumentException("La taille " + tailleP2 + " doit être une puissance de 2 !");
        }
    }

    /**
     * Constructeur d'objet à partir d'un signal réel
     *
     * @param tailleP2
     * @param tabReel
     */
    public FFT(int tailleP2, float[] tabReel) throws IllegalArgumentException {
        if (estPuissance2(tailleP2)) {
            this.tailleP2 = tailleP2;
            this.signalR = tabReel;
            LOGGER.debug("Création d'un objet FFT de taille : " + tailleP2);
        } else {
            LOGGER.error("La taille " + tailleP2 + " doit être une puissance de 2 !");
            throw new IllegalArgumentException("La taille " + tailleP2 + " doit être une puissance de 2 !");
        }
    }

    /**
     * Méthode calculant une FFT à partir de valeurs réelles
     *
     * @return
     */
    public Complexe[] FFTRelle() {
        this.sortie = new Complexe[this.tailleP2];
        if (tailleP2 == 1) {
            this.sortie[0] = new Complexe(this.signalR[0], 0);
        } else {
            float[] tabPairs = new float[tailleP2 / 2];
            float[] tabImpairs = new float[tailleP2 / 2];

            for (int i = 0; i < this.tailleP2 / 2; i++) {
                tabPairs[i] = this.signalR[2 * i];
                tabImpairs[i] = this.signalR[2 * i + 1];
            }

            FFT paire = new FFT(this.tailleP2 / 2, tabPairs);
            FFT impaire = new FFT(this.tailleP2 / 2, tabImpairs);
            LOGGER.debug("Calcul transofrmées de Fourrier pour tableau paires et tableau impaires");
            // On calcule la FFT des deux listes
            paire.FFTRelle();
            impaire.FFTRelle();

            LOGGER.debug("Remplissage tableau final");
            for (int k = 0; k <= (tailleP2 / 2) - 1; k++) {
                Complexe M = new Complexe((float) (-2 * Math.PI * k) / tailleP2);
                this.sortie[k] = (paire.sortie[k]).addition(impaire.sortie[k].multiplication(M));
                this.sortie[k + (tailleP2 / 2)] = (paire.sortie[k]).soustraction(impaire.sortie[k].multiplication(M));
            }
        }
        return this.sortie;
    }

    /**
     * Méthode calculant une FFT à partir de valeurs complexes
     *
     * @return
     */
    public Complexe[] FFTComplexe() {
        this.sortie = new Complexe[this.tailleP2];
        if (tailleP2 == 1) {
            this.sortie[0] = this.signal[0];
        } else {
            Complexe[] tabPairs = new Complexe[tailleP2 / 2];
            Complexe[] tabImpairs = new Complexe[tailleP2 / 2];

            for (int i = 0; i < this.tailleP2 / 2; i++) {
                tabPairs[i] = this.signal[2 * i];
                tabImpairs[i] = this.signal[2 * i + 1];
            }

            FFT paire = new FFT(this.tailleP2 / 2, tabPairs);
            FFT impaire = new FFT(this.tailleP2 / 2, tabImpairs);
            LOGGER.debug("Calcul transofrmées de Fourrier pour tableau paires et tableau impaires");
            // On calcule la FFT des deux listes
            paire.FFTRelle();
            impaire.FFTRelle();

            LOGGER.debug("Remplissage tableau final");
            for (int k = 0; k <= (tailleP2 / 2) - 1; k++) {
                Complexe M = new Complexe((float) (-2 * Math.PI * k) / tailleP2);
                this.sortie[k] = (paire.sortie[k]).addition(impaire.sortie[k].multiplication(M));
                this.sortie[k + (tailleP2 / 2)] = (paire.sortie[k]).soustraction(impaire.sortie[k].multiplication(M));
            }
        }
        return this.sortie;
    }

    /**
     * Méthode calculant la FFT inverse
     *
     * @return
     */
    public Complexe[] inverseFFT() {
        LOGGER.debug("Remplissage du tableau de signal avec les conjugués");
        for (int i = 0; i < signal.length; i++) {
            signal[i] = signal[i].conjugue();
        }
        LOGGER.debug("Calcul de la FFT");
        this.FFTComplexe();
        LOGGER.debug("Remplissage du tableau de sortie avec le conjugué");
        for (int i = 0; i < this.sortie.length; i++) {
            this.sortie[i] = this.sortie[i].conjugue();
            this.sortie[i] = this.sortie[i].multiplication(new Complexe(1 / (float) this.sortie.length, 0));
        }
        return this.sortie;
    }

    /**
     * Méthode vérifiant si une valeur correspond bien à une puissance de 2
     *
     * @param value
     * @return
     */
    private boolean estPuissance2(int value) {
        //bitCount compte le nombre de bits à l'état haut
        //Pour la représentation d'une puissance de 2 en binaire, seul 1 bit est à l'état haut
        return Long.bitCount(value) == 1;
    }

    /**
     * Renvoie la taille du tableau
     *
     * @return tailleP2
     */
    public int getTailleP2() {
        return tailleP2;
    }
}