package org.firstinspires.ftc.teamcode;

public class RobotHeadingNormalized {
    double angle;


    //constructor method
    public RobotHeadingNormalized(double angle) {
        this.angle = angle;
    }

    public double getHeading() {
        //this method normalized robot heading between -180 and 180 degrees
        //this is useful for calculating turn angles, especially  when crossing 0, 360 boundary

        double angle = this.angle; // copy angle of imu

        while (angle > 180) {
            angle -= 360; //subtract until in target range
        }
        while (angle <= -180) {
            angle += 360;  //add until in target range
        }
        return angle;
    }

    public void turnRobot(double angleChange) {
        angle += angleChange;
    }
    public void setAngle(double angle) {
       this.angle = angle;
    }
}
