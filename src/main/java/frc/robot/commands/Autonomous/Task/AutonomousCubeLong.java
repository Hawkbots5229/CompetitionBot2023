// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous.Task;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.commands.ShiftGears;
import frc.robot.commands.Autonomous.Command.AutonomousDriveDelay;
import frc.robot.commands.Autonomous.Command.AutonomousDriveDistance;
import frc.robot.commands.Autonomous.CommandGroup.AutonomousShiftGears;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousCubeLong extends SequentialCommandGroup {
  /** Creates a new AutonomousCubeLong. */
  public AutonomousCubeLong(DriveSubsystem s_robotDrive) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutonomousShiftGears(s_robotDrive, DriveSubsystem.gear.kHigh),
      //new AutonomousDriveDelay(s_robotDrive, 1.5),
      new AutonomousDriveDistance(s_robotDrive, 100, -0.5),
      new AutonomousDriveDistance(s_robotDrive, 100, 0.4),
      new AutonomousDriveDistance(s_robotDrive, 400, -0.5));
  }
}