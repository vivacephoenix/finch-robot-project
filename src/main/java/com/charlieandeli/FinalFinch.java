package com.charlieandeli;

import com.birdbrain.Finch;
import java.util.ArrayList;

public class FinalFinch extends Finch {

    // Lists for storing the song
    private ArrayList<Integer> notes;
    private ArrayList<Double> beats; 
    private double beatSpeed;

    public FinalFinch(String device) {
        super(device);
        notes = new ArrayList<>();
        beats = new ArrayList<>();
        beatSpeed = 0.45; // 0.45 sec per beat
        initializeFullHavaNagila();
    }

    private void initializeFullHavaNagila() {
        // MIDI array for the song notes
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
        
        // Rhythm / beat lengths
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

        // copies array elements over to the arraylists
        for (int i = 0; i < melody.length; i++) {
            notes.add(melody[i]);
            beats.add(rhythm[i]);
        }
    }

    // Overloaded dance method for basic wheels
    public void danceStep(int leftWheel, int rightWheel) {
        this.setMotors(leftWheel, rightWheel);
    }

    // Overloaded dance method for wheels plus beak color
    public void danceStep(int leftWheel, int rightWheel, int r, int g, int b) {
        this.setMotors(leftWheel, rightWheel);
        this.setBeak(r, g, b); 
    }

    public void playHavaNagilaRoutine() {
        System.out.println("Mazel Tov! Starting Hava Nagila...");

        for (int i = 0; i < notes.size(); i++) {
            int currentNote = notes.get(i);
            double noteTime = beats.get(i) * beatSpeed; 

            // Low power spin so it stays in a small area
            if (i < 32) {
                if (i % 2 == 0) {
                    danceStep(20, -20, 100, 0, 0);  // low speed right turn (red)
                } else {
                    danceStep(-20, 20, 0, 0, 100);  // low speed left turn (blue)
                }
            } 
            // Part 3 and 4: Faster wiggles
            else {
                if (i % 2 == 0) {
                    danceStep(15, -15, 100, 0, 100); // tiny wiggle right (magenta)
                } else {
                    danceStep(-15, 15, 0, 100, 100); // tiny wiggle left (cyan)
                }
            }

            this.playNote(currentNote, noteTime);

            // handles thread sleep timing
            try {
                long sleepTime = (long) (noteTime * 1000) + 40;
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Oops, thread got interrupted: " + e.getMessage());
            }
        }

        // shut down motors and lights at the end
        this.stopMotors();
        this.setBeak(0, 0, 0);
    }

    public void stopMotors() {
        this.setMotors(0, 0);
    }

    public static void main(String[] args) {
        try {
            FinalFinch myRobot = new FinalFinch("A"); 
            myRobot.playHavaNagilaRoutine();
            myRobot.disconnect();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("Error connecting to Finch Device A: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
