package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.TestBenchColor;
import org.firstinspires.ftc.teamcode.mechanisms.TestBenchColorDecode;

@TeleOp
public class ColorSensorTestDecode extends OpMode {

    TestBenchColorDecode bench = new TestBenchColorDecode();
    TestBenchColorDecode.DetectedColor detectedColor;

    @Override
    public void init() {
        bench.init(hardwareMap);
    }

    @Override
    public void loop() {
        bench.getDetectedColor(telemetry); //done initally to get color calibration stuff
        detectedColor = bench.getDetectedColor(telemetry);
        telemetry.addData("Color Detected", detectedColor);
    }
}
