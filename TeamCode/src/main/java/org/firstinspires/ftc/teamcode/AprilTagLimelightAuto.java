package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.HardwareMap;


@Autonomous(name = "AprilTag Limelight Auto", group = "Autonomous")
public class AprilTagLimelightAuto extends LinearOpMode {

    private Limelight3A limelight;

    // Detected tag ID
    private int detectedTagID = -1;

    @Override
    public void runOpMode() {

        // Initialize Limelight
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0); //april tag #23, 22, 21

        telemetry.addLine("Initializing Limelight...");
        telemetry.update();

        // Start Limelight processing
        limelight.start();

        // Give the camera time to boot and detect
        sleep(500);

        telemetry.addLine("Waiting for start...");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        // -------------------------------
        // Detect AprilTag
        // -------------------------------
        LLResult result = limelight.getLatestResult();

        if (result != null && result.isValid()
                && result.getFiducialResults() != null
                && !result.getFiducialResults().isEmpty()) {

            // Use the FIRST detected tag
            LLResultTypes.FiducialResult tag = result.getFiducialResults().get(0);
            detectedTagID = tag.getFiducialId();

            telemetry.addData("Detected AprilTag ID", detectedTagID);
            telemetry.update();
            sleep(3000);

        } else {
            telemetry.addLine("No AprilTag detected");
            sleep(3000);
        }

        telemetry.update();


        // -------------------------------
        // Choose Autonomous Path
        // -------------------------------
        switch (detectedTagID) {

            case 21:
                runLeftAuto();
                break;

            case 22:
                runCenterAuto();
                break;

            case 23:
                runRightAuto();
                break;

            default:
                telemetry.addLine("Running DEFAULT (Center) Auto");
                telemetry.update();
                runCenterAuto();
                break;
        }

        telemetry.addLine("Autonomous Complete");
        telemetry.update();
    }

    // ===============================
    // Autonomous Path Methods
    // ===============================

    private void runLeftAuto() {
        telemetry.addLine("Running LEFT Auto");
        telemetry.update();

        // TODO: Replace with real robot actions
        sleep(3000);
    }

    private void runCenterAuto() {
        telemetry.addLine("Running CENTER Auto");
        telemetry.update();

        //TODO: Replace with real robot actions
        sleep(3000);
    }

    private void runRightAuto() {
        telemetry.addLine("Running RIGHT Auto");
        telemetry.update();

        // TODO: Replace with real robot actions
        sleep(3000);
    }
}