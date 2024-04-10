// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.HIDCommands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANdleSystem;

public class ControlCANdle extends Command {
  private CANdleSystem candle;
  private BooleanSupplier shooterReady, hasNote;

  /** Creates a new ControlCANdle. */
  public ControlCANdle(CANdleSystem candle, BooleanSupplier shooterReady, BooleanSupplier hasNote) {
    this.candle = candle;
    this.shooterReady = shooterReady;
    this.hasNote = hasNote;

    addRequirements(candle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!hasNote.getAsBoolean()) {
      candle.setBlue();
    } else if (shooterReady.getAsBoolean()) {
      candle.setGreen();
    } else {
      candle.setOrange();
    }
  }
}
