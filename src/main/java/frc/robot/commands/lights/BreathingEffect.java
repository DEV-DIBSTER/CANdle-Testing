package frc.robot.commands.lights;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANdleSubsystem;

public class BreathingEffect extends Command {

  private final CANdleSubsystem candleSubsystem;
  private final int red;
  private final int green;
  private final int blue;
  private final double breathePeriod; // Time in seconds for a complete breathe cycle
  private final Timer timer = new Timer();

  /**
   * Creates a command that makes the LEDs fade in and out (breathe) with the specified color.
   * 
   * @param r Red value (0-255)
   * @param g Green value (0-255)
   * @param b Blue value (0-255)
   * @param period Time in seconds for a complete breathe cycle
   */
  public BreathingEffect(int r, int g, int b, double period) {
    candleSubsystem = CANdleSubsystem.getInstance();
    this.red = r;
    this.green = g;
    this.blue = b;
    this.breathePeriod = period;
    addRequirements(candleSubsystem);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    // Calculate the brightness factor (0.0 to 1.0) based on a sine wave
    double timeInCycle = timer.get() % breathePeriod;
    double brightness = (Math.sin((timeInCycle / breathePeriod) * 2 * Math.PI - Math.PI/2) + 1) / 2;
    
    // Apply the brightness factor to the RGB values
    int adjustedRed = (int)(red * brightness);
    int adjustedGreen = (int)(green * brightness);
    int adjustedBlue = (int)(blue * brightness);
    
    // Set the color with adjusted brightness
    candleSubsystem.setColor(adjustedRed, adjustedGreen, adjustedBlue);
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