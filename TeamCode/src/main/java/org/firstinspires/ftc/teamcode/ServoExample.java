package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.TestBenchServo;

@TeleOp
public class ServoExample extends OpMode {

    TestBenchServo bench = new TestBenchServo();

    @Override
    public void init() {
        bench.init(hardwareMap);
        ElapsedTime timer = new ElapsedTime();
        timer.reset();


    }

    @Override
    public void loop() {


        if (gamepad1.a) {
            // Always approach 0.50 from below
            bench.setServoPos(0.48);

            ElapsedTime timer = new ElapsedTime();
            timer.reset();
            while (timer.milliseconds() < 500) {
                // do other robot work
            }
            bench.setServoPos(0.50);
        }

        if (gamepad1.b) {
            bench.setServoPos(0.57);
        }

        if (gamepad1.y) {
            // Always approach 0.43 from below
            bench.setServoPos(.41);
            ElapsedTime timer = new ElapsedTime();
            timer.reset();
            while (timer.milliseconds() < 500) {
                // do other robot work
            }
            bench.setServoPos((0.43));
        }
        /*else {
            bench.setServoPos(1.0); // max pos
        }

        if (gamepad1.b) {
            bench.setServoRot(1.0); // max power
        }
        else {
            bench.setServoRot(0.0); // zero power
        }*/

    }
}
