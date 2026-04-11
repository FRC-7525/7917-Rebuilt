package frc.robot.Subsystems.Drive;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;

public class DriveConstants {

	public static final double MAX_SPEED = 4.6;
	public static final LinearVelocity SLOW_SPEED = MetersPerSecond.of(MAX_SPEED * 0.2);
	public static final AngularVelocity MAX_ANGULAR_VELOCITY = RotationsPerSecond.of(3);
	public static final AngularVelocity SLOW_ANGULAR_VELOCITY = RotationsPerSecond.of(MAX_ANGULAR_VELOCITY.magnitude() * 0.2);
	public static final Transform2d RED_TRANSFORM = new Transform2d(Translation2d.kZero, Rotation2d.fromDegrees(180));
	public static final Pose2d BLUE_START = new Pose2d(3.3, 4, Rotation2d.kZero);
	public static final Pose2d RED_START = new Pose2d(13.3, 4, Rotation2d.k180deg);
}
