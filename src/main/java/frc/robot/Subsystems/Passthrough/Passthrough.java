package frc.robot.Subsystems.Passthrough;

import static frc.robot.Subsystems.Passthrough.PassthroughConstants.*;
import static frc.robot.Subsystems.Passthrough.PassthroughStates.*;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Passthrough {

	private static Passthrough instance;

	protected PassthroughStates state;
	protected SparkMax mainmotor;
	protected PIDController mainmotorcontroller;

	public static Passthrough getInstance() {
		if (instance == null) {
			instance = new Passthrough();
		}

		return instance;
	}

	public Passthrough() {
		state = IDLE;
		mainmotorcontroller = new PIDController(MOTOR_PROPORTION, MOTOR_INTEGRAL, MOTOR_DERIVATIVE);
		mainmotor = new SparkMax(MAIN_MOTOR_ID, MotorType.kBrushless);
		mainmotor.configure(new SparkMaxConfig().idleMode(IdleMode.kCoast), ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
	}

	public void setState(PassthroughStates state) {
		this.state = state;
	}

	public void periodic() {
		SmartDashboard.putNumber("Passthrough/Pass RPM", mainmotor.getEncoder().getVelocity());
		SmartDashboard.putNumber("Passthrough/Target Speed (RPM)", state.getSpeed().in(Units.RadiansPerSecond) * RPS_TO_RPM);
		SmartDashboard.putData("Passthrough/PID Controller", mainmotorcontroller);

		if (state == IDLE) {
			mainmotor.set(0);
			//mainmotor.set(SPEED);
		} else if (state == PASS) {
			//mainmotor.set(mainmotorcontroller.calculate(mainmotor.getEncoder().getVelocity(), PASSTHROUGH_MAINMOTOR_RPS * RPS_TO_RPM));
			mainmotor.set(SPEED);
			SmartDashboard.putBoolean("Passthrough/On", true);
		}
	}
}
