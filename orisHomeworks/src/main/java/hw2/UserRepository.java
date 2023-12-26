package hw2;

import java.util.List;

public interface UserRepository<T> {
    List<T> findAllByAge(Integer age);
    List<T> findByusername(String username);
}
