package frc.robot.Subsystems.AutoAlign;

import edu.wpi.first.math.controller.PIDController;
import frc.robot.GlobalConstants;
import java.util.function.Supplier;

public class AutoAlignConstants {

	public static final Supplier<PIDController> ROTATIONAL_CONTROLLER = () ->
		switch (GlobalConstants.ROBOT_MODE) {
			case REAL -> new PIDController(0.2, 0, 0);
			case SIM -> new PIDController(0.1, 0, 0);
			default -> new PIDController(0.2, 0, 0);
		};

	public static final Supplier<PIDController> X_TRANSLATIONAL_CONTROLLER = () ->
		switch (GlobalConstants.ROBOT_MODE) {
			case REAL -> new PIDController(20, 0, 1);
			case SIM -> new PIDController(20, 0, 0);
			default -> new PIDController(3, 0, 0);
		};
	public static final Supplier<PIDController> Y_TRANSLATIONAL_CONTROLLER = () ->
		switch (GlobalConstants.ROBOT_MODE) {
			case REAL -> new PIDController(20, 0, 1);
			case SIM -> new PIDController(20, 0, 0);
			default -> new PIDController(3, 0, 0);
		};
}
