package org.example.Lesson_4_Mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserRepository userRepository = new UserRepositoryStub();
    PasswordEncoder passwordEncoder = new PasswordEncoderStub();
    UserService userService= new UserService(userRepository, passwordEncoder);

    @Test
    void loginShouldReturnTrueIfUserWithSuchPasswordExist(){
         boolean isLogin = userService.lodin("password", "example@gmail.com");
        assertTrue(isLogin);
    }
    @Test
    void loginShouldReturnFalseIfUserWithSuchEmailsDoesNotExist(){
        boolean isLogin = userService.lodin("password", "exampl@gmail.com");
        assertFalse(isLogin);
    }

    @Test
    void lodinShouldReturnFalseIfNotCorrectPassword(){
        boolean isLogin = userService.lodin("password", "exmapl@gmail.com");
        assertFalse(isLogin);

    }

}

class PasswordEncoderStub implements PasswordEncoder{
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