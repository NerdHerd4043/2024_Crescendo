// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {
  private CANSparkMax leftArmMotor = new CANSparkMax(ArmConstants.leftArmMotorID, MotorType.kBrushless);
  private CANSparkMax rightArmMotor = new CANSparkMax(ArmConstants.rightArmMotorID, MotorType.kBrushless);

  @SuppressWarnings("unused")
  private SparkLimitSwitch rightReverseLimitSwitch;

  private CANcoder encoder = new CANcoder(ArmConstants.encoderID);

  /** Creates a new Arm. */
  public Arm() {
    leftArmMotor.restoreFactoryDefaults();
    rightArmMotor.restoreFactoryDefaults();

    leftArmMotor.setIdleMode(IdleMode.kBrake);
    rightArmMotor.setIdleMode(IdleMode.kBrake);

    rightArmMotor.setInverted(true);
    leftArmMotor.follow(rightArmMotor, true);

    rightReverseLimitSwitch = rightArmMotor.getReverseLimitSwitch(SparkLimitSwitch.Type.kNormallyClosed);
  }

  public void setArmSpeed(double speed) {
    rightArmMotor.set(speed);
  }

  public void setArmVoltage(double voltage) {
    rightArmMotor.setVoltage(voltage);
  }

  public double getEncoder() {
    return encoder.getAbsolutePosition().getValueAsDouble();
  }

  public double getEncoderRadians() {
    return getEncoder() * 2 * Math.PI;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
