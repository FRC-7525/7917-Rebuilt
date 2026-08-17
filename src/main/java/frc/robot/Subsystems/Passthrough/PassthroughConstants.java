package frc.robot.Subsystems.Passthrough;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.units.measure.AngularVelocity;

public class PassthroughConstants {

	public static final double PASSTHROUGH_MAINMOTOR_RPS = 40;

	public static final double MOTOR_PROPORTION = 0.0002;
	public static final double MOTOR_INTEGRAL = 0;
	public static final double MOTOR_DERIVATIVE = 0;
	public static final int MAIN_MOTOR_ID = 14;

	public static final AngularVelocity IDLE_SPEED = RotationsPerSecond.of(0);
	public static final AngularVelocity PASS_SPEED = RotationsPerSecond.of(15);
	public static final AngularVelocity REVERSE_SPEED = RotationsPerSecond.of(-15);

	public static final int NUM_MOTORS = 1;
	public static final int GEARING = 1;

	public static final double JKG_METERS_SQUARED = 0.0001;
	public static final double PASS_VOLTAGE = 13;
	public static final double CAN_BUS_VOLTAGE = 12;

	public static final double DT_SECONDS = 0.02;

	public static final double SET_INPUT_VOLTS = 0;

	public static final int SPEED = 1;

	public static final double RPS_TO_RPM = 60;
}
