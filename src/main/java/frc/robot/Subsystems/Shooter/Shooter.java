package frc.robot.Subsystems.Shooter;

import static frc.robot.Subsystems.Shooter.ShooterConstants.*;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {

	private static Shooter instance;
	protected ShooterStates state;
	protected SparkMax followerleftMotor;
	protected SparkMax leaderrightMotor;
	protected PIDController motorcontrollerright;
	private SparkMaxConfig followerConfig;
	protected SimpleMotorFeedforward feedforward;

	public static Shooter getInstance() {
		if (instance == null) {
			instance = new Shooter();
		}

		return instance;
	}

	public Shooter() {
		state = ShooterStates.IDLE;
		motorcontrollerright = WHEEL_PID.get();
		followerleftMotor = new SparkMax(LEFT_MOTOR_ID, MotorType.kBrushless);
		leaderrightMotor = new SparkMax(RIGHT_MOTOR_ID, MotorType.kBrushless);
		followerConfig = new SparkMaxConfig();
		followerConfig.follow(leaderrightMotor, true);
		followerleftMotor.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
		feedforward = WHEEL_FEEDFORWARD.get();
		leaderrightMotor.configure(new SparkMaxConfig().idleMode(IdleMode.kCoast), ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
		followerleftMotor.configure(new SparkMaxConfig().idleMode(IdleMode.kCoast), ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
	}

	public void setState(ShooterStates state) {
		this.state = state;
	}

	public boolean atSpeed() {
		return Math.abs(followerleftMotor.getEncoder().getVelocity() - (state.getShooterRPS().in(Units.RotationsPerSecond) * RPS_TO_RPM_CONVERSION_FACTOR)) < 60;
	}

	public void periodic() {
		SmartDashboard.putNumber("Shooter/Shooter RPM", followerleftMotor.getEncoder().getVelocity());
		SmartDashboard.putNumber("Shooter/Target Speed (RPM)", state.getShooterRPS().in(Units.RotationsPerSecond) * RPS_TO_RPM_CONVERSION_FACTOR);
		SmartDashboard.putData("Shooter/PID Controller", motorcontrollerright);

		feedforward.setKa(SmartDashboard.getNumber("kA", feedforward.getKa()));
		SmartDashboard.putNumber("kA", feedforward.getKa());
		feedforward.setKv(SmartDashboard.getNumber("kV", feedforward.getKv()));
		SmartDashboard.putNumber("kV", feedforward.getKv());
		feedforward.setKs(SmartDashboard.getNumber("kS", feedforward.getKs()));
		SmartDashboard.putNumber("kS", feedforward.getKs());

		if (state == ShooterStates.IDLE) {
			leaderrightMotor.set(0);
			//leaderrightMotor.set(IDLE_SPEED_OR_VOLTAGE);
		} else {
			leaderrightMotor.setVoltage(
				motorcontrollerright.calculate(followerleftMotor.getEncoder().getVelocity(), state.getShooterRPS().in(Units.RotationsPerSecond) * RPS_TO_RPM_CONVERSION_FACTOR) + feedforward.calculate(state.getShooterRPS().in(Units.RotationsPerSecond) * RPS_TO_RPM_CONVERSION_FACTOR)
			);
			SmartDashboard.putBoolean("Shooter/On", true);
		}
	}
}
