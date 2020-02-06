/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class InputDrive extends CommandBase {
  private final DriveTrain m_subsystem;
  double rMotors, lMotors, setTime, elapsedTime;
  Timer timer = new Timer();
  Boolean timeOut;

  /**
   * Creates a new InputDrive.
   */
  public InputDrive(DriveTrain subsystem, double inRMotors, double inLMotors, double inTime) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    rMotors = inRMotors;
    lMotors = inLMotors;
    setTime = inTime;
    timeOut = false;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    m_subsystem.setRightMotors(rMotors);
    m_subsystem.setLeftMotors(lMotors);
    timer.reset();
    timer.start();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double elapsedTime = timer.get();

    if (elapsedTime == setTime) {
      timer.stop();
      timeOut = true;
    }

    m_subsystem.displayInputDriveValues(elapsedTime);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setRightMotors(0);
    m_subsystem.setLeftMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timeOut;
  }
}
