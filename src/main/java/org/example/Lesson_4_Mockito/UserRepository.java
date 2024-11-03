package org.example.Lesson_4_Mockito;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional <User> findById(Integer id);
    List<User> findAll();
    Optional<User> findByEmail(String email);



}
