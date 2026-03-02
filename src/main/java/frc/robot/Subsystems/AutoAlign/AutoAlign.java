package frc.robot.Subsystems.AutoAlign;

import static frc.robot.Subsystems.AutoAlign.AutoAlignConstants.*;
import static frc.robot.Subsystems.AutoAlign.AutoAlignStates.*;

import com.revrobotics.spark.SparkClosedLoopController.ArbFFUnits;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Subsystems.Drive.Drive;
import org.littletonrobotics.junction.Logger;

public class AutoAlign {

	private static AutoAlign instance;

	private final PIDController rotationalController;
	private final PIDController xtranslationalController;
	private final PIDController ytranslationalController;
	private static AutoAlignStates state;
	private static final XboxController DRIVER_CONTROLLER = new XboxController(0);

	private PosePair targetPose;
	private Field2d goalPoseLog;
	private Pose2d goalPose;

	private AutoAlign() {
		super();
		state = AutoAlignStates.OFF;
		this.rotationalController = ROTATIONAL_CONTROLLER.get();
		rotationalController.enableContinuousInput(-180, 180);

		this.xtranslationalController = X_TRANSLATIONAL_CONTROLLER.get();
		this.ytranslationalController = Y_TRANSLATIONAL_CONTROLLER.get();

		targetPose = new PosePair(Pose2d.kZero, Pose2d.kZero);
		goalPose = Pose2d.kZero;
		goalPoseLog = new Field2d();
		SmartDashboard.putData("Rotaitonal Cotroller", rotationalController);
		SmartDashboard.putData("Y Transisitional Controller", ytranslationalController);
		SmartDashboard.putData("X Transisitional Controller", xtranslationalController);
	}

	public static AutoAlign getInstance() {
		if (instance == null) {
			instance = new AutoAlign();
		}
		return instance;
	}

	public AutoAlignStates getAutoAlignState() {
		return state;
	}

	public void periodic() {
		if (getAutoAlignState() != AutoAlignStates.OFF) {
			targetPose = getAutoAlignState().getLocation();
		}

		if (!DriverStation.getAlliance().isEmpty() && DriverStation.getAlliance().get() == Alliance.Red) {
			goalPose = targetPose.getBluePose();
		} else {
			goalPose = targetPose.getRedPose();
		}

		Pose2d currentPose = Drive.getInstance().getPose();

		if (!(AutoAlignStates.OFF == getAutoAlignState())) {
			if (
				Drive.getInstance()
					.driveRobotAutoAlign(xtranslationalController.calculate(currentPose.getX(), goalPose.getX()), ytranslationalController.calculate(currentPose.getY(), goalPose.getY()), rotationalController.calculate(currentPose.getRotation().getDegrees(), goalPose.getRotation().getDegrees()))
			) {
				state = AutoAlignStates.OFF;
			}
		}
		if (DRIVER_CONTROLLER.getXButtonPressed()) {
			state = AutoAlignStates.toOutpost;
		} else if (DRIVER_CONTROLLER.getYButtonPressed()) {
			state = AutoAlignStates.toShootRangeHub;
		} else if (DRIVER_CONTROLLER.getBButtonPressed()) {
			state = AutoAlignStates.toTower;
		} else if (DRIVER_CONTROLLER.getAButtonPressed()) {
			state = AutoAlignStates.toDepot;
		}

		SmartDashboard.putString("State", state.name());
		if (DRIVER_CONTROLLER.getRightBumperButtonPressed()) {
			state = AutoAlignStates.OFF;
			rotationalController.reset();
			xtranslationalController.reset();
			ytranslationalController.reset();
		}
		goalPoseLog.getObject("Goal Pose").setPose(goalPose);
		SmartDashboard.putData(goalPoseLog);
	}
}
