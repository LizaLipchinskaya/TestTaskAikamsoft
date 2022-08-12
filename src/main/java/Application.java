import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.Controller;
import dto.ExceptionDto;
import dto.search.SearchDto;
import dto.stat.StatDto;
import exception.BadJsonException;
import org.json.simple.parser.ParseException;
import parser.SearchParser;
import parser.StatParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Application {

    private static final Controller controller = new Controller();

    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        if (args.length != 3) {
            Writer writer = new FileWriter("src/main/resources/error.json");
            ExceptionDto exceptionDto = new ExceptionDto("error",
                    "Неверное количество аргументов командной строки");
            gson.toJson(exceptionDto, writer);

            writer.close();
            return;
        }

        Writer writer = new FileWriter(args[2]);

        switch (args[0]) {
            case "search" -> {
                SearchParser searchParser = new SearchParser(controller);
                try {
                    SearchDto searchDto = searchParser.parse(new File(args[1]));
                    gson.toJson(searchDto, writer);
                } catch (IOException | ParseException | BadJsonException e) {
                    ExceptionDto exceptionDto = new ExceptionDto("error", e.getMessage());
                    gson.toJson(exceptionDto, writer);
                } finally {
                    writer.close();
                }
            }
            case "stat" -> {
                StatParser statParser = new StatParser(controller);
                try {
                    StatDto statDto = statParser.parse(new File(args[1]));
                    gson.toJson(statDto, writer);
                } catch (IOException | ParseException | BadJsonException e) {
                    ExceptionDto exceptionDto = new ExceptionDto("error", e.getMessage());
                    gson.toJson(exceptionDto, writer);
                } finally {
                    writer.close();
                }
            }
            default -> {
                ExceptionDto exceptionDto = new ExceptionDto("error", "Неверный тип операции");
                gson.toJson(exceptionDto, writer);
                writer.close();
            }
        }
    }
}
