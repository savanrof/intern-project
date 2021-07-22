package com.nt.rookie.post.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nt.rookie.post.dto.PostDTO;
import com.nt.rookie.post.entity.Author;
import com.nt.rookie.post.entity.Post;
import com.nt.rookie.post.exception.NotFoundException;
import com.nt.rookie.post.mapper.PostMapper;
import com.nt.rookie.post.repository.AuthorRepository;
import com.nt.rookie.post.repository.PostRepository;
import com.nt.rookie.post.service.impl.PostServiceImpl;

@ExtendWith(SpringExtension.class)
public class PostServiceImplTests {
	@Mock
	private PostRepository postRepository;
	
	@Mock
	private AuthorRepository authorRepository;

	@Mock
	private PostMapper mapper;

	@Captor
	private ArgumentCaptor<Post> captor;

	@InjectMocks
	private PostServiceImpl underTest;

	@Test
	public void testFindPostByIdGivenIdNotExistInDatabaseShouldThrowNotFoundException() {
		NotFoundException exception = assertThrows(NotFoundException.class, () -> underTest.findPostById(1));
		assertEquals("No record found with id 1", exception.getMessage());
	}

	@Test
	public void testFindPostByIdGivenIdExistInDatabaseShouldReturnDataSuccessfully() {
		Post mockPost = mock(Post.class);
		when(mockPost.getId()).thenReturn(1);
		when(mockPost.getTitle()).thenReturn("Post title");
		when(mockPost.getContent()).thenReturn("Post content");
		Mockito.when(postRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mockPost));
		when(mapper.fromEntity(mockPost)).thenCallRealMethod();
		PostDTO post = underTest.findPostById(1);
		assertEquals(mockPost.getTitle(), post.getTitle());
		assertEquals(mockPost.getContent(), post.getContent());
	}

	@Test
	public void testDeletePostGivenIdNotFoundShouldThrowNotFoundException() {
		NotFoundException exception = assertThrows(NotFoundException.class, () -> underTest.deletePostById(1));
		assertEquals("No record found with id 1", exception.getMessage());
		verify(postRepository, never()).delete(any(Post.class));
	}

	@Test
	public void testDeletePostGivenIdExistShouldDeleteSuccessfully() {
		Post mockPost = mock(Post.class);
		when(mockPost.getId()).thenReturn(1);
		when(mockPost.getTitle()).thenReturn("Title mock post");
		when(postRepository.findById(anyInt())).thenReturn(Optional.of(mockPost));
		underTest.deletePostById(1);
		verify(postRepository, times(1)).delete(captor.capture());
		Post deletedPost = captor.getValue();
		assertEquals("Title mock post", deletedPost.getTitle());
		assertEquals(1, deletedPost.getId());
	}

	/**
	 * do the same for 
	 * findPostByAuthorInMonth 
	 * findPostByCreatedDate
	 * findTop10NewestPost 
	 * findByTitle
	 */
	@Test
	public void testFindByTitleLikeEmptyResultShouldNeverCallMapper() {
		when(postRepository.findByTitleContains(anyString())).thenReturn(Collections.emptyList());
		underTest.findByTitleLike("mock post");
		verify(mapper, never()).fromEntity(any(Post.class));
	}

	/**
	 * do the same for 
	 * findPostByAuthorInMonth 
	 * findPostByCreatedDate
	 * findTop10NewestPost 
	 * findByTitle
	 */
	@Test
	public void testFindByTitleLikeReturn2PostsShouldCallMapper2Times() {
		Post first = new Post("Java 8 tutorial");
		Post second = new Post("Getting started with Javascript");

		List<Post> posts = java.util.Arrays.asList(first, second);

		when(postRepository.findByTitleContains(anyString())).thenReturn(posts);

		underTest.findByTitleLike("Java");

		verify(mapper, times(2)).fromEntity(any()); // verify mapper call 2 times
		// because results return 2 elements
	}
	@Nested
	@DisplayName("Test Update Post")
	class TestUpdatePost {
		@Test
		public void testUpdatePostGivenPostIdIsNullShouldThrowException() {
			PostDTO mockPayload = mock(PostDTO.class);
			IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->underTest.updatePost(null, mockPayload));
			assertEquals("Post id can not be null", exception.getMessage());
			verify(postRepository, never()).findById(anyInt());
		}
		
		@Test
		public void testUpdatePostGivenPayloadIsNullShouldThrowException() {
			IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->underTest.updatePost(1, null));
			assertEquals("Request payload can not be null", exception.getMessage());
			verify(postRepository, never()).findById(anyInt());
		}
		@Test
		public void testUpdatePostGivenIdAnPayloadNotNullButNotFoundPostInDbShouldNotUpdated() {
			PostDTO mockPayload = mock(PostDTO.class);
			when(mockPayload.getTitle()).thenReturn("Updated title");
			when(mockPayload.getContent()).thenReturn("Updated content");
			when(mockPayload.getDescription()).thenReturn("New desc");
			when(postRepository.findById(anyInt())).thenReturn(Optional.empty());
			assertDoesNotThrow(() -> underTest.updatePost(1, mockPayload));
			verify(mapper, never()).fromEntity(any(Post.class));
			verify(postRepository, never()).save(any(Post.class));
			verify(mapper, never()).fromDTO(any(PostDTO.class));
			
		}
		
		@Test
		public void testUpdatePostSuccess() {
			Post mockPost = mock(Post.class);
			when(mockPost.getId()).thenReturn(1);
			when(mockPost.getTitle()).thenReturn("Old title");
			when(mockPost.getContent()).thenReturn("Old content");
			when(mockPost.getDescription()).thenReturn("Old desc");
			
			PostDTO mockPayload = mock(PostDTO.class);
			when(mockPayload.getTitle()).thenReturn("New title");
			when(mockPayload.getContent()).thenReturn("New content");
			when(mockPayload.getDescription()).thenReturn("New desc");
			
			when(postRepository.findById(anyInt())).thenReturn(Optional.of(mockPost));
			
			when(mapper.fromDTO(any(Post.class), any(PostDTO.class))).thenCallRealMethod();
			
			assertDoesNotThrow(() -> underTest.updatePost(1, mockPayload));
			
			verify(mapper, times(1)).fromDTO(any(Post.class), any(PostDTO.class));
			
			verify(postRepository, times(1)).save(any(Post.class));
		}
	}
	@Nested
	@DisplayName("Test Create Post")
	class TestCreatePost {
		@Test
		public void testCreatePostGivenAuthorIdIsNullShouldThrowException() {
			PostDTO mockPayload = mock(PostDTO.class);
			IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->underTest.createPost(mockPayload, null));
			assertEquals("Author id can not be null", exception.getMessage());
			verify(postRepository, never()).findById(anyInt());
		}
		
		@Test
		public void testCreatePostGivenPayloadIsNullShouldThrowException() {
			IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, ()->underTest.createPost( null, 1));
			assertEquals("Request payload can not be null", exception.getMessage());
			verify(postRepository, never()).findById(anyInt());
		}
		
		@Test
		public void testCreatePostSuccess() {
			Author mockAuthor = mock(Author.class);
			when(mockAuthor.getId()).thenReturn(1);
			when(authorRepository.getById(anyInt())).thenReturn(mockAuthor);
			
			PostDTO mockPayload = mock(PostDTO.class);
			when(mockPayload.getTitle()).thenReturn("New post title");
			when(mockPayload.getContent()).thenReturn("New post content");
			when(mockPayload.getDescription()).thenReturn("New post desc");

			when(mapper.fromDTO(any(PostDTO.class))).thenCallRealMethod();
			when(mapper.fromDTO(any(Post.class), any(PostDTO.class))).thenCallRealMethod();
			
			
			assertDoesNotThrow(() -> underTest.createPost(mockPayload, 1));
			
			verify(mapper, times(1)).fromDTO(any(Post.class), any(PostDTO.class));
			
			verify(postRepository, times(1)).save(any(Post.class));
		}
	}
}
