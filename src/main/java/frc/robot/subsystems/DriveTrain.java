/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.ManualDriveCommand;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Talon frontLeft, frontRight, backLeft, backRight;
  private SpeedControllerGroup left, right;
  private DifferentialDrive drive;

  public DriveTrain(int frontLeft, int frontRight, int backLeft, int backRight) {
    this.frontLeft = new Talon(frontLeft);
    this.frontRight = new Talon(frontRight);
    this.backLeft = new Talon(backLeft);
    this.backRight = new Talon(backRight);
    left = new SpeedControllerGroup(this.frontLeft, this.backLeft);
    right = new SpeedControllerGroup(this.frontRight, this.backRight);
    drive = new DifferentialDrive(left, right);
  }

  public void drive(double x, double y) {
    drive.arcadeDrive(x, y);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManualDriveCommand());
  }
}
