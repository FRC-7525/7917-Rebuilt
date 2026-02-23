package frc.robot.Subsystems.Passthrough;

import static frc.robot.Subsystems.Passthrough.PassthroughConstants.*;
import static frc.robot.Subsystems.Passthrough.PassthroughStates.*;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.controller.PIDController;

public class Passthrough {

	protected PassthroughStates state;
	protected SparkMax mainmotor;
	protected PIDController mainmotorcontroller;
	protected SparkMax backmotor;
	protected PIDController backmotorcontroller;

	public Passthrough() {
		state = IDLE;
		mainmotorcontroller = new PIDController(MOTOR_PROPORTION, MOTOR_INTEGRAL, MOTOR_DERIVATIVE);
		mainmotor = new SparkMax(MAIN_MOTOR_ID, MotorType.kBrushless);
		backmotorcontroller = new PIDController(MOTOR_PROPORTION, MOTOR_INTEGRAL, MOTOR_DERIVATIVE);
		backmotor = new SparkMax(BACK_MOTOR_ID, MotorType.kBrushless);
	}

	public void setState(PassthroughStates state) {
		this.state = state;
	}

	public void periodic() {
		if (state == IDLE) {
			mainmotor.set(SPEED);
			backmotor.set(SPEED);
		} else if (state == PASS) {
			mainmotor.set(mainmotorcontroller.calculate(mainmotor.getEncoder().getVelocity(), PASSTHROUGH_MAINMOTOR_RPS * RPS_TO_RPM));
			backmotor.set(backmotorcontroller.calculate(backmotor.getEncoder().getVelocity(), PASSTHROUGH_BACKMOTOR_RPS * RPS_TO_RPM));
		}
	}
}
