package frc.robot;

// WPILIB Imports:
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

//Constants:
import frc.robot.Constants.OperatorConstants;

//Commands:
import frc.robot.commands.lights.AllianceColor;
import frc.robot.commands.lights.AlternatingColors;
import frc.robot.commands.lights.BlinkingLights;
import frc.robot.commands.lights.BreathingEffect;
import frc.robot.commands.lights.RainbowAnimation;
import frc.robot.commands.lights.SetSolidColor;

//Subsystems:
import frc.robot.subsystems.CANdleSubsystem;

public class RobotContainer {
  // The robot's subsystems
  private final CANdleSubsystem m_candleSubsystem = CANdleSubsystem.getInstance();

  // The driver's controller
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Button bindings for light commands
    
    // A button - Solid Red
    m_driverController.a().onTrue(new SetSolidColor());
    
    // B button - Blinking Green (0.5 second interval)
    m_driverController.b().onTrue(new BlinkingLights(0, 255, 0, 0.5));
    
    // X button - Breathing Blue effect (2 second period)
    m_driverController.x().onTrue(new BreathingEffect(0, 0, 255, 2.0));
    
    // Y button - Rainbow Animation
    m_driverController.y().onTrue(new RainbowAnimation());
    
    // Left Bumper - Alliance Color
    m_driverController.leftBumper().onTrue(new AllianceColor());
    
    // Right Bumper - Alternating Yellow and Purple (1 second interval)
    m_driverController.rightBumper().onTrue(new AlternatingColors(255, 255, 0, 128, 0, 128, 1.0));
    
    // Back button - Turn off all lights
    m_driverController.back().onTrue(new SetSolidColor(0, 0, 0));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Create and return a new SetSolidColorCommand with the CANdle subsystem
    return new SetSolidColor();
  }
}