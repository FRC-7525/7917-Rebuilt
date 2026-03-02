package frc.robot.Subsystems.Drive;

import static frc.robot.Subsystems.Drive.DriveConstants.*;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.GlobalConstants;
import frc.robot.GlobalConstants.RobotMode;
import frc.robot.Subsystems.AutoAlign.AutoAlign;
import frc.robot.Subsystems.AutoAlign.AutoAlignStates;
import java.io.File;
import swervelib.SwerveDrive;
import swervelib.SwerveInputStream;
import swervelib.parser.SwerveParser;

public class Drive {

	private static Drive instance;
	private SwerveInputStream swerveInputs;
	private SwerveDrive swerveDrive;
	public final XboxController DRIVER_CONTROLLER;
	private Field2d robot;
	private boolean fieldRelative;
	private AutoAlign autoAlign;

	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive();
		}
		return instance;
	}

	private Drive() {
		robot = new Field2d();
		DRIVER_CONTROLLER = new XboxController(0);

		try {
			File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(), "swerve");
			swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(MAX_SPEED, new Pose2d(9.9, 4.0, Rotation2d.fromDegrees(0)));
		} catch (Exception e) {
			throw new RuntimeException("Failed to create SwerveDrive", e);
		}
		swerveDrive.setMotorIdleMode(true);

		swerveInputs = SwerveInputStream.of(swerveDrive, () -> -DRIVER_CONTROLLER.getLeftY(), () -> -DRIVER_CONTROLLER.getLeftX()).withControllerRotationAxis(() -> -DRIVER_CONTROLLER.getRightX()).allianceRelativeControl(true).driveToPoseEnabled(false);
	}

	public Pose2d getPose() {
		return swerveDrive.getPose();
	}

	public void periodic() {
		if (fieldRelative) {
			if (DRIVER_CONTROLLER.getBackButtonPressed()) {
				fieldRelative = false;
			}
			swerveDrive.driveFieldOriented(swerveInputs.get());
		} else {
			if (DRIVER_CONTROLLER.getBackButtonPressed()) {
				fieldRelative = true;
			}
			swerveDrive.drive(swerveInputs.get());
		}

		robot.setRobotPose(swerveDrive.getSimulationDriveTrainPose().get());

		SmartDashboard.putData(robot);
	}

	public void addVisionMeasurement(Pose2d visionPose, double timestamp, Matrix<N3, N1> visionMeasurementStdDevs) {
		if (GlobalConstants.ROBOT_MODE == RobotMode.REAL) {
			swerveDrive.addVisionMeasurement(visionPose, timestamp, visionMeasurementStdDevs);
		} else {
			swerveDrive.addVisionMeasurement(visionPose, timestamp, visionMeasurementStdDevs);
		}
		swerveDrive.updateOdometry();
	}

	public ChassisSpeeds getRobotRelativeSpeeds() {
		return swerveDrive.getRobotVelocity();
	}

	public boolean driveRobotAutoAlign(double x, double y, double rot) {
		swerveInputs.driveToPoseEnabled(true);
		swerveDrive.drive(new ChassisSpeeds(x, y, rot));
		if (AutoAlign.getInstance().getAutoAlignState() == AutoAlignStates.OFF) {
			swerveInputs.driveToPoseEnabled(false);
			return true;
		}
		return false;
	}
}
