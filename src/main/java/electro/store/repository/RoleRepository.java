package electro.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import electro.store.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
