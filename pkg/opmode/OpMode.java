package pkg.opmode;

import pkg.util.HardwareMap;
import pkg.util.Telemetry;

public abstract class OpMode {
    final int BASE_MS_DELAY = 5; //20 millisecond delay if there is no sleep specified
    private final int OPMODE_LENGTH = 30; //30 seconds

    public Telemetry telemetry;
    public double time;
    double START_TIME;
    boolean opModeCalled;
    protected HardwareMap hardwareMap;
    
    public Thread internalOpModeServices;
    protected boolean exit = false;
    public Thread internalUpdate;

    public OpMode() {
        init();
    }


    public final void requestOpModeStop() {
        exit = true;
        stop();
    }


    public abstract void loop();
    public abstract void init();
    
    /** User defined start method. By default, does nothing. */
    public void start() {        
    
    }

    /** User defined stop method. By default, does nothing. */
    public void stop() {
        opModeCalled = true;
        updateTelemetry(telemetry);
    }

    /** Get the runtime in seconds. */
    public double getRuntime() {
        return (System.currentTimeMillis() - START_TIME)/1000;
    }

    public void updateTelemetry(Telemetry telemetry) {
        telemetry.update();
    }

}
