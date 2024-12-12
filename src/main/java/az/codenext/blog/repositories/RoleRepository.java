package az.codenext.blog.repositories;

import az.codenext.blog.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
//    List<Role> findAllById(List<Long> roleIds);
}
