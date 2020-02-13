/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ColorChange extends SubsystemBase {
  /**
   * Creates a new ColorChange.
   */
  public ColorChange() {

  }

  private final ColorSensorV3 colorSensor = new ColorSensorV3(Constants.I2C_PORT);
  private final TalonSRX motorAdjust = new TalonSRX(Constants.MOTOR_ADJUSTER);

  public void displayColor(double confidence, String setColor, String colorName, boolean matchResult) {
    Robot.m_oi.DisplayInt("color CONFIDENCE", confidence);
    Robot.m_oi.DisplayString("color TARGET COLOR", setColor);
    Robot.m_oi.DisplayString("color DETECTED COLOR", colorName);
    Robot.m_oi.DisplayBool("color MATCH RESULT", matchResult);
  }

  public void setAdjustMotor(double speed) {
    motorAdjust.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    int r = colorSensor.getRed();
    int g = colorSensor.getGreen();
    int b = colorSensor.getRed();

    Robot.m_oi.DisplayInt("color RED", r);
    Robot.m_oi.DisplayInt("color GREEN", g);
    Robot.m_oi.DisplayInt("color BLUE", b);
  }
}
