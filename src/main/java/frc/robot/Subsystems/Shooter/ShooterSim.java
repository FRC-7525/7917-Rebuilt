package frc.robot.Subsystems.Shooter;

import static frc.robot.Subsystems.Shooter.ShooterConstants.*;

import com.revrobotics.sim.SparkMaxSim;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.AngularVelocityUnit;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterSim extends Shooter {

	public static ShooterSim instance;
	private SparkMaxSim leftSim;
	private SparkMaxSim rightSim;
	private FlywheelSim bigWheel;
	private AngularVelocityUnit angularVelocityUnit;

	public static ShooterSim getInstance() {
		if (instance == null) {
			instance = new ShooterSim();
		}

		return instance;
	}

	public ShooterSim() {
		leftSim = new SparkMaxSim(followerleftMotor, DCMotor.getNEO(LEFT_SIM_MOTOR));
		rightSim = new SparkMaxSim(leaderrightMotor, DCMotor.getNEO(RIGHT_SIM_MOTOR));
		bigWheel = new FlywheelSim(LinearSystemId.createFlywheelSystem(DCMotor.getNEO(LEFT_SIM_MOTOR + RIGHT_SIM_MOTOR), JKG_METERS_SQUARED, GEARING), DCMotor.getNEO(LEFT_SIM_MOTOR + RIGHT_SIM_MOTOR));
		angularVelocityUnit = edu.wpi.first.units.Units.RadiansPerSecond;
	}

	public void setState(ShooterStates state) {
		this.state = state;
	}

	public void periodic() {
		// Rand sim annoying stuffs
		leftSim.setVelocity(Units.radiansToRotations(bigWheel.getAngularVelocityRadPerSec()));
		rightSim.setVelocity(Units.radiansToRotations(bigWheel.getAngularVelocityRadPerSec()));
		leftSim.setBusVoltage(BUS_VOLTAGE);
		rightSim.setBusVoltage(BUS_VOLTAGE);
		bigWheel.update(BIG_WHEEL_UPDATE);

		// Logging
		SmartDashboard.putNumber("Shooter/Current Speed (RPM)", bigWheel.getAngularVelocityRadPerSec() * RPS_TO_RPM_CONVERSION_FACTOR);
		SmartDashboard.putNumber("Shooter/Target Speed (RPM)", state.getShooterRPS().in(angularVelocityUnit) * RPS_TO_RPM_CONVERSION_FACTOR);
		SmartDashboard.putData("Shooter/PID Controller", motorcontrollerright);

		feedforward.setKa(SmartDashboard.getNumber("kA", feedforward.getKa()));
		SmartDashboard.putNumber("kA", feedforward.getKa());
		feedforward.setKv(SmartDashboard.getNumber("kV", feedforward.getKv()));
		SmartDashboard.putNumber("kV", feedforward.getKv());
		feedforward.setKs(SmartDashboard.getNumber("kS", feedforward.getKs()));
		SmartDashboard.putNumber("kS", feedforward.getKs());

		if (state == ShooterStates.IDLE) {
			bigWheel.setInputVoltage(IDLE_SPEED_OR_VOLTAGE);
		} else {
			bigWheel.setInputVoltage(
				BIG_WHEEL_VOLTAGE_INITIAL_CALC_FACTOR * motorcontrollerright.calculate(bigWheel.getAngularVelocityRPM(), state.getShooterRPS().in(angularVelocityUnit) * RPS_TO_RPM_CONVERSION_FACTOR) + feedforward.calculate(state.getShooterRPS().in(angularVelocityUnit) * RPS_TO_RPM_CONVERSION_FACTOR)
			);
		}
	}
}
