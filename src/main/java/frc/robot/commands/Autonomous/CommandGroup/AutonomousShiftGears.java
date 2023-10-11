// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous.CommandGroup;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ShiftGears;
import frc.robot.commands.Autonomous.Command.AutonomousDriveDelay;
import frc.robot.commands.Autonomous.Command.AutonomousDriveDistance;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousShiftGears extends SequentialCommandGroup {
  /** Creates a new AutonomousShiftGears. */
  public AutonomousShiftGears(DriveSubsystem s_robotDrive, DriveSubsystem.gear gearPos) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutonomousDriveDelay(s_robotDrive, 0.5),
      new ShiftGears(s_robotDrive, gearPos),
      new AutonomousDriveDistance(s_robotDrive, 5, -0.2)
    );
  }
}
