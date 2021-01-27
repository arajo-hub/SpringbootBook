package com.ara.book.springboot.domain.posts;

import com.ara.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor // public Posts() {}
@Entity // 테이블과 연결될 클래스라는 것을 나타낸다.

public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙
    private Long id;

    @Column(length=500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함(생성자 대신!)
    public Posts(String title, String content, String author) {
        this.title=title;
        this.content=content;
        this.author=author;

    }

    public void update(String title, String content) {
        this.title=title;
        this.content=content;
    }
}
