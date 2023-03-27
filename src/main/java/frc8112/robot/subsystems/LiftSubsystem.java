package frc8112.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LiftSubsystem extends SubsystemBase {
    private final CANSparkMax m_liftMotor = new CANSparkMax(9, MotorType.kBrushed);

    public void raise() {
        m_liftMotor.set(1);
    }

    public void lower() {
        m_liftMotor.set(-1);
    }

    public void stop() {
        m_liftMotor.set(0);
    }

    public Command getRaiseCommand() {
        return Commands.runEnd(
            () -> raise(),
            () -> stop(),
            this
        );
    }

    public Command getLowerCommand() {
        return Commands.runEnd(
            () -> lower(),
            () -> stop(),
            this
        );
    }
}
