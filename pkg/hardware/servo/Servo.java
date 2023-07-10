package pkg.hardware.servo;

import pkg.hardware.HardwareDevice;

public interface Servo extends HardwareDevice {
    static enum Direction {
        FORWARD,
        REVERSE;
    }

    static double MAX_POSITION = 1;
    static double MIN_POSITION = 0;

    public Direction getDirection();
    public int getPortNumber();
    public double getPosition();
    public void scaleRange(double min, double max);
    public void setDirection(Direction direction);
    public void setPosition(double position);

}
