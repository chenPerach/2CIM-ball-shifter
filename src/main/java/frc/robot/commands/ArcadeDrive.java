
package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
  /** Creates a new ArcadeDrive. */
  DriveTrain driveTrain;
  DoubleSupplier speed,rot;
  public ArcadeDrive(DriveTrain driveTrain,DoubleSupplier speed, DoubleSupplier rot) {
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    this.speed = speed;
    this.rot = rot;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.driveTrain.arcadeDrive(speed.getAsDouble()*0.85, rot.getAsDouble()*0.9);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
