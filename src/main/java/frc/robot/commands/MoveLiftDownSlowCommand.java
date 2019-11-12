/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveLiftDownSlowCommand extends Command {
  public MoveLiftDownSlowCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.launcher);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("init down");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("down" + Robot.launcher.check(false));
    if (Robot.launcher.check(false)) {
      Robot.launcher.moveDownSlow();
    } else {
      Robot.launcher.stopLift();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !Robot.launcher.check(false);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.launcher.stopLift();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
