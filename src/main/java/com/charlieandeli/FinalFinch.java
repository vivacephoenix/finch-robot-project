package com.charlieandeli;

import com.birdbrain.Finch;
import java.util.ArrayList;

/**
 * FinalFinch Class
 * Demonstrates 6 APCSA/OOP Concepts: Inheritance, ArrayLists, Loops.
 */
public class FinalFinch extends Finch {

    // Arrays and ArrayLists for dynamic data tracking
    private ArrayList<Integer> notes;
    private ArrayList<Double> beats; 
    private double beatDurationSec; 

    public FinalFinch(String device) {
        super(device);
        this.notes = new ArrayList<>();
        this.beats = new ArrayList<>();
        this.beatDurationSec = 0.45; 
        initializeFullHavaNagila();
    }

    /**
     * Populates the arrays with the melody and the rhythm data 
     */
    private void initializeFullHavaNagila() {
        int[] melody = {
            64, 64, 68, 64,   68, 68, 69, 68, 
            69, 69, 72, 71,   68, 65, 64, 64,
            64, 64, 68, 64,   68, 68, 69, 68, 
            69, 69, 72, 71,   68, 65, 64, 64,
            68, 68, 71, 68,   71, 71, 72, 71, 
            72, 72, 74, 72,   71, 69, 68, 68,
            68, 68, 71, 68,   71, 71, 72, 71, 
            72, 72, 74, 72,   71, 69, 68, 68
        };
        
        double[] rhythm = {
            2.0, 2.0, 2.0, 2.0,   2.0, 2.0, 2.0, 2.0,
            1.0, 1.0, 2.0, 1.0,   1.0, 2.0, 2.0, 4.0,
            2.0, 2.0, 2.0, 2.0,   2.0, 2.0, 2.0, 2.0,
            1.0, 1.0, 2.0, 1.0,   1.0, 2.0, 2.0, 4.0,
            2.0, 2.0, 2.0, 2.0,   2.0, 2.0, 2.0, 2.0,
            1.0, 1.0, 2.0, 1.0,   1.0, 2.0, 2.0, 4.0,
            2.0, 2.0, 2.0, 2.0,   2.0, 2.0, 2.0, 2.0,
            1.0, 1.0, 2.0, 1.0,   1.0, 2.0, 2.0, 4.0
        };

        // Loops (for-loop) populating lists dynamically
        for (int i = 0; i < melody.length; i++) {
            notes.add(melody[i]);
            beats.add(rhythm[i]);
        }
    }

    public void stopMotors() {
        this.setMotors(0, 0);
    }

    public static void main(String[] args) {
        FinalFinch myRobot = new FinalFinch("A"); 
        myRobot.disconnect();
    }
}
