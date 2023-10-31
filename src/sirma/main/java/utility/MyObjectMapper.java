package sirma.main.java.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    // read list of items from json and add to list
    public static List<InventoryItem> loadItemsFromJson(String path) throws IOException {
        return objectMapper.readValue(new File(path), new TypeReference<List<InventoryItem>>() {});
    }
}
