package frc8112.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private final MotorControllerGroup m_armMotors = 
        new MotorControllerGroup(
            new CANSparkMax(0, MotorType.kBrushless), 
            new CANSparkMax(0, MotorType.kBrushless));
    
    public void extend() {
        if (m_armMotors.get() == 0) {
            m_armMotors.set(0.05);
        }
    }

    public void retract() {
        if (m_armMotors.get() == 0) {
            m_armMotors.set(-0.05);
        }
    }

    public void stop() {
        m_armMotors.set(0);
    }
}
