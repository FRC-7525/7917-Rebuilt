package frc.robot.Subsystems.Intake;

import static frc.robot.Subsystems.Intake.IntakeConstants.*;

import com.revrobotics.sim.SparkMaxSim;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakelOSim extends Intake {

	private SparkMaxSim leftSim;
	private SparkMaxSim rightSim;
	private FlywheelSim bigWheel;

	public IntakelOSim () {
		leftSim = new SparkMaxSim(followerleftMotor, DCMotor.getNEO(leftSimMotor));
		rightSim = new SparkMaxSim(leaderrightMotor, DCMotor.getNEO(rightSimMotor));
		bigWheel = new FlywheelSim(LinearSystemId.createFlywheelSystem(DCMotor.getNEO(leftSimMotor + rightSimMotor), JKgMetersSquared, gearing), DCMotor.getNEO(leftSimMotor + rightSimMotor));
	}

	public void setState(IntakelOSim  state) {
		this.state = state;
	}

	public void periodic() {
		// Rand sim annoying stuffs
		leftSim.setVelocity(Units.radiansToRotations(bigWheel.getAngularVelocityRadPerSec()));
		rightSim.setVelocity(Units.radiansToRotations(bigWheel.getAngularVelocityRadPerSec()));
		leftSim.setBusVoltage(busVoltage);
		rightSim.setBusVoltage(busVoltage);
		bigWheel.update(bigWheelUpdate);

		// Logging
		SmartDashboard.putNumber("Shooter/Current Speed (RPM)", bigWheel.getAngularVelocityRPM());
		SmartDashboard.putNumber("Shooter/Target Speed (RPM)", state.getShooterRPS() * RPStoRPMConversionFactor);
		SmartDashboard.putData("Shooter/PID Controller", motorcontrollerright);

		feedforward.setKa(SmartDashboard.getNumber("kA", kADefaultValueSim));
		SmartDashboard.putNumber("kA", feedforward.getKa());
		feedforward.setKv(SmartDashboard.getNumber("kV", kVDefaultValueSim));
		SmartDashboard.putNumber("kV", feedforward.getKv());
		feedforward.setKs(SmartDashboard.getNumber("kS", kSDefaultValueSim));
		SmartDashboard.putNumber("kS", feedforward.getKs());

		if (state == IntakelOSim.IDLE) {
			bigWheel.setInputVoltage(IDLESpeedOrVoltage);
		} else {
			bigWheel.setInputVoltage(BigWheelVoltageInitalCalcFactor * motorcontrollerright.calculate(bigWheel.getAngularVelocityRPM(), state.getShooterRPS() * RPStoRPMConversionFactor) + feedforward.calculate(state.getShooterRPS() * RPStoRPMConversionFactor));
		}
	}
}