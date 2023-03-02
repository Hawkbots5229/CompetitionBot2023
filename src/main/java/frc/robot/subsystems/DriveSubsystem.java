// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  // The front-left-side drive motor
  private final CANSparkMax m_frontLeft = 
    new CANSparkMax(DriveConstants.kFrontLeftMotorPort, MotorType.kBrushless);
  // The rear-left-side drive motor
  // TODO create m_rearLeft
  // The front-right-side drive motor
  // TODO create m_frontRight
  // The rear-right-side drive motor
  // TODO create m_rearRight

  // The front-left-side drive encoder
  private final RelativeEncoder m_frontLeftEncoder = m_frontLeft.getEncoder();
  // The rear-left-side drive encoder
  // TODO: create m_rearLeftEncoder
  // The front-right-side drive encoder
  // TODO: create m_frontRightEncoder
  // The rear-right-side drive encoder
  // TODO: create m_rearRightEncoder

  // Front left motor velocity PID controller
  private final SparkMaxPIDController m_frontLeftVelPIDController = m_frontLeft.getPIDController();
  // Rear left motor velocity PID controller
  // TODO: create m_rearLeftVelPIDController
  // Front right motor velocity PID controller
  // TODO: create m_frontRightVelPIDController
  // Rear right motor velocity PID controller
  // TODO: create m_rearRightVelPIDController

  // Kauailabs navX-MXP motion processor
  private final AHRS m_gyro = 
      new AHRS(SPI.Port.kMXP);

  private final MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  // TODO: create MotorControllerGroup m_right

  private final DifferentialDrive m_drive;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {

    initMotors();
    initEncoders()

    m_drive = new DifferentialDrive(m_left, m_right);
    m_drive.setExpiration(0.1);
  }

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  private void initMotors() {

    // TODO: Check if motors need to be inverted or if it is done in DifferentialDrive
    
    m_frontLeft.restoreFactoryDefaults();
    // TODO: restoreFactoryDefaults for m_rearLeft, m_frontRight, and m_rearRight
    
    m_frontLeft.setIdleMode(DriveConstants.kIdleMode);
    // TODO: create DriveConstants.kIdleMode in Constants.java
    // TODO: setIdleMode for m_rearLeft, m_frontRight, and m_rearRight
    
    m_frontLeft.setOpenLoopRampRate(DriveConstants.kOpenLoopRampRate);
    // TODO: create DriveConstants.kOpenLoopRampRate in Constants.java
    // TODO: setOpenLoopRampRate for m_rearLeft, m_frontRight, and m_rearRight
    
    m_frontLeft.setClosedLoopRampRate(DriveConstants.kClosedLoopRampRate);
    // TODO: create DriveConstants.kClosedLoopRampRate in Constants.java
    // TODO: setClosedLoopRampRate for m_rearLeft, m_frontRight, and m_rearRight
    
    m_frontLeft.setSmartCurrentLimit(DriveConstants.kCurrentLimit);
    // TODO: create DriveConstants.kCurrentLimit in Constants.java
    // TODO: setSmartCurrentLimit for m_rearLeft, m_frontRight, and m_rearRight
  }

  private void initEncoders() {

    // Converts revolutions to meters
    m_frontLeftEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMeters);
    // TODO: create DriveConstants.kEncoderRevToMeters in Constants.java
    // TODO: setPositionConversionFactor for m_rearLeft, m_frontRight, and m_rearRight

    // Converts RPM to meters per second
    m_frontLeftEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecond);
    // TODO: create DriveConstants.kEncoderRpmToMetersPerSecond in Constants.java
    // TODO: setVelocityConversionFactor for m_rearLeft, m_frontRight, and m_rearRight
    
    resetEncoders();

    // Note: SparkMax relateive encoders are inverted with motors. No action needed here.
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {

    // TODO: setPosition of m_frontLeftEncoder, m_rearLeftEncoder, m_frontRightEncoder, and m_rearRightEncoder to zero

  }

  /**
   * Drives the robot at given left and right speeds. Speeds range from [-1, 1] and the linear
   * speeds have no effect on the angular speed.
   *
   * @param leftSpeed - The robot's left side speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param rightSpeed - The robot's right side speed along the X axis [-1.0..1.0]. Forward is positive.
   */
  @SuppressWarnings("ParameterName")
  public void drive(double ySpeed, double xSpeed) {
    
    m_drive.setSafetyEnabled(true);
    
    // TODO: create tankDrive for m_drive
  }

  /** Stops all drive motors */
  public void stopMotors() {

    // TODO: stopMotor for m_frontLeft, m_rearLeft, m_frontRight, and m_rearRight

  }

  /**
   * * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    
    // TODO: setMaxOutput for m_drive
    
  }

  /** Zeroes the heading of the robot. */
  public void zeroHeading() {

    // TODO: reset m_gyro

  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {

    // TODO: getRate of m_gyro

  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The yaw of the robot, in degrees
   */
  public double getAngle() {

    // TODO: getAngle of m_gyro

  }
}
