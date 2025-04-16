package org.esteban.springboot.springmvc.app.controllers;

import org.esteban.springboot.springmvc.app.models.User;
import org.esteban.springboot.springmvc.app.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/view")
    public String view(Model model) {
        model.addAttribute("title","Hola mundo");
        model.addAttribute("message","Hola mundo desde Spring Boot");
        model.addAttribute("user", new User("Esteban","Salazar"));
        return "view";
    }
    @GetMapping
    public String list(Model model) {
        model.addAttribute("title","Listado de usuarios");
        model.addAttribute("users",userService.findAll());
        return "list";
    }
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("Title","Crear usuario");
        model.addAttribute("user",new User());
        return "form";
    }
    @GetMapping("/form/{id}")
    public String form(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<User> optionalUser = userService.findById(id);
        if(optionalUser.isPresent()){
            redirect.addFlashAttribute("success","El usuario se ha encontrado en el sistema");
            model.addAttribute("user",optionalUser.get());
            model.addAttribute("title","Editar usuario");
            return "form";
        } else{
            redirect.addFlashAttribute("error","El usuario no existe");
            return "redirect: /users";
        }

    }
    @PostMapping
    public String form(  User user, Model model, RedirectAttributes redirect) {
        String message = (user.getId() != null && user.getId() > 0)
                ? "El usuario se ha actualizado con exito"
                : "El usuario se ha creado con exito";

        userService.save(user);
        redirect.addFlashAttribute("success",message);
        return "redirect:/users";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirect) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            redirect.addFlashAttribute("message","El usuario" + optionalUser.get().getName() + "se ha eliminado con exito");
            userService.delete(id);
            return "redirect:/users";
        }
         return "redirect:/users";
    }
}
