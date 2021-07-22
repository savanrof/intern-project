package com.nt.rookie.post.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nt.rookie.post.entity.Post;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/add-post.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/remove-post.sql")
public class PostRepositoryTests {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void testGetAllPosts() {
        List<Post> posts = postRepository.findAll();
        assertEquals(12, posts.size());
    }

    @Test
    public void testFindByTitleLikeShouldReturnRecordsCorrectly() {
        List<Post> posts = postRepository.findByTitleContains("Post 1");
        assertEquals(4, posts.size());
        for (Post post : posts) {
            assertTrue(post.getTitle().contains("Post 1"));
        }
    }
}
