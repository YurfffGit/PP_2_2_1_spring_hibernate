package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      User u1 = new User("Usertest1", "Usertest1", "Usertest1@gmail.com");
      Car c1 = new Car("Testmodel1", 1);
      u1.setUserCar(c1);

      User u2 = new User("Usertest2", "Usertest1", "Usertest1@mail.ru");
      Car c2 = new Car("Testmodel2", 2);
      u2.setUserCar(c2);

      User u3 = new User("Usertest3", "Usertest3", "Usertest3@rambler.ru");
      Car c3 = new Car("Testmodel3", 3);
      u3.setUserCar(c3);

      userService.add(u1);
      userService.add(u2);
      userService.add(u3);



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getUserCar());
         System.out.println();
      }

      context.close();
   }
}
