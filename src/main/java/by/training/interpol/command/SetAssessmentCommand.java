package by.training.interpol.command;

import by.training.interpol.entity.User;
import by.training.interpol.logic.SetUserAssessmentLogic;
import by.training.interpol.util.AttributeParameterName;
import by.training.interpol.util.PageServletPath;

public class SetAssessmentCommand implements Command {
    private static final String TO_FULL_MESSAGE_ACTION = "?command=show_full_message&message_id=";
    private static final String ERROR_ATTR = "setAssessmentError";

    @Override
    public ResponseType execute(SessionRequestContent content) {
        ResponseTypeCreator builder = new ResponseTypeCreator();
        String[] loginParams = content.getFromRequestParameters(AttributeParameterName.LOGIN_PARAM);
        String[] assessmentParams = content.getFromRequestParameters(AttributeParameterName.ASSESSMENT_MESSAGE_PARAM);
        String[] messageIdParams = content.getFromRequestParameters(AttributeParameterName.MESSAGE_ID_PARAM);
        String login = (loginParams != null) ? loginParams[0] : null;
        String assessment = (assessmentParams != null) ? assessmentParams[0] : null;
        Long messageId;
        try {
            messageId = (messageIdParams != null) ? Long.parseLong(messageIdParams[0]) : null;
        } catch (NumberFormatException e) {
            content.putInRequestAttributes(ERROR_ATTR, "Invalid message id!");
            return builder.buildResponseType(PageServletPath.SET_ASSESSMENT_PAGE, SendType.FORWARD);
        }
        User user = (User)content.getFromSessionAttributes(AttributeParameterName.USER_ATTR);
        boolean isSet = SetUserAssessmentLogic.setAssessmentToUser(login, assessment);
        if (isSet) {
            user.getAssessment().setAssessmentText(assessment);
            content.putInSessionAttributes(AttributeParameterName.USER_ATTR, user);
            return builder.buildResponseType(PageServletPath.FRONT_CONTROLLER + TO_FULL_MESSAGE_ACTION + messageId, SendType.REDIRECT);
        } else {
            content.putInRequestAttributes(ERROR_ATTR, "Assessment isn't set!");
            return builder.buildResponseType(PageServletPath.SET_ASSESSMENT_PAGE, SendType.FORWARD);
        }
    }
}
