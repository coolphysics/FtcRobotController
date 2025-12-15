package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.TestBench;
import org.firstinspires.ftc.teamcode.mechanisms.TestBench1;

@TeleOp
public class DcMotorPractice extends OpMode {

    TestBench bench = new TestBench();

    @Override
    public void init() {

        bench.init(hardwareMap);
    }

    @Override
    public void loop() {

        // could use a gamepad to control speed or a touch sensor (see commented out section below)
        double motorSpeed = gamepad1.left_stick_y;
        bench.setMotorSpeed(motorSpeed);

        /*
        if (bench.getTouchSensorPressed()) {
            bench.setMotorSpeed(0.5);
        }
        else {
            bench.setMotorSpeed(0.0); // stops the motor
        } */

        telemetry.addData("Motor Revs", bench.getMotorRevs());
    }
}
