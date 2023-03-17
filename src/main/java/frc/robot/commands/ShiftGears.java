// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShiftGears extends InstantCommand {

  private final DriveSubsystem s_robotDrive;
  private final DriveSubsystem.gear gearPos;

  public ShiftGears(DriveSubsystem s_robotDrive, DriveSubsystem.gear gearPos) {
    this.s_robotDrive = s_robotDrive;
    this.gearPos = gearPos;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_robotDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    switch(gearPos) {
      case kHigh: 
        //s_robotDrive.shiftHighGear();
        break;
      case kLow: 
        //s_robotDrive.shiftLowGear();
        break;
      default:
        throw new AssertionError("Illegal value: " + gearPos);   
    }
  }
}
