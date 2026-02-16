package frc.robot.IntakeStates;

import static edu.wpi.first.units.Units.Degree;

import edu.wpi.first.units.measure.Angle;

public class IntakeConstants {
    public static final double INTAKE_SPEED = 0.5;
    public static final double IDLE_SPEED = 0;
    public static final double PASSING_SPEED = -0.5;
    public static final Angle INTAKE_POSITION = Degree.of(90);
    public static final Angle IDLE_POSITION = Degree.of(0);
    public static final Angle PASSING_POSITION = Degree.of(0);
}