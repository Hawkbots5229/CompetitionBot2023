// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class OperateIntake extends CommandBase {

  private final IntakeSubsystem s_robotIntake;
  private final double speed;

  /** Creates a new OperateIntake. */
  public OperateIntake(IntakeSubsystem s_robotIntake, double speed) {

    this.s_robotIntake = s_robotIntake;
    this.speed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_robotIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    s_robotIntake.setTargetVelocity(speed);

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
