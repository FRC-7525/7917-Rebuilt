package frc.robot.Manager;

// import frc.robot.Subsystems.AutoAlign.AutoAlign;
import frc.robot.Subsystems.Climber.ClimberStates;
import frc.robot.Subsystems.Intake.IntakeStates;
// import frc.robot.Subsystems.Passthrough.PassthroughStates;
import frc.robot.Subsystems.Shooter.ShooterStates;

public enum ManagerStates {
	// climber should have intake in
	// CLIMBOUT(ClimberStates.GOINGOUT, PassthroughStates.IDLE, ShooterStates.IDLE, "CLIMBOUT"),
	// CLIMBIN(ClimberStates.COMINGIN, PassthroughStates.IDLE, ShooterStates.IDLE, "CLIMBIN"),
	INIDLE(IntakeStates.IDLE, ClimberStates.IDLE, ShooterStates.IDLE, "REALLYIDLE"),
	IDLE(IntakeStates.IDLE, ClimberStates.IDLE, ShooterStates.IDLE, "IDLE"),
	INTAKING(IntakeStates.INTAKING, ClimberStates.IDLE, ShooterStates.IDLE, "INTAKING"),
	// CLIMBAUTO(ClimberStates.GOINGOUT, PassthroughStates.IDLE, ShooterStates.IDLE, "CLIMBAUTO"),
	// DYNAMICSHOT(ClimberStates.IDLE, In
	// takeStates.INTAKING_OUT, PassthroughStates.PASS, ShooterStates.DYNAMICSHOOT, "DYNAMICSHOT"),
	// LONGSHOT(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.PASS, ShooterStates.LONGPASS, "LONGPASS"),
	// SPINUP(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.IDLE, ShooterStates.FIXEDSHOOT, "SPINUP"),;
	FIXEDSHOT(IntakeStates.INTAKING_OUT, ClimberStates.IDLE, ShooterStates.FIXEDSHOOT, "FIXEDSHOOT"),
	WINDUP(IntakeStates.INTAKING_OUT, ClimberStates.IDLE, ShooterStates.FIXEDSHOOT, "WINDUP"),
	// -------------------------------------------------------- AUTO STATES --------------------------------------------------------
	SHOOT_AUTO(IntakeStates.INTAKING_OUT, ClimberStates.IDLE, ShooterStates.FIXEDSHOOT, "SHOOT_AUTO"),
	WINDUP_AUTO(IntakeStates.INTAKING_OUT, ClimberStates.IDLE, ShooterStates.FIXEDSHOOT, "WINDUP_AUTO"),
	// REVERSE_SHOOT(ClimberStates.IDLE, IntakeStates.INTAKING_OUT, PassthroughStates.IDLE, ShooterStates.REVERSESHOOT, "REVERSE_SHOOT");
	REVERSE_PASS(IntakeStates.INTAKING_OUT, ClimberStates.IDLE, ShooterStates.IDLE, "REVERSE_PASS");

	private final String stateString;
	private final IntakeStates intakeState;
	private final ShooterStates shooterState;
	private final ClimberStates climberState;


	ManagerStates(IntakeStates intakeState, ClimberStates climberState, ShooterStates shooterState, String stateString) {
		this.climberState = climberState;
		this.intakeState = intakeState;
		this.shooterState = shooterState;
		this.stateString = stateString;
	}

	public ClimberStates getClimberState() {
		return climberState;
	}

	public IntakeStates getIntakeState() {
	    return intakeState;
	}

	// public PassthroughStates getPassthroughState() {
	// 	return passthroughState;
	// }

	public ShooterStates getShooterState() {
		return shooterState;
	}

	// public AutoAlign getAutoAlignState() {
	//     return autoalign;
	// }

	public String getStateString() {
		return stateString;
	}
}
