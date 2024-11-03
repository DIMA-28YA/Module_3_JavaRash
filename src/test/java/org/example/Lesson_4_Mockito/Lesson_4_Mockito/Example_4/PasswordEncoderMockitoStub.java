package org.example.Lesson_4_Mockito.Lesson_4_Mockito.Example_4;

import org.example.Lesson_4_Mockito.PasswordEncoder;

import java.util.Objects;

class PasswordEncoderMockitoStub implements PasswordEncoder{
    @Override
    public String encode(String ranPassword) {
        return ranPassword;
    }
    @Override
    public boolean match(String ranPassword, String encodePassword) {
        System.out.println("invoke match method");
        if(ranPassword.equals("1")&&encodePassword.equals("2")){
            throw new RuntimeException();
        }
        return false;
    }

}
