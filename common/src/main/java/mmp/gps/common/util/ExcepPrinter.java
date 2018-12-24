package mmp.gps.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ExcepPrinter {

    public static void init(String filename) {
        PrintStream err = null;
        File file = new File(filename);

        try {
            err = new PrintStream(new BufferedOutputStream(new FileOutputStream(file, true)), true);
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
        }

        System.setErr(err);
    }

    public static void print(Exception ex) {
        System.err.print(StringUtils.formatDate(System.currentTimeMillis(), ""));
        ex.printStackTrace();
    }

    public static void print(Throwable cause) {
        System.err.print(StringUtils.formatDate(System.currentTimeMillis(), ""));
        cause.printStackTrace();
    }
}
