/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.commands.ClimberControl;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.GTADrive;
import frc.robot.commands.InputDrive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Climber m_climber = new Climber();
  private final ClimberControl m_climberUp = new ClimberControl(m_climber, Value.kForward);
  private final ClimberControl m_climberDown = new ClimberControl(m_climber, Value.kReverse);

  private final DriveTrain m_driveTrain = new DriveTrain();
  private final GTADrive m_gtaDrive = new GTADrive(m_driveTrain);
  private final InputDrive m_90Right = new InputDrive(m_driveTrain, .5, -.3, .3);
  private final InputDrive m_90Left = new InputDrive(m_driveTrain, .5, .3, -.3);

  private final InputDrive m_autoCommand = new InputDrive(m_driveTrain, 1, 1, 3);

  Joystick driverController = new Joystick(Constants.DRIVER_CONTROLLER);
  Button threeButton = new JoystickButton(driverController, 3);
  Button fourButton = new JoystickButton(driverController, 4);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveTrain.setDefaultCommand(m_gtaDrive);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    threeButton.whenPressed(m_climberUp);
    fourButton.whenPressed(m_climberDown);
    /*
    Robot.m_oi.hatRight.whenPressed(m_90Right);
    Robot.m_oi.hatLeft.whenPressed(m_90Left);
    */
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
