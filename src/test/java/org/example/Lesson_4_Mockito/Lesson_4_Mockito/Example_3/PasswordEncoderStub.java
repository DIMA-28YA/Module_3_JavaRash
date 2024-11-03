package org.example.Lesson_4_Mockito.Lesson_4_Mockito.Example_3;

import org.example.Lesson_4_Mockito.PasswordEncoder;
import org.example.Lesson_4_Mockito.User;
import org.example.Lesson_4_Mockito.UserRepository;
import org.example.Lesson_4_Mockito.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class UserServiceWithMockitoTest {
    private static final String EMAIL = "example@gmail.com";
    private static final String PASSWORD = "password";

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    UserService userService;


    @Test
    void loginShouldReturnTrueIfUserWithSuchPasswordExist(){
      //  UserService userService = new UserService(userRepository, passwordEncoder);

        Mockito
                .when(userRepository.findByEmail(EMAIL))
                .thenReturn(Optional.of(new User().setEmail(EMAIL).setPassword(PASSWORD)));
        Mockito
//                .when(passwordEncoder.match(PASSWORD,PASSWORD)).thenReturn(true);
                .when(passwordEncoder.match(ArgumentMatchers.any(),ArgumentMatchers.any()))
                .thenReturn(true);
         boolean isLogin = userService.lodin(PASSWORD, EMAIL);
        assertTrue(isLogin);
    }


}

public class PasswordEncoderStub implements PasswordEncoder{
    @Override
    public String encode(String ranPassword) {
        return ranPassword;
    }
    @Override
    public boolean match(String ranPassword, String encodePassword) {
        return Objects.equals(ranPassword,encodePassword);
    }

}
class UserRepositoryStub implements UserRepository{

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }
    @Override
    public List<User> findAll() {
        return null;
    }
    @Override
    public Optional<User> findByEmail(String email) {
        if ("example@gmail.com".equals(email)){
            final User user = new User();
            user.setEmail(email);
            user.setPassword("password");
            return Optional.of(user);
        }
        return Optional.empty();
    }
}