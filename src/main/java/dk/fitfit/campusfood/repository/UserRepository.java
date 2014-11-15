package dk.fitfit.campusfood.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dk.fitfit.campusfood.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmailAndPassword(String email, String name);
	User findByEmail(String email);
}
