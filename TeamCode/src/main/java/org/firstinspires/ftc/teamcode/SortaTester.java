package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.mechanisms.Sorta;

@Autonomous
public class SortaTester extends OpMode {

    Sorta sorta = new Sorta();

    @Override
    public void init() {
        sorta.init(hardwareMap);
        //TODO GET DECODE PATTERN FROM OBELISK USING LIMELIGHT3A CAMERA

        // Send DECODE pattern to sorta depend
        sorta.sortPattern(Sorta.DecodePattern.PURPLE_GREEN_PURPLE);
    }

    @Override
    public void loop() {

    }
}
