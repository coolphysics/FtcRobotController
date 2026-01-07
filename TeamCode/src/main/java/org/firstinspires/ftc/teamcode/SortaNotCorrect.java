package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



/*
Key Assumptions (you can adjust easily)
	1.	Three ball positions are spaced 120° apart in a circle.
	2.	The five-turn servo is configured as a continuous-range positional servo using setPosition() from 0.0 to 1.0.
	3.	We define fixed servo positions for each ball:
	•	PURPLE_A
	•	PURPLE_B
	•	GREEN
	4.	The method receives a pattern enum describing one of the three valid sequences.
	5.	The servo:
	•	Turns to ball 1 → waits 1 second
	•	Turns to ball 2 → waits 1 second
	•	Turns to ball 3 → waits 1 second


	Problem here is that we cannot have fixed positions for each ball. There are three patters
	during auto

	Example usage in OpMode
	Sorta sorta = new Sorta(hardwareMap);

// Example usage
sorta.sortPattern(Sorta.Pattern.PURPLE_GREEN_PURPLE);

 */
public class SortaNotCorrect {

    // Servo reference
    private Servo fiveTurnServo;

    // Timing helper
    private ElapsedTime timer = new ElapsedTime();

    // Servo positions (example values — tune on robot)
    private static final double PURPLE_A_POS = 0.00;
    private static final double GREEN_POS    = 0.33;
    private static final double PURPLE_B_POS = 0.66;

    // Hold time (seconds)
    private static final double HOLD_TIME = 1.0;

    // Enum for colors
    public enum BallColor {
        PURPLE_A,
        GREEN,
        PURPLE_B
    }

    // Enum for valid patterns
    public enum Pattern {
        PURPLE_PURPLE_GREEN,
        PURPLE_GREEN_PURPLE,
        GREEN_PURPLE_PURPLE
    }

    // Constructor
    public SortaNotCorrect(HardwareMap hardwareMap) {
        fiveTurnServo = hardwareMap.get(Servo.class, "five_turn_servo");
    }

    /**
     * Sorts balls based on the given pattern.
     */
    public void sortPattern(Pattern pattern) {

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
                return; // Safety
        }

        // Move through the sequence
        for (BallColor color : sequence) {
            moveToBall(color);
            hold(HOLD_TIME);
        }
    }

    /**
     * Moves the servo to the specified ball position.
     */
    private void moveToBall(BallColor color) {
        switch (color) {
            case PURPLE_A:
                fiveTurnServo.setPosition(PURPLE_A_POS);
                break;

            case GREEN:
                fiveTurnServo.setPosition(GREEN_POS);
                break;

            case PURPLE_B:
                fiveTurnServo.setPosition(PURPLE_B_POS);
                break;
        }
    }

    /**
     * Holds position for a given number of seconds.
     */
    private void hold(double seconds) {
        timer.reset();
        while (timer.seconds() < seconds) {
            // Busy wait — OK for short actions
        }
    }
}