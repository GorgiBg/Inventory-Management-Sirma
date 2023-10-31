package sirma.main.java.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import sirma.main.java.constants.StringConstants;
import sirma.main.java.entities.InventoryItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        configureMapper();
    }

    public static void configureMapper() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    // read list of items from json
    public static List<InventoryItem> loadItemsFromJson(String path) {

        try {
            return objectMapper.readValue(new File(path), new TypeReference<List<InventoryItem>>() {
            });
        } catch (IOException e) {
            System.out.println(StringConstants.ERROR_LOADING_JSON);
        }
        return null;
    }
}
