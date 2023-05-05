package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    //TODO : not fixes rejex to change password
    //TODO : hash code into json
    //TODO : static regex
    PROFILE_USERNAME_CHANGE("profile\\s+change\\s+-u\\s+(?<username>(?=\")\"[^\"]+\"|\\S+)"),
    PROFILE_NICKNAME_CHANGE("profile\\s+change\\s+-n\\s+(?<nickname>(?=\")\"[^\"]+\"|\\S+)"),
    //    PROFILE_PASSWORD_CHANGE("(?<old>(-o\\s+(?=\"+)[^-]+)\"{1}|(-o\\s+(?=\"{0})[^\\s-]+))\\s+(?<new>(-n\\s+(?=\"+)[^-]+)\"{1}|(-n\\s+(?=\"{0})[^\\s-]+))"),
    PROFILE_PASSWORD_CHANGE("profile\\s+change\\s+password"),
    PROFILE_GET_OLD_PASSWORD("-o\\s+(?<old>(?=\")\"[^\"]+\"|\\S+)"),
    PROFILE_GET_NEW_PASSWORD("-n\\s+(?<new>(?=\")\"[^\"]+\"|\\S+)"),

    //TODO : fix it
    PROFILE_EMAIL_CHANGE("profile\\s+change\\s+(--email|-e)\\s+(?<email>(?=\")\"[^\"]+\"|\\S+)"),
    PROFILE_REMOVE_SLOGAN("profile\\s+remove\\s+slogan"),
    PROFILE_SLOGAN_CHANGE("profile\\s+change\\s+slogan\\s+-s\\s+(?<slogan>(?=\")\"[^\"]+\"|\\S+)");
    String regex ;
    ProfileMenuCommands(String regex){
        this.regex = regex ;
    }
    public static Matcher getMatcher(String command , ProfileMenuCommands mainRegex){
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if(matcher.find()) return matcher ;
        return null ;
    }

}

