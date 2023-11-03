package com.example.group4_web_project.controllers.mvc;

import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.services.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeMvcController1 {

    @Autowired
    private PostService postService;

    @GetMapping
    public String homePage(Model model) {
        // Fetch top commented posts
        FilterOptions topCommentedFilter = new FilterOptions(null, null, "commentCount", "desc");
        List<com.example.group4_web_project.models.Post> topCommentedPosts = postService.get(topCommentedFilter).stream().limit(10).collect(Collectors.toList());

        // Fetch recent posts
        FilterOptions recentFilter = new FilterOptions(null, null, "creationDate", "desc");
        List<com.example.group4_web_project.models.Post> recentPosts = postService.get(recentFilter).stream().limit(10).collect(Collectors.toList());

        model.addAttribute("topCommentedPosts", topCommentedPosts);
        model.addAttribute("recentPosts", recentPosts);
        model.addAttribute("postCount", postService.getPostCount());

        return "Index"; // This should match the name of your Index.html
    }
}
