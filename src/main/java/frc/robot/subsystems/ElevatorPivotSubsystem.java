// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorPivotConstants;

public class ElevatorPivotSubsystem extends SubsystemBase {

  private final WPI_TalonFX m_leftFront = 
    new WPI_TalonFX(ElevatorPivotConstants.kLeftFrontMotorPort);
  private final WPI_TalonFX m_leftRear =
    new WPI_TalonFX(ElevatorPivotConstants.kLeftRearMotorPort);
  private final WPI_TalonFX m_rightFront =
    new WPI_TalonFX(ElevatorPivotConstants.kRightFrontMotorPort);
  private final WPI_TalonFX m_rightRear =
    new WPI_TalonFX(ElevatorPivotConstants.kRightRearMotorPort);

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

    m_leftRear.follow(m_leftFront);
    m_rightFront.follow(m_leftFront);
    m_rightRear.follow(m_leftFront);

    /* Config sensor used for Primary PID [Velocity] */
    m_leftFront.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
      ElevatorPivotConstants.kVelPidSlot, ElevatorPivotConstants.kTimeoutMs);

    /* Config the Velocity closed loop gains in slot0 */
		m_leftFront.config_kF(ElevatorPivotConstants.kVelPidSlot, ElevatorPivotConstants.kF, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.config_kP(ElevatorPivotConstants.kVelPidSlot, ElevatorPivotConstants.kP, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.config_kI(ElevatorPivotConstants.kVelPidSlot, ElevatorPivotConstants.kI, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.config_kD(ElevatorPivotConstants.kVelPidSlot, ElevatorPivotConstants.kD, ElevatorPivotConstants.kTimeoutMs);
  }

  public void setTargetOutput(double output) {
    m_leftFront.set(output);
  }

  public void setTargetVelocity(double velocity) {
    m_leftFront.set(TalonFXControlMode.Velocity, velocity);
  }

  public double getElevatorPivotVel() {
    return m_leftFront.getSelectedSensorVelocity();
  }

  public void stopMotor() {
    m_leftFront.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Elevator Pivot Velocity", getElevatorPivotVel());
  }
}
