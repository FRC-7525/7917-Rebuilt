package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import java.util.ArrayList;
import java.util.Arrays;

public class GlobalConstants {

	public enum RobotMode {
		REAL,
		SIM;
	}

	public static final double SIMULATION_PERIOD = 0.02;

	public static final RobotMode ROBOT_MODE = "Crash".equals(System.getenv("CI_NAME")) || !Robot.isReal() ? RobotMode.SIM : RobotMode.REAL;

	public static class FaultManagerConstants {
		public static final ArrayList<Integer> CANIVORE_DEVICE_ORDER = new ArrayList<Integer>(Arrays.asList(39, 56, 6, 4, 58, 9, 5, 11, 12, 2, 59, 3, 8));
	}

	public static class Controllers {
		public static final XboxController DRIVER_CONTROLLER = new XboxController(0);
		public static final XboxController OPERATOR_CONTROLLER = new XboxController(1);
	}
}
