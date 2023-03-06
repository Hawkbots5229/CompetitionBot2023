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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  // The front-left-side drive motor
  private final CANSparkMax m_frontLeft = 
    new CANSparkMax(DriveConstants.kFrontLeftMotorPort, MotorType.kBrushless);
  // The rear-left-side drive motor
  private final CANSparkMax m_rearLeft =
    new CANSparkMax(DriveConstants.kRearLeftMotorPort, MotorType.kBrushless);
  // The front-right-side drive motor
  private final CANSparkMax m_frontRight =
    new CANSparkMax(DriveConstants.kFrontRightMotorPort, MotorType.kBrushless);
  // The rear-right-side drive motor
  private final CANSparkMax m_rearRight =
    new CANSparkMax(DriveConstants.kRearRightMotorPort, MotorType.kBrushless);

  // The front-left-side drive encoder
  private final RelativeEncoder m_frontLeftEncoder = m_frontLeft.getEncoder();
  // The rear-left-side drive encoder
  private final RelativeEncoder m_rearLeftEncoder = m_rearLeft.getEncoder();
  // The front-right-side drive encoder
  private final RelativeEncoder m_frontRightEncoder = m_frontRight.getEncoder();
  // The rear-right-side drive encoder
  private final RelativeEncoder m_rearRightEncoder = m_rearRight.getEncoder();

  // Front left motor velocity PID controller
  private final SparkMaxPIDController m_frontLeftVelPIDController = m_frontLeft.getPIDController();
  // Rear left motor velocity PID controller
  private final SparkMaxPIDController m_rearLeftVelPIDController = m_rearLeft.getPIDController();
  // Front right motor velocity PID controller
  private final SparkMaxPIDController m_frontRightVelPIDController = m_frontRight.getPIDController();
  // Rear right motor velocity PID controller
  private final SparkMaxPIDController m_rearRightVelPIDController = m_rearRight.getPIDController();

  // Kauailabs navX-MXP motion processor
  private final AHRS g_navX = 
      new AHRS(SPI.Port.kMXP);

  private final MotorControllerGroup mcg_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  private final MotorControllerGroup mcg_right = new MotorControllerGroup(m_frontRight, m_rearRight);
  private final Solenoid ss_shifterLeft = new Solenoid(PneumaticsModuleType.REVPH, 1);
  private final Solenoid ss_shifterRight = new Solenoid(PneumaticsModuleType.REVPH, 2);

  private final DifferentialDrive dd_drive;

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {

    initMotors();
    initEncoders();

    dd_drive = new DifferentialDrive(mcg_left, mcg_right);
    dd_drive.setExpiration(0.1);
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
    m_rearLeft.restoreFactoryDefaults();
    m_frontRight.restoreFactoryDefaults();
    m_rearRight.restoreFactoryDefaults();
    
    m_frontLeft.setIdleMode(DriveConstants.kIdleMode);
    m_rearLeft.setIdleMode(DriveConstants.kIdleMode);
    m_frontRight.setIdleMode(DriveConstants.kIdleMode);
    m_rearRight.setIdleMode(DriveConstants.kIdleMode);
    
    m_frontLeft.setOpenLoopRampRate(DriveConstants.kOpenLoopRampRate);
    m_rearLeft.setOpenLoopRampRate(DriveConstants.kOpenLoopRampRate);
    m_frontRight.setOpenLoopRampRate(DriveConstants.kOpenLoopRampRate);
    m_rearRight.setOpenLoopRampRate(DriveConstants.kOpenLoopRampRate);
    
    m_frontLeft.setClosedLoopRampRate(DriveConstants.kClosedLoopRampRate);
    m_rearLeft.setClosedLoopRampRate(DriveConstants.kClosedLoopRampRate);
    m_frontRight.setClosedLoopRampRate(DriveConstants.kClosedLoopRampRate);
    m_rearRight.setClosedLoopRampRate(DriveConstants.kClosedLoopRampRate);
    
    m_frontLeft.setSmartCurrentLimit(DriveConstants.kCurrentLimit);
    m_rearLeft.setSmartCurrentLimit(DriveConstants.kCurrentLimit);
    m_frontRight.setSmartCurrentLimit(DriveConstants.kCurrentLimit);
    m_rearRight.setSmartCurrentLimit(DriveConstants.kCurrentLimit);
  }

  private void initEncoders() {

    // Converts revolutions to meters
    m_frontLeftEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMeters);
    m_rearLeftEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMeters);
    m_frontRightEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMeters);
    m_rearRightEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMeters);

    // Converts RPM to meters per second
    m_frontLeftEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecond);
    m_rearLeftEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecond);
    m_frontRightEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecond);
    m_rearRightEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecond);
    
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
  public void drive(double leftSpeed, double rightSpeed) {
    
    dd_drive.setSafetyEnabled(true);
    
    dd_drive.tankDrive(leftSpeed, rightSpeed);
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
    
    // TODO: setMaxOutput for dd_drive
    
  }

  /** Zeroes the heading of the robot. */
  public void zeroHeading() {

    // TODO: reset g_navX

  }

  /** Shifts gearbox into high */
  public void shiftHighGear() {

    // TODO: set ss_shifterLeft and ss_shifterRight

  }

  /** Shifts gearbox into low */
  public void shiftLowGear() {

    // TODO: set ss_shifterLeft and ss_shifterRight

  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {

    // TODO: getRate of g_navX

    return 0;

  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The yaw of the robot, in degrees
   */
  public double getAngle() {

    // TODO: getAngle of g_navX

    return 0;

  }
}
