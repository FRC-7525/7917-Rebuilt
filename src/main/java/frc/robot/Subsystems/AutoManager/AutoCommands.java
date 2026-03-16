package frc.robot.Subsystems.AutoManager;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.Manager.Manager;
import frc.robot.Manager.ManagerStates;

public class AutoCommands {

	Robot robot;

	public AutoCommands(Robot robot) {
		this.robot = robot;
	}

	public static Command intake() {
		return new InstantCommand(() -> Manager.getInstance().setState(ManagerStates.INTAKING));
	}

	public static Command returnToIdle() {
		return new InstantCommand(() -> Manager.getInstance().setState(ManagerStates.IDLE));
	}

	public static Command startWindingUp() {
		return new InstantCommand(() -> Manager.getInstance().setState(ManagerStates.WINDING_UP));
	}

	public static Command windAndIntake() {
		return new InstantCommand(() -> Manager.getInstance().setState(ManagerStates.WINDING_AND_INTAKING));
	}

    public static Command climb() {
		return new InstantCommand(() -> Manager.getInstance().setState(ManagerStates.CLIMBING_LV1));
	}
}