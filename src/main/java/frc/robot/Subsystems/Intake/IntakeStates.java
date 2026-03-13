package frc.robot.Subsystems.Intake;

import static frc.robot.Subsystems.Intake.IntakeConstants.*;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public enum IntakeStates {
	IDLE("IDLE", OFF_WHEEL_SPEED, IN_ANGLE),
	INTAKING("INTAKING", INTAKING_WHEEL_SPEED, OUT_ANGLE);

	String stateString;
	AngularVelocity wheelSpeed;
	Angle targetAngle;

	private IntakeStates(String stateString, AngularVelocity wheelSpeed, Angle targetAngle) {
		this.stateString = stateString;
		this.wheelSpeed = wheelSpeed;
		this.targetAngle = targetAngle;
	}

	public String getStateString() {
		return stateString;
	}

	public AngularVelocity getWheelSpeed() {
		return wheelSpeed;
	}

	public Angle getTargetAngle() {
		return targetAngle;
	}
}
