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

     List mainFunction(String[] fileN){
        int nSi = 1000;
        int f;
        long sum;
        long fSize;
        long sep;
        List result = new ArrayList();
        if (si){
            nSi = 1024;
        }
        sum = 0;
        for (String i:fileN){
            File file = new File(i);
            if (file.exists()) {
                if (!size) {
                    fSize = fileLength(file);
                    sep = fSize / nSi;
                    if (!format) {
                        fSize = fSize / nSi;
                        result.add(fSize);
                    }
                    else{
                        f = 0;
                        while (sep > 0) {
                            f = f + 1;
                            fSize = fSize / nSi;
                            sep = fSize / nSi;
                        }
                        result.add(fSize + unit[f]);
                    }

                }
                else sum = sum + fileLength(file);
            }
        }
        if (size) {
            if (format) {
                f = 0;
                while (sum / nSi > 0) {
                    f += 1;
                    sum = sum / nSi;
                }
                result.add(sum + unit[f]);
            } else
            {
                sum = sum / nSi;
                result.add(sum);
            }
            return result;
        }
        return result;
    }


    private static long fileLength(File file) {
         long length = 0;
         File[] files = file.listFiles();
         if (!file.isFile()) {
             for (File i : files) {
                 if (!i.isDirectory()) length = length + i.length();
                 else length = length + fileLength(i);
             }
             return length;
         } else return file.length();
     }


}



