/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimberControl extends CommandBase {
  private final Climber m_subsystem;
  private final Value sValue;

  /**
   * Creates a new ClimberControl.
   */
  public ClimberControl(Climber subsystem, Value state) {
    sValue = state;
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    String stringValue = "Reverse";

    if (sValue == Value.kForward) {
      stringValue = "Forward";
    }
    else if (sValue == Value.kReverse) {
      stringValue = "Reverse";
    }
    
    m_subsystem.setRam(sValue);
    m_subsystem.displayValues(stringValue);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
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
