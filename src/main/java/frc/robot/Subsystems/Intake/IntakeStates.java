package frc.robot.Subsystems.Intake;

import static frc.robot.Subsystems.Intake.IntakeConstants.*;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public enum IntakeStates {
	IDLE(IDLE_SPEED, IDLE_ANGLE),
	INTAKING(INTAKING_SPEED, INTAKING_ANGLE),
	PASSING(PASSING_SPEED, PASSING_ANGLE);

	private String stateString;
	private AngularVelocity wheelSpeed;
	private Angle position;

    IntakeStates(AngularVelocity wheelSpeed, Angle position) {
		this.wheelSpeed = wheelSpeed;
		this.position = position;
	}

	public AngularVelocity getStateString() {
		return wheelSpeed;
	}

	public Angle getShooterRPS() {
		return position;
	}
}