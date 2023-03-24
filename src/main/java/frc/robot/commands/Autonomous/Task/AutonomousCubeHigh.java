// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous.Task;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.commands.ShiftGears;
import frc.robot.commands.Autonomous.Command.AutonomousDriveDelay;
import frc.robot.commands.Autonomous.Command.AutonomousDriveDistance;
import frc.robot.commands.Autonomous.Command.AutonomousDrivePitch;
import frc.robot.commands.Autonomous.Command.AutonomousOperateIntake;
import frc.robot.commands.Autonomous.CommandGroup.AutonomousShiftGears;
//import frc.robot.subsystems.ClawPivotSubsystem;
import frc.robot.subsystems.DriveSubsystem;
//import frc.robot.subsystems.ElevatorPivotSubsystem;
//import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousCubeHigh extends SequentialCommandGroup {
  /** Creates a new AutonomousHighCube. */
  public AutonomousCubeHigh(DriveSubsystem s_robotDrive, IntakeSubsystem s_intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutonomousShiftGears(s_robotDrive, DriveSubsystem.gear.kLow),
      new AutonomousOperateIntake(s_intake, -1, 2),
      //new AutonomousDriveDelay(s_robotDrive, 0.2),
      new AutonomousDriveDistance(s_robotDrive, 4000, -0.9),
      new AutonomousDrivePitch(s_robotDrive, 86, -0.9),
      new AutonomousDriveDistance(s_robotDrive, 1500, -0.9),
      new AutonomousDrivePitch(s_robotDrive, 86, -0.9),
      new AutonomousDriveDistance(s_robotDrive, 1000, -0.9),
      new AutonomousDriveDistance(s_robotDrive, 2500, 0.9),
      new AutonomousDrivePitch(s_robotDrive, 86, 0.5) 
    );
  }
}
