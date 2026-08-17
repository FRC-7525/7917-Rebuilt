// package frc.robot.Subsystems.Intake;

// import static edu.wpi.first.units.Units.KilogramSquareMeters;
// import static edu.wpi.first.units.Units.Meters;
// import static edu.wpi.first.units.Units.Radian;
// import static edu.wpi.first.units.Units.Radians;
// import static edu.wpi.first.units.Units.RotationsPerSecond;
// import static frc.robot.Subsystems.Intake.IntakeConstants.*;

// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.system.plant.DCMotor;
// import edu.wpi.first.math.system.plant.LinearSystemId;
// import edu.wpi.first.units.measure.Angle;
// import edu.wpi.first.units.measure.AngularVelocity;
// import edu.wpi.first.wpilibj.simulation.FlywheelSim;
// import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
// import frc.robot.GlobalConstants;

// public class IntakeIOSim implements IntakeIO { // blah blah blah sim here

// 	FlywheelSim wheelSim;
// 	SingleJointedArmSim pivotSim;
// 	PIDController pivotController;
// 	PIDController wheelController;

// 	public IntakeIOSim() {
// 		this.pivotController = PIVOT_CONTROLLER.get();
// 		this.wheelController = WHEEL_CONTROLLER.get();
// 		var flywheelPlant = LinearSystemId.createFlywheelSystem(DCMotor.getNEO(FLYWHEEL_MOTOR_COUNT), FLYWHEEL_MOI.in(KilogramSquareMeters), FLYWHEEL_GEARING);
// 		wheelSim = new FlywheelSim(flywheelPlant, DCMotor.getNEO(FLYWHEEL_MOTOR_COUNT));
// 		var pivotPlant = LinearSystemId.createSingleJointedArmSystem(DCMotor.getNEO(PIVOT_MOTOR_COUNT), PIVOT_MOI.in(KilogramSquareMeters), PIVOT_GEARING);
// 		pivotSim = new SingleJointedArmSim(pivotPlant, DCMotor.getNEO(PIVOT_MOTOR_COUNT), PIVOT_GEARING, PIVOT_ARM_LENGTH.in(Meters), PIVOT_MIN_ANGLE.in(Radians), PIVOT_MAX_ANGLE.in(Radians), false, PIVOT_MIN_ANGLE.in(Radians));
// 	}

// 	@Override
// 	public void setWheelSpeed(AngularVelocity wheelSpeed) {
// 		wheelSim.setInputVoltage(wheelController.calculate(wheelSim.getAngularVelocityRPM() / 60, wheelSpeed.in(RotationsPerSecond)));
// 		wheelSim.update(GlobalConstants.SIMULATION_PERIOD);
// 	}

// 	@Override
// 	public void setTargetAngle(Angle targetAngle) {
// 		pivotSim.setInputVoltage(pivotController.calculate(pivotSim.getAngleRads(), targetAngle.in(Radian)));
// 		pivotSim.update(GlobalConstants.SIMULATION_PERIOD);
// 	}

// 	@Override
// 	public Angle getCurrentAngle() {
// 		return Radians.of(pivotSim.getAngleRads());
// 	}

// 	@Override
// 	public AngularVelocity getCurrentWheelSpeed() {
// 		return wheelSim.getAngularVelocity();
// 	}

// 	@Override
// 	public double getWheelMotorCurrent() {
// 		return wheelSim.getCurrentDrawAmps();
// 	}

// 	@Override
// 	public double getPivotMotorCurrent() {
// 		return pivotSim.getCurrentDrawAmps();
// 	}

// 	@Override
// 	public double getIntakeAngle() {
// 		// TODO Auto-generated method stub
// 		return 0;
// 	}

// }
