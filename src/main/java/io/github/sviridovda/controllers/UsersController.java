package io.github.sviridovda.controllers;

import io.github.sviridovda.models.User;
import io.github.sviridovda.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users/users_table";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        System.out.println(userService.getUserById(id).getName());
        return "users/user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user){
        return "users/new_user";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String createUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit_user";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id){
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
