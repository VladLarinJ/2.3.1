package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsersList(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "/userList";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "/addUser";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String editUser(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", userService.getUserById(id));
        return "/updateUser";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}