package simpleRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simpleRest.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
