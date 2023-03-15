// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;

public class ClawSubsystem extends SubsystemBase {

  public enum clawPosition {kOpen, kClosed};
  private String clawPos;
  
  private final DoubleSolenoid ds_left = new DoubleSolenoid(PneumaticsModuleType.REVPH, ClawConstants.kPneumaticLeftForwardChannel, ClawConstants.kPneumaticLeftReverseChannel);
  private final DoubleSolenoid ds_right = new DoubleSolenoid(PneumaticsModuleType.REVPH, ClawConstants.kPneumaticRightForwardChannel, ClawConstants.kPneumaticRightReverseChannel);


  /** Creates a new ClawSubsystem. */
  public ClawSubsystem() {
    clawPos = "Off";
  }

  public void open() {
    clawPos = "Opened";
    ds_left.set(DoubleSolenoid.Value.kReverse);
    ds_right.set(DoubleSolenoid.Value.kReverse);
  }

  public void close() {
    clawPos = "Closed";
    ds_left.set(DoubleSolenoid.Value.kForward);
    ds_right.set(DoubleSolenoid.Value.kForward);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putString("Claw Position", clawPos);
  }
}
