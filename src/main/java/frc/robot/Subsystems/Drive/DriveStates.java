package frc.robot.Subsystems.Drive;

public enum DriveStates {
	Manual("Manual"),
	Auto("Auto");

	public String stateString;

	public DriveStates driveState;

	DriveStates(String stateString) {
		this.stateString = stateString;
	}

	public String getStateString() {
		return stateString;
	}
}
