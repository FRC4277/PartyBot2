/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  public static int XBOX_USB_PORT = 0;
  // Motors
  public static final int LEFT_FRONT = 1;
  public static final int LEFT_BACK = 0;
  public static final int RIGHT_FRONT = 4;
  public static final int RIGHT_BACK = 3;
  public static final int LIFT = 6;
  public static final int INTAKE_LEFT = 2;
  public static final int INTAKE_RIGHT = 5;
  public static final int PUSHER_SERVO = 7;
  // PWM IN
  public static final int LIFT_POTENTIOMETER = 0;
}
