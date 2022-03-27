package tn.esprit.spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.Entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
