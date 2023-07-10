package pkg.hardware.dcmotor;

public class DcMotorImpl implements DcMotor {

    int portNumber;
    Direction direction;
    
    private RunMode runMode;
    private double targetPosition;
    private double currentPosition;
    private double pow;
    private ZeroPowerBehavior zpb;

    //HARDCODED CONSTANTS FOR 20:1 MOTOR FOR SIMULATION PURPOSES -- DOES NOT REFLECT ACTUAL MEASUREMENTS
    private double COUNTS_PER_REV = 280;
    private double DRIVE_GEAR_REDUCTION = 2.0;
    private double MAX_RPM = 12;
    private double TICKS_PER_SECOND = (COUNTS_PER_REV * DRIVE_GEAR_REDUCTION / 60.0) * getPower();

    public DcMotorImpl(int portNumber) {
        this.portNumber = portNumber;
        currentPosition = 0;
    }
    

    /* DcMotorImpl functions  */

    double adjustPosition(double position) {
        currentPosition += position;
        return currentPosition;
    }

    double adjustPower(double power) {
        pow += power;
        return pow;
    }

    /* DcMotor inherited functions */

    public double getCurrentPosition() {
        return currentPosition;
    }

    public RunMode getMode() {
        return runMode;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public double getTargetPosition() {
        return targetPosition;
    }

    public ZeroPowerBehavior getZeroPowerBehavior() {
        return zpb;
    }

    public void setMode(RunMode mode) {
        this.runMode = mode;
    }

    public void setTargetPosition(int position) {
        this.targetPosition = position;
    }

    public void setZeroPowerBehavior(ZeroPowerBehavior zeroPowerBehavior) {
        zpb = zeroPowerBehavior;
    }

    /* DcMotorSimple inherited functions */

    public double getPower() {
        return pow;
    }

    public void setPower(double power) {
        pow = power;
        TICKS_PER_SECOND = (COUNTS_PER_REV * DRIVE_GEAR_REDUCTION * MAX_RPM / 60.0) * pow; 
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /* HardwareDevice inherited functions */

    public void close() {
        return;
    }

    public String getConnectionInfo() {
        return "";
    }

    public String getDeviceName() {
        return "DcMotor";
    }

    public int getVersion() {
        return 1;
    }

    public void resetDeviceConfigurationForOpMode() {
        targetPosition = 0;
        currentPosition = 0;
        pow = 0.0;

        direction = Direction.FORWARD;
        zpb = ZeroPowerBehavior.UNKNOWN;
        runMode = RunMode.STOP_AND_RESET_ENCODER;
    }

    public void internalUpdate(double msDelay) {
        //System.out.println("INTERNAL UPDATE IS BEING CALLED: " + currentPosition);
        if (getMode() != null) {
            if (getMode().equals(RunMode.RUN_USING_ENCODER)) {
                //System.out.println("SS");
                currentPosition = adjustPosition(((direction.equals(Direction.FORWARD)) ? 1 : -1) * TICKS_PER_SECOND * (msDelay)/1000);
                //System.out.println(TICKS_PER_SECOND * (msDelay)/1000);
            } else if (getMode().equals(RunMode.STOP_AND_RESET_ENCODER)) {
                    targetPosition = 0;
                    currentPosition = 0;
                    pow = 0.0;
            } else if (getMode().equals(RunMode.RESET_ENCODERS)) {
                    currentPosition = 0;
            } else if (getMode().equals(RunMode.RUN_TO_POSITION)) {
                if (currentPosition >= targetPosition) {
                    pow = 0.0;
                } else {
                    //System.out.println(TICKS_PER_SECOND * (msDelay)/1000);
                    currentPosition = adjustPosition(((direction.equals(Direction.FORWARD)) ? 1 : -1) * TICKS_PER_SECOND * (msDelay)/1000);
                }
            }
        }
    }
}
