/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Launcher extends Subsystem {
  public static final double MAXIMUM_ANGLE = 1080;
  public static final double MINIMUM_ANGLE = 1013;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Talon lift;
  private Potentiometer potentiometer;
  private AnalogInput analogInput;

  public Launcher(int lift, int potentiometer) {
    this.lift = new Talon(lift);
    this.analogInput = new AnalogInput(potentiometer);
    this.potentiometer = new AnalogPotentiometer(analogInput, 4687.5);
    
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Potentiometer", this.potentiometer.get());
    SmartDashboard.putNumber("Potentiometer Voltage", this.analogInput.getVoltage());
  }

  public double getAngle() {
    return this.potentiometer.get();
  }

  public boolean check(boolean up) {
    if (up) {
      if (getAngle() >= MAXIMUM_ANGLE) {
        lift.set(0);
        System.out.println("STOP in MAx a");
        return false;
      }
    } else {
      if (getAngle() <= MINIMUM_ANGLE) {
        System.out.println("STOP in MIN a");
        lift.set(0);
        return false;
      }
    }
    return true;
  }
  
  public void moveUpSlow() {
    if (getAngle() >= MAXIMUM_ANGLE) {
      lift.set(0);
      System.out.println("MAX ANGLE REACHED");
      return;
    }
    lift.set(0.5);
  }

  public void moveDownSlow() {
    if (getAngle() <= MINIMUM_ANGLE) {
      System.out.println("MIN ANGLE REACHED");
      lift.set(0);
      return;
    }
    lift.set(-0.5);
  }

  public void stopLift() {
    lift.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
