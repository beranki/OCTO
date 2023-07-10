package pkg.hardware.dcmotor;

import pkg.hardware.servo.ServoController;

public class CRServoImpl implements CRServo {
    
    double apiPowerMax;
    double apiPowerMin;
    double apiServoPositionMax;
    double apiServoPositionMin;
    Direction direction;
    int portNumber;

    private double power;

    public CRServoImpl(int portNumber) {
        apiServoPositionMin = 0.0;
        apiServoPositionMax = 1.0;
        
        this.portNumber = portNumber;
    }

    public void close() {

    }

    public String getDeviceName() {
        return "CRServo";
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getConnectionInfo() {
        return "Invalid, as this is a simulation software. Device runs on thread pool.";
    }

    public int getVersion() {
        return 1;
    }

    public void resetDeviceConfigurationForOpMode() {
        direction = Direction.FORWARD;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void internalUpdate(double ms) {
        
    }

}
