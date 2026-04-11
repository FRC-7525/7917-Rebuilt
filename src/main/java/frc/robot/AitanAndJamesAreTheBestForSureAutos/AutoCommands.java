package frc.robot.AitanAndJamesAreTheBestForSureAutos;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Manager.Manager;
import frc.robot.Manager.ManagerStates;
import frc.robot.Subsystems.Drive.Drive;
import frc.robot.Subsystems.Drive.DriveStates;

public class AutoCommands {

	private static AutoCommands instance;

	public static AutoCommands getInstance() {
		if (instance == null) {
			instance = new AutoCommands();
		}
		return instance;
	}

	public AutoCommands() {}

	public Command intakeDeploy() {
		return new InstantCommand(() -> {
			Manager.getInstance().setIntakeOut(true);
		});
	}

	public Command returnToIdle() {
		return new InstantCommand(() -> {
			Manager.getInstance().setState(ManagerStates.IDLE);
			Drive.getInstance().setState(DriveStates.Manual);
		});
	}

	public Command startWindingUp() {
		return new InstantCommand(() -> Manager.getInstance().setState(ManagerStates.WINDUP_AUTO));
	}

	public Command shootFuel() {
		return new InstantCommand(() -> {
			Manager.getInstance().setState(ManagerStates.SHOOT_AUTO);
			Drive.getInstance().setState(DriveStates.Manual);
		});
	}
}
