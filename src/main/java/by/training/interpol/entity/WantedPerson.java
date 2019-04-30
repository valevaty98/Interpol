package by.training.interpol.entity;

import java.sql.Blob;

import java.sql.SQLException;
import java.util.Base64;
import java.util.Objects;

public class WantedPerson extends Entity {
    private String name;
    private String surname;
    private Gender gender;
    private String characteristics;
    private float height;
    private float weight;
    private String charges;
    private int age;
    private String birthPlace;
    private String image;

    public WantedPerson(long id, String name, String surname, int age, String birthPlace, Blob img) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.birthPlace = birthPlace;
        try {
            this.image = Base64.getEncoder().encodeToString(img.getBytes(1,(int)img.length()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public WantedPerson(long id, String name, String surname, Gender gender, String characteristics, float height, 
                        float weight, String charges, String birthPlace, int age, Blob img) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.characteristics = characteristics;
        this.height = height;
        this.weight = weight;
        this.charges = charges;
        this.birthPlace = birthPlace;
        this.age = age;
        try {
            this.image = Base64.getEncoder().encodeToString(img.getBytes(1,(int)img.length()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getState_id() {
        return birthPlace;
    }

    public void setState_id(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WantedPerson that = (WantedPerson) o;
        return birthPlace == that.birthPlace &&
                name.equals(that.name) &&
                Objects.equals(surname, that.surname) &&
                gender == that.gender &&
                Objects.equals(characteristics, that.characteristics) &&
                Objects.equals(height, that.height) &&
                Objects.equals(weight, that.weight) &&
                charges.equals(that.charges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, gender, characteristics, height, weight, charges, birthPlace);
    }

    @Override
    public String toString() {
        return "WantedPerson{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender=" + gender +
                ", characteristics='" + characteristics + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", charges='" + charges + '\'' +
                ", birthPlace=" + birthPlace +
                '}';
    }
}
