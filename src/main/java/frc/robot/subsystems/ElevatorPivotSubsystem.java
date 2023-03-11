// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorPivotConstants;

public class ElevatorPivotSubsystem extends SubsystemBase {

  private final WPI_TalonFX m_leftFront = 
    new WPI_TalonFX(ElevatorPivotConstants.kLeftFrontMotorPort);
  private final WPI_TalonFX m_leftRear =
    new WPI_TalonFX(ElevatorPivotConstants.kLeftRearMotorPart);
  private final WPI_TalonFX m_rightFront =
    new WPI_TalonFX(ElevatorPivotConstants.kRightFrontMotorPart);
  private final WPI_TalonFX m_rightRear =
    new WPI_TalonFX(ElevatorPivotConstants.kRightRearMotorPart);

  /** Creates a new ElevatorPivotSubsystem. */
  public ElevatorPivotSubsystem() {
    
    m_leftFront.configFactoryDefault();
    m_leftRear.configFactoryDefault();
    m_rightFront.configFactoryDefault();
    m_rightRear.configFactoryDefault();
    

    m_leftFront.configNeutralDeadband(0.001); 
    m_leftRear.configNeutralDeadband(0.001);
    m_rightFront.configNeutralDeadband(0.001);
    m_rightRear.configNeutralDeadband(0.001);

    m_leftFront.setInverted(ElevatorPivotConstants.kLeftFrontMotorInverted);
    // TODO: Do this for all motors. Each motor will need a ElevatorConstant
    m_leftRear.setInverted(ElevatorPivotConstants.kLeftRearMotorInverted);
    m_rightFront.setInverted(ElevatorPivotConstants.kRightFrontMotorInverted);
    
    m_leftFront.setNeutralMode(NeutralMode.Brake);
    // TODO: Do this for all motors. Each motor will need a ElevatorConstant
    
    m_LeftRearMotor.follow(m_leftFront);
    m_RightFrontMotor.follow(m_leftFront);
    m_RightRearMotor.follow(m_leftFront);

    /* Config sensor used for Primary PID [Velocity] */
    m_leftFront.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor,
      ElevatorPivotConstants.kPIDLoopIdx, ElevatorPivotConstants.kTimeoutMs);

    /* Config the peak and nominal outputs */
    // TODO: Add kTimeoutMs to ElevatorPivotConstants
    m_leftFront.configNominalOutputForward(0, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.configNominalOutputReverse(0, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.configPeakOutputForward(1, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.configPeakOutputReverse(-1, ElevatorPivotConstants.kTimeoutMs);

    /* Config the Velocity closed loop gains in slot0 */
    // TODO: Add all these constants to ElevatorPivotConstants
		m_leftFront.config_kF(ElevatorPivotConstants.kPIDLoopIdx, ElevatorPivotConstants.kF, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.config_kP(ElevatorPivotConstants.kPIDLoopIdx, ElevatorPivotConstants.kP, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.config_kI(ElevatorPivotConstants.kPIDLoopIdx, ElevatorPivotConstants.kI, ElevatorPivotConstants.kTimeoutMs);
		m_leftFront.config_kD(ElevatorPivotConstants.kPIDLoopIdx, ElevatorPivotConstants.kD, ElevatorPivotConstants.kTimeoutMs);
  }

  public void setTargetOutput(double output) {
    m_leftFront.set(output);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
