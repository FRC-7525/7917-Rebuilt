package frc.robot.Subsystems.Intake;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static frc.robot.Subsystems.Intake.IntakeConstants.*;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Manager.Manager;

public class IntakeIOReal implements IntakeIO {

	private boolean intakeON;
	private boolean intakeReverse;

	private SparkMax pivotMotor;
	private SparkMax wheelMotor;
	private XboxController operatorController;
	private XboxController driverController;

	public IntakeIOReal() {
		this.pivotMotor = new SparkMax(PIVOT_MOTOR_ID, MotorType.kBrushless);
		this.pivotMotor.configure(new SparkMaxConfig().smartCurrentLimit(30), ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
		this.wheelMotor = new SparkMax(WHEEL_MOTOR_ID, MotorType.kBrushless);
		wheelMotor.configure(new SparkMaxConfig().idleMode(IdleMode.kCoast), ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
		driverController = new XboxController(0);
		operatorController = new XboxController(1);
	}

	@Override
	public void setWheelSpeed(AngularVelocity wheelSpeed) {
		if (operatorController.getRightBumperButtonPressed() || driverController.getLeftBumperButtonPressed()) {
			if (intakeON == true) {
				intakeON = false;
				SmartDashboard.putBoolean("Intake/intakeOn", intakeON);
			} else {
				intakeON = true;
				SmartDashboard.putBoolean("Intake/intakeOn", intakeON);
			}
		}
		if (intakeON) {
			wheelMotor.set(1);
		} else if (intakeReverse) {
			wheelMotor.set(-1);
		} else {
			wheelMotor.set(0);
		}

		if (operatorController.getXButton() && Manager.getInstance().isIntakeOut()) {
			intakeReverse = true;
		} else {
			intakeReverse = false;
		}
	}

	@Override
	public void setTargetAngle(Angle targetAngle) {
		if (targetAngle.in(Degrees) == 0) {
			pivotMotor.set(0.3);
		} else {
			pivotMotor.set(-0.1);
		}
	}

	@Override
	public Angle getCurrentAngle() {
		return Rotations.of(pivotMotor.getEncoder().getPosition());
	}

	@Override
	public AngularVelocity getCurrentWheelSpeed() {
		return RotationsPerSecond.of(wheelMotor.getEncoder().getVelocity() / 60);
	}

	@Override
	public double getWheelMotorCurrent() {
		return wheelMotor.getOutputCurrent();
	}

	@Override
	public double getPivotMotorCurrent() {
		return pivotMotor.getOutputCurrent();
	}

	@Override
	public double getIntakeAngle() {
		return getCurrentAngle().in(Degrees);
	}

	@Override
	public void setIntakeOn(boolean intakeOn) {
		this.intakeON = intakeOn;
	}
}
