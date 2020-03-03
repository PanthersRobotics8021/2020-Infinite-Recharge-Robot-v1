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
import frc.robot.subsystems.Climber;

public class ClimberControl extends CommandBase {
  private final Climber m_subsystem;
  private boolean toggle;

  /**
   * Creates a new ClimberControl.
   */
  public ClimberControl(Climber subsystem) {
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
    boolean lBumper = Robot.m_oi.GetOperatorButtonPressed(Constants.LEFT_BUMPER);
    boolean rBumper = Robot.m_oi.GetOperatorButtonPressed(Constants.RIGHT_BUMPER);


    if (lBumper || rBumper) {
      toggle = !toggle;
    }

    if (toggle == true) {
      m_subsystem.setRam(Value.kForward);
    }
    else if (toggle == false) {
      m_subsystem.setRam(Value.kReverse);
    }
    
    m_subsystem.displayValues(toggle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.setRam(Value.kReverse);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
