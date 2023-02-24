package frc8112.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc8112.robot.Constants.ClawConstants;

public class ClawSubsystem extends SubsystemBase {
    private final CANSparkMax m_clawMotor = new CANSparkMax(ClawConstants.kClawMotorPort, MotorType.kBrushless);
    private final double m_motorSpeed = 0.01;

    public void open() {
        if (m_clawMotor.get() == 0) {
            m_clawMotor.set(m_motorSpeed);
        }
    }

    public void close() {
        if (m_clawMotor.get() == 0) {
            m_clawMotor.set(-m_motorSpeed);
        }
    }

    public void stop() {
        m_clawMotor.set(0);
    }
}
