package io.login.test;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OtherTest {

    @Test
    public void testRegex() {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("\\w@(\\w\\.)*\\w", Pattern.CASE_INSENSITIVE);

        String emailStr = "wyt@qq.vom";
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        boolean res = matcher.find();
        System.out.println(emailStr.matches("\\w+@(\\w+\\.)*\\w+"));
    }
}
