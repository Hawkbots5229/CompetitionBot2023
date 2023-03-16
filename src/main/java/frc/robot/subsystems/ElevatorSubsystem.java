// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.ElevatorConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

  private final CANSparkMax m_elevator =
    new CANSparkMax(ElevatorConstants.kElevatorMotorPort, MotorType.kBrushless);
  
  private final RelativeEncoder e_ElevatorEncoder = m_elevator.getEncoder();

  private final SparkMaxPIDController pid_ElevatorVelControl = m_elevator.getPIDController();

  /** Creates a new ExampleSubsystem. */
  public ElevatorSubsystem() {

    m_elevator.restoreFactoryDefaults();
    m_elevator.setInverted(ElevatorConstants.kElevatorMotorIntverted);
    m_elevator.setIdleMode(ElevatorConstants.kIdleMode);
    m_elevator.setSmartCurrentLimit(ElevatorConstants.kCurrentLimit);
    m_elevator.setClosedLoopRampRate(ElevatorConstants.kClosedLoopRampRate);
    
    pid_ElevatorVelControl.setFF(ElevatorConstants.kFVel, ElevatorConstants.kVelPidSlot);
    pid_ElevatorVelControl.setP(ElevatorConstants.kPVel, ElevatorConstants.kVelPidSlot);
    pid_ElevatorVelControl.setD(ElevatorConstants.kDVel, ElevatorConstants.kVelPidSlot);
    pid_ElevatorVelControl.setI(ElevatorConstants.kIVel, ElevatorConstants.kVelPidSlot);
  }
  
  public void setTargetOutput(double output) {
    m_elevator.set(output);
  }

  public void setTargetVelocity(double upVel, double downVel) {
    pid_ElevatorVelControl.setReference(
      (upVel + downVel),
      CANSparkMax.ControlType.kVelocity,
      ElevatorConstants.kVelPidSlot);
  }

  public double getElevatorVel() {
    return e_ElevatorEncoder.getVelocity();
  }

  public void stopMotor() {
    m_elevator.stopMotor();
  }

   @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Elevator Velocity", getElevatorVel());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
