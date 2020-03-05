/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  public Shooter() {

  }

  private final TalonSRX motorShooter = new TalonSRX(Constants.MOTOR_SHOOTER_ID);
  private final DoubleSolenoid doubleSolenoid = new DoubleSolenoid(Constants.PCM_ID, Constants.TRAPDOOR_FORWARD, Constants.TRAPDOOR_REVERSE);

  
  public void setShooterMotor(double speed) {
    motorShooter.set(ControlMode.PercentOutput, speed);
  }

  
  public void setRam(Value state) {
    doubleSolenoid.set(state);
  }


  public void displayValues(boolean toggle, boolean door) {
    Robot.m_oi.DisplayBool("shooter STATE", toggle);
    Robot.m_oi.DisplayBool("shooter DOOR", door);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
