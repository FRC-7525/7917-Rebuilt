package frc.robot.AitanAndJamesAreTheBestForSureAutos;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Manager.Manager;
import frc.robot.Manager.ManagerStates;
import frc.robot.Subsystems.Climber.Climber;

public class ClimbGoCommand extends Command {

	public ClimbGoCommand() {}

	@Override
	public void initialize() {
		Manager.getInstance().setState(ManagerStates.CLIMBAUTO);
	}

	@Override
	public void execute() {
		if (Climber.getInstance().getPosition() >= 14.16) {
			Climber.getInstance().setSpeed(0);
			this.cancel();
			return;
		}
		if (Climber.getInstance().getPosition() >= -13.16) {
			Climber.getInstance().setSpeed(0.2);
		} else {
			Climber.getInstance().setSpeed(0.3);
		}
	}

	@Override
	public void end(boolean interrupted) {
		Climber.getInstance().setSpeed(0);
		Manager.getInstance().setState(ManagerStates.INIDLE);
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
