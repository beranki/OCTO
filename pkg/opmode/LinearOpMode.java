package pkg.opmode;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import pkg.util.Telemetry;

public abstract class LinearOpMode extends OpMode {
    
    public LinearOpMode() {
        super();

    }

    public void init() {
        START_TIME = System.currentTimeMillis();
        exec = Executors.newSingleThreadScheduledExecutor();
        telemetry = new Telemetry();

        internalOpModeServices = new Runnable() {
            @Override 
            public void run() {
                loop();
                time += BASE_MS_DELAY;
            }
        };

        execHandle = exec.scheduleWithFixedDelay(internalOpModeServices, 0, BASE_MS_DELAY, TimeUnit.MILLISECONDS);


        masterCancel = new Runnable() {
            @Override
            public void run() {
                execHandle.cancel(true);
            }
        };
    }

    //Place holder isStarted - not yet integrated w/ interface
    public boolean isStarted() {
        return true && !isStopRequested();
    }

    public boolean isStopRequested() {
        return opModeCalled;
    }
    
    public boolean opModeIsActive() {
        return !execHandle.isDone();
    }

    /** Override this method and write code here. */
    public abstract void runOpMode() throws InterruptedException;

    
    public void sleep(long milliseconds) throws InterruptedException{
        Thread.sleep(milliseconds);
    }

    public void waitForStart() throws InterruptedException {
        /*while (ENTER CONDITION) {
            idle();
        }*/
    }
    

    public void idle() throws InterruptedException {
        Thread.sleep(100);
    }

    public void loop() {
        try {
            runOpMode();
        } catch (InterruptedException e) {}
    }
}
