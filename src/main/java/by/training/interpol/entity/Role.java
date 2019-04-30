package by.training.interpol.entity;

public enum Role {
    ADMIN,
    GUEST;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
