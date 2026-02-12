package frc.robot.Subsystems.Climber;

import static frc.robot.Subsystems.Climber.ClimberConstants.*;

public enum ClimberStates {
	IDLE("IDLE", IDLE_POSITION),
	L1("L1", L1_POSITION),
	L2("L2", L2_POSITION),
	DEPLOY("DEPLOY", DEPLOY_POSITION);

	private String stateString;
	private double position;

	ClimberStates(String stateString, double position) {
		this.stateString = stateString;
		this.position = position;
	}

	public String getStateString() {
		return stateString;
	}

	public double getPosition() {
		return position;
	}
}
