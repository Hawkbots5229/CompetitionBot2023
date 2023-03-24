// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ElevatorPivotSubsystem;

public class PivotElevator extends CommandBase {

  private final ElevatorPivotSubsystem s_robotElevatorPivot;

  /** Creates a new PivotElevator. */
  public PivotElevator(ElevatorPivotSubsystem s_robotElevatorPivot) {
    this.s_robotElevatorPivot = s_robotElevatorPivot;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_robotElevatorPivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    s_robotElevatorPivot.setTargetPos(RobotContainer.l_elevatorPivotPos.getTargetPosition());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
