// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawPivotSubsystem;

public class AutonomousPivotClaw extends CommandBase {

  private final ClawPivotSubsystem s_clawPivot;
  private final double speed;
  private final double angle;  

  /** Creates a new AutonomousPivotClaw. */
  public AutonomousPivotClaw(ClawPivotSubsystem s_clawPivot, double speed, double angle) {

    this.s_clawPivot = s_clawPivot;
    this.speed = speed;
    this.angle = angle;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_clawPivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    s_clawPivot.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    s_clawPivot.setTargetOutput(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_clawPivot.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return s_clawPivot.getClawPivotPos() >= angle;
  }
}
