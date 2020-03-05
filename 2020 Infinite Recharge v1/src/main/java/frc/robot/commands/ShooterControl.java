/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class ShooterControl extends CommandBase {
  private final Shooter m_subsystem;
  private boolean toggle = false;
  private boolean door = true;

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
    //variables
    boolean backButton = Robot.m_oi.GetOperatorButtonPressed(Constants.BACK_BUTTON);
    boolean startButton = Robot.m_oi.GetOperatorButtonPressed(Constants.START_BUTTON);


    //toggle value
    if (backButton) {
      toggle = !toggle;
    }

    if (startButton) {
      door = !door;
    }


    //final commands
    if (toggle) {
      m_subsystem.setShooterMotor(Constants.SHOOTER_SPEED);
    }
    else {
      m_subsystem.setShooterMotor(0);
    }

    if (door) {
      m_subsystem.setRam(Value.kForward);
    }
    else {
      m_subsystem.setRam(Value.kReverse);
    }

    m_subsystem.displayValues(toggle, door);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setShooterMotor(0);
    m_subsystem.setRam(Value.kForward);
    toggle = false;
    door = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
