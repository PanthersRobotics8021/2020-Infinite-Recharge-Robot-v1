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
    double joyX = Robot.m_oi.GetAxis(Constants.JOY_X);
    double joyY = Robot.m_oi.GetAxis(Constants.JOY_Y);
    double joyZ = Robot.m_oi.GetAxis(Constants.JOY_Z);
    double throttle = Robot.m_oi.GetAxis(Constants.JOY_SLIDE);
    boolean thumbButton = Robot.m_oi.GetButton(Constants.THUMB_BUTTON);


    //motor variables
    double turnValue = 0;
    double lMotors = joyY; 
    double rMotors = joyY;


    //throttle logic
    if (throttle <= 0) {
      throttle += 1;
      throttle /= 2;
    }


    //reverse system
    if (thumbButton) {
      reverse = !reverse;
    }

    if (reverse) {
      lMotors = -lMotors;
      rMotors = -rMotors;
    }


    //turn logic
    if (joyX > Constants.TURN_THRESHOLD || joyX < -Constants.TURN_THRESHOLD) {
      turnValue += joyX;
    }

    if (joyZ > Constants.TURN_THRESHOLD || joyZ < -Constants.TURN_THRESHOLD) {
      turnValue += joyZ;
    }


    //turning values
    if (turnValue < 0 || turnValue > 0) {
      //turnValue *= .6;
      lMotors *= Constants.MOTOR_SPEED_FACTOR;
      rMotors *= Constants.MOTOR_SPEED_FACTOR;
      lMotors -= turnValue * Constants.MOTOR_SPEED_FACTOR;
      rMotors += turnValue * Constants.MOTOR_SPEED_FACTOR;
    }
    else {
      lMotors *= Constants.MOTOR_SPEED_FACTOR;
      rMotors *= Constants.MOTOR_SPEED_FACTOR;
    }

    //final command
    m_subsystem.setLeftMotors(lMotors * throttle);
    m_subsystem.setRightMotors(rMotors * throttle);
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
