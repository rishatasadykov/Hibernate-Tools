import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import entity.Orders;
import entity.Users;
import utils.HibernateUtil;

public class App 
{
    public static void main( String[] args )
    {
    	try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			System.out.println("All users");
			Criteria criteria = session.createCriteria(Users.class);
			showUserList(criteria);
			System.out.println("Age:\n>20");
			criteria = session.createCriteria(Users.class).add(Restrictions.gt("age", 20));
			showUserList(criteria);
			System.out.println("<20");
			criteria = session.createCriteria(Users.class).add(Restrictions.lt("age", 20));
			showUserList(criteria);
			System.out.println(">=20");
			criteria = session.createCriteria(Users.class).add(Restrictions.ge("age", 20));
			showUserList(criteria);
			System.out.println("<=20");
			criteria = session.createCriteria(Users.class).add(Restrictions.le("age", 20));
			showUserList(criteria);
			System.out.println("18<age<23");
			criteria = session.createCriteria(Users.class).add(Restrictions.between("age", 18, 23));
			showUserList(criteria);
			System.out.println("Начинается с D");
			criteria = session.createCriteria(Users.class).add(Restrictions.like("firstname", "D%"));
			showUserList(criteria);
			System.out.println("В имени есть i");
			criteria = session.createCriteria(Users.class).add(Restrictions.like("firstname", "%i%"));
			showUserList(criteria);
			System.out.println("В имени есть i или e");
			criteria = session.createCriteria(Users.class).add(Restrictions.or(Restrictions.like("firstname", "%i%"),
			Restrictions.like("firstname", "%e%")));
			showUserList(criteria);
			System.out.println("Возраст от 15 до 25 и в имени есть или ha или mi или re");
			criteria = session.createCriteria(Users.class).add(Restrictions.and(Restrictions.ge("age", 15),
				Restrictions.le("age", 25))).add(Restrictions.or(Restrictions.like("firstname", "%ha%"), 
						Restrictions.like("firstname", "%mi%"), Restrictions.like("firstname", "%re%")));
			showUserList(criteria);
			System.out.println("Имя из списка {Roman, Damir, Rishat}");
			criteria = session.createCriteria(Users.class).add(Property.forName("firstname").in( new String[] { "Roman", "Damir", "Rishat" } ) );
			showUserList(criteria);
			System.out.println("По возрастанию age");
			criteria = session.createCriteria(Users.class).addOrder(Order.asc("age"));
			showUserList(criteria);
			System.out.println("По убыванию age");
			criteria = session.createCriteria(Users.class).addOrder(Order.desc("age"));
			showUserList(criteria);
			System.out.println("Первые семь у которых возраст больше 20");
			criteria = session.createCriteria(Users.class).add(Restrictions.gt("age", 20)).setMaxResults(7);
			showUserList(criteria);
			System.out.println("Из них с 3 по 6");
			criteria.setFirstResult(2).setMaxResults(4);
			showUserList(criteria);
			criteria = session.createCriteria(Orders.class);
			List<Orders> orders = criteria.list();
			for (Orders o: orders)
				System.out.println("Заказы id=" + o.getUserId()+" price="+o.getPrice());
			System.out.println("Все пользователи у которых сумма заказов больше 100");
			Query query = session.createQuery("select new Users(u.firstname, u.lastname, u.age) from Users u inner join Orders o"
					+ " on u.userId=o.userId group by u.userId having sum(o.price)>100");
			List<Users> allUsers = query.list();
	    	for (Users user : allUsers) {
				System.out.println("User: " + user.getFirstname() + "|" + user.getLastname() + "|age=" + user.getAge());
		 	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		HibernateUtil.shutdown();
    	}
    }
    public static void showUserList(Criteria criteria){
    	List<Users> allUsers = criteria.list();
    	for (Users user : allUsers) {
			System.out.println("User: " + user.getFirstname() + "|" + user.getLastname() + "|age=" + user.getAge() + "|id="+user.getUserId());
	 	}
    }
}