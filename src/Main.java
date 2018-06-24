import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class MainLauncher{
    public static ArrayList result = new ArrayList();

    public static void main(List<String> args) {
        new MainLauncher().launch(args);
    }

    private void launch(List<String> args) {
        CmdLineParser cmd = new CmdLineParser(this);
        try {
            cmd.parseArgument(args);
        } catch (CmdLineException e) {
            return;
        }
        Main mainDU = new Main(format, size, si);
        mainDU.resultList(mainDU.mainFunction(fileN));
        result = mainDU.mainFunction(fileN);
    }

    @Option(name = "-h", usage = "Normal format")
    private boolean format = false;

    @Option(name = "-c", usage = "Size")
    private boolean size = false;

    @Option(name = "--si", usage = "Another unit")
    private boolean si = false;

    @Argument(metaVar = "fileN", usage = "Input file names")
    private String[] fileN;


public class Main {
    private boolean format;
    private boolean size;
    private boolean si;
    private String[] unit = {"B", "KB", "MB", "GB"};

    private Main(boolean format,boolean size, boolean si){
        this.format = format;
        this.size = size;
        this.si = si;
    }

    private void resultList (ArrayList finalResult) {
        for (Object i:finalResult) System.out.println(i);
    }

    private ArrayList mainFunction(String[] fileN){
        int nSi;
        int f;
        long sum = 0;
        long fSize;
        long sep;
        ArrayList result = new ArrayList();
        if (si){
            nSi = 1024;
        }
        else {
            nSi = 1000;
        }
        for (String i:fileN){
            File file = new File(i);
            if (file.exists()) {
                if (size) sum = sum + fileSize(file);
                else {
                    fSize = fileSize(file);
                    if (format) {
                        f = 0;
                        sep = fSize/nSi;
                        while (sep > 0) {
                            f += 1;
                            fSize = fSize / nSi;
                            sep = fSize / nSi;
                        }
                        result.add(i + " " + fSize + unit[f]);
                    }
                    else{
                        fSize = fSize / nSi;
                        result.add(i + " " + fSize);
                    }
                }
            }
            else
                {
                    throw new IllegalArgumentException();
                }
        }
        if (size) {
            if (format) {
                f = 0;
                while (sum / nSi > 0) {
                    f += 1;
                    sum = sum / nSi;
                }
                result.add("Размер каталога " + sum + unit[f]);
            } else {
                sum /= nSi;
                result.add("Размер каталога " + sum);
            }
        }
        return result;
    }

    boolean fileSizeChk(File file) {
        if (!file.isFile())
            return true;
        else return false;
    }

    long fileSize(File file) {
        long a = 0;
        if (fileSizeChk(file)) {
            File[] files = file.listFiles();
            for (int i = 0; i <= files.length - 1; i++) {
                if (!files[i].isDirectory()) a = a + files[i].length();
                else a = a + fileSize(files[i]);
            }
            return a;
        }
        else return file.length();
    }
}
}

