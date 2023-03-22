// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClawPivotSubsystem;
import frc.robot.subsystems.ElevatorPivotSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousHighCube extends SequentialCommandGroup {
  /** Creates a new AutonomousHighCube. */
  public AutonomousHighCube(ElevatorSubsystem s_elevator, IntakeSubsystem s_intake, ClawPivotSubsystem s_clawPivot, ElevatorPivotSubsystem s_elevatorPivot) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new AutonomousPivotElevator(s_elevatorPivot, 0, 0),
      new AutonomousPivotClaw(s_clawPivot, 0, 0),
      new AutonomousOperateElevator(s_elevator, 0, 0),
      new AutonomousOperateIntake(s_intake, 0, 0)
    );
  }
}
