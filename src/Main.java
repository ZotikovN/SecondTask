import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Du{
    public static void main(List<String> args) {
        new Du().launch(args);
    }

    public static ArrayList result = new ArrayList();

    private void resultListCmd (ArrayList finalResult) {
        for (Object i:finalResult) {
            System.out.println(i);
        }
    }

    private void launch(List<String> files) {
        CmdLineParser cmd = new CmdLineParser(this);
        try {
            cmd.parseArgument(files);
        }
        catch (CmdLineException a) {
            System.err.println(a.getMessage());
            cmd.printUsage(System.err);
        }
        Main mainDU = new Main(format, size, si);
        result = mainDU.mainFunction(fileN);
        resultListCmd(mainDU.mainFunction(fileN));
    }

    //Комманды ввода в консоль

    @Option(name = "-h", usage = "format")
    private boolean format = false;

    @Option(name = "-c", usage = "size")
    private boolean size = false;

    @Option(name = "--si", usage = "si")
    private boolean si = false;

    @Argument(metaVar = "fileN", usage = "file name")
    private String[] fileN;


 private class Main {
    private boolean format;
    private boolean size;
    private boolean si;
    private String[] unit = {"B", "KB", "MB", "GB"};

    private boolean fileLengthChk(File file) {
        if (!file.isFile())
            return true;
        else return false;
    }

    private Main(boolean format, boolean size, boolean si){
        this.format = format;
        this.size = size;
        this.si = si;
    }

     //Функция реализующая комманды ввода в консоль

    private ArrayList mainFunction(String[] fileN){
        int nSi;
        int f;
        long sum;
        long fSize;
        long sep;
        ArrayList result = new ArrayList();
        if (si){
            nSi = 1024;
        }
        else {
            nSi = 1000;
        }
        sum = 0;
        for (String i:fileN){
            File file = new File(i);
            if (file.exists()) {
                if (!size) {
                    fSize = fileLength(file);
                    if (!format) {
                        fSize = fSize / nSi;
                        result.add(i + " " + fSize);
                    }
                    else{
                        f = 0;
                        sep = fSize/nSi;
                        while (sep > 0) {
                            f = f + 1;
                            fSize = fSize / nSi;
                            sep = fSize / nSi;
                        }
                        result.add(i + " " + fSize + unit[f]);
                    }

                }
                else sum = sum + fileLength(file);
            }
        }
        if (!size) {
            return result;
        }
        if (format) {
            f = 0;
            while (sum / nSi > 0) {
                f += 1;
                sum = sum / nSi;
            }
            result.add("Размер каталога " + sum + unit[f]);
        } else
            {
                sum = sum / nSi;
                result.add("Размер каталога " + sum);
            }
        return result;
    }

    private long fileLength(File file) {
        long length = 0;
        File[] files = file.listFiles();
        if (fileLengthChk(file)) {
            for (File i: files) {
            if (!i.isDirectory()) length = length + i.length();
            else length = length + fileLength(i);
        }
        return length;
    }
        else return file.length();
}
}
}

