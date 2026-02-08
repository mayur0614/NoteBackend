package com.csmayur.NoteBackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserLogin {
    private  String email;
    private  String password ;
}