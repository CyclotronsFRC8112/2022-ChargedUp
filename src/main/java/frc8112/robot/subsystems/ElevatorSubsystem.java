package frc8112.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc8112.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    private final CANSparkMax m_upMotor = new CANSparkMax(ElevatorConstants.kMotorUpPort, MotorType.kBrushless);
    private final CANSparkMax m_downMotor = new CANSparkMax(ElevatorConstants.kMotorDownPort, MotorType.kBrushless);

    public void setUpSpeed(double speed) {
        if (m_downMotor.get() == 0) {
            m_upMotor.set(speed);
        }
    }

    public void setDownSpeed(double speed) {
        if (m_upMotor.get() == 0) {
            m_downMotor.set(speed);
        }
    }

    public void stop() {
        m_upMotor.set(0);
        m_downMotor.set(0);
    }
}
