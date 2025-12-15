package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.TestBenchServo;

@TeleOp
public class ServoExample extends OpMode {

    TestBenchServo bench = new TestBenchServo();

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            bench.setServoPos(0.0); // min pos
        }
        else {
            bench.setServoPos(1.0); // max pos
        }
        /*
        if (gamepad1.b) {
            bench.setServoRot(1.0); // max power
        }
        else {
            bench.setServoRot(0.0); // zero power
        }*/

    }
}
