package by.training.interpol.logic;

import by.training.interpol.entity.WantedPerson;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.YEARS;

public class SearchLogic {
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private final static String UNKNOWN_GENDER_VALUE = "unknown";
    private final static String SPACE_SYMBOL = "\\s";

    public static List<WantedPerson> searchWantedPeople(List<WantedPerson> wantedPeople, String name, String surname,
                                                        String gender, String fromAge, String toAge, String nationality) {
        List<WantedPerson> filteredWantedPeople = new ArrayList<>();

        for (WantedPerson person : wantedPeople) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDate personBirthDate = LocalDate.parse(person.getBirthDate(), formatter);
            int personAge = (int)YEARS.between(personBirthDate, LocalDate.now());
            boolean isPersonNameCorrespondSearchName = (name == null || name.isEmpty());
            if (!isPersonNameCorrespondSearchName) {
                String[] personNameParts = person.getName().split(SPACE_SYMBOL);
                for (String namePart : personNameParts) {
                    if (namePart.equalsIgnoreCase(name)) {
                        isPersonNameCorrespondSearchName = true;
                    }
                }
            }
            if (
                    isPersonNameCorrespondSearchName &&
                    (surname == null || surname.isEmpty() || surname.equalsIgnoreCase(person.getSurname())) &&
                    (gender == null || UNKNOWN_GENDER_VALUE.equals(gender) || gender.equalsIgnoreCase(person.getGender().toString())) &&
                    (fromAge == null || fromAge.isEmpty() || Integer.parseInt(fromAge) <= personAge) &&
                    (toAge == null || toAge.isEmpty() || Integer.parseInt(toAge) >= personAge) &&
                    (nationality == null || nationality.isEmpty() || person.getNationality().contains(nationality))
            ) {
                filteredWantedPeople.add(person);
            }
        }
        return filteredWantedPeople;
    }
}
