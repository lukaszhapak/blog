package example.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import example.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
    Optional<User> findByUserName(String userName);

}
