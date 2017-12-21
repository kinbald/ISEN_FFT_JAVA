package edu.isen.fhgd.fft;


import edu.isen.fhgd.fft.complexe.Complexe;

import java.util.List;

public class FFTController {
    private FFT model = null;
    private Fenetre fen;

    public FFTController(FFT model) {
        this.model = model;
    }

    public void setFft(FFT fft) {
        this.model = fft;
    }

    public Fenetre getFen() {
        return fen;
    }

    public void setFen(Fenetre fen) {
        this.fen = fen;
    }

    public void notifyAction(int choix) {
        switch (choix) {
            case 1:
                float sinus[] = new float[8];
                for (int i = 0; i < sinus.length; i++) {
                    sinus[i] = (float) Math.sin(2 * Math.PI * i / sinus.length);
                }
                this.model.setNewSignalR(sinus, 8);
                this.model.FFTRelle();
                break;
            case 2:
                Complexe[] signal = new Complexe[8];
                for (int i = 0; i < 8; i++) {
                    signal[i] = new Complexe((float) (2 * Math.PI * i / signal.length));
                }
                this.model.setNewSignal(signal, 8);
                this.model.FFTComplexe();
                break;
            default:
                break;
        }
    }
}
