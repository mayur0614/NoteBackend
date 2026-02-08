package com.csmayur.NoteBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "noteUser")
public class User {
    @Id
    private String id;
    private String username;
    private  String email;
    private  String password ;

}
