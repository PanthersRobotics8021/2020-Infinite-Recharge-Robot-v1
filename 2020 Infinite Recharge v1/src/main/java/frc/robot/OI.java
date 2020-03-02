/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

/**
 * Add your docs here.
 */
public class OI {

    //joystick and buttons
    Joystick driverController = new Joystick(Constants.DRIVER_CONTROLLER);
    XboxController operatorController = new XboxController(Constants.OPERATOR_CONTROLLER);

    //driver oi
    public double GetDriverAxis(int axis) {
        return driverController.getRawAxis(axis);
    }
    public boolean GetDriverButton(int button) {
        return driverController.getRawButton(button);
    }
    public boolean GetDriverButtonPressed(int button) {
        return driverController.getRawButtonPressed(button);
    }


    //operator oi
    public double GetOperatorAxis(int axis) {
        return operatorController.getRawAxis(axis);
    }
    public boolean GetOperatorButton(int button) {
        return operatorController.getRawButton(button);
    }
    public boolean GetOperatorButtonPressed(int button) {
        return operatorController.getRawButtonPressed(button);
    }

    //smartdashboard oi
    public void DisplayInt(String label, double value) {
        SmartDashboard.putNumber(label, value);
    }
    public void DisplayBool(String label, Boolean value) {
        SmartDashboard.putBoolean(label, value);
    }
    public void DisplayString(String label, String value) {
        SmartDashboard.putString(label, value);
    }

    public String ColorName(Color color, Color blueTarget, Color greenTarget, Color redTarget, Color yellowTarget) {
        if (color == blueTarget) {
            return "Blue";
          }
          else if (color == greenTarget) {
            return "Green";
          }
          else if (color == redTarget) {
            return "Red";
          }
          else if (color == yellowTarget) {
            return "Yellow";
          }
          else {
            return "Unknown";
          }
    }
}
