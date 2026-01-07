package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(5.1)  // check mass in kg
            .forwardZeroPowerAcceleration(-45.2467893325191)
            .lateralZeroPowerAcceleration(-59.898200152751265)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.1,0.0, 0.0, 0.0))
            .headingPIDFCoefficients(new PIDFCoefficients(1,0.0,0.0,0.01))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(.025, 0.0, .0001, 0.6, .01))
            .centripetalScaling(.0005);



    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("front_right_motor") // check names in robot config
            .rightRearMotorName("back_right_motor")
            .leftRearMotorName("back_left_motor")
            .leftFrontMotorName("front_left_motor")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE) // check directions
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(57.28245196004552)
            .yVelocity(45.07192728838584);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(0.0) //must be in inches - measured to center
            .strafePodX(0.0)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(0.99,
            100,
            1,
            1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}
