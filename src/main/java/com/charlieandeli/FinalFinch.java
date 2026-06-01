package com.charlieandeli;

import com.birdbrain.Finch;

/**
 * FinalFinch Class
 * Extends the BirdBrain Finch library, performs song and dance to "Hava Nagila."
 * 
 * Demonstrates 6 APCSA/OOP Concepts: Inheritance.
 */
public class FinalFinch extends Finch {

    private double beatDurationSec; 

    /**
     * Constructor for FinalFinch
     * @param device Identifier for device A, B or C
     */
    public FinalFinch(String device) {
        // Inheritance (calling the parent Finch constructor)
        super(device);
        this.beatDurationSec = 0.45; 
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
        // Instantiate hardware subclass using the designated letter code
        FinalFinch myRobot = new FinalFinch("A"); 
        System.out.println("Finch connection established successfully.");
        myRobot.disconnect();
    }
}
