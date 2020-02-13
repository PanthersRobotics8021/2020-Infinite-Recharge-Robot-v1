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
import frc.robot.commands.ColorDetection;
import frc.robot.commands.GTADrive;
import frc.robot.commands.InputDrive;
import frc.robot.commands.InputWheelChange;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorChange;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  //climber
  private final Climber m_climber = new Climber();
  private final ClimberControl m_climberUp = new ClimberControl(m_climber, Value.kForward);
  private final ClimberControl m_climberDown = new ClimberControl(m_climber, Value.kReverse);

  //color change
  private final ColorChange m_colorChange = new ColorChange();
  private final ColorDetection m_changeBlue = new ColorDetection(m_colorChange, "Blue");
  private final ColorDetection m_changeGreen = new ColorDetection(m_colorChange, "Green");
  private final ColorDetection m_changeRed = new ColorDetection(m_colorChange, "Red");
  private final ColorDetection m_changeYellow = new ColorDetection(m_colorChange, "Yellow");
  private final InputWheelChange m_autoRotate = new InputWheelChange(m_colorChange);

  //drivetrain
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final GTADrive m_gtaDrive = new GTADrive(m_driveTrain);
  private final InputDrive m_90Right = new InputDrive(m_driveTrain, -.3, .3, .5);
  private final InputDrive m_90Left = new InputDrive(m_driveTrain, .3, -.3, .5);

  //auto command
  private final InputDrive m_autoCommand = new InputDrive(m_driveTrain, .1, .1, 2);

  //joystick oi
  Joystick driverController = new Joystick(Constants.DRIVER_CONTROLLER);
  POVButton hatRight = new POVButton(driverController, Constants.POV_E);
  POVButton hatLeft = new POVButton(driverController, Constants.POV_W);

  //controller oi
  XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROLLER);
  Button xButton = new JoystickButton(operatorController, Constants.X_BUTTON);
  Button aButton = new JoystickButton(operatorController, Constants.A_BUTTON);
  Button bButton = new JoystickButton(operatorController, Constants.B_BUTTON);
  Button yButton = new JoystickButton(operatorController, Constants.Y_BUTTON);
  Button leftBumper = new JoystickButton(operatorController, Constants.LEFT_BUMPER);
  Button rightBumper = new JoystickButton(operatorController, Constants.RIGHT_BUMPER);
  POVButton dpadUp = new POVButton(operatorController, Constants.POV_N);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveTrain.setDefaultCommand(m_gtaDrive);
    m_colorChange.setDefaultCommand(m_autoRotate);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //climber binds
    leftBumper.whenPressed(m_climberUp);
    rightBumper.whenPressed(m_climberDown);

    //color change binds
    xButton.whenPressed(m_changeBlue);
    aButton.whenPressed(m_changeGreen);
    bButton.whenPressed(m_changeRed);
    yButton.whenPressed(m_changeYellow);

    //auto turn binds
    hatRight.whenPressed(m_90Right);
    hatLeft.whenPressed(m_90Left);
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
