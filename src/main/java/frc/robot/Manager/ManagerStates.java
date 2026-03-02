// package frc.robot.Manager;

// import frc.robot.Subsystems.AutoAlign.AutoAlign;
// import frc.robot.Subsystems.Climber.Climber;
// import frc.robot.Subsystems.Intake.Intake;
// import frc.robot.Subsystems.Passthrough.Passthrough;
// import frc.robot.Subsystems.Shooter.Shooter;

// public enum ManagerStates {
//     CLIMBPREP(Climber.CLIMBPREP, Intake.IDLE, Passthrough.IDLE, Shooter.IDLE, "CLIMBPREP"),
//     CLIMBLV1(Climber.CLIMBLV1, Intake.IDLE, Passthrough.IDLE, Shooter.IDLE, "CLIMBLV1"),
//     CLIMBLV2(Climber.CLIMBLV2, Intake.IDLE, Passthrough.IDLE, Shooter.IDLE, "CLIMBLV2"),
//     IDLE(Climber.IDLE, Intake.IDLE, Passthrough.IDLE, Shooter.IDLE, "IDLE"),
//     INTAKING(Climber.IDLE, Intake.INTAKING, Passthrough.IDLE, Shooter.IDLE, "INTAKING"),
//     DYNAMICSHOT(Climber.IDLE, Intake.IDLE, Passthrough.PASS, Shooter.DYNAMICSHOT, "DYNAMICSHOT"),
//     LONGSHOT(Climber.IDLE, Intake.IDLE, Passthrough.PASS, Shooter.LONGSHOT, "LONGSHOT"),
//     FIXEDSHOT(Climber.IDLE, Intake.IDLE, Passthrough.PASS, Shooter.FIXEDSHOT, "FIXEDSHOT"),

//     Climber climber;
//     Intake intake;
//     Passthrough passthrough;
//     Shooter shooter;
//     AutoAlign autoalign;
//     String stateString;

//     ManagerStates(Climber climber, Intake intake, Passthrough passthrough, Shooter shooter, AutoAlign autoalign, String stateString) {
//         this.climber = climber;
//         this.intake = intake;
//         this.passthrough = passthrough;
//         this.shooter = shooter;
//         this.autoalign = autoalign;
//         this.stateString = stateString;
//         this.autoalign = autoalign;

//     }

//     public Climber getClimberState() {
//         return climber;
//     }

//     public Intake getIntakeState() {
//         return intake;
//     }

//     public Passthrough getPassthroughState() {
//         return passthrough;
//     }

//     public Shooter getShooterState() {
//         return shooter;
//     }

//     public AutoAlign getAutoAlignState() {
//         return autoalign;
//     }

//     public String getStateString() {
//         return stateString;
//     }

// }
