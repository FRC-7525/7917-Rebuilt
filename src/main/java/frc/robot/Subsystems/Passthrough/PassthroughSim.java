package frc.robot.Subsystems.Passthrough;

import static frc.robot.Subsystems.Passthrough.PassthroughConstants.*;
import static frc.robot.Subsystems.Passthrough.PassthroughStates.*;

import com.revrobotics.sim.SparkMaxSim;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PassthroughSim extends Passthrough {

	private SparkMaxSim mainmotorSim;
	private FlywheelSim mainWheel;

	public PassthroughSim() {
		mainmotorSim = new SparkMaxSim(mainmotor, DCMotor.getNEO(NUM_MOTORS));
		mainWheel = new FlywheelSim(LinearSystemId.createFlywheelSystem(DCMotor.getNEO(NUM_MOTORS), JKG_METERS_SQUARED, GEARING), DCMotor.getNEO(NUM_MOTORS));
	}

	public void setState(PassthroughStates state) {
		this.state = state;
	}

	public void periodic() {
		// Rand sim annoying stuffs
		mainmotorSim.setVelocity(Units.radiansToRotations(mainWheel.getAngularVelocityRadPerSec()));
		mainmotorSim.setBusVoltage(CAN_BUS_VOLTAGE);
		mainWheel.update(DT_SECONDS);

		// Logging

		SmartDashboard.putNumber("Passthrough/Current MainSpeed (RPM)", mainWheel.getAngularVelocityRPM());
		SmartDashboard.putNumber("Passthrough/Target MainSpeed (RPM)", PASSTHROUGH_MAINMOTOR_RPS * RPS_TO_RPM);
		SmartDashboard.putData("Passthrough/PID Controller main", mainmotorcontroller);

		if (state == IDLE) {
			mainWheel.setInputVoltage(SET_INPUT_VOLTS);
		} else if (state == PASS) {
			mainWheel.setInputVoltage(PASS_VOLTAGE * mainmotorcontroller.calculate(mainWheel.getAngularVelocityRPM(), PASSTHROUGH_MAINMOTOR_RPS * RPS_TO_RPM));
		}
	}
}
