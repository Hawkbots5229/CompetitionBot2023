// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.ElevatorConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

  public enum ElevatorPos{kHome, kExtend};

  private final CANSparkMax m_elevator =
    new CANSparkMax(ElevatorConstants.kElevatorMotorPort, MotorType.kBrushless);
  
  private final RelativeEncoder e_ElevatorEncoder = m_elevator.getEncoder();

  private final SparkMaxPIDController pid_ElevatorVelControl = m_elevator.getPIDController();

  private final ProfiledPIDController pid_elevatorPos;

  /** Creates a new ExampleSubsystem. */
  public ElevatorSubsystem() {

    m_elevator.restoreFactoryDefaults();
    m_elevator.setInverted(ElevatorConstants.kElevatorMotorIntverted);
    m_elevator.setIdleMode(ElevatorConstants.kIdleMode);
    m_elevator.setSmartCurrentLimit(ElevatorConstants.kCurrentLimit);
    m_elevator.setClosedLoopRampRate(ElevatorConstants.kClosedLoopRampRate);
    m_elevator.setOpenLoopRampRate(ElevatorConstants.kOpenLoopRampRate);

    e_ElevatorEncoder.setPositionConversionFactor(ElevatorConstants.kEncoderRevToInches);
    e_ElevatorEncoder.setVelocityConversionFactor(ElevatorConstants.kEncoderRpmToInchesPerSec);
    
    pid_ElevatorVelControl.setFF(ElevatorConstants.kFVel, ElevatorConstants.kVelPidSlot);
    pid_ElevatorVelControl.setP(ElevatorConstants.kPVel, ElevatorConstants.kVelPidSlot);
    pid_ElevatorVelControl.setD(ElevatorConstants.kDVel, ElevatorConstants.kVelPidSlot);
    pid_ElevatorVelControl.setI(ElevatorConstants.kIVel, ElevatorConstants.kVelPidSlot);

    pid_elevatorPos = new ProfiledPIDController(ElevatorConstants.kPPos, ElevatorConstants.kDPos, ElevatorConstants.kDPos, new TrapezoidProfile.Constraints(ElevatorConstants.kMaxVel,  ElevatorConstants.kMaxAcc));
    pid_elevatorPos.setTolerance(ElevatorConstants.kPosErrTolerance);
  }
  
  public void setTargetOutput(double upVel, double downVel) {
    m_elevator.set((upVel+downVel));
  }

  public void setTargetVelocity(double upVel, double downVel) {
    pid_ElevatorVelControl.setReference(
      (upVel+downVel)*ElevatorConstants.kMaxVel,
      CANSparkMax.ControlType.kVelocity,
      ElevatorConstants.kVelPidSlot);
  }

  public void setTargetVoltage(double volts) {
    m_elevator.setVoltage(volts);
  }

  public void setTargetPos(double position) {
    pid_elevatorPos.setGoal(position);
    double volts = pid_elevatorPos.calculate(getElevatorPos());
    setTargetVoltage(volts);
  }

  public double getElevatorVel() {
    return e_ElevatorEncoder.getVelocity();
  }

  public double getElevatorPos() {
    return e_ElevatorEncoder.getPosition();
  }

  public void stopMotor() {
    m_elevator.stopMotor();
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {

    e_ElevatorEncoder.setPosition(0);
  }

   @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Elevator Velocity", getElevatorVel());
    SmartDashboard.putNumber("Elevator Position", getElevatorPos());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
