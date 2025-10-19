package com.pratham.fanfiction.apis.storyFetcherTitle;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pratham.fanfiction.mongo.Stories;

@SpringBootTest
public class StoryServiceImplIntegrationTest
{
	
	
	@Autowired
	private StoryServiceImpl storyServiceImpl;
	
	
	
	@Test
	public void test()
	{
		Optional<Stories> storyOptional = storyServiceImpl.getStoryByTitle("ef");
		assertThat(storyOptional)
        .isPresent()
        .hasValueSatisfying(s -> assertThat(s.getTitle()).isEqualTo("ef"));
	}

}
