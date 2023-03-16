// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
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
    public static final int kMechControllerPort = 1;
    public static final int kUpDPad = 180;
    public static final int kDownDPad = 0;
  }

  public static class DriveConstants {
    public static final int kFrontLeftMotorPort = 1;
    public static final int kRearLeftMotorPort = 2;
    public static final int kFrontRightMotorPort = 3;
    public static final int kRearRightMotorPort = 4;

    public static final int kPneumaticForwardChannel = 0;
    public static final int kPneumaticReverseChannel = 1;

    public static final boolean kFrontLeftMotorInverted = false;
    public static final boolean kRearLeftMotorInverted = true;
    public static final boolean kFrontRightMotorInverted = false;
    public static final boolean kRearRightMotorInverted = true;

    public static final IdleMode kIdleMode = IdleMode.kBrake;
    public static final int kCurrentLimit = 40;  // Amps
    public static final double kClosedLoopRampRate = 0.7;
    public static final double kOpenLoopRampRate = 0.7;
    
    // TODO: Calculate these conversion factors with gear box ratios
    // High Gear 9.54:1
    // Low Gear 45.33:1
    public static final double kEncoderRevToMetersHighGear = 0;
    public static final double kEncoderRpmToMetersPerSecondHighGear = 0;
    public static final double kEncoderRevToMetersLowGear = 0;
    public static final double kEncoderRpmToMetersPerSecondLowGear = 0;  
  }

  public static class ElevatorConstants {

    public static final int kElevatorMotorPort = 5;

    public static final boolean kElevatorMotorIntverted = false;
    public static final IdleMode kIdleMode = IdleMode.kBrake;
    public static final int kCurrentLimit = 40;
    public static final double kClosedLoopRampRate = 1.5;
    
    public static final int kVelPidSlot = 0;
    public static final double kFVel = 1;
    public static final double kPVel = 0;
    public static final double kIVel = 0;
    public static final double kDVel = 0; 

    public static final double kMaxVel = 10; //Motor RPM
  }

  public static class ElevatorPivotConstants {

    public static final int kLeftFrontMotorPort = 6;
    public static final int kLeftRearMotorPort = 7;
    public static final int kRightFrontMotorPort = 8;
    public static final int kRightRearMotorPort = 9;

    public static final boolean kLeftFrontMotorInverted = false;
    public static final boolean kLeftRearMotorInverted = true;
    public static final boolean kRightFrontMotorInverted = false;
    public static final boolean kRightRearMotorInverted = true;
    public static final NeutralMode kIdleMode = NeutralMode.Brake;
    public static final int kCurrentLimit = 40;
    public static final double kClosedLoopRampRate = 1.5;

    public static final int kVelPidSlot = 0;
    public static final int kTimeoutMs = 30;
    public static final double kF = 1;
    public static final double kP = 0;
    public static final double kI= 0;
    public static final double kD = 0;

    public static final double kMaxVel = 10; //Motor RPM
  }

  public static class IntakeConstants{

    public static final int kLeftMotorPort = 10;
    public static final int kRightMotorPort = 11;

    public static final boolean kLeftMotorIntverted = false;
    public static final boolean kRightMotorInverted = false;
    public static final IdleMode kIdleMode = IdleMode.kBrake;
    public static final int kCurrentLimit = 40;
    public static final double kClosedLoopRampRate = 1.5;

    public static final int kVelPidSlot = 0;
    public static final double kFVel = 1;
    public static final double kPVel = 0;
    public static final double kIVel = 0;
    public static final double kDVel = 0;
    
    public static final double kMaxVel = 10; //Motor RPM
  }

  public static class ClawConstants{
    public static final int kPneumaticLeftForwardChannel = 4;
    public static final int kPneumaticRightForwardChannel = 5;
    public static final int kPneumaticLeftReverseChannel = 6;
    public static final int kPneumaticRightReverseChannel = 7;
  }

  public static class ClawPivotConstants{

    public static final int kClawPivotMotorPort = 12;

    public static final boolean kClawPivotMotorIntverted = false;
    public static final IdleMode kIdleMode = IdleMode.kBrake;
    public static final int kCurrentLimit = 40;
    public static final double kClosedLoopRampRate = 1.5;


    public static final int kVelPidSlot = 0;
    public static final double kFVel = 1;
    public static final double kPVel = 0;
    public static final double kIVel = 0;
    public static final double kDVel = 0;
    
    public static final double kMaxVel = 10; //Motor RPM
  }
}
