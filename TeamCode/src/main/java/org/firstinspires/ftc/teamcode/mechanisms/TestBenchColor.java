package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestBenchColor {
    NormalizedColorSensor colorSensor;

    public enum DetectedColor {
        RED,
        BLUE,
        YELLOW,
        UNKNOWN
    }

    public void init(HardwareMap hwMap) {
        colorSensor =hwMap.get(NormalizedColorSensor.class, "sensor_color_distance");
        colorSensor.setGain(12);
        /*add after initial test of color sensor, gain usually at 1, jacked up to 4
        wants values even higer than.3 so jacked it up from 4 to 8, now readings almost at max of 1, it's about .7
         */
    }

    public DetectedColor getDetectedColor(Telemetry telemetry){
        NormalizedRGBA colors = colorSensor.getNormalizedColors(); // returns four values RGBA between 0 and 1

        float normRed, normGreen, normBlue;

        // normalizes colors more - alpha is how much light
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        telemetry.addData("red", normRed);
        telemetry.addData("green", normGreen);
        telemetry.addData("blue", normBlue);

        //TODO add specific IF statements for specific colors added
        /*
           This is for when a colored object is at a specific height, angle, or fixed position
           in pratt video timestamp for this is 14:10; need to fudge range a bit

           challenf\ge will be tune numbers if too close or far away
                        red, green, blue
        RED object=    >.4,  <.3,  < .2
        YELLOW object = <.9,  >.9,  < .3
        BLUE object=   < .2   <.3   >  .6
         */

        if (normRed > 0.40 && normGreen < 0.3 && normBlue < 0.2) {
            return DetectedColor.RED;
        }
        else if (normRed < 0.9 && normGreen > 0.9 && normBlue < 0.3) {
            return DetectedColor.YELLOW;
        }
        else if (normRed < 0.2 && normGreen < 0.3 && normBlue > 0.6) {
            return DetectedColor.BLUE;
        }

        else {
            return DetectedColor.UNKNOWN;
        }

    }
}
