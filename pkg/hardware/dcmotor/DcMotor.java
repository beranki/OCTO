package pkg.hardware.dcmotor;

public interface DcMotor extends DcMotorSimple {
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

    public double getCurrentPosition();
    public RunMode getMode();
    public int getPortNumber();
    public double getTargetPosition();
    public ZeroPowerBehavior getZeroPowerBehavior();
    public void setMode(RunMode mode);
    public void setTargetPosition(int position);
    public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior);
    
}