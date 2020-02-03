/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * Add your docs here.
 */
public class OI {

    //joystick and buttons
    Joystick driverController = new Joystick(Constants.DRIVER_CONTROLLER);
    Button threeButton = new JoystickButton(driverController, Constants.TOP_DOWN_LEFT);
    Button fourButton = new JoystickButton(driverController, Constants.TOP_DOWN_RIGHT);

    public double GetAxis(int axis) {
        return driverController.getRawAxis(axis);
    }

    public boolean GetButton(int button) {
        return driverController.getRawButton(button);
    }

}
