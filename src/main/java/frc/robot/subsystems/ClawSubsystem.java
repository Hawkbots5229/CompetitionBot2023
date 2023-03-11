// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase {

  public enum gear{kOpen, kClosed};
  // TODO: Create new DoubleSolenoid ds_left and ds_right

  /** Creates a new ClawSubsystem. */
  public ClawSubsystem() {}

  public void open() {
    // TODO: set ds_left and ds_right kReverse
  }

  public void close() {
    // TODO: set ds_left and ds_right kForward
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
