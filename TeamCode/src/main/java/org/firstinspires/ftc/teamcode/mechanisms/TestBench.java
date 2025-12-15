package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestBench {
    private DigitalChannel touchSensor;
    private DcMotor motor; //e.g. linearSlideMotor0
    private double ticksPerRev; // revolution

    public void init(HardwareMap hwMap) {
        //Touch Sensor
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);

        // DC motor
        motor = hwMap.get(DcMotor.class, "motor");
        /* run_using_encoder means the motor will try to run at the target speed.
        Two motors on a drivetrain will have the best chance at running at the same speed
        when run_using_encoder is selected

        If run_to_position is selected, the motors will try to run to that many
        ticks designated
         */
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRev = motor.getMotorType().getTicksPerRev();
        //BRAKE attempts to stop motor, FLOAT allows it to spin to rest (inertia)
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //changes direction of motor, could also be done by reversing polarity
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    //-------------Touch Sensor-----------------------------------------
    public boolean getTouchSensorPressed() {
        return !touchSensor.getState();
    }
    public boolean isTouchSensorReleased() {
        return touchSensor.getState();
    }

    //------------Motor Speed---------------------------------------------
    public void setMotorSpeed(double speed){
        // accepts values from -1.0 to 1.0
        motor.setPower(speed);

    }
    public double getMotorRevs() {
        return motor.getCurrentPosition() / ticksPerRev; // normalizes ticks to revolutions
    }
}
