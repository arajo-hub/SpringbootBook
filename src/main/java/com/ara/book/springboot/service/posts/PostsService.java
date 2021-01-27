package com.ara.book.springboot.service.posts;

import com.ara.book.springboot.domain.posts.Posts;
import com.ara.book.springboot.domain.posts.PostsRepository;
import com.ara.book.springboot.web.dto.PostsListResponseDto;
import com.ara.book.springboot.web.dto.PostsResponseDto;
import com.ara.book.springboot.web.dto.PostsSaveRequestDto;
import com.ara.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    // 등록
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // 수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts=postsRepository.findById(id)
                                    .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        // update 쿼리 X -> 트랜잭션 안에서 데이터를 가지고 오면 영속성 컨텍스트가 유지되고, 트랜잭션이 끝나면 변경이 반영된다. -> 더티 체킹

        return id;
    }

    // 조회
    public PostsResponseDto findById(Long id) {
        Posts entity=postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly=true)
    public List<PostsListResponseDto> findAllDesc() { // findAllDesc()를 통해 나온 스트림을 map을 통해 결과적으로 List화한다.
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // = .map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());
    }

    // 삭제
    @Transactional
    public void delete(Long id) {
        Posts posts=postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);
    }

}
