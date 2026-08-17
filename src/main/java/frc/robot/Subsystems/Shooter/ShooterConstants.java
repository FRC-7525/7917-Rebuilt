package frc.robot.Subsystems.Shooter;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.GlobalConstants;
import java.util.function.Supplier;

public final class ShooterConstants {

	public static final AngularVelocity IDLE_RPS = Units.RotationsPerSecond.of(0);
	public static final AngularVelocity FIXED_SHOOT_RPS = Units.RotationsPerSecond.of(73);
	public static final AngularVelocity REVERSE_SHOOT_RPS = Units.RotationsPerSecond.of(-75);

	public static final int LEFT_MOTOR_ID = 13;
	public static final int RIGHT_MOTOR_ID = 12;

	public static final double KP = SmartDashboard.getNumber("KP", 0.0);
	public static final double KI = SmartDashboard.getNumber("KI", 0.0);
	public static final double KD = SmartDashboard.getNumber("KD", 0.0);

	public static final Supplier<PIDController> WHEEL_PID = () ->
		switch (GlobalConstants.ROBOT_MODE) {
			case REAL -> new PIDController(KP, KI, KD);
			case SIM -> new PIDController(1, 0, 0.01);
			// default -> new PIDController(20, 1, 0);
		};

	public static final Supplier<SimpleMotorFeedforward> WHEEL_FEEDFORWARD = () ->
		switch (GlobalConstants.ROBOT_MODE) {
			//case REAL -> new SimpleMotorFeedforward(0.26, 0.00207, 0);
			case REAL -> new SimpleMotorFeedforward(0.26, 0.00207, 0);
			case SIM -> new SimpleMotorFeedforward(0, 0.001, 0);
			// default -> new SimpleMotorFeedforward(0, 0.1, 0);
		};

	// for Simulation
	public static final int LEFT_SIM_MOTOR = 1;
	public static final int RIGHT_SIM_MOTOR = 1;
	public static final double JKG_METERS_SQUARED = 0.001;
	public static final double GEARING = 0.001;

	public static final int BUS_VOLTAGE = 12;
	public static final double BIG_WHEEL_UPDATE = 0.02;

	public static final int RPS_TO_RPM_CONVERSION_FACTOR = 60;
	public static final int IDLE_SPEED_OR_VOLTAGE = 0;
	public static final int BIG_WHEEL_VOLTAGE_INITIAL_CALC_FACTOR = 12;
}
