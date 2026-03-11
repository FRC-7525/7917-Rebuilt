package frc.robot.AutoManager;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.Manager.Manager;
import frc.robot.Manager.ManagerStates;
import org.littletonrobotics.junction.Logger;


import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;


public class AutoManager {

	private final SendableChooser<Command> autoChooser;

	public AutoManager() {
		//Register Commands
		NamedCommands.registerCommand("Intake Fuel", AutoCommands.intake());
		NamedCommands.registerCommand("Pre-Shoot", AutoCommands.startWindingUp());
		NamedCommands.registerCommand("Intake Neutral", AutoCommands.windAndIntake());
		NamedCommands.registerCommand("Climb", AutoCommands.climb());
		NamedCommands.registerCommand("Idle", AutoCommands.returnToIdle());
		NamedCommands.registerCommand("Shoot", new ShootCommand());
		//NamedCommands.registerCommand("DRIVE !", AutoCommands.DriveForward.driveForward());
		//NamedCommands.registerCommand("Sideways to Right Face", AutoCommands.SidewaysToRightFace.sidewaysToRightFace());

		//Autochooser!
		autoChooser = new SendableChooser<>();
		autoChooser.setDefaultOption("Nothing", new PrintCommand("Nothing"));

		//Shoot PreLoad -> Intake Neutral -> Shoot -> Climb (BA)
		autoChooser.addOption("PreLoad Neutral Climb", new PathPlannerAuto("PreLoadShootNClimbBA"));

		//Shoot PreLoad -> Outpost -> Shoot -> Climb (BA)
		autoChooser.addOption("Preload Out Climb", new PathPlannerAuto("RShootOutClimbBA"));

		// Shoot Preloaded Fuel (BA)
		autoChooser.addOption("Preload & Moveout", new PathPlannerAuto("MShootMoveOutBA"));
		autoChooser.addOption("Preload & Climb", new PathPlannerAuto("MShootClimbBA"));


		// Shoot Preloaded Fuel (RA)
		autoChooser.addOption("Preload & Moveout", new PathPlannerAuto("MShootMoveOutRA"));
		autoChooser.addOption("Preload & Climb", new PathPlannerAuto("MShootClimbRA"));


		SmartDashboard.putData("Auto Chooser", autoChooser);
	}

	public Command getSelectedCommand() {
		if (autoChooser.getSelected() == null) {
			return new PrintCommand("No auto selected");
		}
		return autoChooser.getSelected();
	}
}