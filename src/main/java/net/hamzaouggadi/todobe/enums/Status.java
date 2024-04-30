package net.hamzaouggadi.todobe.enums;

public enum Status {

    TO_DO("Task waiting to be added to the IN_PROGRESS state."),
    IN_PROGRESS("Currently ongoing task."),
    DONE("Completed task.");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
