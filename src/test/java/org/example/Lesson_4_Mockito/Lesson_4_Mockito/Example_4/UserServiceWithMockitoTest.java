package org.example.Lesson_4_Mockito.Lesson_4_Mockito.Example_4;

import org.example.Lesson_4_Mockito.Lesson_4_Mockito.Example_3.PasswordEncoderStub;
import org.example.Lesson_4_Mockito.PasswordEncoder;
import org.example.Lesson_4_Mockito.User;
import org.example.Lesson_4_Mockito.UserRepository;
import org.example.Lesson_4_Mockito.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceWithMockitoTest {
    private static final String EMAIL = "example@gmail.com";
    private static final String PASSWORD = "password";

    @Mock
    UserRepository userRepository;
    @Spy
    PasswordEncoder passwordEncoder = new PasswordEncoderMockitoStub();
    @InjectMocks
    UserService userService;


    @Test
    void loginShouldReturnTrueIfUserWithSuchPasswordExist(){

        when(userRepository.findByEmail(EMAIL))
                .thenReturn(Optional.of(new User().setEmail(EMAIL).setPassword(PASSWORD)));
        when(passwordEncoder.match(ArgumentMatchers.any(),ArgumentMatchers.any()))
                .thenReturn(true);
         boolean isLogin = userService.lodin(PASSWORD, EMAIL);
        assertTrue(isLogin);
        Mockito.verify(userRepository).findByEmail(any());
        Mockito.verify(passwordEncoder).match(anyString(),anyString());
    }



    @Test
    void test1(){
        String email1 = "email_1";
        String email2 = "email_2";

        userRepository.findByEmail(email1);
        userRepository.findByEmail(email2);


        Mockito.verify(userRepository,times(1)).findByEmail(email1);
        Mockito.verify(userRepository.findByEmail(email2));
        Mockito.verify(userRepository,times(2)).findByEmail(anyString());
        Mockito.verify(userRepository,never()).findAll();

        Mockito.verifyNoInteractions(passwordEncoder);
        Mockito.verifyNoMoreInteractions(userRepository);




    }

    @Test
    void test2(){
      //  when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());


        Mockito.doReturn(Optional.empty()).when(userRepository).findByEmail(EMAIL);
        final Optional<User> byEmail = userRepository.findByEmail(EMAIL);

        Assertions.assertFalse(byEmail.isPresent());
    }

    @Test
    void test3(){
        when(passwordEncoder.match(ArgumentMatchers.any(),ArgumentMatchers.any()))
                .thenReturn(true);
        final boolean match = passwordEncoder.match("1", "2");
        Assertions.assertTrue(match);
    }
    @Test
    void test4(){
//        when(passwordEncoder.match(ArgumentMatchers.any(),ArgumentMatchers.any()))
//                .thenReturn(true);
        Mockito.doReturn(true).when(passwordEncoder).match(anyString(),anyString());
        final boolean match = passwordEncoder.match("1", "2");
        Assertions.assertTrue(match);
    }
    @Test
    void test5(){
        //Mockito.when(userRepository.findAll()).thenThrow(new RuntimeException());
        Mockito.doThrow(new RuntimeException()).when(userRepository.findAll());

        Assertions.assertThrows(RuntimeException.class, () -> userRepository.findAll());
    }
    @Test
    void test6(){
        Mockito.when(userRepository.findAll())
                .thenReturn(Collections.EMPTY_LIST)
                .thenThrow(new RuntimeException());

        final List<User> all = userRepository.findAll();
        System.out.println(all);
        Assertions.assertThrows(RuntimeException.class, () -> userRepository.findAll());
    }
}