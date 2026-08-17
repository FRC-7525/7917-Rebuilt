package frc.robot.Subsystems.Climber;

import static frc.robot.Subsystems.Climber.ClimberConstants.*;

public enum ClimberStates {
	IDLE("IDLE", IDLE_SPEED),
	GOINGOUT("GOINGOUT", GOINGOUT_SPEED),
	COMINGIN("COMINGIN", COMINGIN_SPEED);

	private String stateString;
	private double speed;

	ClimberStates(String stateString, double speed) {
		this.stateString = stateString;
		this.speed = speed;
	}

	public String getStateString() {
		return stateString;
	}

	public double getSpeed() {
		return speed;
	}
}
