package edu.isen.fhgd.fft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        float sinus[] = new float[0];
        FFT fft = new FFT(2,sinus);
        FFTController controller = new FFTController(fft);
        Fenetre fen = new Fenetre(controller);
        fft.addObserver(fen);
        controller.setFft(fft);
        controller.setFen(fen);
    }
}
