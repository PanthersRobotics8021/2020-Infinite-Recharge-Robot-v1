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
import frc.robot.subsystems.Shooter;

public class ShooterControl extends CommandBase {
  private final Shooter m_subsystem;
  private boolean toggle = false;

  /**
   * Creates a new ShooterControl.
   */
  public ShooterControl(Shooter subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean startButton = Robot.m_oi.GetOperatorButtonPressed(Constants.START_BUTTON);

    if (startButton) {
      toggle = !toggle;
    }

    if (toggle) {
      m_subsystem.setShooterMotor(Constants.SHOOTER_SPEED);
    }
    else {
      m_subsystem.setShooterMotor(0);
    }

    m_subsystem.displayValues(toggle);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setShooterMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
