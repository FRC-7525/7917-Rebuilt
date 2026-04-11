package frc.robot.Subsystems.AutoAlign;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public enum AutoAlignStates {
	OFF(new PosePair(new Pose2d(1, 1, new Rotation2d()), new Pose2d(1, 1, new Rotation2d())), "OFF"),
	toOutpost(new PosePair(new Pose2d(15.963, 7.376, new Rotation2d(Math.toRadians(0))), new Pose2d(0.609, 0.630, new Rotation2d(Math.toRadians(180)))), "toOutpost"),
	toShootRangeHub(new PosePair(new Pose2d(13.101, 4.030, new Rotation2d(Math.toRadians(180))), new Pose2d(3.428, 4.019, new Rotation2d(Math.toRadians(0)))), "toShootRangeHub"),
	toTower(new PosePair(new Pose2d(14.887, 3.320, new Rotation2d(Math.toRadians(0))), new Pose2d(1.664, 2.760, new Rotation2d(Math.toRadians(180)))), "toTower"),
	toDepot(new PosePair(new Pose2d(15.231, 2.093, new Rotation2d(Math.toRadians(0))), new Pose2d(1.277, 5.912, new Rotation2d(Math.toRadians(180)))), "toDepot");

	private PosePair location;
	private String stringName;

	private AutoAlignStates(PosePair location, String stringName) {
		this.location = location;
		this.stringName = stringName;
	}

	public PosePair getLocation() {
		return location;
	}

	public String getstringName() {
		return stringName;
	}
}
