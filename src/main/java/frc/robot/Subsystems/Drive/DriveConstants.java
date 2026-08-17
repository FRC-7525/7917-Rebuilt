package frc.robot.Subsystems.Drive;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;

public class DriveConstants {
	
	public static final double MAX_SPEED = 4.6;
	public static final Transform2d RED_TRANSFORM = new Transform2d(Translation2d.kZero, Rotation2d.fromDegrees(180));
}
