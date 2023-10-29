package entities.interfaces;

import entities.enums.Category;

public interface Categorizable {

    String getItemCategory();
    void setItemCategory(Category category);
}
