package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.util.Timer;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;


@Autonomous
@Disabled
public class SampleAutoPathing extends OpMode {

    private Follower follower;
    private Timer pathTimer, opModeTimer;

    public enum PathState {
        //START POSITION_END POSITION
        //DRIVE > MOVEMENT STATE
        //SHOOT > ATTEMPT TO SCORE the ARTIFACT

        DRIVE_STARTPOS_SHOOT_POS,
        SHOOT_PRELOAD,
        DRIVE_SHOOTPOS_ENDPOS

    }

    PathState pathState;

    private final Pose startPose = new Pose(20, 122, Math.toRadians(138));
    private final Pose shootPose = new Pose(46, 96, Math.toRadians(138));
    private final Pose endPose = new Pose(63,106, Math.toRadians(90));

    private PathChain driveStartPosShootPos, driveShootPosEndPos;

    public void buildPaths() {
        // put in coordinates for starting pos > ending pos for entire path

        driveStartPosShootPos = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(startPose.getHeading(),shootPose.getHeading())
                .build();

        driveShootPosEndPos = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, endPose))
                .setLinearHeadingInterpolation(shootPose.getHeading(),endPose.getHeading())
                .build();
    }

    public void statePathUpdate() {
        switch (pathState) {
            case DRIVE_STARTPOS_SHOOT_POS:
                follower.followPath(driveStartPosShootPos, true); // holds at that final pos
                //pathState = PathState.SHOOT_PRELOAD;
                setPathState(PathState.SHOOT_PRELOAD); // reset timer and make new state
                break;
            case SHOOT_PRELOAD:
                // check if the follower has done its path?
                // and check that 5 secs have elapsed. Probably not done shooting after path over
                // not busy, it has finished its path from the previous state
                //TODO add logic to fywheel shooter
                if (!follower.isBusy() && pathTimer.getElapsedTime() > 5)  {
                   //telemetry.addLine("Done Path 1"); was originally here until added next case
                   //transition to next state
                    follower.followPath(driveShootPosEndPos, true);
                    setPathState(PathState.DRIVE_SHOOTPOS_ENDPOS);
                }
                break;
            case DRIVE_SHOOTPOS_ENDPOS:
                // all done!
                if (!follower.isBusy()) {
                    telemetry.addLine("Done all paths");
                }

            default:
                telemetry.addLine("No State Commanded");
                break;

        }
    }

    // timer helper function
    public void setPathState(PathState newState) {
        pathState = newState;
        pathTimer.resetTimer();
    }



    @Override
    public void init() {
        pathState = PathState.DRIVE_STARTPOS_SHOOT_POS;
        pathTimer = new Timer();
        opModeTimer = new Timer();
        follower = Constants.createFollower(hardwareMap);
        //TODO add in any other init mechanisms, limelights, flywheels etc.

        buildPaths();
        follower.setPose(startPose);
    }

    public void start() {
        opModeTimer.resetTimer();
        setPathState(pathState);
    }

    @Override
    public void loop() {
        follower.update();
        statePathUpdate();

        telemetry.addData ("path state", pathState.toString());
        telemetry.addData ("x", follower.getPose().getX());
        telemetry.addData ("y", follower.getPose().getY());
        telemetry.addData ("heading", follower.getPose().getHeading());
        telemetry.addData ("Path time", pathTimer.getElapsedTime());

    }
}
