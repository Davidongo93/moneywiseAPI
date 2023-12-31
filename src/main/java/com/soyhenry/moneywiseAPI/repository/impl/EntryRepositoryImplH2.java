package com.soyhenry.moneywiseAPI.repository.impl;

import com.soyhenry.moneywiseAPI.model.Entry;
import com.soyhenry.moneywiseAPI.repository.EntryRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class EntryRepositoryImplH2 implements EntryRepository {

    // SQL sentences:
    private static final java.lang.String INSERT_ENTRY = "INSERT INTO entries (user_id, date, description, amount, type, category) VALUES (?, ?, ?, ?, ?, ?)";
    private static final java.lang.String GET_USER_ENTRIES = "SELECT * FROM entries WHERE user_id = ?";
    private static final java.lang.String UPDATE_ENTRY = "UPDATE entries SET user_id=?, date=?, description=?, amount=?, type=?, category=? WHERE id=?";
    private static final java.lang.String DELETE_ENTRY = "DELETE FROM entries WHERE user_id = ? AND id = ?";

    // Connection managed by JdbcTemplate.
    private final JdbcTemplate jdbcTemplate;

    public EntryRepositoryImplH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insert(Entry entry) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ENTRY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entry.getUserId());
            ps.setString(2, entry.getDate());
            ps.setString(3, entry.getDescription());
            ps.setBigDecimal(4, BigDecimal.valueOf(entry.getAmount()));
            ps.setString(5, entry.getType());
            ps.setString(6, entry.getCategory());
            return ps;
        }, keyHolder);

        if (rowsAffected > 0) {
            Integer entryId = Objects.requireNonNull(keyHolder.getKey()).intValue();
            entry.setId(entryId);
        }

        return entry.getId();
    }

    @Override
    public List<Entry> getUserEntries(int userId) {
        return jdbcTemplate.query(GET_USER_ENTRIES, new EntryRowMapper(), userId);
    }

    @Override
    public Integer updateEntry(int entryId, Entry entry) {
        return jdbcTemplate.update(UPDATE_ENTRY,
                entry.getUserId(),
                entry.getDate(),
                entry.getDescription(),
                entry.getAmount(),
                entry.getType(),
                entry.getCategory(),
                entryId);
    }

    @Override
    public void deleteEntry(int userId, int entryId) {
        System.out.println("ID from deleted entry: " + entryId);
        try {
            jdbcTemplate.update(DELETE_ENTRY, userId, entryId);
        } catch (DataAccessException exception) {
            System.out.println(("Error deleting entry with ID " + entryId));
        }
        System.out.println("Entry has been deleted");
    }

    private static class EntryRowMapper implements RowMapper<Entry> {
        @Override
        public Entry mapRow(ResultSet resultSet, int rowNum) throws SQLException {


            Entry entry = new Entry();
            entry.setId(resultSet.getInt("id"));
            entry.setUserId(resultSet.getInt("user_id"));
            entry.setDate(java.lang.String.valueOf(resultSet.getDate("date")));

            entry.setDescription(resultSet.getString("description"));
            entry.setAmount(resultSet.getBigDecimal("amount").doubleValue());
            java.lang.String typeString = resultSet.getString("type");
            String string = String.valueOf(typeString.toUpperCase());
            entry.setType(string);
            entry.setCategory(resultSet.getString("category"));
            return entry;
        }
    }
}
