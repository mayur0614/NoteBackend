package com.csmayur.NoteBackend.service;

import com.csmayur.NoteBackend.model.Note;
import com.csmayur.NoteBackend.repository.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepo noteRepo;

    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    public List<Note> getNotesByUser(String userId) {
        return noteRepo.findByUserId(userId);
    }

    public Note addNote(Note note, String userId) {
        note.setUserId(userId);
        return noteRepo.save(note);
    }

    // ✅ Delete note by noteId
    public void deleteNote(String noteId, String userId) {

        Note note = noteRepo.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUserId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete this note");
        }

        noteRepo.deleteById(noteId);
    }


    public Note updateNote(String noteId, String userId, Note updatedNote) {

        Note existingNote = noteRepo.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        // 🔐 Ownership validation
        if (!existingNote.getUserId().equals(userId)) {
            throw new RuntimeException("You are not allowed to update this note");
        }

        // ✅ Update only allowed fields
        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());

        return noteRepo.save(existingNote);
    }

    // ✅ Find note by noteId
    public Optional<Note> findById(String id) {
        return noteRepo.findById(id);
    }
}

