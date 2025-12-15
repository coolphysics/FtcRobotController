package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestBench1 {
    private DcMotor motor; //e.g. linearSlideMotor0

    private double ticksPerRev; // revolution

    public void init(HardwareMap hwMap) {
        // touch sensor code from other test bench

        // Dc motor

        motor = hwMap.get(DcMotor.class, "motor");
        /* run_using_encoder mean motor will try to run at target speed.
        two motors on a drivetrain will have the best chance at running at the same speed
        when run_using_encoder is selected

        If run_to_position is selected the motors will try to run to that many
        ticks designated
         */
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRev = motor.getMotorType().getTicksPerRev();

    }

    public void setMotorSpeed(double speed){
        // accepts values from -1.0 to 1.0
        motor.setPower(speed);

    }

    public double getMotorRevs() {

        return motor.getCurrentPosition() / ticksPerRev; // normalizes ticks to revolutions

    }
}
