package org.firstinspires.ftc.teamcode;

public class RobotLocationPractice {

    double  angle;

    //constructor method
    public RobotLocationPractice(double angle) {
        this.angle = angle;
    }

    public double getHeading()
    // this method normalizes robot heading betwen -180 and 180
    // this is useful for calculating turn angles, especially when crossing the 0,360 boundary


