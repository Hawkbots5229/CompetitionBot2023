// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class AutonomousOperateElevator extends CommandBase {

  private final ElevatorSubsystem s_elevator;
  private final double speed;
  private final double distance;

  /** Creates a new AutonomousElevator. */
  public AutonomousOperateElevator(ElevatorSubsystem s_elevator, double speed, double distance) {

    this.s_elevator = s_elevator;
    this.speed = speed;
    this.distance = distance;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // TODO: add encoder reset
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    s_elevator.setTargetOutput(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_elevator.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return s_elevator.getElevatorPos() >= distance;
  }
}
