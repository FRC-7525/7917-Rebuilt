package frc.robot.Subsystems.Passthrough;

import static frc.robot.Subsystems.Passthrough.PassthroughConstants.*;

import edu.wpi.first.units.measure.AngularVelocity;

public enum PassthroughStates {
	IDLE(IDLE_SPEED),
	PASS(PASS_SPEED);

	AngularVelocity Speed;

	PassthroughStates(AngularVelocity Speed) {
		this.Speed = Speed;
	}

	public AngularVelocity getSpeed() {
		return Speed;
	}
}
