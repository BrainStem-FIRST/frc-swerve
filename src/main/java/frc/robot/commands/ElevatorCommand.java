package frc.robot.commands;

import java.lang.reflect.Method;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;


public class ElevatorCommand extends CommandBase{
    private ShooterSubsystem shooterSubsystem;
    private double elevatorSpeed;

    public ElevatorCommand(ShooterSubsystem shooterSubsystem, double elevatorSpeed){
        this.shooterSubsystem = shooterSubsystem;
        this.elevatorSpeed = elevatorSpeed;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize(){
        shooterSubsystem.resetElevatorMotorEncoder();
    }

    @Override
    public void execute(){
        
    }

    @Override 
    public void end(boolean interrupted){
        
    }

    @Override 
    public boolean isFinished(){
        return false;
    }
}
