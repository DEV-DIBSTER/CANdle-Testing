package frc.robot.commands.lights;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANdleSubsystem;

public class SetSolidColor extends Command {

  //Declares the CANdleSubsystem.
  private final CANdleSubsystem candleSubsystem;

  public SetSolidColor() {
    candleSubsystem = CANdleSubsystem.getInstance();

    // Not need to add requirements.
    addRequirements();
  }

  @Override
  public void initialize() {
    // Set the LEDs to solid red
    candleSubsystem.setColor(255, 0, 0);
  }

  @Override
  public boolean isFinished() {
    // This command completes immediately after setting the color
    return true;
  }
}
