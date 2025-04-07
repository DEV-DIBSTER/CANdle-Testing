package frc.robot.commands.lights;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANdleSubsystem;

public class BlinkingLights extends Command {

  private final CANdleSubsystem candleSubsystem;
  private final int red;
  private final int green;
  private final int blue;
  private final double blinkInterval; // Time in seconds for each blink state
  private final Timer timer = new Timer();
  private boolean isLit = false;

  /**
   * Creates a command that makes the LEDs blink between the specified color and off.
   * 
   * @param r Red value (0-255)
   * @param g Green value (0-255)
   * @param b Blue value (0-255)
   * @param interval Time in seconds for each blink state (on/off)
   */
  public BlinkingLights(int r, int g, int b, double interval) {
    candleSubsystem = CANdleSubsystem.getInstance();
    this.red = r;
    this.green = g;
    this.blue = b;
    this.blinkInterval = interval;
    addRequirements(candleSubsystem);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    isLit = false;
  }

  @Override
  public void execute() {
    // Check if it's time to toggle the lights
    if (timer.advanceIfElapsed(blinkInterval)) {
      isLit = !isLit;
      
      if (isLit) {
        candleSubsystem.setColor(red, green, blue);
      } else {
        candleSubsystem.setColor(0, 0, 0);
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    // Turn off the lights when the command ends
    candleSubsystem.setColor(0, 0, 0);
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    // This command runs until interrupted
    return false;
  }
}