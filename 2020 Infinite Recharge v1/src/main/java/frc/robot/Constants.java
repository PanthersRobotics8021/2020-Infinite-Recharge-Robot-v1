/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
    public static final int MOTOR_LEFT_ID = 0;
    public static final int MOTOR_RIGHT_ID = 1;
    public static final int MOTOR_ADJUSTER = 2;

    //pneumatics ids
    public static final int PCM_ID = 5;
    public static final int REVERSE_CHANNEL = 0;
    public static final int FORWARD_CHANNEL = 1;
  
    //controller ids
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
  
    //drivetrain variables
    public static final double MOTOR_SPEED_FACTOR = 1;
    public static final double MOTOR_THRESHOLD = .05;
    public static final double TURN_THRESHOLD = .3;
    
}
