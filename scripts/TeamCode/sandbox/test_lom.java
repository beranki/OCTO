package scripts.TeamCode.sandbox;

import pkg.opmode.LinearOpMode;
import pkg.opmode.annotations.TeleOp;
import pkg.hardware.dcmotor.*;
import pkg.hardware.dcmotor.DcMotor.RunMode;
import pkg.hardware.dcmotor.DcMotorSimple.Direction;


@TeleOp(name = "Test LinearOpMode")
public class test_lom extends LinearOpMode {
    
    DcMotor motor;

    

    @Override
    public void runOpMode() throws InterruptedException {

        motor = hardwareMap.get(DcMotor.class, "testMotor");
        //motor = new DcMotorImpl(1);
        motor.setDirection(Direction.FORWARD);
        motor.setPower(0.2);
        motor.setTargetPosition(1000);
        motor.setMode(RunMode.RUN_TO_POSITION);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Motor position", motor.getCurrentPosition());
            telemetry.addData("Motor power", motor.getPower());
            telemetry.update();
            sleep(20);
            //requestOpModeStop();
        }

        System.out.println(getRuntime());
    }
}

class MainScript {
    public static void main(String[] args) {
        
        test_lom test = new test_lom();
    }
}


