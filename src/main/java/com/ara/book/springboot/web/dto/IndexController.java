package com.ara.book.springboot.web.dto;

import com.ara.book.springboot.config.auth.LoginUser;
import com.ara.book.springboot.config.auth.SessionUser;
import com.ara.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController { // 페이지에 관련된 컨트롤러는 모두 IndexController를 사용한다.

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user!=null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save"; // "~/posts/save"를 호출하면 posts-save.mustache 호출
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto=postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
