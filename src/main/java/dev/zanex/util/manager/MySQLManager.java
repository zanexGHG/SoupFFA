package dev.zanex.util.manager;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLManager {

    public Connection connect(String host, String port, String database, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            Bukkit.getLogger().info("§a§lConnection successful!");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            Bukkit.getLogger().severe("{message.mysql.connection.failed}" + e.getMessage());
            return null;
        }
    }

    public void createDefaultTables(Connection connection) {
        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `players` (`uuid` VARCHAR(36) PRIMARY KEY, `name` VARCHAR(16), `kills` INT, `deaths` INT)");
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `playerKitInventories` (`uuid` VARCHAR(36) PRIMARY KEY, `customKitInventory` TEXT)");
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS `kitInventories` (`kitName` VARCHAR(16) PRIMARY KEY, `defaultKitInventory` TEXT, `enabled` INT(1))");
            Bukkit.getLogger().info("{message.mysql.table.created}");
        } catch (SQLException e) {
            Bukkit.getLogger().severe("{message.mysql.table.create.failed}" + e.getMessage());
        }
    }

    public void executeUpdateStatement(Connection connection, String statement) {
        try {
            connection.createStatement().executeUpdate(statement);
            Bukkit.getLogger().info("{message.mysql.statement.created}");
        } catch (SQLException e) {
            Bukkit.getLogger().severe("{message.mysql.statement.create.failed}" + e.getMessage());
        }
    }
}
