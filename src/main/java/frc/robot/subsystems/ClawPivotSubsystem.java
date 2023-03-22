// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawPivotConstants;

public class ClawPivotSubsystem extends SubsystemBase {

  private final CANSparkMax m_clawPivot =
    new CANSparkMax(ClawPivotConstants.kClawPivotMotorPort, MotorType.kBrushless);

  private final RelativeEncoder e_ClawPivotEncoder = m_clawPivot.getEncoder();

  private final SparkMaxPIDController pid_ClawPivotVelControl = m_clawPivot.getPIDController();

  /** Creates a new ClawPivotSubsystem. */
  public ClawPivotSubsystem() {

    m_clawPivot.restoreFactoryDefaults();
    m_clawPivot.setInverted(ClawPivotConstants.kClawPivotMotorIntverted);
    m_clawPivot.setIdleMode(ClawPivotConstants.kIdleMode);
    m_clawPivot.setSmartCurrentLimit(ClawPivotConstants.kCurrentLimit);
    m_clawPivot.setClosedLoopRampRate(ClawPivotConstants.kClosedLoopRampRate);

    e_ClawPivotEncoder.setVelocityConversionFactor(ClawPivotConstants.kEncoderRpmToClawRpm);
    // TODO: setPositionCoversionFactor

    pid_ClawPivotVelControl.setFF(ClawPivotConstants.kFVel, ClawPivotConstants.kVelPidSlot);
    pid_ClawPivotVelControl.setP(ClawPivotConstants.kPVel, ClawPivotConstants.kVelPidSlot);
    pid_ClawPivotVelControl.setD(ClawPivotConstants.kDVel, ClawPivotConstants.kVelPidSlot);
    pid_ClawPivotVelControl.setI(ClawPivotConstants.kIVel, ClawPivotConstants.kVelPidSlot);
  }

  public void setTargetOutput(double output) {
    m_clawPivot.set(output);
  }

  public void setTargetVelocity(double velocity) {
    pid_ClawPivotVelControl.setReference(
      velocity*ClawPivotConstants.kMaxVel,
      CANSparkMax.ControlType.kVelocity,
      ClawPivotConstants.kVelPidSlot);
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {

    e_ClawPivotEncoder.setPosition(0);
  }

  public double getClawPivotVel() {
    return e_ClawPivotEncoder.getVelocity();
  }

  public double getClawPivotPos() {
    return e_ClawPivotEncoder.getPosition();
  }

  public void stopMotor() {
    m_clawPivot.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Claw Pivot Velocity", getClawPivotVel());
  }
}
