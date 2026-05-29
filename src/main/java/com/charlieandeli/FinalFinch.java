package com.charlieandeli;

import com.birdbrain.Finch;
import java.util.ArrayList;

/**
 * FinalFinch Class
 * Extends the BirdBrain Finch library, performs song and dance to "Halva Nagila.""
 * 
 * Demonstrates 6 APCSA/OOP Concepts: Inheritance, Method Overloading, 
 * ArrayLists, Loops, Conditional Statements, and Exception Handling.
 */
public class FinalFinch extends Finch {

    // Arrays/ArrayLists for dynamic data tracking
    private ArrayList<Integer> notes;
    private ArrayList<Double> beats; 
    private double beatDurationSec; 

    /**
     * Constructor for FinalFinch
     * @param device Identifier for device A, B or C
     */
    public FinalFinch(String device) {
        // Inheritance (calling the parent Finch constructor)
        super(device);
        this.notes = new ArrayList<>();
        this.beats = new ArrayList<>();
        
        // Rythmic pace at 0.45 second per quarter note.
        this.beatDurationSec = 0.45; 
        initializeFullHavaNagila();
    }

    /**
     * Populates the arrays with the melody and the rythem data 
     * for Halva Nagila including repeats and section phrases.
     */
    private void initializeFullHavaNagila() {
        // MIDI Notes: E4=64, F4=65, G#4=68, A4=69, B4=71, C5=72, D5=74
        int[] melody = {
            // 1. Ha-va na-gi-la, ha-va na-gi-la
            64, 64, 68, 64,   68, 68, 69, 68, 
            // ve-nis-me-cha!
            69, 69, 72, 71,   68, 65, 64, 64,

            // 2.Repeat: Ha-va na-gi-la, ha-va na-gi-la
            64, 64, 68, 64,   68, 68, 69, 68, 
            // ve-nis-me-cha!
            69, 69, 72, 71,   68, 65, 64, 64,
            
            // 3. Ha-va ne-ra-ne-na, ha-va ne-ra-ne-na
            68, 68, 71, 68,   71, 71, 72, 71, 
            // ve-nis-me-cha!
            72, 72, 74, 72,   71, 69, 68, 68,

            // 4. Repeat: Ha-va ne-ra-ne-na, ha-va ne-ra-ne-na
            68, 68, 71, 68,   71, 71, 72, 71, 
            // ve-nis-me-cha!
            72, 72, 74, 72,   71, 69, 68, 68
        };
        
        // Rhythm Beats: 1.0 = Quarter Note, 2.0 = Half Note, 4.0 = Sustained Note
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

    /**
     * Method Overloading (Version 1 - General motion)
     */
    public void danceStep(int leftWheel, int rightWheel) {
        this.setMotors(leftWheel, rightWheel);
    }

    /**
     * Method Overloading (Version 2 - Synchronized motion + light parameters)
     */
    public void danceStep(int leftWheel, int rightWheel, int r, int g, int b) {
        this.setMotors(leftWheel, rightWheel);
        this.setBeak(r, g, b); // Color variables respect strict 0 to 100 limit constraints
    }

    /**
     * Executes the synchronized song and dance loop.
     * Uses math to keep the robot in a small area.
     */
    public void playHavaNagilaRoutine() {
        System.out.println("Mazel Tov! Starting Hava Nagila...");

        // Loops (Iterating smoothly over array size)
        for (int i = 0; i < notes.size(); i++) {
            int noteMidi = notes.get(i);
            double finalDurationSec = beats.get(i) * beatDurationSec; 

            // Conditional Statements (Managing structural choreography adjustments)
            if (i < 32) {
                // PARTS 1 & 2 ("Hava Nagila / Ve-nis-me-cha"): Alternate Spins and Self-Correcting Steps
                if (i % 4 == 0) {
                    danceStep(35, -35, 0, 0, 100);  // Sharp Axis Spin Right (Blue)
                } else if (i % 4 == 1) {
                    danceStep(25, 25, 100, 100, 0); // Short forward shuffle (Yellow)
                } else if (i % 4 == 2) {
                    danceStep(-35, 35, 100, 0, 0);  // Sharp Axis Spin Left (Red)
                } else {
                    danceStep(-25, -25, 0, 100, 0); // Equal reverse shuffle to reset origin point (Green)
                }
            } else {
                // PARTS 3 & 4 ("Hava Ne-ra-ne-na"): Quick side-to-side rocking pivots
                if (i % 2 == 0) {
                    danceStep(40, 10, 100, 0, 100); // Sharp pivot Right (Magenta)
                } else {
                    danceStep(10, 40, 0, 100, 100); // Sharp pivot Left (Cyan)
                }
            }

            // Play the chosen MIDI note asynchronously
            this.playNote(noteMidi, finalDurationSec);

            // Exception Handling (try-catch) used for precision thread sleep timing
            try {
                // Suspends the code block to guarantee movement duration syncs with audio note length
                long sleepMillis = (long) (finalDurationSec * 1000) + 40;
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                System.err.println("Routine execution process interrupted: " + e.getMessage());
            }
        }

        // Clean up hardware states safely upon routine termination
        this.stopMotors();
        this.setBeak(0, 0, 0);
    }

    /**
     * Resets wheel engines immediately
     */
    public void stopMotors() {
        this.setMotors(0, 0);
    }

    /**
     * Execution Entry Point
     */
    public static void main(String[] args) {
        // Exception Handling (try-catch) protecting top-level runtime engine
        try {
            // Instantiate hardware subclass using the designated letter code
            FinalFinch myRobot = new FinalFinch("A"); 
            
            // Start routine performance
            myRobot.playHavaNagilaRoutine();
            
            // Tear down connection safely
            myRobot.disconnect();
            System.out.println("Routine finished successfully.");
            
        } catch (Exception e) {
            System.err.println("A connection problem was encountered with Finch Device A: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
