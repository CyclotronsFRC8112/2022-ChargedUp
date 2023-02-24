// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc8112.robot;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc8112.robot.Constants.OperatorConstants;
import frc8112.robot.subsystems.ArmSubsystem;
import frc8112.robot.subsystems.ClawSubsystem;
import frc8112.robot.subsystems.DriveSubsystem;
import frc8112.robot.subsystems.ElevatorSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
  private final ClawSubsystem m_clawSubsystem = new ClawSubsystem();
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    m_driveSubsystem.setDefaultCommand(
      Commands.run(
        () -> 
          m_driveSubsystem.arcadeDrive(-m_driverController.getLeftY(), -m_driverController.getLeftX()), 
      m_driveSubsystem)
      );
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Elevator Up
    m_driverController.pov(0).whileTrue(
      Commands.runEnd(
        () -> m_elevatorSubsystem.setUpSpeed(0.25), 
        () -> m_elevatorSubsystem.stop(), 
        m_elevatorSubsystem
      )
    );
    // Elevator Down
    m_driverController.pov(180).whileTrue(
      Commands.runEnd(
        () -> m_elevatorSubsystem.setDownSpeed(0.25), 
        () -> m_elevatorSubsystem.stop(), 
        m_elevatorSubsystem
      )
    );
    // Claw Open
    m_driverController.pov(270).whileTrue(
      Commands.runEnd(
        () -> m_clawSubsystem.open(), 
        () -> m_clawSubsystem.stop(), 
        m_clawSubsystem
      )
    );
    // Claw Close
    m_driverController.pov(90).whileTrue(
      Commands.runEnd(
        () -> m_clawSubsystem.close(), 
        () -> m_clawSubsystem.stop(), 
        m_clawSubsystem
      )
    );
    // Arm Extend
    m_driverController.leftTrigger().whileTrue(
      Commands.runEnd(
        () -> m_armSubsystem.extend(),
        () -> m_armSubsystem.stop(),
        m_armSubsystem
      )
    );
    // Arm Retract
    m_driverController.rightTrigger().whileTrue(
      Commands.runEnd(
        () -> m_armSubsystem.retract(), 
        () -> m_armSubsystem.stop(), 
        m_armSubsystem
      )
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new SequentialCommandGroup();
  }
}
