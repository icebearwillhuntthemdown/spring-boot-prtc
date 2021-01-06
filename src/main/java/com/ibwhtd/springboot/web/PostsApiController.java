package com.ibwhtd.springboot.web;

import com.ibwhtd.springboot.service.posts.PostsService;
import com.ibwhtd.springboot.web.dto.PostsResponseDto;
import com.ibwhtd.springboot.web.dto.PostsSaveRequestDto;
import com.ibwhtd.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    /**
     * 게시글 등록
     */
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    /**
     * 게시글 조회
     */
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }



}
