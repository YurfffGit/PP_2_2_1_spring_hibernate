package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
//      Query q1 = sessionFactory.getCurrentSession().createQuery("FROM User as u INNER JOIN u.userCar as uc WITH uc.model = ?1 and uc.series = ?2");
      TypedQuery<User> q1 = sessionFactory.getCurrentSession().createQuery("FROM User WHERE userCar.model = :model and userCar.series = :series");
      q1.setParameter("model", model);
      q1.setParameter("series", series);
//      User userByCar = q1.getResultList().get(0);
      return q1.setMaxResults(1).getSingleResult();
   }

}
