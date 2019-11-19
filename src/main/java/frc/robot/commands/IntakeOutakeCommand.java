/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeOutakeCommand extends Command {
  private static final double TRIGGER_THRESHOLD = 0.25;
  private static final double INTAKE_SPEED = 0.75;
  private static final double OUTAKE_SPEED = 0.75;
  private static Long OUTTAKE_START_TIME = null;
  private static final long DELAY_FOR_PUSHER_MS = 10;

  public IntakeOutakeCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.launcher);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("init");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    XboxController controller = Robot.m_oi.xboxController;
    if (controller.getTriggerAxis(Hand.kLeft) >= TRIGGER_THRESHOLD) {
      Robot.launcher.runIntake(INTAKE_SPEED);
      Robot.launcher.resetFlipper();
      OUTTAKE_START_TIME = null;
      System.out.println("intake, 0");
    } else if (controller.getTriggerAxis(Hand.kRight) >= TRIGGER_THRESHOLD) {
      Robot.launcher.runOutake(OUTAKE_SPEED);
      if (OUTTAKE_START_TIME == null) {
        System.out.println("outtake time started");
        OUTTAKE_START_TIME = System.currentTimeMillis();
      }
      System.out.println("checking time.. = " + (System.currentTimeMillis() - OUTTAKE_START_TIME));
      if ((System.currentTimeMillis() - OUTTAKE_START_TIME) >= DELAY_FOR_PUSHER_MS) {
        Robot.launcher.pushFlipper();
        System.out.println("pushing flipper.");
      }
    } else {
      Robot.launcher.stopIntakeOutake();
      Robot.launcher.resetFlipper();
      OUTTAKE_START_TIME = null;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
