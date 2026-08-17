package frc.robot.Subsystems.Intake;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static frc.robot.Subsystems.Intake.IntakeConstants.IN_ANGLE;
import static frc.robot.Subsystems.Intake.IntakeConstants.OUT_ANGLE;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.GlobalConstants;
import frc.robot.Manager.Manager;

public class Intake {

	private static Intake instance;

	private IntakeIO io;

	// private IntakeStates currentState;

	Angle targetAngle;

	// private XboxController controller;

	// private boolean intakeOn;

	private Intake(IntakeIO io) {
		this.io = io;
		// currentState = IntakeStates.IDLE;
		// controller = new XboxController(0);
		// intakeOn = false;
		targetAngle = IN_ANGLE;
	}

	public static Intake getInstance() {
		if (instance == null) {
			switch (GlobalConstants.ROBOT_MODE) {
				case REAL -> instance = new Intake(new IntakeIOReal());
				default -> instance = new Intake(new IntakeIOReal());
				// case SIM -> instance = new Intake(new IntakeIOSim());
			}
		}
		return instance;
	}

	// public IntakeIO getIO() {
	//     return io;
	// }

	public void periodic() {
		// if (controller.getAButtonPressed()) {
		// 	intakeOn = !intakeOn;
		// }

		// if (intakeOn) {
		// 	setState(IntakeStates.INTAKING);
		// } else if (intakeOn == false) {
		// 	setState(IntakeStates.IDLE);
		// }
		// io.setTargetAngle(currentState.getTargetAngle());
		io.setTargetAngle(targetAngle);
		io.setWheelSpeed(DegreesPerSecond.of(1));
		SmartDashboard.putNumber("Intake/Pivot Angle", io.getIntakeAngle());
		logData();
		// if (currentState == IntakeStates.INTAKING) {
		//     SmartDashboard.putBoolean("Intake/Roller On", true);
		// } else {
		//     SmartDashboard.putBoolean("Intake/Roller On", false);
		// }

		if (Manager.getInstance().isIntakeOut()) {
			targetAngle = OUT_ANGLE;
		} else {
			targetAngle = IN_ANGLE;
		}
	}

	public void setIntakeOn(boolean intakeOn) {
		io.setIntakeOn(intakeOn);
	}

	// public void setState(IntakeStates newState) {
	// 	currentState = newState;
	// }

	private void logData() {
		//SmartDashboard.putString("Intake/CurrentState", currentState.getStateString());

		SmartDashboard.putNumber("Intake/targetAngle (DEG)", targetAngle.in(Degrees));
		SmartDashboard.putNumber("Intake/Intake DEG", io.getCurrentAngle().in(Degree));
		SmartDashboard.putNumber("Intake/Intake RPS", io.getCurrentWheelSpeed().in(RotationsPerSecond));
		SmartDashboard.putNumber("Intake/wheelmotorcurrent", io.getWheelMotorCurrent());
		SmartDashboard.putNumber("Intake/pivotMotorCurrent", io.getPivotMotorCurrent());
		// SmartDashboard.putNumber("Intake/P Value", WHEEL_CONTROLLER.getP());
	}
}
