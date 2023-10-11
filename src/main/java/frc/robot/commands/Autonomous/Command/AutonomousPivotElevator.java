// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous.Command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorPivotSubsystem;

public class AutonomousPivotElevator extends CommandBase {

  private final ElevatorPivotSubsystem s_elevatorPivot;
  private final double speed;
  private final double angle;

  /** Creates a new AutonomousElevatorPivot. */
  public AutonomousPivotElevator(ElevatorPivotSubsystem s_elevatorPivot, double speed, double angle) {
    
    this.s_elevatorPivot = s_elevatorPivot;
    this.speed = speed;
    this.angle = angle;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_elevatorPivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    s_elevatorPivot.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    s_elevatorPivot.setTargetOutput(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_elevatorPivot.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return s_elevatorPivot.getElevatorPivotPos() >= angle;
  }
}
