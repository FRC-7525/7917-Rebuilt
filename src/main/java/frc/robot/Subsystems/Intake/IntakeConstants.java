package frc.robot.Subsystems.Intake;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.KilogramSquareMeters;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.MomentOfInertia;
import frc.robot.GlobalConstants;
import java.util.function.Supplier;

public class IntakeConstants {

	public static int PIVOT_MOTOR_ID = 10; // TODO: Change this to the correct can id
	public static int WHEEL_MOTOR_ID = 9;

	public static Angle IN_ANGLE = Degree.of(0);
	public static Angle OUT_ANGLE = Degree.of(-70);
	public static AngularVelocity OFF_WHEEL_SPEED = RotationsPerSecond.of(0);
	public static AngularVelocity INTAKING_WHEEL_SPEED = RotationsPerSecond.of(90);
	// dont go lower than 60

	public static MomentOfInertia FLYWHEEL_MOI = KilogramSquareMeters.of(1);
	public static double FLYWHEEL_GEARING = 45;
	public static int FLYWHEEL_MOTOR_COUNT = 1;

	public static MomentOfInertia PIVOT_MOI = KilogramSquareMeters.of(1);
	public static double PIVOT_GEARING = 25;
	public static int PIVOT_MOTOR_COUNT = 1;
	public static Distance PIVOT_ARM_LENGTH = Meters.of(1);
	public static Angle PIVOT_MIN_ANGLE = Degree.of(-70);
	public static Angle PIVOT_MAX_ANGLE = Degree.of(0);

	public static final Supplier<PIDController> WHEEL_CONTROLLER = () ->
		switch (GlobalConstants.ROBOT_MODE) {
			case REAL -> new PIDController(0.1, 0, 0);
			case SIM -> new PIDController(0.1, 0, 0);
		};

	public static final Supplier<PIDController> PIVOT_CONTROLLER = () ->
		switch (GlobalConstants.ROBOT_MODE) {
			case REAL -> new PIDController(0.055, 0, 0);
			case SIM -> new PIDController(0.1, 0, 0);
		};
}
