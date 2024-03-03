// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANdleConstants;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdleConfiguration;
import com.ctre.phoenix.led.ColorFlowAnimation;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import com.ctre.phoenix.led.CANdle.VBatOutputMode;
import com.ctre.phoenix.led.ColorFlowAnimation.Direction;

public class CANdleSystem extends SubsystemBase {
  private final CANdle candle = new CANdle(CANdleConstants.id, "rio");

  public enum AnimationTypes {
    ColorFlow,
  }

  private AnimationTypes currentAnimation;
  private Animation toAnimate;

  /** Creates a new CANdle. */
  public CANdleSystem() {
    CANdleConfiguration config = new CANdleConfiguration();

    config.statusLedOffWhenActive = true;
    config.disableWhenLOS = true;
    config.stripType = LEDStripType.GRB;
    config.brightnessScalar = 0.1;
    config.vBatOutputMode = VBatOutputMode.Off;

    changeAnimation(AnimationTypes.ColorFlow);

    candle.configAllSettings(config);
  }

  public AnimationTypes getCurrentAnimation() {
    return currentAnimation;
  }

  public void changeAnimation(AnimationTypes toChange) {
    currentAnimation = toChange;

    switch (toChange) {
      case ColorFlow:
        toAnimate = new ColorFlowAnimation(128, 20, 70, 0, 0.7, CANdleConstants.ledCount, Direction.Forward);
        break;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    candle.animate(toAnimate);
  }
}