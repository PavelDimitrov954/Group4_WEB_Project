package com.example.group4_web_project.controllers.mvc;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeMvcController {
   // @ModelAttribute("isAuthenticated")
  //  public boolean populateIsAuthenticated(HttpSession session) {

   //     return session.getAttribute("currentUser") != null;
   // }

    @GetMapping()
    public String showHomePage() {
        return "Index";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "Contact";
    }

}