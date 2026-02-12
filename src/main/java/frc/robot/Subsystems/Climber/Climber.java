package frc.robot.Subsystems.Climber;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber {

	private static Climber instance;
	protected ClimberStates state;
	protected SparkMax motor;
	protected PIDController motorcontroller;

	public static Climber getInstance() {
		if (instance == null) {
			instance = new Climber();
		}

		return instance;
	}

	public Climber() {
		state = ClimberStates.IDLE;
		motorcontroller = new PIDController(ClimberConstants.MOTOR_PROPORTION, ClimberConstants.MOTOR_INTEGRAL, ClimberConstants.MOTOR_DERIVATIVE);
		motor = new SparkMax(23, MotorType.kBrushless);
	}

	public void setState(ClimberStates state) {
		this.state = state;
	}

	public void periodic() {
		if (state == ClimberStates.IDLE) {
			motor.set(0);
		} else {
			motor.set(motorcontroller.calculate(motor.getEncoder().getPosition(), state.getPosition()));
		}

		SmartDashboard.putNumber("Climber rot", motor.getAbsoluteEncoder().getPosition());
	}
}
