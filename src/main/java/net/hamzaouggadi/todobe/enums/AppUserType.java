package net.hamzaouggadi.todobe.enums;

public enum AppUserType {
    NORMAL("Normal user."),
    MODERATOR("Moderator user, with more privileges."),
    ADMIN("God user.");

    private final String value;

    AppUserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
