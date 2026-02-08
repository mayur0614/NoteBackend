    package com.csmayur.NoteBackend.service;

    import com.csmayur.NoteBackend.model.User;
    import com.csmayur.NoteBackend.model.UserLogin;
    import com.csmayur.NoteBackend.repository.UserRepo;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.Optional;

    @Service
    public class UserService {


        private final UserRepo userRepo;
        private final PasswordEncoder passwordEncoder;

        public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
            this.userRepo = userRepo;
            this.passwordEncoder = passwordEncoder;
        }
        public String registeruser(User user) {
            if(userRepo.existsByEmail(user.getEmail())){
                return "Already an user";
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return "user registered";
        }

        public String login(UserLogin userLogin) {
            Optional<User> optionalUser = userRepo.findByEmail(userLogin.getEmail());

            if (optionalUser.isEmpty()) {
                return "user not exist please register";
            }
            User user = optionalUser.get();
            if (!passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
                return "password not matched";
            }
            return user.getId();
        }
    }
