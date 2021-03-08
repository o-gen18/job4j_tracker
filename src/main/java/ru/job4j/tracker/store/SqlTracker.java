package ru.job4j.tracker.store;

import ru.job4j.tracker.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlTracker implements Store {

    private final Connection conn;

    public SqlTracker(Connection conn) {
        this.conn = conn;
    }

    @Override
    public String getName() {
        return "PostgreSQL database \"tracker\"";
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
                    item.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(Integer id, Item item) {
        boolean result = false;
        try {
            PreparedStatement st = conn.prepareStatement(
                    "update items set name = ? where id = ?");
            st.setString(1, item.getName());
            st.setInt(2, id);
            int rs  = st.executeUpdate();
            result = rs == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        boolean result = false;
        try {
            PreparedStatement st = conn.prepareStatement(
                    "delete from items where id = ?");
            st.setInt(1, id);
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
                item.setId(rs.getInt("id"));
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
                item.setId(rs.getInt("id"));
                list.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Item findById(Integer id) {
        Item item = null;
        try {
            PreparedStatement st = conn.prepareStatement("select * from items where id = ?");
            st.setInt(1, id);
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
}
