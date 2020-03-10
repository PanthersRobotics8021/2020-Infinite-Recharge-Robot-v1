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

public class InputWheelChangeColorBased extends CommandBase {
  private final ColorChange m_subsystem;
  boolean debounce;
  double elapsedRotations = 0;

  Color initColor;
  ColorMatchResult initMatch;
  String initName = "None";

  final ColorSensorV3 colorSensor;
  final ColorMatch colorMatcher;    
  final Color blueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  final Color greenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  final Color redTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  final Color yellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  /**
   * Creates a new InputWheelChangeColorBased.
   */
  public InputWheelChangeColorBased(ColorChange subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

    colorSensor  = new ColorSensorV3(Constants.I2C_PORT);
    colorMatcher = new ColorMatch();

    colorMatcher.addColorMatch(blueTarget);
    colorMatcher.addColorMatch(greenTarget);
    colorMatcher.addColorMatch(redTarget);
    colorMatcher.addColorMatch(yellowTarget); 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {    

    //joystick variables
    boolean addClick = Robot.m_oi.GetOperatorButtonPressed(Constants.RIGHT_TRIGGER);
    boolean subClick = Robot.m_oi.GetOperatorButtonPressed(Constants.LEFT_TRIGGER);


    //rotation adjust
    if (addClick && m_subsystem.rotations == 0) {
      m_subsystem.rotations += 1;
      debounce = true;
      initColor = colorSensor.getColor();
      initMatch = colorMatcher.matchClosestColor(initColor);
      initName = Robot.m_oi.ColorName(initMatch.color, blueTarget, greenTarget, redTarget, yellowTarget);
    }
    else if (addClick) {
      m_subsystem.rotations += 1;
    }
    if (subClick && m_subsystem.rotations != 0) {
      m_subsystem.rotations -= 1;
    }
    

    //command variables
    Color detectedColor = colorSensor.getColor();
    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    String colorName = Robot.m_oi.ColorName(match.color, blueTarget, greenTarget, redTarget, yellowTarget);
    double rotations = m_subsystem.rotations;


    //motor set
    if (rotations > 0) {
      m_subsystem.setAdjustMotor(Constants.REVOLUTIONS_ADJUSTER_SPEED);
    }
    else if (rotations <= 0) {
      m_subsystem.setAdjustMotor(0);
    }
    

    //rotation reset
    if (elapsedRotations >= rotations) {
      m_subsystem.rotations = 0;
      elapsedRotations = 0;
      initName = "None";
    } 

    
    //color match
    if (initName == colorName && debounce == false) {
      elapsedRotations += .5;
      debounce = true;
    }
    else if (initName != colorName) {
      debounce = false;
    }

    m_subsystem.displayAutoColor(initName, elapsedRotations);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.rotations = 0;
    elapsedRotations = 0;
    initName = "None";
    debounce = false;
    m_subsystem.setAdjustMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
