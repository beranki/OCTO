package pkg.hardware.dcmotor;

import pkg.hardware.HardwareDevice;

public interface DcMotorSimple extends HardwareDevice {
    enum Direction {
        FORWARD,
        REVERSE;
    } 

    public double getPower();
    public void setDirection(Direction direction);
    public void setPower(double power);
}
