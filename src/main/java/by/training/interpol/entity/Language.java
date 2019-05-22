package by.training.interpol.entity;

public enum Language {
    ENG, RUS;
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
