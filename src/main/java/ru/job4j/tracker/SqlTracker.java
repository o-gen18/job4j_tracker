package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection conn;

    @Override
    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            conn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        }   catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Item add(Item item) {
        try {
            PreparedStatement st = conn.prepareStatement("insert into items(name) values (?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, item.getName());
            st.executeUpdate();
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(String.valueOf(generatedKeys.getInt(1)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        try {
            PreparedStatement st = conn.prepareStatement(
                    "update items set name = ? where id = ?");
            st.setString(1, item.getName());
            st.setInt(2, Integer.parseInt(id));
            int rs  = st.executeUpdate();
            result = rs == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        int itemId = Integer.parseInt(id);
        boolean result = false;
        try {
            PreparedStatement st = conn.prepareStatement(
                    "delete from items where id = ?");
            st.setInt(1, itemId);
            int rs = st.executeUpdate();
            result = rs == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from items");
            while (rs.next()) {
                Item item = new Item();
                item.setName(rs.getString("name"));
                item.setId(String.valueOf(rs.getInt("id")));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("select * from items where name = ?");
            st.setString(1, key);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setName(rs.getString("name"));
                item.setId(String.valueOf(rs.getInt("id")));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Item findById(String id) {
        Item item = null;
        try {
            PreparedStatement st = conn.prepareStatement("select * from items where id = ?");
            st.setInt(1, Integer.parseInt(id));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                item = new Item();
                item.setName(rs.getString("name"));
                item.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }
}
