package frc.robot.Subsystems.Intake;
import com.ctre.phoenix6.sim.TalonFXSimState.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static frc.robot.Subsystems.Intake.IntakeConstants.*;


public class Intake {

    protected IntakeStates state;
    protected SparkMax rollerMotor;
    protected SparkMax pivotMotor;
    protected PIDController motorControllerForRollerMotor;
    protected PIDController motorControllerForPivotMotor;
    protected SimpleMotorFeedforward feedforward;

    public Intake() {
        state = IntakeStates.IDLE;
        motorControllerForRollerMotor = WHEEL_PID.get();
        motorControllerForPivotMotor = WHEEL_PID.get();
        rollerMotor = new SparkMax(ROLLER_MOTOR_ID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        pivotMotor = new SparkMax(PIVOT_MOTOR_ID, MotorType.kdis);

        

        
    


    }

    public void setState(IntakeStates state) {
		this.state = state;
	}

	public void periodic() {
		if (state == IntakeStates.IDLE) {
			rollerMotor.set(0);
            pivotMotor.set(0);
		} else {
			rollerMotor.set(motorControllerForRollerMotor.calculate(rollerMotor.getAbsoluteEncoder().getVelocity(), state.getWheelSpeed().in(RotationsPerSecond)));
            pivotMotor.set(motorControllerForPivotMotor.calculate(pivotMotor.getAbsoluteEncoder().getPosition(), state.getAngle().in(Degree)));
	}
}

}