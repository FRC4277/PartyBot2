/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.IntakeOutakeCommand;

/**
 * Add your docs here.
 */
public class Launcher extends Subsystem {
  public static final double MAXIMUM_ANGLE = 1080;
  public static final double MINIMUM_ANGLE = 1013;
  public static final double FLIPPER_ANGLE = 360 * 0.35;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Talon lift;
  private Potentiometer potentiometer;
  private AnalogInput analogInput;
  private Talon intakeLeft, intakeRight;
  private Servo pusherServo;

  public Launcher(int lift, int potentiometer, int intakeLeft, int intakeRight, int pusherServo) {
    this.lift = new Talon(lift);
    this.analogInput = new AnalogInput(potentiometer);
    this.potentiometer = new AnalogPotentiometer(analogInput, 4687.5);
    this.intakeLeft = new Talon(intakeLeft);
    this.intakeRight = new Talon(intakeRight);
    this.intakeRight.setInverted(true); 
    this.pusherServo = new Servo(pusherServo);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Potentiometer", this.potentiometer.get());
    SmartDashboard.putNumber("Potentiometer Voltage", this.analogInput.getVoltage());
    SmartDashboard.putNumber("Servo Angle", pusherServo.getAngle());
    LiveWindow.add(pusherServo);
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

  private void runIntakeOutake(double speed) {
    intakeLeft.set(speed);
    intakeRight.set(speed);
  }

  public void runIntake(double speed) {
    runIntakeOutake(speed);
  }

  public void runOutake(double speed) {
    runIntake(-speed);
  }

  public void stopIntakeOutake() {
    runIntakeOutake(0);
  }

  public void pushFlipper() {
    pusherServo.setAngle(FLIPPER_ANGLE);
  }

  public void resetFlipper() {
    pusherServo.setAngle(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new IntakeOutakeCommand());
  }
}
