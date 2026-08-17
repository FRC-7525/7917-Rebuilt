package frc.robot.Subsystems.Intake;

import static frc.robot.Subsystems.Intake.IntakeConstants.*;

import edu.wpi.first.units.measure.Angle;


public enum IntakeStates {
	IDLE("IDLE", IN_ANGLE),
	INTAKING("INTAKING", OUT_ANGLE),
	INTAKING_OUT("INTAKINGNOROLLER", OUT_ANGLE);

	String stateString;

	Angle targetAngle;

	private IntakeStates(String stateString, Angle targetAngle) {
		this.stateString = stateString;

		this.targetAngle = targetAngle;
	}

	public String getStateString() {
		return stateString;
	}

	public Angle getTargetAngle() {
		return targetAngle;
	}
}
