// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;

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

  public static class DriveConstants {
    public static final int kFrontLeftMotorPort = 1;
    public static final int kRearLeftMotorPort = 2;
    public static final int kFrontRightMotorPort = 3;
    public static final int kRearRightMotorPort = 4;
    public static final int kCurrentLimit = 40;  // Amps
    public static final double kClosedLoopRampRate = 0.7;
    public static final double kOpenLoopRampRate = 0.7;
    public static final IdleMode kIdleMode = IdleMode.kBrake;
    public static final int kPneumaticForwardChannel = 0;
    public static final int kPneumaticReverseChannel = 1;

    // TODO: Calculate these conversion factors with gear box ratios
    public static final double kEncoderRevToMetersHighGear = 0;
    public static final double kEncoderRpmToMetersPerSecondHighGear = 0;
    public static final double kEncoderRevToMetersLowGear = 0;
    public static final double kEncoderRpmToMetersPerSecondLowGear = 0;  
  }

  public static class ElevatorConstants {



  }

  public static class ElevatorPivotConstants {

    public static final int kLeftFrontMotorPort = 0;
    public static final boolean kLeftFrontMotorInverted = false;

  }
}
