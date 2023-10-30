package sirma.main.java.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sirma.main.java.entities.InventoryItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {

    // write single item
    public static String writeItemToJson(InventoryItem item) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(item);
    }


    // read single item
    public static InventoryItem readItemFromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, InventoryItem.class);
    }

    // save list of items
    public static void saveItemsToJson(List<InventoryItem> inventory, String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filename), inventory);
    }

    // read list of items from json and add to list
    public static List<InventoryItem> loadItemsFromJson(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filename), new TypeReference<List<InventoryItem>>() {});
    }
}
