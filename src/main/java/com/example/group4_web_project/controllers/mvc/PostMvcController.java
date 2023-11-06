package com.example.group4_web_project.controllers.mvc;


import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;

import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.helpers.AuthenticationHelper;
import com.example.group4_web_project.helpers.CommentMapper;
import com.example.group4_web_project.helpers.PostMapper;
import com.example.group4_web_project.models.*;

import com.example.group4_web_project.services.CommentService;
import com.example.group4_web_project.services.PostService;
import com.example.group4_web_project.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostMvcController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    private final PostMapper postMapper;
    private final  CommentMapper commentMapper;
    private final AuthenticationHelper authenticationHelper;


    public PostMvcController(PostService postService, UserService userService, CommentService commentService, PostMapper postMapper, CommentMapper commentMapper, AuthenticationHelper authenticationHelper) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;

        this.postMapper = postMapper;
        this.commentMapper = commentMapper;

        this.authenticationHelper = authenticationHelper;
    }


    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("comments")
    public List<Comment> populateStyles() {
        return null;
    }



    @GetMapping("/new")
    public String showNewPost(Model model) {
        model.addAttribute("post", new PostDto());
        return "NewPostView";
    }
    @PostMapping("/new")
    public String handleNewPost(@Valid @ModelAttribute("post") PostDto post,
                                BindingResult bindingResult,
                                HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "NewPostView";
        }

        try {
            Post p = postMapper.fromDto(post);
            String username = (String) session.getAttribute("currentUser");
            User user = userService.get(username);
            postService.create(p, user);

            return "redirect:/";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("title", "title_error", e.getMessage());
            return "NewPostView";
        }
    }

    @GetMapping
    public String showAllPost(@ModelAttribute("filterOptions") FilterDto filterDto, Model model) {
        FilterOptions filterOptions = new FilterOptions(
                filterDto.getTitle(),
                filterDto.getCreatedByUserName(),
                filterDto.getSortBy(),
                filterDto.getSortOrder(),
                filterDto.getTag());
        List<Post> posts = postService.get(filterOptions);
        model.addAttribute("filterOptions", filterDto);
        model.addAttribute("posts", posts);
        return "PostsView";

    }

    @GetMapping("/{id}")
    public String showSinglePost(@PathVariable int id, Model model, HttpSession session) {
        try {
            Post post = postService.get(id);
            model.addAttribute("post", post);
            model.addAttribute("likeCount", postService.getLikesCount(post));
            try {
                List<Comment> comments = commentService.getByPostId(id);
                model.addAttribute("comments", comments);
                model.addAttribute("hasComments",true);
            }catch (EntityNotFoundException e){
                model.addAttribute("hasComments", false);
            }


            User user = authenticationHelper.tryGetCurrentUser(session);
            model.addAttribute("hasUserLikedPost", postService.hasUserLikedPost(post, user));
            model.addAttribute("hasModifyPermissions", postService.checkForModifyPermissions(user, post));

            return "Post";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
        catch (AuthorizationException e) {
            return "Post";
        }
    }

    @GetMapping("/{id}/like")
    public String likePost(@PathVariable int id,
                           HttpSession session) {


        try {
            User user = authenticationHelper.tryGetCurrentUser(session);

            postService.likePost(user, id);
            return "redirect:/posts/{id}";

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }



    @GetMapping("/{id}/comment")
    public String showCreateCommentPage(@PathVariable int id, Model model, HttpSession session) {
        try {
            authenticationHelper.tryGetCurrentUser(session);
            model.addAttribute("comment", new CommentDto());
            return "CommentView";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable int id,
                             @Valid @ModelAttribute("comment") CommentDto dto,
                             BindingResult bindingResult,
                             Model model,
                             HttpSession session) {
        User user;

        try {
            user = authenticationHelper.tryGetCurrentUser(session);

        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            return "CommentView";
        }

        try {
            Comment comment = commentMapper.createFromDto(user.getId(),dto);
            comment.setPost(postService.get(id));
            System.out.println(comment.getContent());
            commentService.create(comment);
            return "redirect:/posts/{id}/comment";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("name", "duplicate_beer", e.getMessage());
            return "CommentView";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @GetMapping("/{id}/update")
    public String showEditBeerPage(@PathVariable int id, Model model, HttpSession session) {
        try {
            authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            Post post = postService.get(id);
            PostDto postDto = postMapper.toDto(post);
            model.addAttribute("postId", id);
            model.addAttribute("post", postDto);
            return "UpdatePostView";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @PostMapping("/{id}/update")
    public String updateBeer(@PathVariable int id,
                             @Valid @ModelAttribute("post") PostDto dto,
                             BindingResult bindingResult,
                             Model model,
                             HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            return "UpdatePostView";
        }

        try {
            Post post = postMapper.fromDto(id, dto);
            postService.update(user, post);
            return "redirect:/posts";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("title", "duplicate_post", e.getMessage());
            return "UpdatePostView";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }
    @GetMapping("/{id}/delete")
    public String deleteBeer(@PathVariable int id, Model model, HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {

            postService.delete(user,id);
            return "redirect:/posts";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }



}

