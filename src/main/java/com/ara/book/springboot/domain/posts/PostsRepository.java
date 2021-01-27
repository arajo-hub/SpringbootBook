package com.ara.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository = Dao
// JpaRepository<Entity 클래스, Pk 타입>을 상속하면 CRUD 메소드가 자동 생성된다.
public interface PostsRepository extends JpaRepository<Posts, Long> {



}
