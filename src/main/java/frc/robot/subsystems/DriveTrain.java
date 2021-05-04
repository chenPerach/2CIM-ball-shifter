// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  
  enum State{
    R1t9(true),R1t21(false);
    boolean value;

    State(boolean v){
      value = v;
    }
  }

  private static final boolean OPEN = true;


  WPI_TalonSRX left,right;
  WPI_VictorSPX leftFollower,rightFollower;

  Solenoid solenoid;

  SpeedControllerGroup leftController,rightController;
  DifferentialDrive diffDrive;
  public DriveTrain() {
    left = new WPI_TalonSRX(0);
    right = new WPI_TalonSRX(0);

    leftFollower = new WPI_VictorSPX(0);
    rightFollower = new WPI_VictorSPX(0);
  
    solenoid = new Solenoid(0);

    leftFollower.follow(left);
    rightFollower.follow(right);
    leftController = new SpeedControllerGroup(left,leftFollower);
    rightController = new SpeedControllerGroup(right,rightFollower);

    diffDrive = new DifferentialDrive(leftController,rightController);



  }
  public void arcadeDrive(double speed,double rot){
    diffDrive.arcadeDrive(speed, -rot);
  }
  public boolean getGearState(){
    return this.solenoid.get();
  }

  public void switchGear(){
    solenoid.set(!solenoid.get());
  }
  /**
   * true for 1/9 and false for 1/21
   */
  public void setState(boolean State){
    if(State != solenoid.get()) switchGear();
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("SolState",solenoid.get());
    // This method will be called once per scheduler run
  }
}
