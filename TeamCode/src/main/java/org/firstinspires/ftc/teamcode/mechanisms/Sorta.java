package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/*
Assumptions -- using servo positions of the sorta for mapping the three colored balls,
The colored balls must be in these set servo positions of the sorta for this code to work
The .5 servo position must be at the home (ready for shoot) position at the start of the match

Iteration 1 = preload
Place the following balls in these positions for preload
0.50 - PURPLE_A
0.42 - GREEN
0.57 - PURPLE_B

Iteration 2 = first spike mark nearest to the goal
TODO - Make code to pick up balls from spike lines in the order shown below.
The bot must pick up the balls from the first spike line in the order shown, with the sorta at
each of those positions during pick up.
0.57 - PURPLE_A
0.50 - GREEN
0.42 - PURPLE_B

Iteration 3 = second sprite mark nearest to the goal
TODO - Make code to pick up balls from spike lines in the order shown below.
The bot must pick up the balls from the second spike line in the order shown, with the sorta at
each of those positions during pick up.
0.50 - PURPLE_A
0.42 - GREEN
0.57 - PURPLE_B

 */
public class Sorta {

    private Servo fiveTurnServo;
    private ElapsedTime timer = new ElapsedTime();

    private static final double HOLD_TIME = 1.0;

    // Tracks which iteration we're on (1 → 3)
    private int iteration = 1;

    // Ball identifiers
    public enum BallColor {
        PURPLE_A,
        GREEN,
        PURPLE_B
    }

    // Valid patterns
    public enum DecodePattern {
        PURPLE_PURPLE_GREEN,
        PURPLE_GREEN_PURPLE,
        GREEN_PURPLE_PURPLE
    }

    public void init(HardwareMap hwMap){
        fiveTurnServo = hwMap.get(Servo.class, "five_turn_servo");
    }

    /**
     * Sorts balls based on the given pattern.
     */
    public void sortPattern(DecodePattern pattern) {

        BallColor[] sequence;

        switch (pattern) {
            case PURPLE_PURPLE_GREEN:
                sequence = new BallColor[] {
                        BallColor.PURPLE_A,
                        BallColor.PURPLE_B,
                        BallColor.GREEN
                };
                break;

            case PURPLE_GREEN_PURPLE:
                sequence = new BallColor[] {
                        BallColor.PURPLE_A,
                        BallColor.GREEN,
                        BallColor.PURPLE_B
                };
                break;

            case GREEN_PURPLE_PURPLE:
                sequence = new BallColor[] {
                        BallColor.GREEN,
                        BallColor.PURPLE_A,
                        BallColor.PURPLE_B
                };
                break;

            default:
                return;
        }

        for (BallColor color : sequence) {
            moveToBall(color);
            hold(HOLD_TIME);
            //TODO: FLICK OUT ONE BALL & SHOOT ONE BALL
        }

        advanceIteration();
    }

    /**
     * Moves servo to correct position based on color AND iteration.
     */
    private void moveToBall(BallColor color) {

        double position = getServoPosition(color);
        fiveTurnServo.setPosition(position);
    }

    /**
     * Returns the correct servo position for the given ball
     * depending on the current iteration.
     */
    private double getServoPosition(BallColor color) {

        switch (iteration) {

            case 1:
                switch (color) {
                    case PURPLE_A: return 0.50;
                    case GREEN:    return 0.57;
                    case PURPLE_B: return 0.42;
                }

            case 2:
                switch (color) {
                    case PURPLE_A: return 0.57;
                    case GREEN:    return 0.50;
                    case PURPLE_B: return 0.42;
                }

            case 3:
                switch (color) {
                    case PURPLE_A: return 0.50;
                    case GREEN:    return 0.57;
                    case PURPLE_B: return 0.42;
                }
        }

        return 0.0; // Failsafe
    }

    /**
     * Advances iteration (1 → 2 → 3 → 1)
     */
    private void advanceIteration() {
        iteration++;
        if (iteration > 3) {
            iteration = 1;
        }
    }

    /**
     * Holds position for given seconds.
     * TODO - Code to flick and shoot the ball
     */
    private void hold(double seconds) {
        timer.reset();
        while (timer.seconds() < seconds) {
            // Intentional blocking delay
        }
    }
}