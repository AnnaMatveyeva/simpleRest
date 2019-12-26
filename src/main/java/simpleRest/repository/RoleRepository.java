package simpleRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simpleRest.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
