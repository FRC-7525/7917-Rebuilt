package frc.robot.Subsystems.Intake;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public interface IntakeIO {
	public void setWheelSpeed(AngularVelocity wheelSpeed);

	public void setTargetAngle(Angle targetAngle);

	public Angle getCurrentAngle();

	public AngularVelocity getCurrentWheelSpeed();

	public double getWheelMotorCurrent();

	public double getPivotMotorCurrent();

	public double getIntakeAngle();

	public void setIntakeOn(boolean intakeOn);
}
