/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ColorChange;

public class InputWheelChange extends CommandBase {
  private final ColorChange m_subsystem;
  Timer timer = new Timer();

  /**
   * Creates a new InputWheelChange.
   */
  public InputWheelChange(ColorChange subsystem) {
    m_subsystem = subsystem;;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //controller variables
    boolean addClick = Robot.m_oi.GetOperatorButtonPressed(Constants.RIGHT_TRIGGER);
    boolean subClick = Robot.m_oi.GetOperatorButtonPressed(Constants.LEFT_TRIGGER);
    

    //rotation adjust
    if (addClick) {
      m_subsystem.rotations += 1;
    }
    if (subClick) {
      m_subsystem.rotations -= 1;
    }

    //auto rotate variables
    double rotations = m_subsystem.rotations;
    double rotationTime = rotations * Constants.ROTATION_TIME_FACTOR;
    double elapsedTime = timer.get();
    double motorSpeed = Constants.REVOLUTIONS_ADJUSTER_SPEED;
    

    //motor set
    if (rotations > 0) {
      m_subsystem.setAdjustMotor(motorSpeed);
    }
    else if (rotations <= 0) {
      m_subsystem.setAdjustMotor(0);
    }


    //rotation reset
    if (elapsedTime >= rotationTime) {
      m_subsystem.rotations = 0;
    } 


    //timer reset
    if (rotations == 0) {
      timer.reset();
    }


    //final command
    m_subsystem.displayAuto(elapsedTime, rotationTime);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setAdjustMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
