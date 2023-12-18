package com.soyhenry.moneywiseAPI.service.impl;
import com.soyhenry.moneywiseAPI.repository.dto.response.EntryResponseDto;
import com.soyhenry.moneywiseAPI.model.Entry;
import com.soyhenry.moneywiseAPI.repository.EntryRepository;
import com.soyhenry.moneywiseAPI.repository.dto.request.EntryRequestDto;
import com.soyhenry.moneywiseAPI.service.EntriesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntriesServiceImpl implements EntriesService {

    private final EntryRepository entryRepository;

    public EntriesServiceImpl(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public String createEntry(int userId, EntryRequestDto entryRequestDto) {
        String response = "Entry created successfully";

        Entry entry = mapDtoToEntry(userId, entryRequestDto);
        Integer responseInserted = entryRepository.insert(entry);
        if (responseInserted.equals(0)) {
            System.out.println("Entry wasn't created");
        }
        return response;
    }

    public List<EntryResponseDto> getUserEntries(int userId) {
        List<Entry> entries = entryRepository.getUserEntries(userId);
        return entries.stream()
                .map(this::mapEntryToResponseDto)
                .collect(Collectors.toList());
    }

    public String updateEntry(int userId, int entryId, EntryRequestDto entryRequestDto) {
        String response = "Entry updated successfully";
        Entry entry = mapDtoToEntry(userId, entryRequestDto);
        Integer responsesUpdated = entryRepository.updateEntry(entryId, entry);
        return response;
    }

    public void deleteEntry(int userId, int entryId) {
        String response = "Entry has been deleted";
        entryRepository.deleteEntry(userId, entryId);
    }

    private Entry mapDtoToEntry(int userId, EntryRequestDto entryRequestDto) {
        Entry entry = new Entry();
        entry.setUserId(userId);
        entry.setDate(entryRequestDto.getDate());
        entry.setDescription(entryRequestDto.getDescription());
        entry.setAmount(entryRequestDto.getAmount());
        entry.setType(entryRequestDto.getType());
        entry.setCategory(entryRequestDto.getCategory());
        return entry;
    }

    private EntryResponseDto mapEntryToResponseDto(Entry entry) {
        EntryResponseDto entryResponseDto = new EntryResponseDto();
        entryResponseDto.setDate(entry.getDate());
        entryResponseDto.setDescription(entry.getDescription());
        entryResponseDto.setAmount(entry.getAmount());
        entryResponseDto.setType(entry.getType());
        entryResponseDto.setCategory(entry.getCategory());
        return entryResponseDto;
    }
}
