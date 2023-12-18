package com.soyhenry.moneywiseAPI.repository;

import com.soyhenry.moneywiseAPI.model.Entry;

import java.util.List;

public interface EntryRepository {

    Integer insert(Entry entry);

    List<Entry> getUserEntries(int userId);

    Integer updateEntry(int entryId, Entry entry);

    void deleteEntry(int userId, int entryId);
}
