package scripts.TeamCode.sandbox;

import pkg.opmode.LinearOpMode;
import pkg.opmode.annotations.TeleOp;

@TeleOp(name = "Test LinearOpMode")
public class test_lom extends LinearOpMode {
    
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Test", 3);
            telemetry.addLine("I am test 2");
            sleep(1000);
            telemetry.update();
            telemetry.addData("End test noise", "Apple");
            sleep(1000);
            requestOpModeStop();
        }

        System.out.println(getRuntime());
    }
}

