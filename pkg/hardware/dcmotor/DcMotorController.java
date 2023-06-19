package pkg.hardware.dcmotor;

import pkg.hardware.dcmotor.DcMotor.RunMode;
import pkg.hardware.dcmotor.DcMotor.ZeroPowerBehavior;

public interface DcMotorController {
    public int getMotorCurrentPosition(int motor);
    public RunMode getMotorMode(int motor);
    public double getMotorPower(int motor);
    public boolean getMotorPowerFloat(int motor);
    public int getMotorTargetPosition(int motor);
    public ZeroPowerBehavior getMotorZeroPowerBehavior(int motor);
    void resetDeviceConfigurationForOpMode(int motor);
    void setMotorMode(int motor, RunMode mode);
    void setMotorPower(int motor, double power);
    void setMotorTargetPosition(int motor, int position);
    void setMotorZeroPowerBehavior(int motor, ZeroPowerBehavior zeroPowerBehavior);
}
