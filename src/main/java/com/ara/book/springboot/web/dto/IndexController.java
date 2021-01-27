package com.ara.book.springboot.web.dto;

import com.ara.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@RequiredArgsConstructor
@Controller
public class IndexController { // 페이지에 관련된 컨트롤러는 모두 IndexController를 사용한다.

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index"; // 머스테치 스타터에서 경로와 파일 확장자 자동으로 지정해준다.
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save"; // "~/posts/save"를 호출하면 posts-save.mustache 호출
    }
}
