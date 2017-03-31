package com.Squallz.Orbs;

import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MainClass extends JavaPlugin {

    private static MainClass instance;
    private HikariDataSource hikari;

    PlayerHandler ph = new PlayerHandler(this);
    Orbs orbs = new Orbs(this);
    Messages msgs = new Messages(this);

    public void onEnable(){
        saveDefaultConfig();

        instance = this;

        String address = getConfig().getString("database" + ".address");
        String databaseName = getConfig().getString("database" + ".name");
        String username = getConfig().getString("database" + ".username");
        String password = getConfig().getString("database" + ".password");
        String port = getConfig().getString("database" + ".port");

        if(address == null || databaseName == null || username == null || password == null || port == null){
            getLogger().info("Orbs disabled. Please set Database information!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        hikari = new HikariDataSource();
        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", address);
        hikari.addDataSourceProperty("databaseName", databaseName);
        hikari.addDataSourceProperty("user", username);
        hikari.addDataSourceProperty("password", password);
        hikari.addDataSourceProperty("port", port);

        createTable();

        Bukkit.getPluginManager().registerEvents(ph, this);

        for(Player allPlayers : Bukkit.getOnlinePlayers()){
            orbs.loadPlayer(allPlayers);
        }

        this.getCommand("orbs").setExecutor(new Commands(this));
    }

    public void createTable(){
        try{
            Statement statement = hikari.getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Orbs(UUID varchar(36) UNIQUE, name varchar(36), orbs int)");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void onDisable(){
        for(Player allPlayers : Bukkit.getOnlinePlayers()){
            try (Connection connection = getHikari().getConnection();
                 PreparedStatement statement = connection.prepareStatement(orbs.SAVE)){
                statement.setInt(1, orbs.getOrbs(allPlayers));
                statement.setString(2, allPlayers.getUniqueId().toString());
                statement.execute();
                orbs.removePlayer(allPlayers);
            }catch(SQLException e){
                getLogger().info("ERROR: Could not connect to database!");
            }
        }
        try{
            if(getHikari().getConnection() != null){
                getHikari().close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public MainClass getInstance(){
        return instance;
    }

    public HikariDataSource getHikari(){
        return hikari;
    }

}
