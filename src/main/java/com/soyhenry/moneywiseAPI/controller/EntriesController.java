package com.soyhenry.moneywiseAPI.controller;

import com.soyhenry.moneywiseAPI.repository.dto.request.EntryRequestDto;
import com.soyhenry.moneywiseAPI.repository.dto.response.EntryResponseDto;
import com.soyhenry.moneywiseAPI.service.EntriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user/{userId}/entries")
public class EntriesController {
private final EntriesService entriesService;

    public EntriesController(EntriesService entriesService) {
        this.entriesService = entriesService;
    }


    @PostMapping
    public ResponseEntity<String> createEntryHandler(@PathVariable int userId, @RequestBody EntryRequestDto entryRequestDto) {
        //create EntriesService

String response = entriesService.createEntry(userId, entryRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EntryResponseDto>> getUserEntries(@PathVariable int userId) {

        List<EntryResponseDto> entries = entriesService.getUserEntries(userId);

        return ResponseEntity.status(HttpStatus.OK).body(entries);
    }

    @PutMapping("/{entryId}")
    public ResponseEntity<String> updateEntryById(@PathVariable int userId, @PathVariable int entryId, @RequestBody EntryRequestDto entryRequestDto) {
String response = entriesService.updateEntry(userId, entryId, entryRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/{entryId}")
    public ResponseEntity<String> deleteEntry(@PathVariable int userId, @PathVariable int entryId) {
        entriesService.deleteEntry(userId, entryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Entry deleted successfully");
    }
}
