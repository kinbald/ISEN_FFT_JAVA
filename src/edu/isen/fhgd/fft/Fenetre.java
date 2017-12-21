package edu.isen.fhgd.fft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Fenetre extends JFrame implements Observer {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Fenetre.class);

    private FFTController controller;
    private int choixActuel;
    private CartesianPanel cartesian;

    public Fenetre(FFTController controller)
    {
        this.setTitle("Projet Java-Maths");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.controller=controller;
        choixActuel=0;
        this.cartesian = new CartesianPanel();
        this.add(cartesian);
        this.setVisible(true);
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof FFT) {
            FFT fft=(FFT)o;
        }
    }
}
