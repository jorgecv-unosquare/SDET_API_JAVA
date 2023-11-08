package core.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonFileReader {
    private static FileReader fileReader;
    private static JSONObject jsonObject;
    public static String readJsonFile(final String jsonPath) {
        JSONParser json = new JSONParser();
        try {
            fileReader = new FileReader(jsonPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            jsonObject  = (JSONObject) json.parse(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return jsonObject.toString();
    }
}
