package frc.robot.Subsystems.Intake;

import static edu.wpi.first.units.Units.RotationsPerSecond;
import static frc.robot.Subsystems.Intake.IntakeConstants.*;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import org.littletonrobotics.junction.Logger;

public class IntakeIONeo implements IntakeIONeo {

	SparkMax Wheelmotor;
	SparkMax Pivotmotor;
	PIDController wheelController;
	PIDController pivotcController;
	AngularVelocity targetspeed;
	Angle targetposition;

	public IntakeIONeo() {
		Wheelmotor = new SparkMax(WHEEL_MOTOR_DEVICE_ID, MotorType.kBrushless);
		Pivotmotor = new SparkMax(PIVOT_MOTOR_DEVICEID, MotorType.kBrushless);

		wheelController = WHEEL_CONTROLLER.get();
		pivotcController = PIVOT_CONTROLLER.get();
	}

	@Override
	public void setWheelSpeed(AngularVelocity wheelSpeed) {
		targetspeed = wheelSpeed;

		Wheelmotor.set(
			wheelController.calculate(
				Wheelmotor.getAbsoluteEncoder().getVelocity() / 60,
				wheelSpeed.in(RotationsPerSecond)
			)
		);
	}

	@Override
	public void setPosition(Angle position) {
		targetposition = position;

		Pivotmotor.set(pivotcController.calculate(Pivotmotor.getAbsoluteEncoder().getPosition()));
	}

	@Override
	public void logData() {
		Logger.recordOutput("Intake/Wheel speed", Wheelmotor.getAbsoluteEncoder().getVelocity());
		Logger.recordOutput("Intake/Pivot position", Pivotmotor.getAbsoluteEncoder().getPosition());
		Logger.recordOutput("Intake/target speed", targetspeed);
		Logger.recordOutput("Intake/target position", targetposition);
	}
}