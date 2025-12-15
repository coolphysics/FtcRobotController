package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class VariablePractice extends OpMode {

    @Override
    public void init() {

        int teamNumber = 23707;
        double motorSpeed = .75;
        boolean clawClosed = true;
        String name = "Coach Matt";
        int motorAngle = 90;

        telemetry.addData("team number", teamNumber);
        telemetry.addData("motor speed", motorSpeed);
        telemetry.addData("claw closed", clawClosed);
        telemetry.addData("name", name);
        telemetry.addData("motor angle", motorAngle);

    }

    @Override
    public void loop() {

    }

    /*
    What's up, Bro?
     */
}
