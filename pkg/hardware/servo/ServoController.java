package pkg.hardware.servo;

import pkg.hardware.HardwareDevice;

public interface ServoController extends HardwareDevice {
    
    double getServoPosition(int servo);
    void setServoPosition(int servo, double position);

}
