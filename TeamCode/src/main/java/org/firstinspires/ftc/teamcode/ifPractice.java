package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ifPractice extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {

        double leftY = gamepad1.left_stick_y;

        boolean aButton = gamepad1.a; // press TRUE, depress FALSE
        //true or false
        if  (aButton) {
            telemetry.addData("A Button", "Pressed");
        }

        else {
            telemetry.addData("A Button", "NOT pressed");
        }

        telemetry.addData("A button state", aButton);

        if (leftY < 0) {

            telemetry.addData("left stick", "is negative");

        }

        else if (leftY > 0.5) {
            telemetry.addData("left stick", " greater than 50%");
        }

        else if (leftY > 0) {

            telemetry.addData("left stick", "is greater than zero");

        } else {

            telemetry.addData("left stick", " is zero");
        }
    }
}
