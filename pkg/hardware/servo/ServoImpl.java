package pkg.hardware.servo;

public class ServoImpl implements Servo {

    Direction direction;
    int portNumber;
    private double position;
    double limitPositionMin;
    double limitPositionMax;

    public ServoImpl(int portNumber) {
        this.portNumber = portNumber;
        direction = Direction.FORWARD;
        position = 0;
    }

    /* Servo Inherited Functions */

    public Direction getDirection() {
        return direction;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public double getPosition() {
        return position;
    }

    public void scaleRange(double min, double max) {
        limitPositionMin = min;
        limitPositionMax = max;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    /* HardwareDevice inherited functions */

    public void close() {

    }

    public String getConnectionInfo() {
        return "Invalid, as this is a simulation software. Device runs on thread pool.";
    }

    public String getDeviceName() {
        return "Servo";
    }

    public int getVersion() {
        return 1;
    }

    public void resetDeviceConfigurationForOpMode() {
        direction = Direction.FORWARD;
        position = 0;
    }

    public void internalUpdate(double ms) {
        
    }
}
