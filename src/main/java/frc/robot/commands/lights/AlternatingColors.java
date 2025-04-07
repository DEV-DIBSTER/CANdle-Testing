package frc.robot.commands.lights;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANdleSubsystem;

public class AlternatingColors extends Command {

  private final CANdleSubsystem candleSubsystem;
  private final int red1, green1, blue1; // First color
  private final int red2, green2, blue2; // Second color
  private final double switchInterval; // Time in seconds for each color
  private final Timer timer = new Timer();
  private boolean isFirstColor = true;

  /**
   * Creates a command that alternates the LEDs between two specified colors.
   * 
   * @param r1 Red value for first color (0-255)
   * @param g1 Green value for first color (0-255)
   * @param b1 Blue value for first color (0-255)
   * @param r2 Red value for second color (0-255)
   * @param g2 Green value for second color (0-255)
   * @param b2 Blue value for second color (0-255)
   * @param interval Time in seconds for each color
   */
  public AlternatingColors(int r1, int g1, int b1, int r2, int g2, int b2, double interval) {
    candleSubsystem = CANdleSubsystem.getInstance();
    this.red1 = r1;
    this.green1 = g1;
    this.blue1 = b1;
    this.red2 = r2;
    this.green2 = g2;
    this.blue2 = b2;
    this.switchInterval = interval;
    addRequirements(candleSubsystem);
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    isFirstColor = true;
    candleSubsystem.setColor(red1, green1, blue1);
  }

  @Override
  public void execute() {
    // Check if it's time to switch colors
    if (timer.advanceIfElapsed(switchInterval)) {
      isFirstColor = !isFirstColor;
      
      if (isFirstColor) {
        candleSubsystem.setColor(red1, green1, blue1);
      } else {
        candleSubsystem.setColor(red2, green2, blue2);
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