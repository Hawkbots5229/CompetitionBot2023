// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClawSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class OperateClaw extends InstantCommand {
  
  private final ClawSubsystem s_robotClaw;
  private final ClawSubsystem.clawPosition clawPos;

  public OperateClaw(ClawSubsystem s_robotClaw, ClawSubsystem.clawPosition clawPos) {

    this.s_robotClaw = s_robotClaw;
    this.clawPos = clawPos;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_robotClaw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    switch(clawPos) {
      case kOpen: 
        s_robotClaw.open();
        break;
      case kClosed: 
        s_robotClaw.close();
        break;
      default:
        throw new AssertionError("Illegal value: " + clawPos);   
    }
  }
}
