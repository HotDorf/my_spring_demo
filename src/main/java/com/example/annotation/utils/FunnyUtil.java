package com.example.annotation.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunnyUtil {

    public static void main(String[] args) {
        System.out.println("\uD83D\uDE0C");
        System.out.println("\uD83D\uDE31");
        System.out.println("\uD83D\uDE06");
        System.out.println("\uD83D\uDE2D");
        System.out.println(filterEmoji("\uD83D\uDE0C"));
    }

    public static String filterEmoji(String source) {
        if(source != null)
        {
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                    Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if( emojiMatcher.find())
            {
                source = emojiMatcher.replaceAll("*");
                return source ;
            }
            return source;
        }
        return "";
    }
}
