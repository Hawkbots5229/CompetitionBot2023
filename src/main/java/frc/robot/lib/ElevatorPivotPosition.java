// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lib;

import frc.robot.subsystems.ElevatorPivotSubsystem;

/** Add your docs here. */
public class ElevatorPivotPosition {

    private double targetPosition;

    public ElevatorPivotPosition(ElevatorPivotSubsystem.ElevatorPivotPos pos) {
        this.targetPosition = updTargetPosition(pos); 
    }

    private double updTargetPosition(ElevatorPivotSubsystem.ElevatorPivotPos pos) {
    switch(pos) {
        case kHome: 
            return 0;
        case kExtend: 
            return 40;
        default:
            throw new AssertionError("Illegal value: " + pos);   
        }
    }

    public void setTargetPosition(ElevatorPivotSubsystem.ElevatorPivotPos pos) {
        this.targetPosition = updTargetPosition(pos);
    }

    public double getTargetPosition() {
        return this.targetPosition;
    }
}
