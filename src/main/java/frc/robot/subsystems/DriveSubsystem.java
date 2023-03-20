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
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  public enum gear{kHigh, kLow};
  private String gearPos;
  

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
  private final SparkMaxPIDController pid_leftVel = m_frontLeft.getPIDController();
  // Front right motor velocity PID controller
  private final SparkMaxPIDController pid_rightVel = m_frontRight.getPIDController();

  // Kauailabs navX-MXP motion processor
  private final AHRS g_navX = 
      new AHRS(SPI.Port.kMXP);

  private final MotorControllerGroup mcg_left = new MotorControllerGroup(m_frontLeft, m_rearLeft);
  private final MotorControllerGroup mcg_right = new MotorControllerGroup(m_frontRight, m_rearRight);
  private final DoubleSolenoid ds_gearBox;

  private final DifferentialDrive dd_drive;

  //private final Compressor phCompressor = new Compressor(62, PneumaticsModuleType.REVPH);

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {

    initMotors();
    initEncoders();
    initPID();

    ds_gearBox = new DoubleSolenoid(62, PneumaticsModuleType.REVPH, DriveConstants.kPneumaticForwardChannel, DriveConstants.kPneumaticReverseChannel);
    dd_drive = new DifferentialDrive(mcg_left, mcg_right);
    setMaxOutput(DriveConstants.kMaxOutput);
    gearPos = "High";
    shiftHighGear();

    dd_drive.setExpiration(0.1);
  }

  private void initMotors() {
    
    m_frontLeft.restoreFactoryDefaults();
    m_rearLeft.restoreFactoryDefaults();
    m_frontRight.restoreFactoryDefaults();
    m_rearRight.restoreFactoryDefaults();

    m_frontLeft.setInverted(DriveConstants.kFrontLeftMotorInverted);
    m_rearLeft.setInverted(DriveConstants.kRearLeftMotorInverted);
    m_frontRight.setInverted(DriveConstants.kFrontRightMotorInverted);
    m_rearRight.setInverted(DriveConstants.kRearRightMotorInverted);
    
    m_frontLeft.setIdleMode(DriveConstants.kIdleMode);
    m_rearLeft.setIdleMode(DriveConstants.kIdleMode);
    m_frontRight.setIdleMode(DriveConstants.kIdleMode);
    m_rearRight.setIdleMode(DriveConstants.kIdleMode);
       
    m_frontLeft.setSmartCurrentLimit(DriveConstants.kCurrentLimit);
    m_rearLeft.setSmartCurrentLimit(DriveConstants.kCurrentLimit);
    m_frontRight.setSmartCurrentLimit(DriveConstants.kCurrentLimit);
    m_rearRight.setSmartCurrentLimit(DriveConstants.kCurrentLimit);
    
    m_frontLeft.setClosedLoopRampRate(DriveConstants.kClosedLoopRampRate);
    m_rearLeft.setClosedLoopRampRate(DriveConstants.kClosedLoopRampRate);
    m_frontRight.setClosedLoopRampRate(DriveConstants.kClosedLoopRampRate);
    m_rearRight.setClosedLoopRampRate(DriveConstants.kClosedLoopRampRate);
    
    m_frontLeft.setOpenLoopRampRate(DriveConstants.kOpenLoopRampRate);
    m_rearLeft.setOpenLoopRampRate(DriveConstants.kOpenLoopRampRate);
    m_frontRight.setOpenLoopRampRate(DriveConstants.kOpenLoopRampRate);
    m_rearRight.setOpenLoopRampRate(DriveConstants.kOpenLoopRampRate);

  }

  private void initEncoders() {

    setEncoderHighGearConversionFactor();  
    resetEncoders();
  }

  private void initPID() {

    pid_leftVel.setFF(DriveConstants.kFVelLeft, DriveConstants.kVelPidSlot);
    pid_leftVel.setP(DriveConstants.kPVelLeft, DriveConstants.kVelPidSlot);
    pid_leftVel.setD(DriveConstants.kDVelLeft, DriveConstants.kVelPidSlot);
    pid_leftVel.setI(DriveConstants.kIVelLeft, DriveConstants.kVelPidSlot);

    pid_rightVel.setFF(DriveConstants.kFVelLeft, DriveConstants.kVelPidSlot);
    pid_rightVel.setP(DriveConstants.kPVelLeft, DriveConstants.kVelPidSlot);
    pid_rightVel.setD(DriveConstants.kDVelLeft, DriveConstants.kVelPidSlot);
    pid_rightVel.setI(DriveConstants.kIVelLeft, DriveConstants.kVelPidSlot);
  }

  // Sets the position and velocity encoder conversion factors when gear box is in high gear
  public void setEncoderHighGearConversionFactor() {

    // Converts revolutions to meters
    m_frontLeftEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMetersHighGear);
    m_rearLeftEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMetersHighGear);
    m_frontRightEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMetersHighGear);
    m_rearRightEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMetersHighGear);

    // Converts RPM to meters per second
    m_frontLeftEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecondHighGear);
    m_rearLeftEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecondHighGear);
    m_frontRightEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecondHighGear);
    m_rearRightEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecondHighGear);
  }

  // Sets the position and velocity encoder conversion factors when gear box is in low gear
  public void setEncoderLowGearConversionFactor() {

    m_frontLeftEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMetersLowGear);
    m_rearLeftEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMetersLowGear);
    m_frontRightEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMetersLowGear);
    m_rearRightEncoder.setPositionConversionFactor(DriveConstants.kEncoderRevToMetersLowGear);

    m_frontLeftEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecondLowGear);
    m_rearLeftEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecondLowGear);
    m_frontRightEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecondLowGear);
    m_rearRightEncoder.setVelocityConversionFactor(DriveConstants.kEncoderRpmToMetersPerSecondLowGear);
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {

    m_frontLeftEncoder.setPosition(0);
    m_rearLeftEncoder.setPosition(0);
    m_frontRightEncoder.setPosition(0);
    m_rearRightEncoder.setPosition(0);
  }

  /**
   * Drives the robot at given left and right speeds. Speeds range from [-1, 1] and the linear
   * speeds have no effect on the angular speed.
   *
   * @param leftSpeed - The robot's left side speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param rightSpeed - The robot's right side speed along the X axis [-1.0..1.0]. Forward is positive.
   */
  @SuppressWarnings("ParameterName")
  public void driveTank(double leftSpeed, double rightSpeed) {
    
    dd_drive.setSafetyEnabled(true);  
    dd_drive.tankDrive(leftSpeed, rightSpeed);
  }

    /**
   * Drives the robot at given left and right speeds. Speeds range from [-1, 1] and the linear
   * speeds have no effect on the angular speed.
   *
   * @param leftSpeed - The robot's left side speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param rightSpeed - The robot's right side speed along the X axis [-1.0..1.0]. Forward is positive.
   */
  @SuppressWarnings("ParameterName")
  public void setTargetVelocity(double leftSpeed, double rightSpeed) {
    
    dd_drive.setSafetyEnabled(true);  
    
    pid_leftVel.setReference(
      leftSpeed*DriveConstants.kMaxVel,
      CANSparkMax.ControlType.kVelocity,
      DriveConstants.kVelPidSlot);

    pid_rightVel.setReference(
      rightSpeed*DriveConstants.kMaxVel,
      CANSparkMax.ControlType.kVelocity,
      DriveConstants.kVelPidSlot);
  }

  /** Stops all drive motors */
  public void stopMotors() {

    m_frontLeft.stopMotor();
    m_rearLeft.stopMotor();
    m_frontRight.stopMotor();
    m_rearRight.stopMotor();
  }

    /**
   * Gets robot position in meters by averaging each wheel position.
   * Does not indicate direction.
   *
   * @return the robot's position
   */
  public double getRobotPosition() {

    final double m_frontLeftPos = Math.abs(m_frontLeftEncoder.getPosition());
    final double m_rearLeftPos = Math.abs(m_rearLeftEncoder.getPosition());
    final double m_frontRightPos = Math.abs(m_frontRightEncoder.getPosition());
    final double m_rearRightPos = Math.abs(m_rearRightEncoder.getPosition());

    return (m_frontLeftPos + m_rearLeftPos + m_frontRightPos + m_rearRightPos) / 4;
  }

  /**
   * * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {  

    dd_drive.setMaxOutput(maxOutput);  
    //System.out.println(maxOutput);
  }

  /** Zeroes the heading of the robot. */
  public void zeroHeading() {

    g_navX.reset();
  }

  /** Shifts gearbox into high 
   *  High Gear is default position
  */
  
  public void shiftHighGear() {

    ds_gearBox.set(DoubleSolenoid.Value.kForward);
    setEncoderHighGearConversionFactor();
    gearPos = "High";
  }
  
  /** Shifts gearbox into low 
   *  Low Gear is extended position
  */
  
  public void shiftLowGear() {

    ds_gearBox.set(DoubleSolenoid.Value.kReverse);
    setEncoderLowGearConversionFactor();
    gearPos = "Low";
  }
  

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {

    return g_navX.getRate();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The yaw of the robot, in degrees
   */
  public double getAngle() {

    return g_navX.getAngle();

    
  }

  public double getPitch() {

    return g_navX.getPitch();
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println(phCompressor.getPressure());
    //System.out.println(phCompressor.isEnabled());
    SmartDashboard.putString("Gear Pos", gearPos);
    SmartDashboard.putNumber("Pitch", getPitch());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
