/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tildasaur.smartsearchback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tildasaur.smartsearchback.entity.Role;

/**
 *
 * @author timof
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
