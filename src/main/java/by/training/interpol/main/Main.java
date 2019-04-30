package by.training.interpol.main;

import by.training.interpol.dao.BaseDao;
import by.training.interpol.dao.DaoException;
import by.training.interpol.dao.UserDao;
import by.training.interpol.dao.impl.UserDaoImpl;
import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        System.out.println(Role.ADMIN);
//        UserDao userDao = new UserDaoImpl();
//        Optional<User> user = Optional.empty();
//        try {
//            user = userDao.findUserByLogin("vlad");
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }

 //       System.out.println(user.get());
//        BaseDAOImpl<User> dao = new UserDaoImpl();
//        System.out.println("List: " + dao.findAll());
//        System.out.println("Delete 2: " + dao.deleteById(2));
//        System.out.println("List: " + dao.findAll());
//        User user = new User(3, "java", "jdbc", "dao@mail.ru", Role.GUEST);
//        System.out.println("Update 3: " + dao.update(user));
//        System.out.println("List: " + dao.findAll());
//        System.out.println("Insert 2: " + dao.insert(user));
//        System.out.println("List: " + dao.findAll());
//        try {
//            Context envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
//            DataSource ds = (DataSource) envCtx.lookup("jdbc/interpol");
//            Connection cn = ds.getConnection();
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM users");
//
//            while (rs.next()) {
//                User user = new User(rs.getLong(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        Role.values()[rs.getInt(5) - 1]
//                );
//
//                System.out.println(user);
//            }
//        } catch (NamingException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try (Connection cn = ConnectorDB.getConnection();
//             Statement st = cn.createStatement()
//        ) {
//            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//
//            ResultSet rs = st.executeQuery("SELECT * FROM users");
//
//            while (rs.next()) {
//                User user = new User(rs.getLong(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        Role.values()[rs.getInt(5) - 1]
//                        );
//
//                System.out.println(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}