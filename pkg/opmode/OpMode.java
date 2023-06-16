package pkg.opmode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import pkg.util.Telemetry;

public abstract class OpMode {
    final int BASE_MS_DELAY = 5; //5 millisecond delay if there is no sleep specified
    private final int OPMODE_LENGTH = 30; //30 seconds

    public Telemetry telemetry;
    public double time;
    double START_TIME;
    boolean opModeCalled;
    
    public Runnable internalOpModeServices;
    Runnable masterCancel;
    ScheduledFuture<?> execHandle;
    private boolean thirtySecondStop;

    ScheduledExecutorService exec;

    public OpMode() {
        init();
    }


    public final void requestOpModeStop() {
        exec.shutdownNow();
        stop();
    }


    public abstract void loop();
    public abstract void init();
    
    /** User defined start method. By default, does nothing. */
    public void start() {
        if (thirtySecondStop) exec.schedule(masterCancel, OPMODE_LENGTH, TimeUnit.SECONDS);
        
    
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

    public void set30SecondStop(boolean stop) {
        thirtySecondStop = stop;
    }

}
