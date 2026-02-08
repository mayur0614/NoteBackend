package com.csmayur.NoteBackend.controller;

import com.csmayur.NoteBackend.model.Note;
import com.csmayur.NoteBackend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/user/{userId}")
    public List<Note> getNotes(@PathVariable String userId) {
        return noteService.getNotesByUser(userId);
    }

    @PostMapping("/user/{userId}")
    public Note addNote(
            @PathVariable String userId,
            @RequestBody Note note) {

        return noteService.addNote(note, userId);
    }

    @DeleteMapping("/user/{userId}/note/{noteId}")
    public String deleteNote(
            @PathVariable String userId,
            @PathVariable String noteId) {

        noteService.deleteNote(noteId, userId);
        return "deleted";
    }

    @PutMapping("/user/{userId}/note/{noteId}")
    public Note updateNote(
            @PathVariable String userId,
            @PathVariable String noteId,
            @RequestBody Note note) {

        return noteService.updateNote(noteId, userId, note);
    }


    @GetMapping("/{id}")
    public Optional<Note> findById(@PathVariable String id) {
        return noteService.findById(id);
    }
}

