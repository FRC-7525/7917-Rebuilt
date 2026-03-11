package frc.robot.AutoManager;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystems.Drive.Drive;
import frc.robot.Manager.Manager;
import frc.robot.Manager.ManagerStates;

// import edu.wpi.first.wpilibj2.command.Command;

public class ShootCommand extends Command {

    private final Timer timer;

	public ShootCommand() {
		this.timer = new Timer();
	}

	@Override
	public void initialize() {
		Manager.getInstance().setState(ManagerStates.FIXED_SHOOT);
		timer.start();
	}

	@Override
	public boolean isFinished() {
		boolean finished = timer.hasElapsed(1.5);
		if (finished) {
			// Drive.getInstance().setState(DriveStates.NORMAL);
			Manager.getInstance().setState(ManagerStates.IDLE);
		}
		return finished;
	}
    
}
