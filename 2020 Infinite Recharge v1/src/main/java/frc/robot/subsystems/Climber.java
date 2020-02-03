/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  public Climber() {

  }

  private final DoubleSolenoid doubleSolenoid = new DoubleSolenoid(Constants.PCM_ID, Constants.FORWARD_CHANNEL, Constants.REVERSE_CHANNEL);

  public void setRam(Value state) {
    doubleSolenoid.set(state);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
