package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.robot.Robot;

@TeleOp
public class UseRobotLocationOpmode extends OpMode {

    RobotHeadingNormalized robotHeadingInstance = new RobotHeadingNormalized(0);

    @Override
    public void init() {
        robotHeadingInstance.setAngle(0);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            robotHeadingInstance.turnRobot(.1);
        }

        else if (gamepad1.b) {
            robotHeadingInstance.turnRobot(-.1);
        }

        telemetry.addData("Heading", robotHeadingInstance.getHeading());

    }
}
