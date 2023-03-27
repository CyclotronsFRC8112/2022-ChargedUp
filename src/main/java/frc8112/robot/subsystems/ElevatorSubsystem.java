package frc8112.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc8112.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    private final CANSparkMax m_upMotor = new CANSparkMax(ElevatorConstants.kMotorUpPort, MotorType.kBrushless);
    private final CANSparkMax m_downMotor = new CANSparkMax(ElevatorConstants.kMotorDownPort, MotorType.kBrushless);

    private final DigitalInput m_topLimitSwitch = new DigitalInput(0);

    public void setUpSpeed(double speed) {
        if (m_topLimitSwitch.get()) {
            stop();
        } else if (m_downMotor.get() == 0) {
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

    public Command getUpCommand() {
        return new FunctionalCommand(
            () -> stop(),
            () -> setUpSpeed(0.25), 
            interrupted -> stop(), 
            () -> m_topLimitSwitch.get(), 
            this);
    }

    public Command getDownCommand() {
        return Commands.startEnd(
                () -> setDownSpeed(0.25),
                () -> stop(),
                this);
    }
}
