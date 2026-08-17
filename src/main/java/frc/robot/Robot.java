// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.SignalLogger;
import com.revrobotics.util.StatusLogger;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Manager.Manager;
import frc.robot.Manager.ManagerStates;
import frc.robot.Subsystems.Drive.Drive;
import frc.robot.Subsystems.Drive.DriveStates;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {

	public static boolean isRedAlliance = false;
	private final Manager manager = Manager.getInstance();
	private final Drive drive = Drive.getInstance();
	/**
	 * This function is run when the robot is first started up and should be used for any
	 * initialization code.
	 */

	public Robot() {
		SignalLogger.enableAutoLogging(false);
		StatusLogger.disableAutoLogging();
		DriverStation.silenceJoystickConnectionWarning(true);
		CommandScheduler.getInstance().unregisterAllSubsystems();
		drive.zeroGyro();
	}

	@Override
	public void robotPeriodic() {
		manager.periodic();
		drive.periodic();
	}

	@Override
	public void autonomousInit() {}

	@Override
	public void autonomousPeriodic() {}

	@Override
	public void teleopInit() {
		drive.setState(DriveStates.MANUAL);
		CommandScheduler.getInstance().cancelAll();
		manager.setState(ManagerStates.IDLE);
		SmartDashboard.putBoolean("Robot State/isAutonomous", DriverStation.isAutonomous());
		SmartDashboard.putBoolean("Robot State/isEnabled", DriverStation.isEnabled());
	}

	@Override
	public void teleopPeriodic() {}

	@Override
	public void disabledInit() {
		SmartDashboard.putBoolean("Robot State/isAutonomous", DriverStation.isAutonomous());
		SmartDashboard.putBoolean("Robot State/isEnabled", DriverStation.isEnabled());
	}

	@Override
	public void disabledPeriodic() {
		isRedAlliance = DriverStation.getAlliance().isPresent() && DriverStation.getAlliance().get() == Alliance.Red;
	}

	@Override
	public void disabledExit() {
		isRedAlliance = DriverStation.getAlliance().isPresent() && DriverStation.getAlliance().get() == Alliance.Red;
	}

	@Override
	public void testInit() {}

	@Override
	public void testPeriodic() {}

	@Override
	public void simulationInit() {}

	@Override
	public void simulationPeriodic() {}
}
