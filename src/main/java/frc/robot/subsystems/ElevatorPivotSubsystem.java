// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorPivotConstants;

public class ElevatorPivotSubsystem extends SubsystemBase {

  public enum ElevatorPivotPos{kHome, kExtend};

  private final WPI_TalonFX m_leftFront = 
    new WPI_TalonFX(ElevatorPivotConstants.kLeftFrontMotorPort);
  private final WPI_TalonFX m_leftRear =
    new WPI_TalonFX(ElevatorPivotConstants.kLeftRearMotorPort);
  private final WPI_TalonFX m_rightFront =
    new WPI_TalonFX(ElevatorPivotConstants.kRightFrontMotorPort);
  private final WPI_TalonFX m_rightRear =
    new WPI_TalonFX(ElevatorPivotConstants.kRightRearMotorPort);

  private final ProfiledPIDController pid_elevatorPivotPos;

  /** Creates a new ElevatorPivotSubsystem. */
  public ElevatorPivotSubsystem() {
    
    m_leftFront.configFactoryDefault();
    m_leftRear.configFactoryDefault();
    m_rightFront.configFactoryDefault();
    m_rightRear.configFactoryDefault();

    m_leftFront.setInverted(ElevatorPivotConstants.kLeftFrontMotorInverted);
    m_leftRear.setInverted(ElevatorPivotConstants.kLeftRearMotorInverted);
    m_rightFront.setInverted(ElevatorPivotConstants.kRightFrontMotorInverted);
    m_rightRear.setInverted(ElevatorPivotConstants.kRightRearMotorInverted);
    
    m_leftFront.setNeutralMode(ElevatorPivotConstants.kIdleMode);
    m_leftRear.setNeutralMode(ElevatorPivotConstants.kIdleMode);
    m_rightFront.setNeutralMode(ElevatorPivotConstants.kIdleMode);
    m_rightRear.setNeutralMode(ElevatorPivotConstants.kIdleMode);

    m_leftFront.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, ElevatorPivotConstants.kCurrentLimit, 80, 0.5));
    m_leftRear.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, ElevatorPivotConstants.kCurrentLimit, 80, 0.5));
    m_rightFront.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, ElevatorPivotConstants.kCurrentLimit, 80, 0.5));
    m_rightRear.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, ElevatorPivotConstants.kCurrentLimit, 80, 0.5));

    m_leftFront.configClosedloopRamp(ElevatorPivotConstants.kClosedLoopRampRate);
    m_leftRear.configClosedloopRamp(ElevatorPivotConstants.kClosedLoopRampRate);
    m_rightFront.configClosedloopRamp(ElevatorPivotConstants.kClosedLoopRampRate);
    m_rightRear.configClosedloopRamp(ElevatorPivotConstants.kClosedLoopRampRate);

    m_leftFront.configOpenloopRamp(ElevatorPivotConstants.kOpenLoopRampRate);
    m_leftRear.configOpenloopRamp(ElevatorPivotConstants.kOpenLoopRampRate);
    m_rightFront.configOpenloopRamp(ElevatorPivotConstants.kOpenLoopRampRate);
    m_rightRear.configOpenloopRamp(ElevatorPivotConstants.kOpenLoopRampRate);

    m_leftRear.follow(m_leftFront);
    m_rightFront.follow(m_leftFront);
    m_rightRear.follow(m_leftFront);

    /* Config sensor used for Primary PID [Velocity] */
    m_leftFront.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
      ElevatorPivotConstants.kVelPidSlot, ElevatorPivotConstants.kTimeoutMs);

    resetEncoders();

    /* Config the Velocity closed loop gains in slot0 */
		m_leftFront.config_kF(ElevatorPivotConstants.kVelPidSlot, ElevatorPivotConstants.kFVel, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.config_kP(ElevatorPivotConstants.kVelPidSlot, ElevatorPivotConstants.kPVel, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.config_kI(ElevatorPivotConstants.kVelPidSlot, ElevatorPivotConstants.kIVel, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.config_kD(ElevatorPivotConstants.kVelPidSlot, ElevatorPivotConstants.kDVel, ElevatorPivotConstants.kTimeoutMs);

    pid_elevatorPivotPos = new ProfiledPIDController(ElevatorPivotConstants.kPPos, ElevatorPivotConstants.kDPos, ElevatorPivotConstants.kDPos, new TrapezoidProfile.Constraints(ElevatorPivotConstants.kMaxVel,  ElevatorPivotConstants.kMaxAcc));
    pid_elevatorPivotPos.setTolerance(ElevatorPivotConstants.kPosErrTolerance);
    pid_elevatorPivotPos.setIntegratorRange(-1, 1);
    pid_elevatorPivotPos.reset(0);
  }

  public void setTargetOutput(double output) {
    if (Math.abs(output) < 0.1) {output = 0;}
    m_leftFront.set(Math.min(output, ElevatorPivotConstants.kMaxOutput));
  }

  public void setTargetVelocity(double velocity) {
    if (Math.abs(velocity) < 0.1) {velocity = 0;}
    //System.out.println(convertTarVelToRPM(velocity*ElevatorPivotConstants.kMaxVel)/600);
    m_leftFront.set(TalonFXControlMode.Velocity, convertTarVelToRPM(velocity*ElevatorPivotConstants.kMaxVel)); // 600 100ms in 1 min
  }

  public void setTargetVoltage(double volts) {
    m_leftFront.setVoltage(volts);
  }

  public void setTargetPos(double position) {
    //System.out.println(position);
    pid_elevatorPivotPos.setGoal(position);
    double volts = pid_elevatorPivotPos.calculate(getElevatorPivotPos());
    //System.out.println(volts);
    //SmartDashboard.putNumber("Elevator Pivot VOltage", volts);
    setTargetVoltage(volts);
  }

  public double convertTarVelToRPM (double velocity) {
    return velocity*ElevatorPivotConstants.kEncoderRpmToElevatorDegreesPerSec;
  }

  public double getElevatorPivotVel() {
    return (m_leftFront.getSelectedSensorVelocity()/4096)*ElevatorPivotConstants.kEncoderRpmToElevatorDegreesPerSec;
  }

  public double getElevatorPivotPos() {
    //System.out.println(ElevatorPivotConstants.kEncoderRevToElevatorDegrees);
    return (m_leftFront.getSelectedSensorPosition()/4096)*ElevatorPivotConstants.kEncoderRevToElevatorDegrees;
  }

  public void stopMotor() {
    m_leftFront.stopMotor();
  }

  public void resetEncoders() {
    m_leftFront.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Elevator Pivot Velocity", getElevatorPivotVel());
    SmartDashboard.putNumber("Elevator Pivot Position", getElevatorPivotPos());
    //System.out.println(m_leftFront.getMotorOutputPercent());
  }
}
