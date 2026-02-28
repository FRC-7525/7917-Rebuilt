package frc.robot.Subsystems.Intake;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import frc.robot.GlobalConstants;

import static frc.robot.GlobalConstants.*;

public final class IntakeConstants {

    public static final AngularVelocity IDLE_SPEED = RotationsPerSecond.of(0);
    public static final Angle IDLE_ANGLE = Degree.of(0);
    public static final AngularVelocity INTAKING_SPEED = RotationsPerSecond.of(30);
    public static final Angle INTAKING_ANGLE = Degree.of(90);

    public static final int ROLLER_MOTOR_ID = 1;
    public static final int PIVOT_MOTOR_ID = 2;
    public static final int kdis = 0;


	public static final Supplier<PIDController> WHEEL_PID = () ->
		switch (GlobalConstants.ROBOT_MODE) {
			case REAL -> new PIDController(1, 0, 0);
			case SIM -> new PIDController(1, 0, 0.01);
			default -> new PIDController(20, 1, 0);
		};

}