import controller.Controller;
import org.json.simple.parser.ParseException;
import parser.SearchParser;
import parser.StatParser;

import java.io.File;
import java.io.IOException;

public class Application {

    private static final Controller controller = new Controller();

    public static void main(String[] args) {
        switch (args[1]) {
            case "search" -> {
                SearchParser searchParser = new SearchParser(controller);
                try {
                    searchParser.parse(new File(args[2]));
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
            case "stat" -> {
                StatParser statParser = new StatParser(controller);
                try {
                    statParser.parse(new File(args[2]));
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
            default -> {
                //...
            }
        }
    }
}
