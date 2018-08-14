import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;




 public class Main {
    private boolean format;
    private boolean size;
    private boolean si;
    private String[] unit = {"B", "KB", "MB", "GB"};


    Main(boolean format, boolean size, boolean si){
        this.format = format;
        this.size = size;
        this.si = si;
    }

     //Функция реализующая комманды ввода в консоль

     ArrayList mainFunction(String[] fileN){
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
                    fSize = Du.fileLength(file);
                    sep = fSize / nSi;
                    if (!format) {
                        fSize = fSize / nSi;
                        result.add(i + " " + fSize);
                    }
                    else{
                        f = 0;
                        while (sep > 0) {
                            f = f + 1;
                            fSize = fSize / nSi;
                            sep = fSize / nSi;
                        }
                        result.add(i + " " + fSize + unit[f]);
                    }

                }
                else sum = sum + Du.fileLength(file);
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
            result.add("Размер каталога составляет " + sum + unit[f]);
        } else
            {
                sum = sum / nSi;
                result.add("Размер каталога составляет " + sum);
            }
        return result;
    }


}



