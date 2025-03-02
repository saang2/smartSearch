/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tildasaur.smartsearchback.controller;

import jakarta.servlet.http.HttpSession;
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
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
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

    //Получить всех пользователей
    @GetMapping("/getUsers")
    Iterable<Users> allUsers() {
        return usersRepository.findAll();
    }

    //Получить все чаты
    @GetMapping("/getRooms")
    Iterable<Room> allRoom() {
        return roomRepository.findAll();
    }

   @PostMapping("/addRoom")
public @ResponseBody ResponseEntity<Boolean> addRoom(@RequestParam(name = "nameRoom") String nameRoom,
                                                    @RequestParam(name = "userIds") List<Integer> userIds,
                                                    HttpSession session) {
    try {
        // Получаем текущего пользователя из сессии
        Users creator = (Users) session.getAttribute("user");
        if (creator == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false); // Пользователь не авторизован
        }

        // Создаем комнату
        Room room = new Room();
        room.setNameRoom(nameRoom);
        room.setDateCreateRoom(new Date()); // Устанавливаем текущую дату
        roomRepository.save(room);

        // Получаем роль "Администратор" (idRole = 1)
        Role adminRole = roleRepository.findById(1).orElse(null);
        if (adminRole == null) {
            throw new RuntimeException("Роль администратора не найдена");
        }

        // Добавляем создателя чата с ролью администратора
        Userroom creatorUserroom = new Userroom();
        creatorUserroom.setIdUser(creator);
        creatorUserroom.setIdRoom(room);
        creatorUserroom.setIdRole(adminRole); // Передаем объект Role
        userroomRepository.save(creatorUserroom);

        // Получаем роль "Участник" (idRole = 2)
        Role participantRole = roleRepository.findById(2).orElse(null);
        if (participantRole == null) {
            throw new RuntimeException("Роль участника не найдена");
        }

        // Добавляем выбранных пользователей с ролью участника
        for (Integer userId : userIds) {
            Users user = usersRepository.findById(userId).orElse(null);
            if (user != null) {
                Userroom userroom = new Userroom();
                userroom.setIdUser(user);
                userroom.setIdRoom(room);
                userroom.setIdRole(participantRole); // Передаем объект Role
                userroomRepository.save(userroom);
            }
        }

        return ResponseEntity.ok(true); // Возвращаем успешный ответ
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false); // Ошибка при создании чата
    }
}

@GetMapping("/getAllUsers")
public @ResponseBody
List<Users> getAllUsers() {
    return usersRepository.findAll();
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
public List<Message> allMessageRoom(
        @RequestParam(name = "idRoom") Integer idRoom,
        HttpSession session
) {
    // Получаем текущего пользователя из сессии
    Users currentUser = (Users) session.getAttribute("user");

    if (currentUser == null) {
        throw new RuntimeException("Пользователь не авторизован");
    }

    // Получаем все сообщения для указанного чата
    List<Message> messages = messageRepository.findByIdRoom_IdRoom(idRoom);

    // Добавляем флаг isCurrentUser для каждого сообщения
    for (Message message : messages) {
        // Получаем идентификатор текущего пользователя и отправителя сообщения
        Integer currentUserId = currentUser.getIsUsers(); // Предполагаем, что getIdUser() возвращает Integer
        Integer messageSenderId = message.getIdUser().getIsUsers(); // Получаем идентификатор отправителя сообщения

        // Сравниваем идентификаторы
        boolean isCurrentUser = currentUserId.equals(messageSenderId);
        message.setCurrentUser(isCurrentUser); // Добавляем флаг в сущность Message
    }

    return messages;
}

    //Добавить сообщение 
    @PostMapping("/addMessage")
public @ResponseBody boolean addMessage(
        @RequestParam(name = "idRoom") String idRoom,
        @RequestParam(name = "idUser") String idUser,
        @RequestParam(name = "content") String content,
        @RequestParam(name = "urlFile", required = false, defaultValue = "") String urlFile,
        @RequestParam(name = "isEditing", required = false, defaultValue = "0") int isEditing,
        @RequestParam(name = "isDeleted", required = false, defaultValue = "0") int isDeleted,
        HttpSession session
) {
    // Получаем текущего пользователя из сессии
    Users currentUser = (Users) session.getAttribute("user");

    if (currentUser == null) {
        throw new RuntimeException("Пользователь не авторизован");
    }

    // Создаем объект сообщения
    Message message = new Message();

    try {
        // Устанавливаем комнату и пользователя
        Room room = new Room(Integer.parseInt(idRoom));
        message.setIdRoom(room);

        Users user = new Users(Integer.parseInt(idUser));
        message.setIdUser(user);

        // Устанавливаем содержание сообщения
        message.setContent(content);
        message.setUrlFile(urlFile);
        message.setIsEditing(isEditing == 1); // Преобразуем int в boolean
        message.setIsDeleted(isDeleted == 1); // Преобразуем int в boolean

        // Сохраняем сообщение в репозитории
        messageRepository.save(message);
    } catch (Exception e) {
        e.printStackTrace(); // Выводим стек вызовов в лог
        throw new RuntimeException("Ошибка при добавлении сообщения: " + e.getMessage());
    }

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
    /* @GetMapping("/getRoomsUser")
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
} */
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

    // Метод для добавления пользователя
    @PostMapping("/addUser")
    public ModelAndView addUser(@RequestParam(name = "userName") String userName, HttpSession session) {
        System.out.println("Метод addUser вызван с userName: " + userName);

        // Получаем список всех пользователей из базы данных
        Iterable<Users> allUsers = usersRepository.findAll();

        // Проверяем, существует ли пользователь с таким именем
        boolean userExists = false;
        Users user = null;
        for (Users existingUser : allUsers) {
            if (existingUser.getUserName().equals(userName)) {
                userExists = true;
                user = existingUser; // Сохраняем найденного пользователя
                break; // Прерываем цикл, если нашли пользователя с таким именем
            }
        }

        // Если пользователь с таким именем не найден, добавляем нового пользователя
        if (!userExists) {
            // Генерируем userTelegramId
            String userTelegramId = userName + "id";

            // Создаем и сохраняем пользователя
            user = new Users();
            user.setUserName(userName);
            user.setUserTelegramId(userTelegramId);

            System.out.println("Saving user: " + user);
            usersRepository.save(user);
        } else {
            System.out.println("Пользователь с именем " + userName + " уже существует.");
        }

        // Сохраняем пользователя в сессии
        session.setAttribute("user", user);

        // Перенаправляем на страницу /Loading
        ModelAndView modelAndView = new ModelAndView("redirect:/smartSearchBack/Loading");
        return modelAndView;
    }

    @GetMapping("/ChatsGet")
public ModelAndView chats(HttpSession session) {
    // Получаем пользователя из сессии
    Users user = (Users) session.getAttribute("user");

    if (user == null) {
        // Если пользователь не найден, перенаправляем на страницу входа
        return new ModelAndView("redirect:/smartSearchBack/index");
    }

    // Получаем все чаты пользователя
    List<Userroom> userRooms = userroomRepository.findByIdUser(user);

    // Передаем данные на страницу
    ModelAndView modelAndView = new ModelAndView("Chats");
    modelAndView.addObject("user", user);
    modelAndView.addObject("userRooms", userRooms); // Передаем полные объекты Userroom
    modelAndView.addObject("workRoleId", user.getIdWorkRole().getIdWorkRole()); // Передаем роль пользователя

    return modelAndView;
}

    //ОТКРЫТИЕ ФОРМ

    @GetMapping("/index")
    public ModelAndView home() {
        return new ModelAndView("index"); // Убедитесь, что файл index.html находится в папке templates
    }

    @GetMapping("/Loading")
    public ModelAndView loading(HttpSession session) {
        // Получаем пользователя из сессии
        Users user = (Users) session.getAttribute("user");

        if (user == null) {
            // Если пользователь не найден, перенаправляем на страницу входа
            return new ModelAndView("redirect:/smartSearchBack/index");
        }

        // Перенаправляем на страницу Chats
        return new ModelAndView("redirect:/smartSearchBack/ChatsGet");
    }

    @GetMapping("/AddChats")
    public ModelAndView addChats() {
        return new ModelAndView("AddChat");
    }

    @GetMapping("/AdminCalendarCreat")
    public ModelAndView adminCalendarCreat() {
        return new ModelAndView("Calendar-administrator-creature");
    }

    @GetMapping("/AdminCalendar")
    public ModelAndView adminCalendar() {
        return new ModelAndView("Calendar-administrator");
    }

    @GetMapping("/CustomerCalendar")
    public ModelAndView customerCalendar() {
        return new ModelAndView("Calendar-customer");
    }

    @GetMapping("/workerCalendar")
    public ModelAndView workerCalendar() {
        return new ModelAndView("Calendar-worker");
    }

    @GetMapping("/Calls")
    public ModelAndView calls() {
        return new ModelAndView("Calls");
    }

  @GetMapping("/ChatSoo")
public ModelAndView chatSoo(@RequestParam("roomId") Integer roomId, HttpSession session) {
    // Получаем пользователя из сессии
    Users user = (Users) session.getAttribute("user");

    if (user == null) {
        // Если пользователь не найден, перенаправляем на страницу входа
        return new ModelAndView("redirect:/smartSearchBack/index");
    }

    // Получаем информацию о чате по его ID
    Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new RuntimeException("Чат с ID " + roomId + " не найден"));

    // Получаем сообщения для этого чата
    List<Message> messages = allMessageRoom(roomId, session);

    // Передаем данные на страницу
    ModelAndView modelAndView = new ModelAndView("ChatSoo");
    modelAndView.addObject("user", user);
    modelAndView.addObject("room", room); // Передаем объект Room с названием чата
    modelAndView.addObject("messages", messages); // Передаем список сообщений

    return modelAndView;
}

    @GetMapping("/Chats")
    public ModelAndView chats() {
        return new ModelAndView("Chats");
    }

    @GetMapping("/EditChat")
    public ModelAndView editChat() {
        return new ModelAndView("EditChat");
    }

    @GetMapping("/EditChatAdmin")
    public ModelAndView editChatAdmin() {
        return new ModelAndView("EditChatAdmin");
    }

    @GetMapping("/Logi")
    public ModelAndView logi() {
        return new ModelAndView("Logi");
    }

    @GetMapping("/Profile")
    public ModelAndView profile() {
        return new ModelAndView("Profile");
    }
@GetMapping("/ChatsSoo")
    public ModelAndView chatsSoo() {
        return new ModelAndView("ChatSoo");
    }
    @GetMapping("/AddCalls")
    public ModelAndView addCalls() {
        return new ModelAndView("AddCalls");
    }
    @GetMapping("/Zvonim")
    public ModelAndView zvonim() {
        return new ModelAndView("Zvonim");
    }
}
