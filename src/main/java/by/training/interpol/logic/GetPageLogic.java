package by.training.interpol.logic;

import by.training.interpol.entity.WantedPerson;

import java.util.List;

public class GetPageLogic {
    public static List<WantedPerson> findWantedPerson(List<WantedPerson> wantedPeople, int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<WantedPerson> wantedPeoplePage = wantedPeople.subList(start, start + recordsPerPage);

        return  wantedPeoplePage;
    }
}
