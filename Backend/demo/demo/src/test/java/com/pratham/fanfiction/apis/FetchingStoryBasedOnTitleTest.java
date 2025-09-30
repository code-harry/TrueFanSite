//package com.pratham.fanfiction.apis;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.Optional;
//
//import com.pratham.fanfiction.mongo.Stories;
//import com.pratham.fanfiction.mongo.StoryRepository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//class FetchingStoryBasedOnTitleTest {
//
//    private StoryRepository repo;
//    private FetchingStoryBasedOnTitle controller;
//
//    @BeforeEach
//    void setUp() {
//        // Mock the repository
//        repo = mock(StoryRepository.class);
//        controller = new FetchingStoryBasedOnTitle();
//        
//        // Inject the mocked repository
//        controller.repo = repo; // repo is @Autowired, so we can set it manually
//    }
//
//    @Test
//    void testStoryFound() {
//        // Arrange
//        Stories story = new Stories();
//        story.setTitle("My Story");
//        story.setAge("18+");
//        story.setLanguage("English");
//
//        when(repo.findById("My Story")).thenReturn(Optional.of(story));
//
//        // Act
//        ResponseEntity<Stories> response = controller.FetchStoryBasedOnTitle("My Story");
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals("My Story", response.getBody().getTitle());
//        assertEquals("18+", response.getBody().getAge());
//        assertEquals("English", response.getBody().getLanguage());
//    }
//
//    @Test
//    void testStoryNotFound() {
//        // Arrange
//        when(repo.findById("Unknown")).thenReturn(Optional.empty());
//
//        // Act
//        ResponseEntity<Stories> response = controller.FetchStoryBasedOnTitle("Unknown");
//
//        // Assert
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//}
