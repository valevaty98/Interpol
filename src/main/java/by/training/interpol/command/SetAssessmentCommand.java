package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.logic.SetUserAssessmentLogic;

public class SetAssessmentCommand implements Command {
    private static final String BACK_TO_FULL_MESSAGE_ACTION = "/controller?command=show_full_message&message_id=";
    private static final String SET_ASSESSMENT_PAGE_PATH = "/jsp/set_assessment.jsp";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        String[] loginParams = content.getFromRequestParameters("user_login");
        String[] assessmentParams = content.getFromRequestParameters("assessment_message");
        String[] messageIdParams = content.getFromRequestParameters("message_id");
        String login = (loginParams != null) ? loginParams[0] : null;
        String assessment = (assessmentParams != null) ? assessmentParams[0] : null;
        Long messageId;
        try {
            messageId = (messageIdParams != null) ? Long.parseLong(messageIdParams[0]) : null;
        } catch (NumberFormatException e) {
            content.putInRequestAttributes("set_assessment_error", "Invalid message id!");
            return builder.buildResponseType(SET_ASSESSMENT_PAGE_PATH, SendType.FORWARD);
        }
        User user = (User)content.getFromSessionAttributes("user");
        boolean isSet = SetUserAssessmentLogic.setAssessmentToUser(login, assessment);
        if (isSet) {
            user.getAssessment().setAssessmentText(assessment);
            content.putInSessionAttributes("user", user);
            return builder.buildResponseType(BACK_TO_FULL_MESSAGE_ACTION + messageId, SendType.REDIRECT);
        } else {
            content.putInRequestAttributes("set_assessment_error", "Assessment isn't set!");
            return builder.buildResponseType(SET_ASSESSMENT_PAGE_PATH, SendType.FORWARD);
        }
    }
}
