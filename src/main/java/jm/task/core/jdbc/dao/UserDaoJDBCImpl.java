package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sql = """
                    CREATE TABLE IF NOT EXISTS Users (
                    id bigint unsigned NOT NULL AUTO_INCREMENT,
                    name varchar(45) NOT NULL,
                    lastname varchar(45) NOT NULL,
                    age tinyint unsigned DEFAULT NULL,
                    PRIMARY KEY (id)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
                """;

        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS Users";

        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO Users (NAME, LASTNAME, AGE) VALUES (?,?,?)";

        try (PreparedStatement prep = getConnection().prepareStatement(sql)) {
            prep.setString(1, name);
            prep.setString(2, lastName);
            prep.setByte(3, age);
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM Users WHERE ID = ?";

        try (PreparedStatement prep = getConnection().prepareStatement(sql)) {
            prep.setLong(1, id);
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (PreparedStatement prep = getConnection().prepareStatement(sql)) {
            ResultSet result = prep.executeQuery();

            while (result.next()) {
                User user = new User();
                user.setId(result.getLong(1));
                user.setName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setAge(result.getByte(4));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE Users";

        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
