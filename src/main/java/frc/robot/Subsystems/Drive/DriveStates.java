package frc.robot.Subsystems.Drive;

public enum DriveStates {
	Manual("Manual");

	public String stateString;

	DriveStates(String stateString) {
		this.stateString = stateString;
	}

	public String getStateString() {
		return stateString;
	}
}
