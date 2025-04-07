package frc.robot.commands.lights;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANdleSubsystem;

public class AllianceColor extends Command {

  private final CANdleSubsystem candleSubsystem;
  
  /**
   * Creates a command that sets the LEDs to the current alliance color (red or blue).
   * If no alliance data is available, it will default to white.
   */
  public AllianceColor() {
    candleSubsystem = CANdleSubsystem.getInstance();
    addRequirements(candleSubsystem);
  }

  @Override
  public void initialize() {
    // Set the LEDs to the alliance color
    updateAllianceColor();
  }
  
  @Override
  public void execute() {
    // Continuously update the alliance color in case it changes
    updateAllianceColor();
  }

  private void updateAllianceColor() {
    if (DriverStation.getAlliance().isPresent()) {
      Alliance alliance = DriverStation.getAlliance().get();
      
      if (alliance == Alliance.Red) {
        candleSubsystem.setColor(255, 0, 0); // Red
      } else if (alliance == Alliance.Blue) {
        candleSubsystem.setColor(0, 0, 255); // Blue
      }
    } else {
      // Default to white if no alliance data is available
      candleSubsystem.setColor(255, 255, 255);
    }
  }

  @Override
  public void end(boolean interrupted) {
    // Turn off the LEDs when the command ends
    candleSubsystem.setColor(0, 0, 0);
  }

  @Override
  public boolean isFinished() {
    // This command runs until interrupted
    return false;
  }
}