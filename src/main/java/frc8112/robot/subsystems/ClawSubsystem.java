package frc8112.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc8112.robot.Constants.ClawConstants;

public class ClawSubsystem extends SubsystemBase {
    private final CANSparkMax m_clawMotor = new CANSparkMax(ClawConstants.kClawMotorPort, MotorType.kBrushless);
    private final double m_motorSpeed = 0.05;

    public void open() {
        setSpeed(-m_motorSpeed);
    }

    public void close() {
        setSpeed(m_motorSpeed);
    }
    
    private void setSpeed(double speed) {
        if (isClosed().getAsBoolean()) {
            stop();
        } else m_clawMotor.set(speed);
    }

    public double getCurrent() {
        return m_clawMotor.getOutputCurrent();
    }

    public BooleanSupplier isClosed() {
        // return () -> getCurrent() >= ClawConstants.kCurrentLimit;
        return () -> false;
    }

    public void stop() {
        m_clawMotor.set(0);
    }

    public Command getOpenCommand() {
        return Commands.startEnd(
            () -> open(), 
            () -> stop(), 
            this
        );
    }

    public Command getCloseCommand() {
        return Commands.startEnd(
            () -> close(), 
            () -> stop(), 
            this
        );
    }
}
