package pkg.hardware.dcmotor;

import pkg.hardware.servo.ServoController;

public interface CRServo extends DcMotorSimple {
    public int getPortNumber();
}
