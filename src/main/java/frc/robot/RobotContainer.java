// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ElevatorPivotSetPos;
import frc.robot.commands.ElevatorSetPos;
import frc.robot.commands.OperateClaw;
import frc.robot.commands.OperateElevator;
import frc.robot.commands.OperateIntake;
import frc.robot.commands.PivotClaw;
import frc.robot.commands.PivotElevator;
import frc.robot.commands.ShiftGears;
import frc.robot.commands.Autonomous.Command.AutonomousDriveStop;
import frc.robot.commands.Autonomous.Task.AutonomousCharge;
import frc.robot.commands.Autonomous.Task.AutonomousChargeLine;
import frc.robot.commands.Autonomous.Task.AutonomousCubeHigh;
import frc.robot.commands.Autonomous.Task.AutonomousCubeLong;
import frc.robot.commands.Autonomous.Task.AutonomousCubeShort;
import frc.robot.commands.Autonomous.Task.AutonomousLine;
import frc.robot.lib.ClawPivotPosition;
import frc.robot.lib.ElevatorPivotPosition;
import frc.robot.lib.ElevatorPosition;
import frc.robot.subsystems.ClawPivotSubsystem;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorPivotSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final ClawPivotSubsystem s_clawPivot = new ClawPivotSubsystem();
  private final ClawSubsystem s_claw = new ClawSubsystem();
  private final DriveSubsystem s_robotDrive = new DriveSubsystem();
  private final ElevatorPivotSubsystem s_elevatorPivot = new ElevatorPivotSubsystem();
  private final ElevatorSubsystem s_elevator = new ElevatorSubsystem();
  private final IntakeSubsystem s_intake = new IntakeSubsystem();

  public static ElevatorPivotPosition l_elevatorPivotPos = new ElevatorPivotPosition(ElevatorPivotSubsystem.ElevatorPivotPos.kHome);
  public static ElevatorPosition l_elevatorPos = new ElevatorPosition(ElevatorSubsystem.ElevatorPos.kHome);
  public static ClawPivotPosition l_clawPivotPos = new ClawPivotPosition(ClawPivotSubsystem.ClawPivotPos.kHome);

  private PivotElevator c_elevatorPivotDefault = new PivotElevator(s_elevatorPivot);
  private PivotClaw c_clawPivotDefault = new PivotClaw(s_clawPivot);
  private OperateElevator c_operateElevatorDefault = new OperateElevator(s_elevator);

  XboxController j_driverController =
      new XboxController(OperatorConstants.kDriverControllerPort);
  
  XboxController j_mechController =
      new XboxController(OperatorConstants.kMechControllerPort);
  
  // Create SmartDashboard chooser for autonomous routines
  private final SendableChooser<Command> sc_autonSelect = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    CameraServer.startAutomaticCapture("Drive Camera", 0);
    CameraServer.startAutomaticCapture("Claw Camera", 1);

    // Configure the trigger bindings
    configureBindings();

    // Setup SmartDashboard Auton options
    sc_autonSelect.setDefaultOption("Don't Move", new AutonomousDriveStop(s_robotDrive));
    sc_autonSelect.addOption("Cube High", new AutonomousCubeHigh(s_robotDrive, s_intake));
    sc_autonSelect.addOption("Cross Line", new AutonomousLine(s_robotDrive));
    sc_autonSelect.addOption("Short Cube Drop", new AutonomousCubeShort(s_robotDrive));
    sc_autonSelect.addOption("Long Cube Drop", new AutonomousCubeLong(s_robotDrive));
    sc_autonSelect.addOption("Charge Station", new AutonomousCharge(s_robotDrive));
    sc_autonSelect.addOption("Hail Mary", new AutonomousChargeLine(s_robotDrive));
    SmartDashboard.putData("Auton Selection", sc_autonSelect);

    // Configure default commands

    s_robotDrive.setDefaultCommand(
      new RunCommand(
        () ->
          s_robotDrive.driveTank(
            -j_driverController.getLeftY(),                   
            -j_driverController.getRightY()),
        s_robotDrive));
    /**
    s_elevatorPivot.setDefaultCommand(
      new RunCommand(
        () ->
          s_elevatorPivot.setTargetOutput(
            -j_mechController.getLeftY()),
        s_elevatorPivot));
    */
    s_elevatorPivot.setDefaultCommand(c_elevatorPivotDefault);

    s_clawPivot.setDefaultCommand(
      new RunCommand(
        () ->
          s_clawPivot.setTargetOutput(
            -j_mechController.getRightY()),
        s_clawPivot));

    //s_clawPivot.setDefaultCommand(c_clawPivotDefault);   

    s_elevator.setDefaultCommand(    
      new RunCommand(
        () ->
          s_elevator.setTargetOutput(
            j_mechController.getLeftTriggerAxis(),
            -j_mechController.getRightTriggerAxis()),
        s_elevator)); 

    //s_elevator.setDefaultCommand(c_operateElevatorDefault);
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

    // Drive Controller

    /** Control Mode: RightBumper-50% */
    /**
    new JoystickButton(j_driverController, Button.kRightBumper.value)
        .onTrue(new RunCommand (() -> s_robotDrive.setMaxOutput(0.5)))
        .onFalse(new RunCommand (() -> s_robotDrive.setMaxOutput(1.0)));  
    */
    /**  Shift Gears: PovUp-High PovDown-Low */
    
    new JoystickButton(j_driverController, Button.kRightBumper.value)
      .onTrue(new ShiftGears(s_robotDrive, DriveSubsystem.gear.kHigh));
    new JoystickButton(j_driverController, Button.kLeftBumper.value)
      .onTrue(new ShiftGears(s_robotDrive, DriveSubsystem.gear.kLow));
    
    // Mech Controller

    /** Intake Wheels: PovUp-Out PovDown-In */
    
    new POVButton(j_mechController, OperatorConstants.kUpDPad)
      .onTrue(new OperateIntake(s_intake, IntakeSubsystem.intakeDir.kIn))
      .onFalse(new OperateIntake(s_intake, IntakeSubsystem.intakeDir.kOff));     
    new POVButton(j_mechController, OperatorConstants.kDownDPad)
      .onTrue(new OperateIntake(s_intake, IntakeSubsystem.intakeDir.kOut))
      .onFalse(new OperateIntake(s_intake, IntakeSubsystem.intakeDir.kOff));
    
    /** Claw: LeftBumper-Open RightBumper-Close */
    
    new JoystickButton(j_mechController, Button.kLeftBumper.value)
        .onTrue(new OperateClaw(s_claw, ClawSubsystem.clawPosition.kOpen));
    new JoystickButton(j_mechController, Button.kRightBumper.value)
        .onTrue(new OperateClaw(s_claw, ClawSubsystem.clawPosition.kClosed));

    /** Elevator Pivot: A-Home B-Extend */
    
    new JoystickButton(j_mechController, Button.kA.value)
        .onTrue(new ElevatorPivotSetPos(ElevatorPivotSubsystem.ElevatorPivotPos.kHome));
    new JoystickButton(j_mechController, Button.kB.value)
        .onTrue(new ElevatorPivotSetPos(ElevatorPivotSubsystem.ElevatorPivotPos.kExtend));
    /**
    new JoystickButton(j_mechController, Button.kX.value)
        .onTrue(new ElevatorSetPos(ElevatorSubsystem.ElevatorPos.kHome));
    new JoystickButton(j_mechController, Button.kY.value)
        .onTrue(new ElevatorSetPos(ElevatorSubsystem.ElevatorPos.kExtend));    
    */
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
