package frc.robot.AitanAndJamesAreTheBestForSureAutos;

import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Drive.Drive;

public class AutoBuilderStuff extends SubsystemBase {

	public static RobotConfig config;

	public static void setConfig() {
		try {
			config = RobotConfig.fromGUISettings();
		} catch (Exception e) {
			e.printStackTrace();
		}
		com.pathplanner.lib.auto.AutoBuilder.configure(
			Drive.getInstance()::getPose, // Robot pose supplier
			Drive.getInstance()::setPose, // Method to reset odometry (will be called if your auto has a starting pose)
			Drive.getInstance()::getRobotRelativeSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
			(speeds, feedforwards) -> Drive.getInstance().drive(speeds), // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds. Also optionally outputs individual module feedforwards
			new PPHolonomicDriveController(
				// PPHolonomicController is the built in path following controller for holonomic drive trains
				new PIDConstants(5.0, 0.0, 0.0),
				new PIDConstants(0, 0.0, 0.0)
			),
			config, // The robot configuration
			() -> {
				var alliance = DriverStation.getAlliance();
				if (alliance.isPresent()) {
					return alliance.get() == DriverStation.Alliance.Red;
				}
				return false;
			},
			Drive.getInstance() // Reference to this subsystem to set requirements
		);
	}
}
