package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ConstraintsConstants;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase implements AutoCloseable {

   PIDController turretPIDController = new PIDController(ShooterConstants.TURRET_PROPORTIONAL,
         ShooterConstants.TURRET_INTREGRAL,
         ShooterConstants.TURRET_DERIVATIVE);

   PIDController elevatorPIDController = new PIDController(ShooterConstants.ELEVATOR_PROPORTIONAL,
         ShooterConstants.ELEVATOR_INTEGRAL,
         ShooterConstants.ELEVATOR_DERIVATIVE);

   PIDController shooterPIDController = new PIDController(ShooterConstants.SHOOTER_PROPORTIONAL,
         ShooterConstants.SHOOTER_INTEGRAL,
         ShooterConstants.SHOOTER_DERIVATIVE);

   private CANSparkMax shooterMotor1 = new CANSparkMax(Constants.ShooterConstants.SHOOTER_1_MOTOR_PORT_ID,
         MotorType.kBrushless);
   private CANSparkMax shooterMotor2 = new CANSparkMax(Constants.ShooterConstants.SHOOTER_2_MOTOR_PORT_ID,
         MotorType.kBrushless);
   private CANSparkMax elevatorMotor = new CANSparkMax(Constants.ShooterConstants.ELEVATOR_MOTOR_PORT_ID,
         MotorType.kBrushless);
   private CANSparkMax turretMotor = new CANSparkMax(Constants.ShooterConstants.TURRET_MOTOR_PORT_ID,
         MotorType.kBrushless);

   public RelativeEncoder shooterMotor1Encoder = returnShooterMotor1Encoder();
   public RelativeEncoder shooterMotor2Encoder = returnShooterMotor2Encoder();
   public RelativeEncoder turretMotorEncoder = returnTurretMotorEncoder();
   public RelativeEncoder elevatorMotorEncoder = returnElevatorMotorEncoder();

   public RelativeEncoder returnShooterMotor1Encoder() {
      return shooterMotor1.getEncoder();
   }

   public RelativeEncoder returnShooterMotor2Encoder() {
      return shooterMotor2.getEncoder();
   }

   public RelativeEncoder returnTurretMotorEncoder() {
      return turretMotor.getEncoder();
   }

   public RelativeEncoder returnElevatorMotorEncoder() {
      return elevatorMotor.getEncoder();
   }

   public void setShooterSpeed(double speed) {
      shooterPIDController.setTolerance(ShooterConstants.SHOOTER_PID_TOLERANCE);
      double shooterSpeed = shooterPIDController
            .calculate(shooterMotor1Encoder.getVelocity() / ConstraintsConstants.CAN_SPARK_MAX_MAXIMUM_RPM, speed);
      shooterMotor2.follow(shooterMotor1);
      shooterMotor1.set(shooterSpeed);
   }
   public void setShooterSpeedAuto(double speed) {
      shooterPIDController.setTolerance(ShooterConstants.SHOOTER_PID_TOLERANCE);
      double shooterSetPoint= shooterPIDController
            .calculate(shooterMotor1Encoder.getPosition() * 42, speed);
      shooterMotor2.follow(shooterMotor1);
      shooterMotor1.set(shooterSetPoint);
   }

   public void setTurretSpeed(double speed) {
      turretPIDController.setTolerance(ShooterConstants.TURRET_PID_TOLERANCE);
      double turretSpeed = turretPIDController
            .calculate(turretMotorEncoder.getVelocity() / ConstraintsConstants.CAN_SPARK_MAX_MAXIMUM_RPM, speed);
      turretMotor.set(turretSpeed);
   }
   public void setTurretSpeedAuto(double speed) {
      turretPIDController.setTolerance(ShooterConstants.TURRET_PID_TOLERANCE);
      double turretSpeed = turretPIDController
            .calculate(turretMotorEncoder.getPosition() * 42, speed);
      turretMotor.set(turretSpeed);
   }

   public void setElevatorSpeed(double speed) {
      elevatorPIDController.setTolerance(ShooterConstants.ELEVATOR_PID_TOLERANCE);
      double elevatorSpeed = elevatorPIDController
            .calculate(elevatorMotorEncoder.getVelocity() / ConstraintsConstants.CAN_SPARK_MAX_MAXIMUM_RPM, speed);
      elevatorMotor.set(elevatorSpeed);
   }
   public void setElevatorSpeedAuto(double speed) {
      elevatorPIDController.setTolerance(ShooterConstants.ELEVATOR_PID_TOLERANCE);
      double elevatorSpeed = elevatorPIDController
            .calculate(elevatorMotorEncoder.getPosition() * 42, speed);
      elevatorMotor.set(elevatorSpeed);
   }

  

   public double elevatorPosition() {
      return elevatorMotorEncoder.getPosition();
   }

   public double turretPosition() {
      return turretMotorEncoder.getPosition();
   }

   public void initShooterMotors() {
      resetBothShooterMotorEncoders();
      stopShooterMotors();
   }

   public void initTurretMotor() {
      resetTurretMotorEncoder();
      stopTurretMotor();
   }

   public void initElevatorMotor() {
      resetElevatorMotorEncoder();
      stopElevatorMotor();
   }
   public void initAllMotors() {
      initElevatorMotor();
      initShooterMotors();
      initTurretMotor();
   }

  

   public void executeShooterMotors(double shooterspeed) {
      setShooterSpeed(shooterspeed);
   }

   public void executeTurretMotor(double turretSetPoint) {
      setTurretSpeed(turretSetPoint);
   }

   public void executeElevatorMotor(double elevatorSetPoint){
      setElevatorSpeed(elevatorSetPoint);
   }
   public void  executeElevatorMotorAuto(double elevatorSpeed) {
      setElevatorSpeedAuto(elevatorSpeed);
   }
   public void executeTurretMotorAuto(double turretSpeed) {
      setTurretSpeedAuto(turretSpeed);
   }
   public void executeShooterMotorsAuto(double shooterSpeed) {
      setShooterSpeedAuto(shooterSpeed);
   }


   public void executeAllMotors(double shooterSpeed, double turretSpeed, double elevatorSpeed){
      executeElevatorMotor(elevatorSpeed);
      executeShooterMotors(shooterSpeed);
      executeTurretMotor(turretSpeed);
   }
   public void executeAllMotorsAuto(double shooterSetPoint, double elevatorSetPoint, double turretSetPoint) {
      executeElevatorMotorAuto(elevatorSetPoint);
      executeShooterMotors(shooterSetPoint);
      executeTurretMotorAuto(turretSetPoint);
   }

   public void endShooter() {
      initShooterMotors();
   }

   public void endElevator() {
      initElevatorMotor();
   }

   public void endTurret() {
      initTurretMotor();
   }
   public void endAllMotors() {
      endShooter();
      endElevator();
      endTurret();
   }

   

   public void resetBothShooterMotorEncoders() {
      shooterMotor1Encoder.setPosition(0);
      shooterMotor2Encoder.setPosition(0);
   }

   public void resetElevatorMotorEncoder() {
      elevatorMotorEncoder.setPosition(0);
   }

   public void resetTurretMotorEncoder() {
      turretMotorEncoder.setPosition(0);
   }

   public void resetAllMotorEncoders() {
      resetElevatorMotorEncoder();
      resetBothShooterMotorEncoders();
      resetTurretMotorEncoder();
   }

   public void stopElevatorMotor() {
      elevatorMotor.set(0);
   }

   public void stopShooterMotors() {
      shooterMotor1.set(0);
      shooterMotor2.set(0);
   }

   public void stopTurretMotor() {
      turretMotor.set(0);
   }

  

   @Override
   public void close() throws Exception {
      shooterMotor1.close();
      shooterMotor2.close();
      turretMotor.close();
      elevatorMotor.close();
   }
}