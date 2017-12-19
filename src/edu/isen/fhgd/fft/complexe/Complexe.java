package edu.isen.fhgd.fft.complexe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * \file Complexe.java
 * Création d'un type de données : Complexe
 */
public class Complexe {

    private static final Logger LOGGER = LoggerFactory.getLogger(Complexe.class);

    /**
     * Nombre réel du complexe
     */
    private final float reel;

    /**
     * Nombre imaginaire du complexe
     */
    private final float imaginaire;

    /**
     * Constructeur par défaut
     *
     * @param reel
     * @param imaginaire
     */
    public Complexe(float reel, float imaginaire) {
        this.reel = reel;
        this.imaginaire = imaginaire;
        LOGGER.info("Création du nombre complexe : " + this.toString());
    }

    /**
     * Renvoie un complexe comprenant le résultat de l'addition de ce complexe et de b
     *
     * @param b
     * @return
     */
    public Complexe addition(Complexe b) {
        float reel = this.reel + b.reel;
        float imaginaire = this.imaginaire + b.imaginaire;
        return new Complexe(reel, imaginaire);
    }

    /**
     * Renvoie un complexe comprenant le résultat de la soustraction de ce complexe et de b
     *
     * @param b
     * @return
     */
    public Complexe soustraction(Complexe b) {
        float reel = this.reel - b.reel;
        float imaginaire = this.imaginaire - b.imaginaire;
        return new Complexe(reel, imaginaire);
    }

    /**
     * Renvoie le complexe conjugué de ce nombre
     *
     * @return
     */
    public Complexe conjugue() {
        return new Complexe(reel, -imaginaire);
    }

    /**
     * Renvoie un complexe comprenant le résultat de la multiplication de ce nombre et de b
     *
     * @param b
     * @return
     */
    public Complexe multiplication(Complexe b) {
        float reel = this.reel * b.reel - this.imaginaire * b.imaginaire;
        float imaginaire = this.reel * b.imaginaire + this.imaginaire * b.reel;
        return new Complexe(reel, imaginaire);
    }

    /**
     * Accesseur sur les réels
     *
     * @return
     */
    public float Re() {
        return this.reel;
    }

    /**
     * Accesseur sur les imaginaires
     *
     * @return
     */
    public float Im() {
        return this.imaginaire;
    }

    @Override
    public boolean equals(Object o) {
        if ((this.reel == ((Complexe) (o)).reel) && (this.imaginaire == ((Complexe) (o)).imaginaire)) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Nombre complexe => Re : " + reel + " | Im : " + imaginaire;
    }
}
