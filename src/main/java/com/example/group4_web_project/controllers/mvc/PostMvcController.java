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


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @ModelAttribute("isBlocked")
    public boolean populateIsBlocked(HttpSession session) {
        try{
            User user = authenticationHelper.tryGetCurrentUser(session);
            return user.isBlocked();
        }
        catch (AuthorizationException e){
            return false;
        }


    }
    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }





    @GetMapping("/new")
    public String showNewPost(Model model) {
        model.addAttribute("post", new PostDto());
        model.addAttribute("tag", new TagDto());
        return "NewPostView";
    }
    @PostMapping("/new")
    public String handleNewPost(@Valid @ModelAttribute("post") PostDto post,
                                BindingResult bindingResult,
                                @RequestParam("tagNames") String tagNames,
                                HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "NewPostView";
        }

        try {
            Post p = postMapper.fromDto(post);
            String username = (String) session.getAttribute("currentUser");
            User user = userService.get(username);
            postService.create(p, user);

            if (tagNames != null && !tagNames.trim().isEmpty()) {
                String[] tagArray = tagNames.split("\\s*,\\s*");
                for (String tagName : tagArray) {
                    if (!tagName.isEmpty()) {

                        postService.addTagToPost(p.getId(), tagName, user);
                    }
                }
            }

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
            model.addAttribute("hasTags", !post.getTags().isEmpty());
            List<Tag> tags = new ArrayList<>();
            tags.addAll(post.getTags());
            model.addAttribute("tags", tags);
            //System.out.println(tags.size());
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



    @GetMapping("/{id}/AddComment")
    public String showCreateCommentPage(@PathVariable int id, Model model, HttpSession session) {
        try {
            authenticationHelper.tryGetCurrentUser(session);
            model.addAttribute("comment", new CommentDto());
            return "AddComment";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @PostMapping("/{id}/AddComment")
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
            return "AddComment";
        }

        try {
            Comment comment = commentMapper.createFromDto(user.getId(),dto);
            comment.setPost(postService.get(id));
            commentService.create(comment);
            return "redirect:/posts/{id}";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("name", "duplicate_post", e.getMessage());
            return "AddComment";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @GetMapping("/{id}/comment/{commentId}")
    public String showCommentPage(@PathVariable int id,@PathVariable int commentId, Model model, HttpSession session) {
        try {
           User user =  authenticationHelper.tryGetCurrentUser(session);
           Comment comment = commentService.get(commentId);
            model.addAttribute("comment", comment);
            model.addAttribute("hasModifyPermissions", commentService.checkForModifyPermissionsComment(user,comment));

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

    @GetMapping("/{id}/comment/{commentId}/delete")
    public String deleteComment(@PathVariable int id, @PathVariable int commentId, Model model, HttpSession session) {

        try {
         User user =   authenticationHelper.tryGetCurrentUser(session);
            commentService.delete(user,commentId );
            return "redirect:/posts/{id}";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }


    @GetMapping("/{id}/comment/{commentId}/update")
    public String updaetComment(Model model, @PathVariable int id, @PathVariable int commentId,HttpSession session) {
        try{
            authenticationHelper.tryGetCurrentUser(session);

            Comment comment = commentService.get(commentId);
            model.addAttribute("comment", comment);
            return "UpdateCommentView";
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }

    }








    @PostMapping("/{id}/comment/{commentId}/update")
    public String updateUser(@Valid @ModelAttribute("comment") CommentDto commentDto,@PathVariable int id,@PathVariable int commentId,
                             BindingResult bindingResult,  HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "UpdateCommentView";
        }



        try {

            User user = authenticationHelper.tryGetCurrentUser(session);

            Comment comment = commentMapper.updateFromDto(commentId, commentDto);
            commentService.update(user, comment);
            return "redirect:/";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("username", "username_error", e.getMessage());
            return "UpdateCommentView";
        }
    }










    @GetMapping("/{id}/update")
    public String showEditPostPage(@PathVariable int id, Model model, HttpSession session) {
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
    public String updatePost(@PathVariable int id,
                             @Valid @ModelAttribute("post") PostDto dto,
                             BindingResult bindingResult,
                             @RequestParam("tagNames") String tagNames,
                             Model model,
                             HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            Post existingPost = postService.get(id);
            String existingTagNames = existingPost.getTags().stream()
                    .map(Tag::getName)
                    .collect(Collectors.joining(", "));
            model.addAttribute("existingTagNames", existingTagNames);
            return "UpdatePostView";
        }

        try {
            Post post = postMapper.fromDto(id, dto);


            if (tagNames != null && !tagNames.trim().isEmpty()) {
                String[] tagArray = tagNames.split("\\s*,\\s*");
                postService.updateTags(post, tagArray, user);
            }
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
    public String deletePost(@PathVariable int id, Model model, HttpSession session) {
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

