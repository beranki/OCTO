package pkg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SANDBOX_REFS {
    public static void main(String[] args) {
        if (args.length > 0) {
            String fileName = args[0];
            String className = fileName.replace(".java", "");
            String sandbox_path = "scripts/TeamCode/sandbox/" + className + ".java";
            
            String tag = "9898dsdkjsd";

            if (new File(sandbox_path).exists()) {
                try {
                    List<String> lines = new ArrayList<String>();
                    
                    lines.add("package pkg.util;\n");
                    lines.add("import scripts.TeamCode.sandbox." + className + ";");
                    lines.add("//THE FOLLOWING CODE IS NOT COMMON SYNTAX - IT IS ONLY FOR RUNNING SIM-PKG");
                    lines.add("public class Validation {");
                    lines.add("    public static void main(String[] args) {");
                    lines.add("        new " + className + "();");
                    lines.add("    }");
                    lines.add("}");
                    

                    FileWriter fw = new FileWriter("pkg/util/Validation.java", false);
                    for (String line: lines) {
                        fw.write(line + "\n");
                    }

                    fw.close();

                    runProcess("javac pkg/util/Validation.java");
                    runProcess("java  pkg/util/Validation.java");

                    
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        } else {
            System.out.println("did not pass file for sandbox ref");
        }
    }

    private static void printLines(InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
      }

      private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(pro.getInputStream());
        printLines(pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
      }
}