package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      // Гадость какая... Можно красивее это сделать?
      // Не получается создавать юзера, передав в конструктор new Car()
      //  Хайбернейт ругается на то, что у Car не выставлен id, что естественно,
      //  ведь в таблицу car так не записать. Через DI может как-то решается элегантно?
//      Car car1, car2, car3, car4;
//
//      carService.add(car1 = new Car("AMW", 1));
//      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
//      carService.add(car2 = new Car("BMW", 2));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
//      carService.add(car3 = new Car("CMW", 3));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
//      carService.add(car4 = new Car("DMW", 4));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

      // Переписал гадость выше, добавив в UserServiceImp поле carDao

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("BMW", 2)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("BMW", 3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("BMW", 4)));

//      User testUser = new User("тест", "сеттера машины", "h@zc.xx");
//      userService.add(new User("тест", "сеттера машины", "h@zc.xx"));
//      Car testCar = new Car("added", 0);
//      userService.addCar(testUser, testCar);

      // Todo разобраться с one-to-one и проч...


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }
      System.out.println(userService.getUserByCar("BMW", 3).toString());

      context.close();
   }
}
