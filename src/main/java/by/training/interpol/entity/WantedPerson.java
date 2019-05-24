package by.training.interpol.entity;

import java.io.InputStream;

import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;


public class WantedPerson extends Entity {
    private String name;
    private String surname;
    private Gender gender;
    private String characteristics;
    private Float height;
    private Float weight;
    private String charges;
    private String birthDate;
    private BirthPlace birthPlace;
    private List<String> nationality;
    private String imageEncoded;
    private InputStream imageInputStream;
    private int imageSize;

    public WantedPerson() {
    }

    public WantedPerson(long id, String name, String surname, String birthDate, List<String> nationality, String imageEncoded) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.imageEncoded = imageEncoded;
    }

    public WantedPerson(String name, String surname, Gender gender, String characteristics, Float height,
                        Float weight, String charges, BirthPlace birthPlace, String birthDate, List<String> nationality, String imageEncoded) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.characteristics = characteristics;
        this.height = height;
        this.weight = weight;
        this.charges = charges;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.imageEncoded = imageEncoded;
    }

    public WantedPerson(long id, String name, String surname, Gender gender, String characteristics, Float height,
                        Float weight, String charges, BirthPlace birthPlace, String birthDate, List<String> nationality, String imageEncoded) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.characteristics = characteristics;
        this.height = height;
        this.weight = weight;
        this.charges = charges;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.imageEncoded = imageEncoded;
    }

    public InputStream getImageInputStream() {
        return imageInputStream;
    }

    public void setImageInputStream(InputStream imageInputStream) {
        this.imageInputStream = imageInputStream;
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
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

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public BirthPlace getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(BirthPlace birthPlace) {
        this.birthPlace = birthPlace;
    }

    public List<String> getNationality() {
        return nationality;
    }

    public void setNationality(List<String> nationality) {
        this.nationality = nationality;
    }

    public String getImageEncoded() {
        return imageEncoded;
    }

    public void setImageEncoded(String imageEncoded) {
        this.imageEncoded = imageEncoded;
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
