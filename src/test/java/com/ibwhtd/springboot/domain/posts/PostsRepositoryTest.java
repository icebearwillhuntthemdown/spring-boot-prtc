package com.ibwhtd.springboot.domain.posts;

import org.apache.tomcat.jni.Local;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleaup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(
                Posts.builder()
                    .title(title)
                    .content(content)
                    .author("개개")
                    .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2021, 1, 6, 18, 00, 00);

        postsRepository.save(Posts.builder()
                .title("이건 새 글이야")
                .content("큰 의미는 없지")
                .author("개개")
                .build());

        // when
        List<Posts> all = postsRepository.findAll();

        // then
        Posts post = all.get(0);
        System.out.println(">>>>>> Created At: " + post.getCreatedDate());
        System.out.println(">>>>>> Modified At: " + post.getModifiedDate());

        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);

    }
}
