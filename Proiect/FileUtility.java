import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtility {
    public static void writeToFile(ListCarte> carti) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        File file = new File("src/main/resources/carti.json");
        try {
            writer.writeValue(file, carti);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ListCarte> readFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/carti.json")));
            ListCarte> carti = mapper.readValue(json, new TypeReference<ListCarte>>() {
            });
            return carti;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
