package frc.robot.Subsystems.AutoAlign;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public final class PosePair {

	private Pose2d redPose;
	private Pose2d bluePose;

	PosePair(Pose2d redPose, Pose2d bluePose) {
		this.redPose = redPose;
		this.bluePose = bluePose;
	}

	public static PosePair of(Pose2d redPose, Pose2d bluePose) {
		return new PosePair(redPose, bluePose);
	}

	public Pose2d getRedPose() {
		return redPose;
	}

	public Pose2d getBluePose() {
		return bluePose;
	}

	public Translation2d getTranslation() {
		return new Translation2d();
	}

	public Rotation2d getRotation() {
		return new Rotation2d();
	}
}
