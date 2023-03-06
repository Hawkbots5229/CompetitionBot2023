// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;

import frc.robot.commands.AutonomousDefault;

import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem s_robotDrive = new DriveSubsystem();
  // TODO: create s_robotArm, s_robotElevator, and s_robotIntake

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandPS4Controller j_driverController =
      new CommandPS4Controller(OperatorConstants.kDriverControllerPort);
  // TODO: create j_mechController with kMechControllerPort

  // Create SmartDashboard chooser for autonomous routines
  private final SendableChooser<Command> sc_autonSelect = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    CameraServer.startAutomaticCapture("Usb Camera 0", 0);
    CameraServer.startAutomaticCapture("Usb Camera 1", 1);

    // Configure the trigger bindings
    configureBindings();

    // Setup SmartDashboard Auton options
    sc_autonSelect.setDefaultOption("Basic Auto", new AutonomousDefault(s_robotDrive));
    //sc_autonSelect.addOption("Auto 2", new Autonomous2(s_robotDrive, s_robotArm, s_robotElevator, s_robotIntake));
    SmartDashboard.putData(sc_autonSelect);

    // Configure default commands
    // Set the default drive command to split-stick tank drive
    s_robotDrive.setDefaultCommand(
        new RunCommand(
            () ->
                s_robotDrive.drive(
                    j_driverController.getLeftY(),                   
                    j_driverController.getRightY()),
            s_robotDrive));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    // TODO: Schedule Commands

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    
    return sc_autonSelect.getSelected();
  }
}
