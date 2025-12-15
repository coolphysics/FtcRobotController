package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestBenchColorDecode {
    NormalizedColorSensor colorSensor;

    public enum DetectedColor {
        PURPLE,
        GREEN,
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
        NormalizedRGBA colors = colorSensor.getNormalizedColors(); // returns four values RGBA between 0 to 1

        float normRed, normGreen, normBlue;

        // normalizes colors even more - aplpha is how bright, how much light
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
        PURPLE Artifact   =  <.30,  >.3,  > .7
        GREEN Artifact    =  <.1,  >.6,  < .6 */



        if (normRed > 0.2 && normBlue > 0.7) {
            return DetectedColor.PURPLE;
        }
        else if (normRed < 0.1 &&  normBlue < 0.5) {
            return DetectedColor.GREEN;
        }

        else  {
            return DetectedColor.UNKNOWN;
        }

    }
}
