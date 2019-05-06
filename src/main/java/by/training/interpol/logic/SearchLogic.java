package by.training.interpol.logic;

import by.training.interpol.entity.WantedPerson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;

public class SearchLogic {
    private static Logger logger = LogManager.getLogger();

    public static List<WantedPerson> searchWantedPeople(List<WantedPerson> wantedPeople, String name, String surname,
                                                        String gender, String fromAge, String toAge, String nationality) {
        List<WantedPerson> filteredWantedPeople = new ArrayList<>();

        for (WantedPerson person : wantedPeople) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate personBirthDate = LocalDate.parse(person.getBirthDate(), formatter);
            int personAge = (int)YEARS.between(personBirthDate, LocalDate.now());
            if (
                    (name == null || name.isEmpty() || name.equals(person.getName())) &&
                    (surname == null || surname.isEmpty() || surname.equals(person.getSurname())) &&
                    (gender.equals("unknown") || gender.equals(person.getGender().toString())) &&
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
