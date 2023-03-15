// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutonomousDistance extends CommandBase {
  
  private final DriveSubsystem s_robotDrive;
  private final double distance;
  private final double speed;

  /** Creates a new AutonomousDistance. */
  public AutonomousDistance(DriveSubsystem s_robotDrive, double distance, double speed) {

    this.s_robotDrive = s_robotDrive;
    this.distance = distance;
    this.speed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_robotDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    s_robotDrive.resetEncoders();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    s_robotDrive.drive(speed, speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_robotDrive.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return s_robotDrive.getRobotPosition()>distance;
  }
}
