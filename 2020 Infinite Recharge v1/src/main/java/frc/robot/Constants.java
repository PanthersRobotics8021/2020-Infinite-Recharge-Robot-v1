/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //motor ids
    public static final int MOTOR_LEFT_ID = 2;
    public static final int MOTOR_RIGHT_ID = 4;
    public static final int MOTOR_ADJUSTER_ID = 5;
    public static final int MOTOR_SHOOTER_ID = 3;
    public static final double COLOR_ADJUSTER_SPEED = .5;
    public static final double REVOLUTIONS_ADJUSTER_SPEED = .7;
    public static final double SHOOTER_SPEED = .5;

    //pneumatics ids
    public static final int PCM_ID = 6;
    public static final int CLIMBER_REVERSE = 0;
    public static final int CLIMBER_FORWARD = 1;
    public static final int TRAPDOOR_REVERSE = 6;
    public static final int TRAPDOOR_FORWARD = 7;

    //roborio ports
    public static final I2C.Port I2C_PORT = I2C.Port.kOnboard;
  
    //joystick ids
    public static final int DRIVER_CONTROLLER = 0;
    public static final int JOY_X = 0;
    public static final int JOY_Y = 1;
    public static final int JOY_Z = 2;
    public static final int JOY_SLIDE = 3;
    public static final int TRIGGER = 1;
    public static final int THUMB_BUTTON = 2;
    public static final int TOP_DOWN_LEFT = 3;
    public static final int TOP_DOWN_RIGHT = 4;
    public static final int TOP_UP_LEFT = 5;
    public static final int TOP_UP_RIGHT = 6;
    public static final int PAD_A1 = 7;
    public static final int PAD_A2 = 8;
    public static final int PAD_B1 = 9;
    public static final int PAD_B2 = 10;
    public static final int PAD_C1 = 11;
    public static final int PAD_C2 = 12;

    //xbox ids
    public static final int OPERATOR_CONTROLLER = 1;
    public static final int RIGHT_X = 0;
    public static final int RIGHT_Y = 1;
    public static final int LEFT_X = 2;
    public static final int LEFT_Y = 3;
    public static final int X_BUTTON = 1;
    public static final int A_BUTTON = 2;
    public static final int B_BUTTON = 3;
    public static final int Y_BUTTON = 4;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;
    public static final int LEFT_TRIGGER = 7;
    public static final int RIGHT_TRIGGER = 8;
    public static final int BACK_BUTTON = 9;
    public static final int START_BUTTON = 10;
    public static final int LEFT_JOY_CLICK = 11;
    public static final int RIGHT_JOY_CLICK = 12;

    //pov ids
    public static final int POV_N = 0;
    public static final int POV_NE = 45;
    public static final int POV_E = 90;
    public static final int POV_SE = 135;
    public static final int POV_S = 180;
    public static final int POV_SW = 225;
    public static final int POV_W = 270;
    public static final int POV_NW = 315;

    //drivetrain variables
    public static final double TURN_SPEED_FACTOR = .9;
    public static final double TURN_THRESHOLD = .2;
    public static final double TURN_90_SPEED = .5;
    public static final double TURN_90_TIME = .5;
    public static final double AUTO_SPEED = .5;
    public static final double AUTO_TIME = 5;
    
}
