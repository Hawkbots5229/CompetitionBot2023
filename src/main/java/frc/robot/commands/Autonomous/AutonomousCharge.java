// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShiftGears;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousCharge extends SequentialCommandGroup {
  /** Creates a new AutonomousCharge. */
  public AutonomousCharge(DriveSubsystem s_robotDrive) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ShiftGears(s_robotDrive, DriveSubsystem.gear.kLow),
      new AutonomousDriveDelay(s_robotDrive, 1.5),
      new AutonomousDriveDistance(s_robotDrive, 1200, 0.9),
      new AutonomousDrivePitch(s_robotDrive, 86, 0.5)
    );
  }
}
