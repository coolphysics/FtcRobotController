package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.TestBench;
import org.firstinspires.ftc.teamcode.mechanisms.TestBenchServo;

@TeleOp
public class AutoState1 extends OpMode {

    TestBench bench = new TestBench();
    int state;

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void start() {
        state = 0;
    }

    @Override
    public void loop() {
        telemetry.addData("State", state);
        if(state == 0){
            bench.setServoPos(0.5);
            if(bench.getTouchSensorPressed()){
                state = 1;
            } else if (state == 1) {
                bench.setServoPos(0.0);
                
            }
        }


    }
}
