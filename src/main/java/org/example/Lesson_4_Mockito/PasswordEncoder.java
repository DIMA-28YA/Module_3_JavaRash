package org.example.Lesson_4_Mockito;

public interface PasswordEncoder {
    String encode(String ranPassword);
    boolean match (String ranPassword, String encodePassword);
}
