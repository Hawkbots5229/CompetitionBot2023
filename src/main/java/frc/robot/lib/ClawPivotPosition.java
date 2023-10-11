// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lib;

import frc.robot.subsystems.ClawPivotSubsystem;

/** Add your docs here. */
public class ClawPivotPosition {
    private double targetPosition;

    public ClawPivotPosition(ClawPivotSubsystem.ClawPivotPos pos) {
        this.targetPosition = updTargetPosition(pos); 
    }

    private double updTargetPosition(ClawPivotSubsystem.ClawPivotPos pos) {
    switch(pos) {
        case kHome: 
            return 0;
        case kExtend: 
            return 0;
        default:
            throw new AssertionError("Illegal value: " + pos);   
        }
    }

    public void setTargetPosition(ClawPivotSubsystem.ClawPivotPos pos) {
        this.targetPosition = updTargetPosition(pos);
    }

    public double getTargetPosition() {
        return this.targetPosition;
    }
}
