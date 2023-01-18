package com.elearning.User;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
    UserRepository userRepository;

    public void createUser(User user) throws IllegalStateException {

        if (null != userRepository.findByUsername(user.getUsername())) {
            throw new IllegalStateException("There is already a user with the name " + user.getUsername());
        } else if (null != userRepository.findByEmail(user.getEmail())) {
            throw new IllegalStateException("There is already a user with the email " + user.getEmail());
        }
        String username = user.getUsername();
        String password = new BCryptPasswordEncoder(11).encode(user.getPassword());
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String email = user.getEmail();
        String imgurl = user.getImgurl();
        LocalDate date = LocalDate.now();
        User newuser = new User(username, password, firstname, lastname, email, imgurl, date,Role.USER);
        userRepository.save(newuser);
    }

    public void update(User user) {
        User current = userRepository.findByUsername(user.getUsername());

        current.setFirstname(user.getFirstname());
        current.setLastname(user.getLastname());
        current.setEmail(user.getEmail());
        current.setImgurl(user.getImgurl());

        userRepository.save(current);
    }
    
    /**/
    public void updateDetail(User user) {
        User current = userRepository.findByUsername(user.getUsername());

        current.setDetail(user.getDetail());

        userRepository.save(current);
    }

}