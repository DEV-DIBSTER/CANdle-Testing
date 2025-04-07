package frc.robot.commands.lights;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANdleSubsystem;

public class RainbowAnimation extends Command {

  private final CANdleSubsystem candleSubsystem;

  /**
   * Creates a command that displays a rainbow animation on the LEDs.
   */
  public RainbowAnimation() {
    candleSubsystem = CANdleSubsystem.getInstance();
    addRequirements(candleSubsystem);
  }

  @Override
  public void initialize() {
    // Start the rainbow animation
    candleSubsystem.startRainbowAnimation();
  }

  @Override
  public void end(boolean interrupted) {
    // Stop the animation when the command ends
    candleSubsystem.stopAnimation();
  }

  @Override
  public boolean isFinished() {
    // This command runs until interrupted
    return false;
  }
}