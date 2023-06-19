package pkg.hardware.dcmotor;

import javax.swing.text.Position;
import pkg.hardware.dcmotor.DcMotorSimple.Direction;

public class DcMotor implements DcMotorSimple {
    public enum RunMode {
        RESET_ENCODERS,
        RUN_TO_POSITION,
        RUN_USING_ENCODER,
        RUN_WITHOUT_ENCODER,
        STOP_AND_RESET_ENCODER;
    }

    public enum ZeroPowerBehavior {
        BRAKE,
        FLOAT,
        UNKNOWN
    }

    private int portNumber;
    private DcMotorController controller;
    private RunMode mode;
    private ZeroPowerBehavior zeroPowerBehavior;
    private Direction direction;
    
    private int targetPosition;
    private int currentPosition;
    private double power;

    public DcMotor(int portNumber) {
        this.portNumber = portNumber;

        controller = new DcMotorController() { 
            public int getMotorCurrentPosition(int motor) {
                return getCurrentPosition();
            }
            public RunMode getMotorMode(int motor) {
                return getMode();
            }
            public double getMotorPower(int motor) {
                return getPower();
            }

            /* DEPRECATED DO NOT USE */
            public boolean getMotorPowerFloat(int motor) {
                return false;
            }

            public int getMotorTargetPosition(int motor) {
                return getTargetPosition();
            }

            public ZeroPowerBehavior getMotorZeroPowerBehavior(int motor) {
                return getZeroPowerBehavior();
            }

            public void resetDeviceConfigurationForOpMode(int motor) {
                return;
            }

            public void setMotorTargetPosition(int motor, int position) {
                setTargetPosition(position);
            }

            public void setMotorMode(int motor, RunMode mode) {
                setMode(mode);
            }

            public void setMotorPower(int motor, double power) {
                setPower(power);
            }

            public void setMotorZeroPowerBehavior(int motor, ZeroPowerBehavior zeroPowerBehavior) {
                setZeroPowerBehavior(zeroPowerBehavior);
            }

        };
    }

    /* DcMotor functions */

    DcMotorController getController() {
        return controller;
    }

    int getCurrentPosition() {
        return currentPosition;
    }

    RunMode getMode() {
        return mode;
    }

    int getPortNumber() {
        return portNumber;
    }

    int getTargetPosition() {
        return targetPosition;
    }

    ZeroPowerBehavior getZeroPowerBehavior() {
        return zeroPowerBehavior;
    }

    void setMode(RunMode mode) {
        this.mode = mode;
    }

    void setTargetPosition(int position) {
        this.targetPosition = position;
    }

    void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {
        this.zeroPowerBehavior = zeroPowerBehavior;
    }

    /* DcMotorSimple inherited functions */

    public void setPower(double power) {
        this.power = power;
    }

    public double getPower() {
        return power;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /* HardwareDevice inherited functions */

    public void close() {

    }

    public String getConnectionInfo() {

    }

    public String getDeviceName() {
    
    }

    public int getVersion() {

    }

    public void resetDeviceConfigurationForOpMode() {}
}