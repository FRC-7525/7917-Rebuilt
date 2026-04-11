// package frc.robot.Subsystems.Climber;

// import static frc.robot.Subsystems.Climber.ClimberConstants.GOINGOUT_SPEED;
// import static frc.robot.Subsystems.Climber.ClimberConstants.COMINGIN_SPEED;

// import com.revrobotics.sim.SparkMaxSim;
// import edu.wpi.first.math.system.plant.DCMotor;
// import edu.wpi.first.math.system.plant.LinearSystemId;
// import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.simulation.DCMotorSim;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// public class ClimberSim extends Climber {

// 	private static ClimberSim instance;
// 	private SparkMaxSim motorSim;
// 	private DCMotorSim climberSim;
// 	private XboxController controller;

// 	public static ClimberSim getInstance() {
// 		if (instance == null) {
// 			instance = new ClimberSim();
// 		}

// 		return instance;
// 	}

// 	public ClimberSim() {
// 		state = ClimberStates.IDLE;
// 		motorSim = new SparkMaxSim(motor, DCMotor.getNEO(1));
// 		climberSim = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getNEO(1), 1, 1), DCMotor.getNEO(1));
// 		controller = new XboxController(0);
// 	}

// 	public void setState(ClimberStates state) {
// 		this.state = state;
// 	}

// 	public void periodic() {
// 		if (state == ClimberStates.IDLE) {
// 			climberSim.setInputVoltage(0);
// 		} else if(state ==ClimberStates.GOINGOUT) {
// 			climberSim.setInputVoltage(GOINGOUT_SPEED);
// 		} else {
// 			climberSim.setInputVoltage(COMINGIN_SPEED);
// 		}

// 		if (controller.getYButtonPressed()) {
// 			state = ClimberStates.L1;
// 		} else if (controller.getXButtonPressed()) {
// 			state = ClimberStates.L2;
// 		} else if (controller.getAButtonPressed()) {
// 			state = ClimberStates.DEPLOY;
// 		} else if (controller.getBButtonPressed()) {
// 			state = ClimberStates.IDLE;
// 		}

// 		climberSim.update(0.02);
// 		motorSim.setPosition(climberSim.getAngularPositionRotations());
// 		motorSim.setVelocity(climberSim.getAngularVelocityRPM());
// 		SmartDashboard.putNumber("Climber sim position", climberSim.getAngularPositionRotations());
// 		SmartDashboard.putNumber("Climber sim setpoint", state.getPosition());
// 		SmartDashboard.putString("Climber sim state", state.getStateString());
// 	}
// }
