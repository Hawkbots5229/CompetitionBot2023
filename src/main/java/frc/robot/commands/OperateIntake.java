// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class OperateIntake extends CommandBase {

  private final IntakeSubsystem s_robotIntake;
  private final IntakeSubsystem.intakeDir direction;

  /** Creates a new OperateIntake. */
  public OperateIntake(IntakeSubsystem s_robotIntake, IntakeSubsystem.intakeDir direction) {

    this.s_robotIntake = s_robotIntake;
    this.direction = direction;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_robotIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    switch(direction) {
      case kIn: 
        s_robotIntake.wheelsIn();
        break;
      case kOut: 
        s_robotIntake.wheelsOut();
        break;
      case kOff:
        s_robotIntake.stopMotor();
        break;
      default:
        throw new AssertionError("Illegal value: " + direction);   
    };
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_robotIntake.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
