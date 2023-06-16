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
        while (tel.size() > 0) {
            System.out.println(tel.remove());
        }
    }
}