// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase {

  public enum clawPosition {kOpen, kClosed};
  
  private final DoubleSolenoid ds_left = new DoubleSolenoid(PneumaticsModuleType.REVPH, ClawConstants.kPneumaticLeftForwardChannel, ClawConstants.kPneumaticLeftReverseChannel);
  // TODO: Create new DoubleSolenoid ds_right


  /** Creates a new ClawSubsystem. */
  public ClawSubsystem() {}

  public void open() {
    // TODO: set ds_left and ds_right kReverse (see shiftLowGear in DriveSubsystem)
  }

  public void close() {
    // TODO: set ds_left and ds_right kForward (see shiftLowGear in DriveSubsystem)
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
