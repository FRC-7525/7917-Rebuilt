package frc.robot.Subsystems.Intake;


public final class IntakeConstants {

	public static final double IDLE_RPS = 0;
	public static final double FIXED_SHOOT_RPS = 30;
	public static final int LEFT_MOTOR_ID = 15;
	public static final int RIGHT_MOTOR_ID = 14;
	public static final double LONG_PASS_RPS = 100;

	public static final double MOTOR_RIGHT_PROPORTION = 0.0002;
	public static final double MOTOR_RIGHT_INTEGRAL = 0;
	public static final double MOTOR_RIGHT_DERIVATIVE = 0;

	public static final double kS = 0;
	public static final double kV = 0;
	public static final double kA = 0;

	// for Simulation
	public static final int leftSimMotor = 1;
	public static final int rightSimMotor = 1;
	public static final double JKgMetersSquared = 0.001;
	public static final double gearing = 0.001;

	public static final int busVoltage = 12;
	public static final double bigWheelUpdate = 0.02;

	public static final double kADefaultValue = 0.02;
	public static final double kVDefaultValue = 0.02;
	public static final double kDefaultValue = 0.02;

	public static final double kADefaultValueSim = 0;
	public static final double kVDefaultValueSim = 0.001;
	public static final double kSDefaultValueSim = 0.1;

	public static final int RPStoRPMConversionFactor = 60;
	public static final int IDLESpeedOrVoltage = 0;
	public static final int BigWheelVoltageInitalCalcFactor = 12;
}