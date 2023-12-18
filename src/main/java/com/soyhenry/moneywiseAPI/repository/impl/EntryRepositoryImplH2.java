package com.soyhenry.moneywiseAPI.repository.impl;

import com.soyhenry.moneywiseAPI.model.Entry;
import com.soyhenry.moneywiseAPI.model.EntryType;
import com.soyhenry.moneywiseAPI.repository.EntryRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EntryRepositoryImplH2 implements EntryRepository {

    // SQL sentences:
    private static final String INSERT_ENTRY = "INSERT INTO entries (user_id, date, description, amount, type, category) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_USER_ENTRIES = "SELECT * FROM entries WHERE user_id = ?";
    private static final String UPDATE_ENTRY = "UPDATE entries SET user_id=?, date=?, description=?, amount=?, type=?, category=? WHERE id=?";
    private static final String DELETE_ENTRY = "DELETE FROM entries WHERE user_id = ? AND id = ?";

    // Connection managed by JdbcTemplate.
    private final JdbcTemplate jdbcTemplate;

    public EntryRepositoryImplH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insert(Entry entry) {
        return jdbcTemplate.update(INSERT_ENTRY,
                entry.getUserId(),
                entry.getDate(),
                entry.getDescription(),
                entry.getAmount(),
                entry.getType(),
                entry.getCategory());
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

            LocalDate localDate = resultSet.getDate("date").toLocalDate();
            Date date = Date.valueOf(localDate);
            entry.setDate(date);

            entry.setDescription(resultSet.getString("description"));
            entry.setAmount(resultSet.getBigDecimal("amount").doubleValue());
            String typeString = resultSet.getString("type");
            EntryType entryType = EntryType.valueOf(typeString.toUpperCase());
            entry.setType(entryType);
            entry.setCategory(resultSet.getString("category"));
            return entry;
        }
    }
}
