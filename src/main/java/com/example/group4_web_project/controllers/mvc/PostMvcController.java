package com.example.group4_web_project.controllers.mvc;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.helpers.AuthenticationHelper;
import com.example.group4_web_project.helpers.PostMapper;
import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.FilterDto;
import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.services.CommentService;
import com.example.group4_web_project.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostMvcController {

    private final PostService postService;
    private final CommentService commentService;
    private final PostMapper postMapper;
    private final AuthenticationHelper authenticationHelper;

    public PostMvcController(PostService postService, CommentService commentService, PostMapper postMapper, AuthenticationHelper authenticationHelper) {
        this.postService = postService;
        this.commentService = commentService;
        this.postMapper = postMapper;
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

    @GetMapping
    public String showAllBeers(@ModelAttribute("filterOptions") FilterDto filterDto, Model model) {
//      FilterOptions filterOptions = new FilterOptions(
//                filterDto.getName(),
//                filterDto.getMinAbv(),
//                filterDto.getMaxAbv(),
//                filterDto.getStyleId(),
//                filterDto.getSortBy(),
//                filterDto.getSortOrder());
//        List<Beer> beers = beerService.get(filterOptions);
//        model.addAttribute("filterOptions", filterDto);
//        model.addAttribute("beers", beers);
//        return "BeersView";
        return null;
    }

    @GetMapping("/{id}")
    public String showSingleBeer(@PathVariable int id, Model model) {
        try {
            Post post = postService.get(id);
            model.addAttribute("post", post);
            return "PostView";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

//    @GetMapping("/new")
//    public String showNewBeerPage(Model model, HttpSession session) {
//        try {
//            authenticationHelper.tryGetCurrentUser(session);
//        } catch (AuthorizationException e) {
//            return "redirect:/auth/login";
//        }
//
//        model.addAttribute("beer", new BeerDto());
//        return "BeerCreateView";
//    }
//
//    @PostMapping("/new")
//    public String createBeer(@Valid @ModelAttribute("beer") BeerDto beerDto,
//                             BindingResult bindingResult,
//                             Model model,
//                             HttpSession session) {
//        User user;
//        try {
//            user = authenticationHelper.tryGetCurrentUser(session);
//        } catch (AuthorizationException e) {
//            return "redirect:/auth/login";
//        }
//
//        if (bindingResult.hasErrors()) {
//            return "BeerCreateView";
//        }
//
//        try {
//            Beer beer = beerMapper.fromDto(beerDto);
//            beerService.create(beer, user);
//            return "redirect:/beers";
//        } catch (EntityNotFoundException e) {
//            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
//            model.addAttribute("error", e.getMessage());
//            return "ErrorView";
//        } catch (EntityDuplicateException e) {
//            bindingResult.rejectValue("name", "duplicate_beer", e.getMessage());
//            return "BeerCreateView";
//        }
//    }
//
//    @GetMapping("/{id}/update")
//    public String showEditBeerPage(@PathVariable int id, Model model, HttpSession session) {
//        try {
//            authenticationHelper.tryGetCurrentUser(session);
//        } catch (AuthorizationException e) {
//            return "redirect:/auth/login";
//        }
//
//        try {
//            Beer beer = beerService.get(id);
//            BeerDto beerDto = beerMapper.toDto(beer);
//            model.addAttribute("beerId", id);
//            model.addAttribute("beer", beerDto);
//            return "BeerUpdateView";
//        } catch (EntityNotFoundException e) {
//            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
//            model.addAttribute("error", e.getMessage());
//            return "ErrorView";
//        }
//    }
//
//    @PostMapping("/{id}/update")
//    public String updateBeer(@PathVariable int id,
//                             @Valid @ModelAttribute("beer") BeerDto dto,
//                             BindingResult bindingResult,
//                             Model model,
//                             HttpSession session) {
//        User user;
//        try {
//            user = authenticationHelper.tryGetCurrentUser(session);
//        } catch (AuthorizationException e) {
//            return "redirect:/auth/login";
//        }
//
//        if (bindingResult.hasErrors()) {
//            return "BeerUpdateView";
//        }
//
//        try {
//            Beer beer = beerMapper.fromDto(id, dto);
//            beerService.update(beer, user);
//            return "redirect:/beers";
//        } catch (EntityNotFoundException e) {
//            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
//            model.addAttribute("error", e.getMessage());
//            return "ErrorView";
//        } catch (EntityDuplicateException e) {
//            bindingResult.rejectValue("name", "duplicate_beer", e.getMessage());
//            return "BeerUpdateView";
//        } catch (AuthorizationException e) {
//            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
//            model.addAttribute("error", e.getMessage());
//            return "ErrorView";
//        }
//    }
//
//    @GetMapping("/{id}/delete")
//    public String deleteBeer(@PathVariable int id, Model model, HttpSession session) {
//        User user;
//        try {
//            user = authenticationHelper.tryGetCurrentUser(session);
//        } catch (AuthorizationException e) {
//            return "redirect:/auth/login";
//        }
//
//        try {
//            beerService.delete(id, user);
//            return "redirect:/beers";
//        } catch (EntityNotFoundException e) {
//            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
//            model.addAttribute("error", e.getMessage());
//            return "ErrorView";
//        } catch (AuthorizationException e) {
//            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
//            model.addAttribute("error", e.getMessage());
//            return "ErrorView";
//        }
//    }
}


