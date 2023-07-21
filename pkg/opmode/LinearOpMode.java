package pkg.opmode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import pkg.hardware.HardwareDevice;
import pkg.hardware.dcmotor.CRServo;
import pkg.hardware.dcmotor.CRServoImpl;
import pkg.hardware.dcmotor.DcMotor;
import pkg.hardware.dcmotor.DcMotorImpl;
import pkg.util.HardwareMap;
import pkg.util.Telemetry;

public abstract class LinearOpMode extends OpMode {
    
    private int POOL_SIZE = 10;
    private String MAPPING_PATH;
    private String MAPPING_ID;
    double recentMs = 0;

    public LinearOpMode() {
        super();

    }

    public void init() {
        START_TIME = System.currentTimeMillis();
        MAPPING_PATH = "pkg\\util\\mappings.txt";
        telemetry = new Telemetry();
        hardwareMap = new HardwareMap();

        map(MAPPING_PATH);

        internalOpModeServices = new Thread() {
            @Override 
            public void run() {
                while (!exit) {
                    loop();
                    time += BASE_MS_DELAY;
                    try {
                        Thread.sleep(BASE_MS_DELAY);
                    } catch (InterruptedException e) {}
                }
            }
        };

        internalUpdate = new Thread() {
            @Override
            public void run() {
                while (!exit) {
                    //System.out.println(hardwareMap.getAllDevices());
                    for (HardwareDevice hwDevice: hardwareMap.getAllDevices()) {
                        hwDevice.internalUpdate(BASE_MS_DELAY);
                    }
                    
                    try {
                        Thread.sleep(BASE_MS_DELAY);
                    } catch (InterruptedException e) {}
                }
            }
        };

        internalOpModeServices.start();
        internalUpdate.start();

    }

    //Place holder isStarted - not yet integrated w/ interface
    public boolean isStarted() {
        return true && !isStopRequested();
    }

    public boolean isStopRequested() {
        return opModeCalled;
    }
    
    public boolean opModeIsActive() {
        return !exit;
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

    private void map(String path) {
        try {
            Scanner s = new Scanner(new File(path));
            while (s.hasNextLine()) {
                String l = s.nextLine();

                if (l.length() > 0) {
                    if (l.charAt(0) == '!') {
                        MAPPING_ID = l;
                    }

                    if (l.equals("@motors")) {
                        while (s.hasNextLine()) {
                            String m = s.nextLine();
                            //System.out.println(m);
                            try {
                                int portNum = Integer.parseInt(m.charAt(0) + "");
                                String[] rest = m.substring(1).trim().split(" | ");
                                String name = rest[0].trim();
                                String type = rest[2].trim();
                                //System.out.println(Arrays.asList(rest));
                                if (name.length() > 0 && type.length() > 0) {
                                    if (type.equals("CRServo")) {
                                        CRServo cr = new CRServoImpl(portNum);
                                        hardwareMap.put(name, cr);
                                    } else if (type.equals("DcMotor")) {
                                        DcMotor motor = new DcMotorImpl(portNum);
                                        //System.out.println("reached here");
                                        hardwareMap.put(name, motor);
                                    }
                                }

                            } catch (Exception e) {
                                //e.printStackTrace();
                                break;
                            }
                        }
                    }

                    
                }

            }
        } catch (IOException e) {System.out.println("mapping path not found");}
    }
}
