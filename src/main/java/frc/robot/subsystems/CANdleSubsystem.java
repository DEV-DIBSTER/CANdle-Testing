package frc.robot.subsystems;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdleConfiguration;
import com.ctre.phoenix.led.RainbowAnimation;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DriverStation;

import frc.robot.Constants.LEDConstants;

public class CANdleSubsystem extends SubsystemBase {
  private final CANdle candle;
  private final RainbowAnimation rainbowAnimation;

  public CANdleSubsystem() {
    // Initialize the CANdle with the specified device ID.
    candle = new CANdle(LEDConstants.kCANdleDeviceID);

    // Configure the CANdle
    CANdleConfiguration config = new CANdleConfiguration();
    config.stripType = CANdle.LEDStripType.RGB; // Set LED strip type
    config.brightnessScalar = 0.5; // Set brightness (0.0 to 1.0)
    candle.configAllSettings(config);

    // Initialize the rainbow animation with full brightness, half speed, and 64 LEDs
    rainbowAnimation = new RainbowAnimation(1.0, 0.5, 64);
  }

  // Starts the rainbow animation
  public void startRainbowAnimation() {
    candle.animate(rainbowAnimation);
  }

  // Stops any ongoing animation
  public void stopAnimation() {
    candle.clearAnimation(0);
  }

  // Sets the LEDs to a solid color
  public void setColor(int r, int g, int b) {
    candle.setLEDs(r, g, b);
  }

  // Stops animations and turns off LEDs when the subsystem is disabled
  @Override
  public void periodic() {
    if (!DriverStation.isDisabled()) {
      stopAnimation();
      setColor(0, 0, 0); // Turn off LEDs
    }
  }
}
