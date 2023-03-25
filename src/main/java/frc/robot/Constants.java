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

  public static class ClawPivotConstants{

    public static final int kClawPivotMotorPort = 31;

    public static final boolean kClawPivotMotorIntverted = false;
    public static final IdleMode kIdleMode = IdleMode.kBrake;
    public static final int kCurrentLimit = 40;
    public static final double kClosedLoopRampRate = 1.5;
    public static final double kOpenLoopRampRate = 30;
    public static final double kMaxOutput = 0.2;
   
    public static final double kVexGearRatio = 1/100.0;
    public static final double kSprocketRatio = (16.0/38.0);
    public static final double kEncoderRevToClawDegrees = kVexGearRatio*kSprocketRatio*360;
    public static final double kEncoderRpmToClawDegreesPerSec = kEncoderRevToClawDegrees/60;

    public static final int kVelPidSlot = 0;
    public static final double kFVel = 1;
    public static final double kPVel = 0;
    public static final double kIVel = 0;
    public static final double kDVel = 0;

    public static final double kPPos = 0.1;
    public static final double kIPos= 0;
    public static final double kDPos = 0;
    public static final double kPosErrTolerance = 0.01;

    public static final double kMaxVel = 1; //Motor RPM
    public static final double kMaxAcc = 10;
  }

  public static class ClawConstants{

    public static final int kPneumaticLeftForwardChannel = 2;
    //public static final int kPneumaticRightForwardChannel = 5;
    public static final int kPneumaticLeftReverseChannel = 3;
    //public static final int kPneumaticRightReverseChannel = 7;
  }

  public static class DriveConstants {

    public static final int kFrontLeftMotorPort = 20;
    public static final int kRearLeftMotorPort = 21;
    public static final int kFrontRightMotorPort = 22;
    public static final int kRearRightMotorPort = 23;

    public static final int kPneumaticForwardChannel = 8;
    public static final int kPneumaticReverseChannel = 9;

    public static final boolean kFrontLeftMotorInverted = true;
    public static final boolean kRearLeftMotorInverted = true;
    public static final boolean kFrontRightMotorInverted = false;
    public static final boolean kRearRightMotorInverted = false;

    public static final IdleMode kIdleMode = IdleMode.kBrake;
    public static final int kCurrentLimit = 40;  // Amps
    public static final double kClosedLoopRampRate = 0.7;
    public static final double kOpenLoopRampRate = 35;
    public static final double kMaxOutput = 0.9;

    public static final double kWheelDiameter = 0.1524;
    public static final double kDrivetrainHighGearRatio = 1/9.54;
    public static final double kDrivetrainLowGearRatio = 1/45.33;
    public static final double kEncoderRevToMetersHighGear = (kWheelDiameter * Math.PI / kDrivetrainHighGearRatio);
    public static final double kEncoderRpmToMetersPerSecondHighGear = kEncoderRevToMetersHighGear / 60;
    public static final double kEncoderRevToMetersLowGear = (kWheelDiameter * Math.PI / kDrivetrainLowGearRatio);
    public static final double kEncoderRpmToMetersPerSecondLowGear = kEncoderRevToMetersLowGear / 60;

    public static final int kVelPidSlot = 0;
    public static final double kFVelLeft = 1;
    public static final double kPVelLeft = 0;
    public static final double kIVelLeft = 0;
    public static final double kDVelLeft = 0; 
    public static final double kFVelRight = 1;
    public static final double kPVelRight = 0;
    public static final double kIVelRight = 0;
    public static final double kDVelRight = 0;

    public static final double kMaxVel = 0.0254; //MeterPerSec
  }

  public static class ElevatorPivotConstants {

    public static final int kLeftFrontMotorPort = 43;
    public static final int kLeftRearMotorPort = 42;
    public static final int kRightFrontMotorPort = 41;
    public static final int kRightRearMotorPort = 40;

    public static final boolean kLeftFrontMotorInverted = false;
    public static final boolean kLeftRearMotorInverted = false;
    public static final boolean kRightFrontMotorInverted = true;
    public static final boolean kRightRearMotorInverted = true;
    public static final NeutralMode kIdleMode = NeutralMode.Brake;
    public static final int kCurrentLimit = 40;
    public static final double kClosedLoopRampRate = 0;
    public static final double kOpenLoopRampRate = 10;
    public static final double kMaxOutput = 0.2;
   
    public static final double kGearBoxRatio = (1/12.75);
    public static final double kSprocketRatio = (22.0/38.0); 
    public static final double kEncoderRevToElevatorDegrees = kGearBoxRatio*kSprocketRatio*360;
    public static final double kEncoderRpmToElevatorDegreesPerSec = kEncoderRevToElevatorDegrees/60;

    public static final int kVelPidSlot = 0;
    public static final int kTimeoutMs = 30;
    public static final double kFVel = 25;
    public static final double kPVel = 0.8;
    public static final double kIVel= 0.005;
    public static final double kDVel = 0;

    public static final double kPPos = 0.5;
    public static final double kIPos= 0;
    public static final double kDPos = 0;
    public static final double kPosErrTolerance = 10;

    public static final double kMaxVel = 12;
    public static final double kMaxAcc = 20;
  }

  public static class ElevatorConstants {

    public static final int kElevatorMotorPort = 30;

    public static final boolean kElevatorMotorIntverted = false;
    public static final IdleMode kIdleMode = IdleMode.kBrake;
    public static final int kCurrentLimit = 40;
    public static final double kClosedLoopRampRate = 1.5;
    public static final double kOpenLoopRampRate = 30;
    public static final double kMaxOutput = 1;
     
    public static final double kVexGearBoxRatio = 1/10.0;
    public static final double kRevtoInches = 5.25;  //5.25 inches
    public static final double kEncoderRevToInches = (kVexGearBoxRatio*kRevtoInches);
    public static final double kEncoderRpmToInchesPerSec = kEncoderRevToInches/60;
    
    public static final int kVelPidSlot = 0;
    public static final double kFVel = 1;
    public static final double kPVel = 0;
    public static final double kIVel = 0;
    public static final double kDVel = 0;

    public static final double kPPos = 0.1;
    public static final double kIPos= 0;
    public static final double kDPos = 0;
    public static final double kPosErrTolerance = 0.01;

    public static final double kMaxVel = 1;
    public static final double kMaxAcc = 2;
  }

  public static class IntakeConstants{

    public static final int kLeftMotorPort = 32;
    public static final int kRightMotorPort = 33;

    public static final boolean kLeftMotorIntverted = true;
    public static final boolean kRightMotorInverted = false;
    public static final IdleMode kIdleMode = IdleMode.kBrake;
    public static final int kCurrentLimit = 40;
    public static final double kClosedLoopRampRate = 1.5;
    public static final double kOpenLoopRampRate = 40;
    public static final double kMaxOutput = 0.9;
        
    public static final double kGearBoxRatio = 1/7.0;
    public static final double kEncoderRpmToWheelRpm = kGearBoxRatio;

    public static final int kVelPidSlot = 0;
    public static final double kFVel = 1;
    public static final double kPVel = 0;
    public static final double kIVel = 0;
    public static final double kDVel = 0;

    public static final double kMaxVel = 1; //Wheel RPM
  }

  public static class OperatorConstants {
    
    public static final int kDriverControllerPort = 0;
    public static final int kMechControllerPort = 1;
    public static final int kUpDPad = 180;
    public static final int kDownDPad = 0;
  }
}
