// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc8112.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class ClawConstants {
    public static final int kClawMotorPort = 7;
    public static final double kCurrentLimit = 0.25;
  }

  public static class DriveConstants {
    public static final int kLeftMotor1Port = 1;
    public static final int kLeftMotor2Port = 6;

    public static final int kRightMotor1Port = 5;
    public static final int kRightMotor2Port = 2;

    public static final CANSparkMax lM1 = new CANSparkMax(kLeftMotor1Port, MotorType.kBrushless);
    public static final CANSparkMax lM2 = new CANSparkMax(kLeftMotor2Port, MotorType.kBrushless);
    public static final CANSparkMax rM1 = new CANSparkMax(kRightMotor1Port, MotorType.kBrushless);
    public static final CANSparkMax rM2 = new CANSparkMax(kRightMotor2Port, MotorType.kBrushless);


    public static final double ksVolts = 0.077;
    public static final double kvVoltSecondsPerMeter = 0.128;
    public static final double kaVoltSecondsSquaredPerMeter = 0.007;

    public static final double kPDriveVel = 0.12;

    public static final double kTrackwidthMeters = 0.69;
    public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);
    
    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecondSquared = 1;

    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
  }

  public static class ElevatorConstants {
    public static final int kMotorUpPort = 9;
    public static final int kMotorDownPort = 10;
  }

  public static class ArmConstants {
    public static final int kWenchMotorPort = 8;

    public static final int kArmMotor1Port = 3;
    public static final int kArmMotor2Port = 4;
  }
}
