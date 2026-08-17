package frc.robot.Subsystems.Drive;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import java.io.File;
import swervelib.SwerveDrive;
import swervelib.SwerveInputStream;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;
import static frc.robot.Subsystems.Drive.DriveConstants.*;
import static frc.robot.GlobalConstants.Controllers.DRIVER_CONTROLLER;

public class Drive extends SubsystemBase {
	private static Drive instance;
	private DriveStates state;
	private SwerveInputStream swerveInputs;
	private SwerveDrive swerveDrive;
	private Field2d field;
	private boolean isDisableRequested = false;
	private boolean isDemoRequested = false;

	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive();
		}
		return instance;
	}

	private Drive() {
		field = new Field2d();
		try {
			File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(), "swerve");
			swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(MAX_SPEED, new Pose2d(9.9, 4.0, Rotation2d.fromDegrees(0)));
		} catch (Exception e) {
			throw new RuntimeException("Failed to create SwerveDrive", e);
		}
		SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
		swerveDrive.setMotorIdleMode(true);
		swerveInputs = SwerveInputStream.of(swerveDrive, () -> -DRIVER_CONTROLLER.getLeftY(), () -> -DRIVER_CONTROLLER.getLeftX()).withControllerRotationAxis(() -> -DRIVER_CONTROLLER.getRightX()).allianceRelativeControl(true);
		state = DriveStates.MANUAL;
	}

	public void periodic() {
		isDisableRequested = SmartDashboard.getBoolean("Disable Drive", false);
		isDemoRequested = SmartDashboard.getBoolean("Enable Demo Mode", false);

		// Independent Action: Zero Gyro (separated so it doesn't block state changes)
		if (DRIVER_CONTROLLER.getBButtonPressed()) {
			zeroGyro();
		}

		// State Transition Logic
		if (isDisableRequested) {
			// Highest priority: Force to DISABLED if dashboard switch is on
			if (state != DriveStates.DISABLED) {
				setState(DriveStates.DISABLED);
			}
		} else if (isDemoRequested) {
			// Second priority: Force to DEMO if requested (and not disabled)
			if (state != DriveStates.DEMO) {
				setState(DriveStates.DEMO);
			}
		} else if (state == DriveStates.DISABLED && !isDisableRequested) {
			// Exiting DISABLED mode -> return to default MANUAL state
			setState(DriveStates.MANUAL);
		} else if (state == DriveStates.DEMO && !isDemoRequested) {
			// Exiting DEMO mode -> return to default MANUAL state
			setState(DriveStates.MANUAL);
		} else if (state != DriveStates.DISABLED && state != DriveStates.DEMO) {
			// Controller transitions: Only allowed if not in an override state
			if (DRIVER_CONTROLLER.getRightBumperButtonPressed() && state != DriveStates.SLOW) {
				setState(DriveStates.SLOW);
			} else if (DRIVER_CONTROLLER.getLeftBumperButtonPressed() && state != DriveStates.MANUAL) {
				setState(DriveStates.MANUAL);
			}
		}
		state.drive();
		field.setRobotPose(swerveDrive.getPose());
		SmartDashboard.putString("Drive/Current State", state.getStateString());
		SmartDashboard.putData("Drive/Field", field);
		SmartDashboard.putBoolean("Disable Drive", isDisableRequested);
		SmartDashboard.putBoolean("Enable Demo Mode", isDemoRequested);
	}

	public void zeroGyro() {
		swerveDrive.zeroGyro();
		if (Robot.isRedAlliance) {
			swerveDrive.resetOdometry(swerveDrive.getPose().transformBy(RED_TRANSFORM));
		}
	}

	public void setState(DriveStates state) {
		this.state = state;
	}

	public DriveStates getDriveState() {
		return state;
	}

	public Pose2d getPose() {
		return swerveDrive.getPose();
	}

	public void resetPose(Pose2d pose) {
		swerveDrive.resetOdometry(pose);
	}

	public ChassisSpeeds getRobotRelativeSpeeds() {
		return swerveDrive.getRobotVelocity();
	}

	public SwerveInputStream getSwerveInputs() {
		return swerveInputs;
	}

	public SwerveDrive getSwerveDrive() {
		return swerveDrive;
	}
}
