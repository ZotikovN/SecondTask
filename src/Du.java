import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Du {
    @Argument(metaVar = "fileN", usage = "file")
    private String[] fileN;

    public static void main(List<String> args) {
        new Du().launch(args);
    }

    public static List result = new ArrayList();

    private void resultListCmd(List finalResult) {
        for (Object i : finalResult) {
            System.out.println("File(s) size is " + i);
        }
    }

    private void launch(List<String> files) {
        CmdLineParser cmd = new CmdLineParser(this);
        try {
            cmd.parseArgument(files);
        } catch (CmdLineException a) {
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

}