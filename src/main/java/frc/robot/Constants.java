// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.robot.subsystems.DrivetrainSubsystem;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.52705; // FIXME Measure and set trackwidth
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.73025; // FIXME Measure and set wheelbase

    // public static final int DRIVETRAIN_PIGEON_ID = 0; // FIXME Set Pigeon ID

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 1; // FIXME Set front left module drive motor ID
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 8; // FIXME Set front left module steer motor ID
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 11; // FIXME Set front left steer encoder ID
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(46.6); // FIXME Measure and set front
                                                                                       // left steer offset

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 3; // FIXME Set front right drive motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 2; // FIXME Set front right steer motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 13; // FIXME Set front right steer encoder ID
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(166.5); // FIXME Measure and set front
                                                                                         // right steer offset

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 7; // FIXME Set back left drive motor ID
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 6; // FIXME Set back left steer motor ID
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 17; // FIXME Set back left steer encoder ID
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(135.5); // FIXME Measure and set back
                                                                                       // left steer offset

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 5; // FIXME Set back right drive motor ID
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 4; // FIXME Set back right steer motor ID
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 15; // FIXME Set back right steer encoder ID
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(151.9); // FIXME Measure and set back
                                                                                        // right steer offset
    // I(Mihir) added these for auto path stuff
    public static final double kMaxAngularAccelerationRadiansPerSecondSquared = Math.PI / 4;
    public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
            DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
            kMaxAngularAccelerationRadiansPerSecondSquared);
    public static final double kMaxAccelerationMetersPerSecondSquared = 5;
    public static final double kPXController = 1.5;
    public static final double kPYController = 1.5;
    public static final double kPThetaController = 3;
    public static final double kMaxSpeedMetersPerSecond = DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND * 2;
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
            new Translation2d(DRIVETRAIN_WHEELBASE_METERS / 2, -DRIVETRAIN_TRACKWIDTH_METERS / 2),
            new Translation2d(DRIVETRAIN_WHEELBASE_METERS / 2, DRIVETRAIN_TRACKWIDTH_METERS / 2),
            new Translation2d(-DRIVETRAIN_WHEELBASE_METERS / 2, -DRIVETRAIN_TRACKWIDTH_METERS / 2),
            new Translation2d(-DRIVETRAIN_WHEELBASE_METERS / 2, DRIVETRAIN_TRACKWIDTH_METERS / 2));

}
