// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawPivotSubsystem;

public class PivotClaw extends CommandBase {
  private final ClawPivotSubsystem s_robotClawPivot;
  private final double speed;

  /** Creates a new PivotClaw. */
  public PivotClaw(ClawPivotSubsystem s_robotClawPivot, double speed) {

    this.s_robotClawPivot = s_robotClawPivot;
    this.speed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_robotClawPivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    s_robotClawPivot.setTargetVelocity(speed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_robotClawPivot.setTargetVelocity(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
