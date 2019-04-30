package by.training.interpol.logic;

import by.training.interpol.entity.WantedPerson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SearchLogic {
    private static Logger logger = LogManager.getLogger();

    public static List<WantedPerson> searchWantedPeople(List<WantedPerson> wantedPeople, String name, String surname,
                                                        String gender, String fromAge, String toAge, String nationality) {
        List<WantedPerson> filteredWantedPeople = new ArrayList<>();

        for (WantedPerson person : wantedPeople) {
            if (
                    (name == null || name.isEmpty() || name.equals(person.getName())) &&
                    (surname == null || surname.isEmpty() || surname.equals(person.getSurname())) &&
                    (gender.equals("Unknown") || gender.toUpperCase().equals(person.getGender().toString())) &&
                    (fromAge == null || fromAge.isEmpty() || Integer.parseInt(fromAge) <= person.getAge()) &&
                    (toAge == null || toAge.isEmpty() || Integer.parseInt(toAge) >= person.getAge()) &&
                    (nationality == null || nationality.isEmpty())
            ) {
                filteredWantedPeople.add(person);
            }
        }

        return filteredWantedPeople;
    }
}
