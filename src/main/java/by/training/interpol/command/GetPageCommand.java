package by.training.interpol.command;

import by.training.interpol.entity.WantedPerson;
import by.training.interpol.logic.GetPageLogic;
import by.training.interpol.logic.ReceiveWantedPersonInfoLogic;

import java.util.List;

public class GetPageCommand implements Command {
    private static final String MAIN_PAGE_PATH = "/jsp/main_page.jsp";
    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();

        int currentPage = Integer.parseInt(content.getFromRequestParameters("currentPage")[0]);
        int recordsPerPage = Integer.parseInt(content.getFromRequestParameters("recordsPerPage")[0]);

        List<WantedPerson> wantedPeople = (List<WantedPerson>)content.getFromSessionAttributes("wantedPerson");
        List<WantedPerson> wantedPeoplePage = GetPageLogic.findWantedPerson(wantedPeople, currentPage, recordsPerPage);

        int rows = wantedPeople.size();

        int numberOfPages = rows/ recordsPerPage;

        if (numberOfPages % recordsPerPage > 0) {
            numberOfPages++;
        }

        content.putInRequestAttributes("wantedPage", wantedPeoplePage);
        content.putInRequestAttributes("numberOfPages", numberOfPages);
        content.putInRequestAttributes("currentPage", currentPage);
        content.putInRequestAttributes("recordsPerPage", recordsPerPage);

        return builder.buildResponseType(MAIN_PAGE_PATH, SendType.REDIRECT);
    }
}
