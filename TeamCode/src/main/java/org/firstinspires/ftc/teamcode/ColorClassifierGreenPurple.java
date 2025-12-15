package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class ColorClassifierGreenPurple extends LinearOpMode {

    private NormalizedColorSensor colorSensor;

    @Override
    public void runOpMode() throws InterruptedException {

        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color_distance");

        // Try enabling LED (if supported)
        SwitchableLight light = null;
        try {
            light = (SwitchableLight) colorSensor;
            light.enableLight(true);
        } catch (Exception ignored) {}

        telemetry.addLine("Ready to detect colors...");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {

            NormalizedRGBA colors = colorSensor.getNormalizedColors();

            // Convert to hue (0-360 degrees)
            float hue = JavaUtil.colorToHue(colors.toColor());

            String detectedColor = classifyColor(hue);

            // Optional: check proximity/distance
            double distanceCm = -1;
            try {
                distanceCm = ((DistanceSensor) colorSensor).getDistance(DistanceUnit.CM);
            } catch (Exception ignored) {}

            telemetry.addData("Hue", "%.1f", hue);
            telemetry.addData("Detected Color", detectedColor);

            telemetry.addData("R", "%.3f", colors.red);
            telemetry.addData("G", "%.3f", colors.green);
            telemetry.addData("B", "%.3f", colors.blue);

            if (distanceCm >= 0) {
                telemetry.addData("Distance (cm)", "%.2f", distanceCm);
            }

            telemetry.update();
            sleep(100);
        }

        if (light != null) light.enableLight(false);
    }

    /** Classifies color based on hue values */
    private String classifyColor(float hue) {

        // Hue ranges (adjust as needed for your specific game pieces):
        // GREEN ~ 90–160° (typically around 120°)
        // PURPLE ~ 260–300° (typically around 275°)

        if (hue >= 90 && hue <= 180) {
            return "GREEN";
        }
        else if (hue >= 210 && hue <= 300) {
            return "PURPLE";
        }
        else {
            return "UNKNOWN";
        }
    }
}


