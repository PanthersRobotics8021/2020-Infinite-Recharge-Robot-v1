/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class GTADrive extends CommandBase {
  private final DriveTrain m_subsystem;
  private boolean reverse;

  /**
   * Creates a new GTADrive.
   */
  public GTADrive(DriveTrain subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    reverse = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //joystick variables
    //double joyX = Robot.m_oi.GetDriverAxis(Constants.JOY_X);
    double joyY = -Robot.m_oi.GetDriverAxis(Constants.JOY_Y);
    double joyZ = Robot.m_oi.GetDriverAxis(Constants.JOY_Z);
    double throttle = -Robot.m_oi.GetDriverAxis(Constants.JOY_SLIDE);
    boolean thumbButton = Robot.m_oi.GetDriverButtonPressed(Constants.THUMB_BUTTON);
    

    //throttle logic
    throttle = throttle/2 + .5;

    //motor variables
    double turnValue = 0;
    double lMotors = joyY; 
    double rMotors = joyY;


    //reverse system
    if (thumbButton) {
      reverse = !reverse;
    }

    if (reverse) {
      lMotors = -lMotors;
      rMotors = -rMotors;
    }


    //turn logic
    if (joyZ - Constants.TURN_THRESHOLD > Constants.TURN_THRESHOLD) {
      turnValue += (joyZ - Constants.TURN_THRESHOLD);
    }
    else if (joyZ + Constants.TURN_THRESHOLD < -Constants.TURN_THRESHOLD) {
      turnValue += (joyZ + Constants.TURN_THRESHOLD);
    }


    //turning values
    if (turnValue < 0 || turnValue > 0) {
      turnValue *= throttle * .7;
      lMotors += turnValue;
      rMotors -= turnValue;
    }


    //final command
    m_subsystem.setLeftMotors(lMotors * throttle);
    m_subsystem.setRightMotors(rMotors * throttle);
    m_subsystem.displayValues(throttle, lMotors, rMotors, turnValue, reverse);
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setLeftMotors(0);
    m_subsystem.setRightMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
