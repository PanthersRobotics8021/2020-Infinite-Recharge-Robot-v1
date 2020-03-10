/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ColorChange;

public class ColorDetection extends CommandBase {
  private final ColorChange m_subsystem;

  String setColor;
  boolean matchResult;
  boolean finished;
  
  final ColorSensorV3 colorSensor;
  final ColorMatch colorMatcher;
  
  final Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  final Color greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  final Color redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  final Color yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);


  /**
   * Creates a new ColorDetection.
   */
  public ColorDetection(ColorChange subsystem, String inColor) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

    setColor = inColor;
    colorSensor  = new ColorSensorV3(Constants.I2C_PORT);
    colorMatcher = new ColorMatch();

    colorMatcher.addColorMatch(blueTarget);
    colorMatcher.addColorMatch(greenTarget);
    colorMatcher.addColorMatch(redTarget);
    colorMatcher.addColorMatch(yellowTarget);  

    matchResult = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finished = false;
    m_subsystem.setAdjustMotor(Constants.COLOR_ADJUSTER_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //variables
    Color detectedColor = colorSensor.getColor();
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    String colorName = Robot.m_oi.ColorName(match.color, blueTarget, greenTarget, redTarget, yellowTarget);
    double confidence = match.confidence;


    //color match
    if (setColor != colorName) {
      matchResult = false;
      m_subsystem.setAdjustMotor(Constants.COLOR_ADJUSTER_SPEED);
    }
    else if (setColor == colorName) {
      matchResult = true;
      m_subsystem.setAdjustMotor(0);
      finished = true;
    }


    //final command
    m_subsystem.displayColor(confidence, setColor, matchResult);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setAdjustMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
