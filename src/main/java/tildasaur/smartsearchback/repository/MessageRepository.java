/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tildasaur.smartsearchback.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tildasaur.smartsearchback.entity.Message;

/**
 *
 * @author timof
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Integer>{
    List<Message> findByIdRoom_IdRoom(Integer idRoom);
}
