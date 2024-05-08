package dh.pensionmanagement.webservices.auth.model.repo;

import dh.pensionmanagement.webservices.auth.model.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetailsEntity,Long> {
    UserDetailsEntity findByUsername(String username);
}
