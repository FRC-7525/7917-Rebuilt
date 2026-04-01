package frc.robot.Subsystems.Drive;

import static frc.robot.Subsystems.Drive.DriveConstants.*;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.io.File;

import com.ctre.phoenix6.swerve.jni.SwerveJNI.DriveState;

import swervelib.SwerveDrive;
import swervelib.SwerveInputStream;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

public class Drive extends SubsystemBase {
	private static Drive instance;
	private DriveStates state;
	private SwerveInputStream swerveInputs;
	private SwerveDrive swerveDrive;
	public final XboxController DRIVER_CONTROLLER;
	public final XboxController operatorController;
	private Field2d robot;
	private boolean fieldRelative;
	private boolean slow;
	private double invert;

	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive();
		}
		return instance;
	}

	private Drive() {
		robot = new Field2d();
		DRIVER_CONTROLLER = new XboxController(0);
		operatorController = new XboxController(1);

		try {
			File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(), "swerve");
			swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(MAX_SPEED, new Pose2d(9.9, 4.0, Rotation2d.fromDegrees(0)));
		} catch (Exception e) {
			throw new RuntimeException("Failed to create SwerveDrive", e);
		}
		invert = 1;
		swerveDrive.setMotorIdleMode(false);
		SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
		swerveInputs = SwerveInputStream.of(swerveDrive, () -> invert * DRIVER_CONTROLLER.getLeftY(), () -> invert * DRIVER_CONTROLLER.getLeftX()).withControllerRotationAxis(() -> DRIVER_CONTROLLER.getRightX()).allianceRelativeControl(true).driveToPoseEnabled(false);
	}

	public void periodic() {
		// if (fieldRelative) {
		// 	if (DRIVER_CONTROLLER.getBackButtonPressed()) {
		// 		fieldRelative = false;
		// 	}
		// 	swerveDrive.driveFieldOriented(swerveInputs.get());
		// } else {
		// 	if (DRIVER_CONTROLLER.getBackButtonPressed()) {
		// 		fieldRelative = true;
		// 	}
			if (operatorController.getPOV() == 0) {
				invert= -1;
			} 
			if (operatorController.getPOV() == 180) {
				invert = 1;
			}

			if (slow) {
			if (DRIVER_CONTROLLER.getLeftBumperButtonPressed()) { 
				slow = false;
				SmartDashboard.putBoolean("Drive/Slow Mode", false);
			}
			swerveInputs.scaleTranslation(0.33);
			swerveInputs.scaleRotation(0.33);
		} else {
			if (DRIVER_CONTROLLER.getLeftBumperButtonPressed()) { 
				slow = true;
				SmartDashboard.putBoolean("Drive/Slow Mode", true);
			}
			swerveInputs.scaleTranslation(1);
			swerveInputs.scaleRotation(1);
		}
		// 	swerveDrive.drive(swerveInputs.get());
		// }


			swerveDrive.driveFieldOriented(swerveInputs.get());
			SmartDashboard.putData(robot);
			if (DRIVER_CONTROLLER.getBButtonPressed()) {
				zeroGyro();
			}

			SmartDashboard.putData(robot);
		}
		
		

	

	public void zeroGyro() {
		swerveDrive.zeroGyro();
	}

	public void setState(DriveStates auto) {
		this.state = DriveStates.Auto;
	}

	public Pose2d getPose() {
		return swerveDrive.getPose();
	}

	public void setPose(Pose2d pose) {
		swerveDrive.resetOdometry(pose);
	}

	public void drive(ChassisSpeeds speeds) {
		speeds = new ChassisSpeeds(speeds.vxMetersPerSecond, speeds.vyMetersPerSecond, 0);
		swerveDrive.drive(speeds);
	}
	public ChassisSpeeds getRobotRelativeSpeeds() {
		return swerveDrive.getRobotVelocity();
	}
}
