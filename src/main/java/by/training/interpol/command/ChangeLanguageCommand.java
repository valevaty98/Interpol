package by.training.interpol.command;

import by.training.interpol.entity.Language;
import by.training.interpol.entity.User;
import by.training.interpol.logic.SetNewUserLanguageLogic;
import by.training.interpol.util.AttributeParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeLanguageCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public ResponseType execute(SessionRequestContent content) {
        User user = (User)content.getFromSessionAttributes(AttributeParameterName.USER_ATTR);
        String[] langParams = content.getFromRequestParameters(AttributeParameterName.LANGUAGE_PARAM);
        Language lang;
        try {
            lang = (langParams != null) ? Language.valueOf(langParams[0].toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARN, "No such language exception", e);
            return new HomeCommand().execute(content);
        }
        SetNewUserLanguageLogic.setLanguageToUser(user, lang);
        content.putInSessionAttributes(AttributeParameterName.USER_ATTR, user);
        return new HomeCommand().execute(content);
    }
}
