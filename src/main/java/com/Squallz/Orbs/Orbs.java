package com.Squallz.Orbs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

public class Orbs {

    private Map<UUID, Integer> orbs;
    MainClass pl;

    String INSERT = "INSERT INTO Orbs VALUES(?,?,?) ON DUPLICATE KEY UPDATE name = ?";
    String SELECT = "SELECT orbs FROM Orbs WHERE UUID = ?";
    String SAVE = "UPDATE Orbs SET orbs = ? WHERE uuid = ?";

    public Orbs(MainClass pl) {
        this.orbs = new HashMap<UUID, Integer>();
        this.pl = pl;
    }

    public String commaFormat(double amount){
        DecimalFormat formatter = new DecimalFormat("#,###");
        String number = formatter.format(amount);
        return number;
    }

    public void addPlayer(Player player, int amount){
        orbs.put(player.getUniqueId(), amount);
    }

    public void removePlayer(Player player){
        orbs.remove(player.getUniqueId());
    }

    public int getOrbs(Player player){
        return orbs.get(player.getUniqueId());
    }

    public void setOrbs(Player player, int amount){
        orbs.put(player.getUniqueId(), amount);
    }

    public void addOrbs(Player player, int amount){
        orbs.put(player.getUniqueId(), getOrbs(player) + amount);
    }

    public void removeOrbs(Player player, int amount){
        orbs.put(player.getUniqueId(), getOrbs(player) - amount);
    }

    public static String toBase64(Inventory inventory)
    {
        try
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeInt(inventory.getSize());
            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    public static Inventory fromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());

            // Read the serialized inventory
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }

            dataInput.close();
            return inventory;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }

    public void loadPlayer(final Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(pl.getInstance(), new Runnable() {
            @Override
            public void run() {
                try {
                    Connection connection = pl.getHikari().getConnection();
                    PreparedStatement insert = connection.prepareStatement(INSERT);
                    PreparedStatement select = connection.prepareStatement(SELECT);
                    insert.setString(1, player.getUniqueId().toString());
                    insert.setString(2, player.getName());
                    insert.setInt(3, 0);
                    insert.setString(4, player.getName());
                    insert.execute();

                    select.setString(1, player.getUniqueId().toString());
                    ResultSet result = select.executeQuery();
                    if (result.next()) {
                        addPlayer(player, result.getInt("orbs"));
                        result.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void savePlayer(final Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(pl.getInstance(), new Runnable() {
            @Override
            public void run() {
                try (Connection connection = pl.getInstance().getHikari().getConnection();
                     PreparedStatement statement = connection.prepareStatement(SAVE)){
                    statement.setInt(1, getOrbs(player));
                    statement.setString(2, player.getUniqueId().toString());
                    statement.execute();
                    removePlayer(player);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
