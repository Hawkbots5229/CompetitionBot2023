// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawPivotConstants;

public class ClawPivotSubsystem extends SubsystemBase {

  public enum ClawPivotPos{kHome, kExtend};

  private final CANSparkMax m_clawPivot =
    new CANSparkMax(ClawPivotConstants.kClawPivotMotorPort, MotorType.kBrushless);

  private final RelativeEncoder e_clawPivotEncoder = m_clawPivot.getEncoder();

  private final SparkMaxPIDController pid_clawPivotVel = m_clawPivot.getPIDController();

  private final ProfiledPIDController pid_clawPivotPos;

  /** Creates a new ClawPivotSubsystem. */
  public ClawPivotSubsystem() {

    m_clawPivot.restoreFactoryDefaults();
    m_clawPivot.setInverted(ClawPivotConstants.kClawPivotMotorIntverted);
    m_clawPivot.setIdleMode(ClawPivotConstants.kIdleMode);
    m_clawPivot.setSmartCurrentLimit(ClawPivotConstants.kCurrentLimit);
    m_clawPivot.setClosedLoopRampRate(ClawPivotConstants.kClosedLoopRampRate);
    m_clawPivot.setOpenLoopRampRate(ClawPivotConstants.kOpenLoopRampRate);
    
    e_clawPivotEncoder.setPositionConversionFactor(ClawPivotConstants.kEncoderRevToClawDegrees);
    e_clawPivotEncoder.setVelocityConversionFactor(ClawPivotConstants.kEncoderRpmToClawDegreesPerSec);

    pid_clawPivotVel.setFF(ClawPivotConstants.kFVel, ClawPivotConstants.kVelPidSlot);
    pid_clawPivotVel.setP(ClawPivotConstants.kPVel, ClawPivotConstants.kVelPidSlot);
    pid_clawPivotVel.setD(ClawPivotConstants.kDVel, ClawPivotConstants.kVelPidSlot);
    pid_clawPivotVel.setI(ClawPivotConstants.kIVel, ClawPivotConstants.kVelPidSlot);

    pid_clawPivotPos = new ProfiledPIDController(ClawPivotConstants.kPPos, ClawPivotConstants.kIPos, ClawPivotConstants.kDPos, new TrapezoidProfile.Constraints(ClawPivotConstants.kMaxVel,  ClawPivotConstants.kMaxAcc));
    pid_clawPivotPos.setTolerance(ClawPivotConstants.kPosErrTolerance);
  }

  public void setTargetOutput(double output) {
    m_clawPivot.set(output);
  }

  public void setTargetVelocity(double velocity) {
    pid_clawPivotVel.setReference(
      velocity*ClawPivotConstants.kMaxVel,
      CANSparkMax.ControlType.kVelocity,
      ClawPivotConstants.kVelPidSlot);
  }

  public void setTargetVoltage(double volts) {
    m_clawPivot.setVoltage(volts);
  }

  public void setTargetPos(double position) {
    pid_clawPivotPos.setGoal(position);
    double volts = pid_clawPivotPos.calculate(getClawPivotPos());
    setTargetVoltage(volts);
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {

    e_clawPivotEncoder.setPosition(0);
  }

  public double getClawPivotVel() {
    return e_clawPivotEncoder.getVelocity();
  }

  public double getClawPivotPos() {
    return e_clawPivotEncoder.getPosition();
  }

  public void stopMotor() {
    m_clawPivot.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Claw Pivot Velocity", getClawPivotVel());
    SmartDashboard.putNumber("Claw Pivot Position", getClawPivotPos());
  }
}
