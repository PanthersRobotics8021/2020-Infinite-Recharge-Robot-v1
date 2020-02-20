/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  public Shooter() {

  }

  private final TalonSRX motorShooter = new TalonSRX(Constants.MOTOR_SHOOTER_ID);

  public void setShooterMotor(double speed) {
    motorShooter.set(ControlMode.PercentOutput, speed);
  }

  public void displayValues(boolean toggle) {
    Robot.m_oi.DisplayBool("shooter STATE", toggle);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
