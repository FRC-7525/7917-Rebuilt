

package frc.robot.Subsystems.Intake;


import edu.wpi.first.units.measure.Angle;



public enum IntakeStates {
    INTAKING( 2,0.5),
    IDLE(1, 1),
    PASSING(2, 5);

    public final double speed;
    public final Angle position;
    void Intakestates(double speed, Angle position) {
        this.speed = speed;
        this.position = position;
    }

    public double getSpeed() {
        return speed;
    }

    public Angle getPosition() {
        return position;
    }
} 