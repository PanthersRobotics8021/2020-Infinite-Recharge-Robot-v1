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

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {

  }

  private final TalonSRX motorLeft = new TalonSRX(Constants.MOTOR_LEFT_ID);
  private final TalonSRX motorRight = new TalonSRX(Constants.MOTOR_RIGHT_ID);

  public void setLeftMotors(double speed) {
    motorLeft.set(ControlMode.PercentOutput, speed);
  }

  public void setRightMotors(double speed) {
    motorRight.set(ControlMode.PercentOutput, -speed);
  }

  public void displayValues(double throttle, double lMotors, double rMotors, double turnValue, boolean reverse) {
    Robot.m_oi.DisplayInt("drive THROTTLE", throttle);
    Robot.m_oi.DisplayInt("drive LMOTORS", lMotors);
    Robot.m_oi.DisplayInt("drive RMOTORS", rMotors);
    Robot.m_oi.DisplayInt("drive TURNING", turnValue);
    Robot.m_oi.DisplayBool("drive REVERSE", reverse);
  }

  public void displayInputDriveValues(double elapsedTime) {
    Robot.m_oi.DisplayInt("inputdrive ELAPSED TIME", elapsedTime);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
