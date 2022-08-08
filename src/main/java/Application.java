import org.json.simple.parser.ParseException;
import parser.SearchParser;

import java.io.File;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        switch (args[1]) {
            case "search" :
                SearchParser searchParser = new SearchParser();
                try {
                    searchParser.parse(new File(args[2]));
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "stat" :
                break;
        }
    }
}
