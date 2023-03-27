package frc8112.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc8112.robot.Constants.ArmConstants;
;
public class ArmSubsystem extends SubsystemBase {
    private final CANSparkMax m_wenchMotor = new CANSparkMax(ArmConstants.kWenchMotorPort, MotorType.kBrushless);
    private final MotorControllerGroup m_armMotors = new MotorControllerGroup(
            new CANSparkMax(ArmConstants.kArmMotor1Port, MotorType.kBrushless),
            new CANSparkMax(ArmConstants.kArmMotor2Port, MotorType.kBrushless));

    public void extend() {
        m_armMotors.set(0.25);
    }

    public void retract() {
        m_armMotors.set(-0.25);
    }

    public void stop() {
        m_armMotors.set(0);
        m_wenchMotor.set(0);
    }

    public void raise() {
        m_wenchMotor.set(0.2);
    }

    public void lower() {
        m_wenchMotor.set(-0.2);
    }

    public Command getExtendCommand() {
        return Commands.startEnd(
                () -> extend(),
                () -> stop(),
                this);
    }

    public Command getRetractCommand() {
        return Commands.startEnd(
                () -> retract(),
                () -> stop(),
                this);
    }

    public Command getRaiseCommand() {
        return Commands.startEnd(
                () -> raise(),
                () -> stop(),
                this);
    }

    public Command getLowerCommand() {
        return Commands.startEnd(
                () -> lower(),
                () -> stop(),
                this);
    }
}
