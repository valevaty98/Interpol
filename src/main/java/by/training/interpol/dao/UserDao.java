package by.training.interpol.dao;

import by.training.interpol.entity.Role;
import by.training.interpol.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByLogin(String login) throws DaoException;

    boolean updateUserEmail(User user, String email) throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    List<User> selectUsersByRole(Role role) throws DaoException;
}
