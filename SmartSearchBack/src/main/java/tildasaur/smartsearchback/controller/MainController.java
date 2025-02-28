/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tildasaur.smartsearchback.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tildasaur.smartsearchback.entity.Message;
import tildasaur.smartsearchback.entity.Role;
import tildasaur.smartsearchback.entity.Room;
import tildasaur.smartsearchback.entity.Userroom;
import tildasaur.smartsearchback.entity.Users;
import tildasaur.smartsearchback.repository.LogsRepository;
import tildasaur.smartsearchback.repository.MentionsRepository;
import tildasaur.smartsearchback.repository.MessageRepository;
import tildasaur.smartsearchback.repository.RoleRepository;
import tildasaur.smartsearchback.repository.RoomRepository;
import tildasaur.smartsearchback.repository.TimeworksRepository;
import tildasaur.smartsearchback.repository.UserroomRepository;
import tildasaur.smartsearchback.repository.UsersRepository;

/**
 *
 * @author timof
 */
@RestController
@RequestMapping("/smartSearchBack")
public class MainController {

    @Autowired
    private LogsRepository logsRepository;
    @Autowired
    private MentionsRepository mentionsRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private TimeworksRepository timeWorksRepository;
    @Autowired
    private UserroomRepository userroomRepository;
    @Autowired
    private UsersRepository usersRepository;

    //Получить все чаты
    @GetMapping("/getRooms")
    Iterable<Room> allRoom() {
        return roomRepository.findAll();
    }

    // Добавить чат
    @PostMapping("/addRoom")
    public @ResponseBody
    boolean addEmployee(@RequestParam(name = "nameRoom") String nameRoom,
            @RequestParam(name = "DateCreateRoom") String DateCreateRoom) {

        Room room = new Room();
        room.setNameRoom(nameRoom);

        DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = format.parse(DateCreateRoom);

        } catch (ParseException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        room.setDateCreateRoom(date);

        roomRepository.save(room);
        return true;
    }

    //удалит чат
    @PostMapping("/deleteRoom")
    public @ResponseBody
    boolean deleteRoom(
            @RequestParam(name = "idRoom") String idRoom) {
        roomRepository.deleteById(Integer.parseInt(idRoom));
        return true;
    }

    //Получить все сообщения
    @GetMapping("/getMessage")
    Iterable<Message> allMessage() {
        return messageRepository.findAll();
    }

    @GetMapping("/getMessageRoom")
    public List<String> allMessageRoom(@RequestParam(name = "idRoom") Integer idRoom) {
        List<Message> messages = messageRepository.findByIdRoom_IdRoom(idRoom);

        List<String> formattedMessages = new ArrayList<>();
        for (Message message : messages) {
            formattedMessages.add("Текст сообщения: " + message.getContent());
        }

        return formattedMessages;
    }

    //Добавить сообщение 
    @PostMapping("/addMessage")
    public @ResponseBody
    boolean addMessage(@RequestParam(name = "idRoom") String idRoom,
            @RequestParam(name = "idUser") String idUser,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "urlFile") String urlFile,
            @RequestParam(name = "isEditing") String isEditing,
            @RequestParam(name = "isDeleted") String isDeleted) {

        Message message = new Message();

        Room room = new Room(Integer.parseInt(idRoom));
        Users user = new Users(Integer.parseInt(idUser));

        message.setIdRoom(room);
        message.setIdUser(user);

        message.setContent(content);
        message.setUrlFile(urlFile);
        boolean isEditingBoolean = Boolean.parseBoolean(isEditing);
        message.setIsEditing(isEditingBoolean);

        boolean isDeletedBoolean = Boolean.parseBoolean(isDeleted);
        message.setIsDeleted(isDeletedBoolean);

        messageRepository.save(message);
        return true;
    }

    //Изменить сообщение
    @PostMapping("/updateMessage")
public @ResponseBody
boolean updateMessage(@RequestParam(name = "idRoom") String idRoom,
                      @RequestParam(name = "idUser") String idUser,
                      @RequestParam(name = "content") String content,
                      @RequestParam(name = "urlFile") String urlFile,
                      @RequestParam(name = "idDeleted") String isDeleted,
                      @RequestParam(name = "idMessage") String idMessage) {

    // Находим сообщение по id
    Message message = messageRepository.findById(Integer.parseInt(idMessage)).orElse(null);
    if (message == null) {
        return false; // Если сообщение не найдено, возвращаем false
    }

    // Создаем объекты Room и Users
    Room room = new Room(Integer.parseInt(idRoom));
    Users user = new Users(Integer.parseInt(idUser));

    // Обновляем поля сообщения
    message.setIdRoom(room);
    message.setIdUser(user);
    message.setContent(content);
    message.setUrlFile(urlFile);
    message.setIsEditing(true); // Всегда устанавливаем isEditing в true
    message.setIsDeleted(Boolean.parseBoolean(isDeleted));

    // Сохраняем обновленное сообщение
    messageRepository.save(message);
    return true;
}

    //Удалить сообщение
    @PostMapping("/deleteMessage")
    public @ResponseBody
    boolean deleteMessage(
            @RequestParam(name = "idMessage") String idMessage) {
        messageRepository.deleteById(Integer.parseInt(idMessage));
        return true;
    }

    //Получить все чаты пользователя
  @GetMapping("/getRoomsUser")
public String allUserRoom(@RequestParam(name = "idUser") Integer idUser) {
    // Получаем все Userroom для данного пользователя
    List<Userroom> userRooms = userroomRepository.findByIdUser_IsUsers(idUser);

    // Получаем пользователя по idUser
    Users user = usersRepository.findById(idUser).orElse(null);

    if (user == null) {
        return "Пользователь не найден"; // Обработка случая, когда пользователь не найден
    }

    // Форматируем результат
    StringBuilder result = new StringBuilder();
    result.append(user.getUserName()).append(": ");

    // Получаем имена комнат и добавляем их в результат
    List<String> roomNames = userRooms.stream()
            .map(userroom -> userroom.getIdRoom().getNameRoom())
            .collect(Collectors.toList());

    // Присоединяем имена комнат к результату
    result.append(String.join(", ", roomNames));

    return result.toString(); // Возвращаем строку с именем пользователя и комнатами
}
    //Добавить пользователя в чат
    @PostMapping("/addRoomUser")
    public @ResponseBody
    boolean addRoomUser(@RequestParam(name = "idUser") String idUser,
            @RequestParam(name = "idRoom") String idRoom,
            @RequestParam(name = "idRole") String idRole) {

        Userroom userroom = new Userroom();
        Users user = new Users(Integer.parseInt(idUser));
        userroom.setIdUser(user);
        Room room = new Room(Integer.parseInt(idRoom));
        userroom.setIdRoom(room);
        Role role = new Role(Integer.parseInt(idRole));
        userroom.setIdRole(role);

        userroomRepository.save(userroom);
        return true;
    }
    //Удалить пользовтаеля из чата

    @PostMapping("/deleteRoomUser")
    public @ResponseBody
    boolean deleteRoomUser(
            @RequestParam(name = "idUserRole") String idUserRole) {
        userroomRepository.deleteById(Integer.parseInt(idUserRole));
        return true;
    }
}
