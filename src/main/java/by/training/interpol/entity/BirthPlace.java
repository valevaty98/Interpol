package by.training.interpol.entity;

import java.util.Objects;

public class BirthPlace extends Entity {
    private String name;

    public BirthPlace(String name) {
        this.name = name;
    }

    public BirthPlace(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BirthPlace birthPlace = (BirthPlace) o;
        return getId() == birthPlace.getId() &&
                Objects.equals(name, birthPlace.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    @Override
    public String toString() {
        return "BirthPlace{" +
                "stateId=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
