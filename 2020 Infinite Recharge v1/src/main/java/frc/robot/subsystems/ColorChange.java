/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ColorChange extends SubsystemBase {
  private final ColorSensorV3 colorSensor;
  private final ColorMatch colorMatcher;

  final Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  final Color greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  final Color redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  final Color yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  /**
   * Creates a new ColorChange.
   */
  public ColorChange() {
    colorSensor  = new ColorSensorV3(Constants.I2C_PORT);
    colorMatcher = new ColorMatch();

    colorMatcher.addColorMatch(blueTarget);
    colorMatcher.addColorMatch(greenTarget);
    colorMatcher.addColorMatch(redTarget);
    colorMatcher.addColorMatch(yellowTarget); 
  }

  private final TalonSRX motorAdjust = new TalonSRX(Constants.MOTOR_ADJUSTER_ID);
  public double rotations = 0;

  public void displayColor(double confidence, String setColor, boolean matchResult) {
    Robot.m_oi.DisplayInt("color CONFIDENCE", confidence);
    Robot.m_oi.DisplayString("color TARGET COLOR", setColor);
    Robot.m_oi.DisplayBool("color MATCH RESULT", matchResult);
  }

  public void displayAutoColor(String initName, double elapsedRotations) {
    Robot.m_oi.DisplayString("INITMATCH", initName);
    Robot.m_oi.DisplayInt("ELAPSED ROT", elapsedRotations);
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
    
    Color detectedColor = colorSensor.getColor();
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    String colorName = Robot.m_oi.ColorName(match.color, blueTarget, greenTarget, redTarget, yellowTarget);

    Robot.m_oi.DisplayInt("color RED", r);
    Robot.m_oi.DisplayInt("color GREEN", g);
    Robot.m_oi.DisplayInt("color BLUE", b);
    Robot.m_oi.DisplayInt("color ROTATIONS", rotations);
    Robot.m_oi.DisplayString("color DETECTED COLOR", colorName);
  }
}
