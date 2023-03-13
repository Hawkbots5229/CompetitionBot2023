// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  private final CANSparkMax m_left =
    new CANSparkMax(IntakeConstants.kLeftMotorPort, MotorType.kBrushless);
  private final CANSparkMax m_right =
    new CANSparkMax(IntakeConstants.kRightMotorPort, MotorType.kBrushless);

    private final SparkMaxPIDController pid_LeftVelControl = m_left.getPIDController();

    private final RelativeEncoder e_LeftEncoder = m_left.getEncoder();

    private final RelativeEncoder e_RightEncoder = m_right.getEncoder();
  
  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    m_left.restoreFactoryDefaults();
    m_left.setIdleMode(IntakeConstants.kIdleMode);
    m_left.setInverted(IntakeConstants.kLeftMotorIntverted);
    m_left.setClosedLoopRampRate(IntakeConstants.kClosedLoopRampRate);
    m_left.setSecondaryCurrentLimit(50);
    
    m_right.restoreFactoryDefaults();
    m_right.setIdleMode(IntakeConstants.kIdleMode);
    m_right.setInverted(IntakeConstants.kRightMotorInverted);
    m_right.setClosedLoopRampRate(IntakeConstants.kClosedLoopRampRate);
    m_right.setSecondaryCurrentLimit(50);

    m_right.follow(m_left);

    pid_LeftVelControl.setFF(IntakeConstants.kFVel, IntakeConstants.kVelPidSlot);
    pid_LeftVelControl.setP(IntakeConstants.kPVel, IntakeConstants.kVelPidSlot);
    pid_LeftVelControl.setD(IntakeConstants.kDVel, IntakeConstants.kVelPidSlot);
    pid_LeftVelControl.setI(IntakeConstants.kIVel, IntakeConstants.kVelPidSlot);

  }

  public void setTargetOutput(double output) {
    m_left.set(output);
  }

  public void setTargetVelocity(double Velocity) {
    pid_LeftVelControl.setReference(
      Velocity,
      CANSparkMax.ControlType.kVelocity,
      IntakeConstants.kVelPidSlot);

  }

  public double getLeftVel() {
    return (e_LeftEncoder.getVelocity() + e_RightEncoder.getVelocity())/2;
  }

  public void stopMotor() {
    m_left.stopMotor();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
