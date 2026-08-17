package frc.robot.Subsystems.Shooter;

import static frc.robot.Subsystems.Shooter.ShooterConstants.*;

import edu.wpi.first.units.measure.AngularVelocity;

public enum ShooterStates {
	IDLE(IDLE_RPS),
	FIXEDSHOOT(FIXED_SHOOT_RPS);

	// REVERSESHOOT(REVERSE_SHOOT_RPS);
	// DYNAMICSHOOT(FIXED_SHOOT_RPS),
	// LONGPASS(LONG_PASS_RPS);

	private String stateString;
	private AngularVelocity shooterSpeed;

	ShooterStates(AngularVelocity shooterSpeed) {
		this.stateString = this.name();
		this.shooterSpeed = shooterSpeed;
	}

	public String getStateString() {
		return stateString;
	}

	public AngularVelocity getShooterRPS() {
		return shooterSpeed;
	}
}
