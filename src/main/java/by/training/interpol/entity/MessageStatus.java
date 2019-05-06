package by.training.interpol.entity;

public enum MessageStatus {
    CHECKED, UNCHECKED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
