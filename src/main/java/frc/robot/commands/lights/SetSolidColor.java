package frc.robot.commands.lights;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANdleSubsystem;

public class SetSolidColor extends Command {

  //Declares the CANdleSubsystem.
  private final CANdleSubsystem candleSubsystem;
  private final int red;
  private final int green;
  private final int blue;

  /**
   * Creates a command that sets the LEDs to solid red (default).
   */
  public SetSolidColor() {
    this(255, 0, 0); // Default to red
  }

  /**
   * Creates a command that sets the LEDs to the specified solid color.
   * 
   * @param r Red value (0-255)
   * @param g Green value (0-255)
   * @param b Blue value (0-255)
   */
  public SetSolidColor(int r, int g, int b) {
    candleSubsystem = CANdleSubsystem.getInstance();
    this.red = r;
    this.green = g;
    this.blue = b;
    addRequirements(candleSubsystem);
  }

  @Override
  public void initialize() {
    // Set the LEDs to the specified color
    candleSubsystem.setColor(red, green, blue);
  }

  @Override
  public boolean isFinished() {
    // This command completes immediately after setting the color
    return true;
  }
}
