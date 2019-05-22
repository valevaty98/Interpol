package by.training.interpol.command;

import by.training.interpol.entity.Language;
import by.training.interpol.entity.User;
import by.training.interpol.logic.SetNewUserLanguageLogic;

public class ChangeLanguageCommand implements Command {
    @Override
    public ResponseType execute(SessionRequestContent content) {
        User user = (User)content.getFromSessionAttributes("user");
        String[] langParams = content.getFromRequestParameters("lang");
        Language lang;
        try {
            lang = (langParams != null) ? Language.valueOf(langParams[0].toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            System.out.println("no such lang");
            return new HomeCommand().execute(content);
        }
        SetNewUserLanguageLogic.setLanguageToUser(user, lang);
        content.putInSessionAttributes("user", user);
        return new HomeCommand().execute(content);
    }
}
