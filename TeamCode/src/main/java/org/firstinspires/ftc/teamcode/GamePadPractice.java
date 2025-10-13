package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@Disabled
@TeleOp
public class GamePadPractice extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {

        // runs 50x a second
        double speedFoward = -gamepad1.left_stick_y / 2.0;
        double diffXandYstick = gamepad1.left_stick_x - gamepad1.right_stick_x;
        double sumTriggers = gamepad1.left_trigger + gamepad1.right_trigger;

        telemetry.addData("x stick left", gamepad1.left_stick_x);
        telemetry.addData("y stick left", gamepad1.left_stick_y);
        telemetry.addData("x stick right", gamepad1.right_stick_x);
        telemetry.addData("y stick right", gamepad1.right_stick_y);
        telemetry.addData("y stick left - half speed", speedFoward);
        telemetry.addData("a button", gamepad1.a);
        telemetry.addData("b button", gamepad1.b);

        telemetry.addData("x left - x right", diffXandYstick);
        telemetry.addData("Sum of Triggers", sumTriggers);


        telemetry.addData("x left - x right", gamepad1.left_stick_x - gamepad1.right_stick_x);


    }
}
