// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  DriveTrain driveTrain;
  XboxController controller;
  JoystickButton aButton;
  JoystickButton bButton;
  Compressor compressor;
  // Compressor comp;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    compressor = new Compressor(0);
    driveTrain = new DriveTrain();
    controller = new XboxController(0);
    driveTrain
        .setDefaultCommand(new ArcadeDrive(driveTrain, () -> controller.getRawAxis(1), () -> controller.getRawAxis(4)));
    // comp = new Compressor();

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to definJo e your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    aButton = new JoystickButton(controller, XboxController.Button.kA.value);
    bButton = new JoystickButton(controller, XboxController.Button.kB.value);
    aButton.whenPressed(new InstantCommand(() -> driveTrain.switchGear(), driveTrain));
    bButton.whenPressed(new InstantCommand(() -> {
      if(compressor.enabled())
        compressor.stop();
      else
        compressor.start();
      
    }));
  }
  public void periodic(){
    SmartDashboard.putBoolean("Compressor state",compressor.enabled());
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  
}
