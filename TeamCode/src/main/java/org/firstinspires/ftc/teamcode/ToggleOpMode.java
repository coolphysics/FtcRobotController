package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.TestBench;

@TeleOp
public class ToggleOpMode extends OpMode {
    TestBench bench = new TestBench();
    boolean aAlreadyPressed;
    boolean motorOn;

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.a && !aAlreadyPressed) {
            motorOn = !motorOn;
            telemetry.addData("Motor", motorOn);
            if(motorOn) {
                bench.setMotorSpeed(0.5);
            } else {
                bench.setMotorSpeed(0.0);
            }
        }

        aAlreadyPressed = gamepad1.a;
    }
}
