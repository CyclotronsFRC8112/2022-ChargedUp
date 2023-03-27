// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc8112.robot;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc8112.robot.Constants.OperatorConstants;
import frc8112.robot.subsystems.DriveSubsystem;
import frc8112.robot.subsystems.LiftSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final LiftSubsystem m_liftSubsystem = new LiftSubsystem();
  private final autoBalance m_autoBalance = new autoBalance();

  private final CommandXboxController m_driverController = new CommandXboxController(
      OperatorConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // m_driveSubsystem.setMaxOutput(0.3);
    m_driveSubsystem.setDefaultCommand(
        Commands.run(
            () -> m_driveSubsystem.arcadeDrive(-m_driverController.getLeftY(), -m_driverController.getLeftX()),
            m_driveSubsystem));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Lift Up
    m_driverController.y().whileTrue(
        m_liftSubsystem.getRaiseCommand());
    // Lift Down
    m_driverController.a().whileTrue(
        m_liftSubsystem.getLowerCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // PathConstraints constraints = new PathConstraints(10, 9);
    // PathPlannerTrajectory path = PathPlanner.loadPath("New New New Path", constraints);

    // return m_driveSubsystem.followTrajectoryCommand(path, true);
    int autonomousMode = 0;

    switch(autonomousMode) {
      case 0:
        return Commands.run(() -> m_driveSubsystem.set(m_autoBalance.autoBalanceRoutine(), m_autoBalance.autoBalanceRoutine()), m_driveSubsystem);
      case 1:
        return m_driveSubsystem.getDriveCommand(0.45).withTimeout(2);
    }

    return null;
    /*return new SequentialCommandGroup(
      m_driveSubsystem.getDriveCommand(0.45)-
        .withTimeout(1.5),
      new WaitCommand(2),
      m_driveSubsystem.getDriveCommand(-0.45)
        .withTimeout(1)
    );*/
  }
}
