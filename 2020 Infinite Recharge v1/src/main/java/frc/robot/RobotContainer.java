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
import frc.robot.commands.ClimberControl;
import frc.robot.commands.ColorDetection;
import frc.robot.commands.GTADrive;
import frc.robot.commands.InputDrive;
import frc.robot.commands.InputShoot;
import frc.robot.commands.InputWheelChangeColorBased;
import frc.robot.commands.ShooterControl;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorChange;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
  private final ClimberControl m_climberControl = new ClimberControl(m_climber);

  //color change
  private final ColorChange m_colorChange = new ColorChange();
  private final ColorDetection m_changeBlue = new ColorDetection(m_colorChange, "Blue");
  private final ColorDetection m_changeGreen = new ColorDetection(m_colorChange, "Green");
  private final ColorDetection m_changeRed = new ColorDetection(m_colorChange, "Red");
  private final ColorDetection m_changeYellow = new ColorDetection(m_colorChange, "Yellow");
  private final InputWheelChangeColorBased m_autoRotate = new InputWheelChangeColorBased(m_colorChange);

  //drivetrain
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final GTADrive m_gtaDrive = new GTADrive(m_driveTrain);
  private final InputDrive m_90Right = new InputDrive(m_driveTrain, -Constants.TURN_90_SPEED, Constants.TURN_90_SPEED, Constants.TURN_90_TIME);
  private final InputDrive m_90Left = new InputDrive(m_driveTrain, Constants.TURN_90_SPEED, -Constants.TURN_90_SPEED, Constants.TURN_90_TIME);


  //shooter
  private final Shooter m_shooter = new Shooter();
  private final ShooterControl m_shooterControl = new ShooterControl(m_shooter);

  //auto command
  private final SequentialCommandGroup m_autoFRF = new InputDrive(m_driveTrain, Constants.AUTO_SPEED, Constants.AUTO_SPEED, Constants.AUTO_TIME).andThen(new InputDrive(m_driveTrain, -Constants.TURN_90_SPEED, Constants.TURN_90_SPEED, Constants.TURN_90_TIME), new InputDrive(m_driveTrain, Constants.AUTO_SPEED, Constants.AUTO_SPEED, Constants.AUTO_TIME), new InputDrive(m_driveTrain, Constants.TURN_90_SPEED, -Constants.TURN_90_SPEED, Constants.TURN_90_TIME), new InputDrive(m_driveTrain, Constants.AUTO_SPEED, Constants.AUTO_SPEED, Constants.AUTO_TIME), new InputShoot(m_shooter, Constants.SHOOTER_SPEED));
  private final SequentialCommandGroup m_autoFFF = new InputDrive(m_driveTrain, Constants.AUTO_SPEED, Constants.AUTO_SPEED, Constants.AUTO_TIME).andThen(new InputDrive(m_driveTrain, Constants.AUTO_SPEED, Constants.AUTO_SPEED, Constants.AUTO_TIME), new InputDrive(m_driveTrain, Constants.AUTO_SPEED, Constants.AUTO_SPEED, Constants.AUTO_TIME), new InputShoot(m_shooter, Constants.SHOOTER_SPEED));
  private final SequentialCommandGroup m_autoFLF = new InputDrive(m_driveTrain, Constants.AUTO_SPEED, Constants.AUTO_SPEED, Constants.AUTO_TIME).andThen(new InputDrive(m_driveTrain, Constants.TURN_90_SPEED, -Constants.TURN_90_SPEED, Constants.TURN_90_TIME), new InputDrive(m_driveTrain, Constants.AUTO_SPEED, Constants.AUTO_SPEED, Constants.AUTO_TIME), new InputDrive(m_driveTrain, -Constants.TURN_90_SPEED, Constants.TURN_90_SPEED, Constants.TURN_90_TIME), new InputDrive(m_driveTrain, Constants.AUTO_SPEED, Constants.AUTO_SPEED, Constants.AUTO_TIME), new InputShoot(m_shooter, Constants.SHOOTER_SPEED));

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

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveTrain.setDefaultCommand(m_gtaDrive);
    m_colorChange.setDefaultCommand(m_autoRotate);
    m_shooter.setDefaultCommand(m_shooterControl);
    m_climber.setDefaultCommand(m_climberControl);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //color change binds
    xButton.whenPressed(m_changeRed);
    aButton.whenPressed(m_changeYellow);
    bButton.whenPressed(m_changeBlue);
    yButton.whenPressed(m_changeGreen);

    //auto turn binds
    //USE .withtimeout !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
    hatRight.whenPressed(m_90Right);
    hatLeft.whenPressed(m_90Left);
  }

  public SequentialCommandGroup getAutoFRF() {
    return m_autoFRF;
  }
  public SequentialCommandGroup getAutoFFF() {
    return m_autoFFF;
  }
  public SequentialCommandGroup getAutoFLF() {
    return m_autoFLF;
  }
}
