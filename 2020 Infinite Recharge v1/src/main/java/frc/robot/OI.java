/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * Add your docs here.
 */
public class OI {

    //joystick and buttons
    Joystick driverController = new Joystick(Constants.DRIVER_CONTROLLER);
    /*
    Button threeButton = new JoystickButton(driverController, Constants.TOP_DOWN_LEFT);
    Button fourButton = new JoystickButton(driverController, Constants.TOP_DOWN_RIGHT);
    POVButton hatLeft = new POVButton(driverController, 270);
    POVButton hatRight = new POVButton(driverController, 90)
    */

    public double GetAxis(int axis) {
        return driverController.getRawAxis(axis);
    }

    public boolean GetButton(int button) {
        return driverController.getRawButton(button);
    }

    public void DisplayInt(String label, double value) {
        SmartDashboard.putNumber(label, value);
    }

    public void DisplayBool(String label, Boolean value) {
        SmartDashboard.putBoolean(label, value);
    }

    public void DisplayString(String label, String value) {
        SmartDashboard.putString(label, value);
    }

}
