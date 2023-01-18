package com.elearning.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
//@PreAuthorize("hasRole('ROLE_USER')")
public class ProfileController {
	
	@Autowired
    UserRepository userRepository ;
	@Autowired
	EnrollmentService enrollmentservice;
	@Autowired
    UserService userService;

    @GetMapping("/profile")
    public String getUserProfile(Authentication authentication, Model model) {
        try {
            String currentUsername = authentication.getName();
            User user = userRepository.findByUsername(currentUsername);
            List<Enrollment>enrollments = enrollmentservice.findAllByUser(user);
            int numCourse = enrollments.size();
            model.addAttribute("user", user);
            model.addAttribute("enrollments", enrollments);
            model.addAttribute("numCourse", numCourse);
            return "user/profile";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/user/edit/{userID}")
    public String getForEdit(@PathVariable Long userID, Authentication authentication, Model model) {

        try {
            String currentusername = authentication.getName();
            User current = userRepository.findById(userID).get();
            if (currentusername.equals(current.getUsername())) {
            model.addAttribute(current);
            return "user/user-edit";
            } else {
                throw new Exception("Error de autenticacion");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/user/edit/{userID}")
    public String updateUser(@PathVariable Long userID, Authentication authentication, User user, Model model) {

        try {
            User current = userRepository.findById(userID).get();
            String currentusername = authentication.getName();
            if (currentusername.equals(current.getUsername())) {
                current.setFirstname(user.getFirstname());
                current.setLastname(user.getLastname());
                current.setEmail(user.getEmail());
                current.setImgurl(user.getImgurl());
                userService.update(current);

                return "redirect:/profile";
            } else {
                throw new Exception("Error de autenticacion");
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/user/updateDetail/{id_user}")
    public String updateDetailUser(@PathVariable Long id_user, Authentication authentication, User user, Model model) {

        try {
            User current = userRepository.findById(id_user).get();
            String currentusername = authentication.getName();
            if (currentusername.equals(current.getUsername())) {
                current.setDetail(user.getDetail());
                userService.updateDetail(current);

                return "redirect:/profile";
            } else {
                throw new Exception("Error de autenticacion");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

}

