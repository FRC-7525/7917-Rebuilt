package frc.robot;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.units.measure.LinearAcceleration;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import java.util.ArrayList;
import java.util.Arrays;

public class GlobalConstants {

	public static final LinearAcceleration GRAVITY = MetersPerSecondPerSecond.of(9.81);
	public static final double SIMULATION_PERIOD = 0.02;

	public static final Field2d FIELD = new Field2d();

	public enum RobotMode {
		REAL,
		TESTING,
		SIM,
	}

	public static final RobotMode ROBOT_MODE = "Crash".equals(System.getenv("CI_NAME")) || !Robot.isReal() ? RobotMode.SIM : RobotMode.REAL;

	public static class FaultManagerConstants {

		public static final ArrayList<Integer> CANIVORE_DEVICE_ORDER = new ArrayList<Integer>(Arrays.asList(39, 56, 6, 4, 58, 9, 5, 11, 12, 2, 59, 3, 8));
	}
}
