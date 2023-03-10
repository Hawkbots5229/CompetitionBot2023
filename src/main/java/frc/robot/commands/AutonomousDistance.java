// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.swing.text.Position;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.subsystems.DriveSubsystem;
import pabeles.concurrency.ConcurrencyOps.Reset;

public class AutonomousDistance extends CommandBase {
  private static final String Position = null;
  private final DriveSubsystem s_robotDrive;
  private final double distance;
  private final double speed;
  private Object RobotPosition;


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
    
    drive();
  }

  // Called once the command ends or is interrupted.
  private void drive() {
  }

  @Override
  public void end(boolean interrupted) {
    s_robotDrive.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // TODO return true when robot reaches position. Hint: getRobotPosition
    return s_robotDrive.getRobotPosition()>distance;
  }
}
