package sirma.main.java.entities.enums;

public enum Category {
    FRAGILE("Fragile"),
    GROCERY("Grocery"),
    ELECTRONIC("Electronic");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
