package frc.robot.Manager;

import static frc.robot.Manager.ManagerStates.*;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Subsystems.Climber.Climber;
import frc.robot.Subsystems.Drive.Drive;
import frc.robot.Subsystems.Intake.Intake;
import frc.robot.Subsystems.Passthrough.Passthrough;
import frc.robot.Subsystems.Shooter.Shooter;
import org.littletonrobotics.junction.Logger;

public class Manager {

	Climber climber;
	Intake intake;
	Passthrough passthrough;
	Shooter shooter;
	ManagerStates robotstate;

	private XboxController controller = new XboxController(0);

	private static Manager instance;

	private Manager() {
		climber = new Climber();
		intake = new Intake();
		passthrough = new Passthrough();
		shooter = new Shooter();

		robotstate = IDLE;

		//Intaking
		if (controller.getXButtonPressed()) {
			robotstate = INTAKING;
		} else if (controller.getXButtonReleased()) {
			robotstate = IDLE;
		}

		//Shooting
		if (controller.getLeftTriggerAxis() > 0.1) {
			robotstate = DYNAMICSHOT;
		} else if (controller.getLeftTriggerAxis() < 0.1) {
			robotstate = IDLE;
		}
		if (controller.getRightTriggerAxis() > 0.1) {
			robotstate = LONGSHOT;
		} else if (controller.getRightTriggerAxis() < 0.1) {
			robotstate = IDLE;
		}
		if (controller.getRightBumperButtonPressed()) {
			robotstate = FIXEDSHOT;
		} else if (controller.getRightBumperButtonReleased()) {
			robotstate = IDLE;
		}
		//Climbing
		if (controller.getYButtonPressed()) {
			robotstate = CLIMBPREP;
		} else if (controller.getYButtonPressed()) {
			robotstate = CLIMBLV1;
		} else if (controller.getAButtonPressed()) {
			robotstate = CLIMBLV2;
		} else if (controller.getYButtonPressed() || controller.getAButtonReleased()) {
			robotstate = IDLE;
		}
	}

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	public void periodic() {
		intake.getInstance().setState().getIntakeState();
		passthrough.getInstance().setState().getPassthroughState();
		shooter.getInstance().setState().getShooterState();
		climber.getInstance().setState().getClimberState();

		Logger.recordOutput("Manager State", robotstate.getStateString());

		Intake.getInstance().periodic();
		Passthrough.getInstance().periodic();
		Shooter.getInstance().periodic();
		Climber.getInstance().periodic();
		Drive.getInstance().periodic();
	}
}
