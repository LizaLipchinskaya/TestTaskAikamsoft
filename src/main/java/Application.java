import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.Controller;
import dto.SearchDto;
import org.json.simple.parser.ParseException;
import parser.SearchParser;
import parser.StatParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Application {

    private static final Controller controller = new Controller();

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        switch (args[0]) {
            case "search" -> {
                SearchParser searchParser = new SearchParser(controller);
                try(Writer writer = new FileWriter(args[2])) {
                    SearchDto searchDto = searchParser.parse(new File(args[1]));
                    gson.toJson(searchDto, writer);
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
