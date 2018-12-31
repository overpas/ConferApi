package tech.overpass.conferauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.overpass.conferauth.exception.LargePostException;
import tech.overpass.conferauth.model.db.Post;
import tech.overpass.conferauth.model.db.User;
import tech.overpass.conferauth.model.other.PostTree;
import tech.overpass.conferauth.service.ApiPostService;
import tech.overpass.conferauth.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class ApiPostController {

    private final ApiPostService apiPostService;
    private final UserService userService;

    @Autowired
    public ApiPostController(ApiPostService apiPostService, UserService userService) {
        this.apiPostService = apiPostService;
        this.userService = userService;
    }

    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public List<Post> getLatestPosts(@RequestParam("start") int start, @RequestParam("size") int size) {
        return apiPostService.findPagedLatest(start, size);
    }

    @RequestMapping(value = "/popular", method = RequestMethod.GET)
    public List<Post> getMostPopularPosts(@RequestParam("limit") long limit) {
        return apiPostService.findMostPopular(limit);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PostTree getPostById(@PathVariable("id") long id) {
        return apiPostService.findById(id);
    }

    @PostMapping("/create/new")
    public ResponseEntity<Post> createPost(@RequestParam("postTitle") String title,
                                           @RequestParam("postBody") String body,
                                           @RequestParam("userId") Long userId,
                                           @RequestParam("inReplyTo") Long postId,
                                           HttpServletRequest request) {
        if (title.length() > 299) {
            throw new LargePostException("The post title length cannot exceed 299 symbols");
        }
        if (title.trim().equals("")) {
            title = "post";
        }
        User author = userService.findById(userId);
        Post post = new Post(title, body, author, new Date(), postId > 0 ? postId : null);
        post = apiPostService.create(post);
        author.setPasswordHash(null);
        author.setPosts(null);
        post.setAuthor(author);
        return ResponseEntity.ok(post);
    }

}
