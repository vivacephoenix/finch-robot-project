package com.charlieandeli;

import com.birdbrain.Finch;
import java.util.ArrayList;

/**
 * FinalFinch Class
 */
public class FinalFinch extends Finch {

    // Arrays and ArrayLists
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

        // Loops and for loops that populate lists
        for (int i = 0; i < melody.length; i++) {
            notes.add(melody[i]);
            beats.add(rhythm[i]);
        }
    }

    /**
     * Method overloading for the general movement.
     */
    public void danceStep(int leftWheel, int rightWheel) {
        this.setMotors(leftWheel, rightWheel);
    }

    /**
     * Method overloading with synchronized motions and light parameters.
     */
    public void danceStep(int leftWheel, int rightWheel, int r, int g, int b) {
        this.setMotors(leftWheel, rightWheel);
        this.setBeak(r, g, b); 
    }

    public void stopMotors() {
        this.setMotors(0, 0);
    }

    public static void main(String[] args) {
        FinalFinch myRobot = new FinalFinch("A"); 
        myRobot.disconnect();
    }
}
