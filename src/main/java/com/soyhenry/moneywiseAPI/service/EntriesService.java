package com.soyhenry.moneywiseAPI.service;

import com.soyhenry.moneywiseAPI.repository.dto.response.EntryResponseDto;
import com.soyhenry.moneywiseAPI.repository.dto.request.EntryRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EntriesService {

    String createEntry(int userId, EntryRequestDto entryRequestDto);

    List<EntryResponseDto> getUserEntries(int userId);

    String updateEntry(int userId, int entryId, EntryRequestDto entryRequestDto);

    void deleteEntry(int userId, int entryId);
}
