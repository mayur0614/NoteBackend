package com.csmayur.NoteBackend.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.csmayur.NoteBackend.model.Note;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepo extends MongoRepository<Note,String>{
    List<Note> findByUserId(String userId);

}
