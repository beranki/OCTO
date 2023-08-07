package pkg.util;

import java.util.*;

public class Telemetry {
    private LinkedList<String> tel = new LinkedList<String>();

    public void addData(String caption, Object value) {
        tel.add(caption + " : " + value.toString());
    }

    public void addLine(String lineCaption) {
        tel.add(lineCaption);
    }

    public void update() {
        System.out.print("\033[H\033[2J");  
        String t = "";
        while (tel.size() > 0) {
            t += tel.remove() + "\r\n";
        }
        System.out.println(t);
        //System.out.println("===============\nPRESS CTRL C to STOP PROGRAM\n===============");
    }
}
