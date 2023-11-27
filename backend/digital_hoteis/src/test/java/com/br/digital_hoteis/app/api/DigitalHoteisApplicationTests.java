package com.br.digital_hoteis.app.api;

import com.br.digital_hoteis.domain.entity.Category;
import com.br.digital_hoteis.domain.entity.RatingEnum;
import com.br.digital_hoteis.domain.exception.CategoryNotFoundException;
import com.br.digital_hoteis.domain.repository.CategoryRepository;
import com.br.digital_hoteis.domain.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class DigitalHoteisApplicationTests {

	@InjectMocks
	private CategoryServiceImpl categoryService;
	@Mock
	private CategoryRepository categoryRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void findAllCategories() {
		Page<Category> expectedPage = mock(Page.class);
		when(categoryRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);

		Page<Category> result = categoryService.findAllCategories(mock(Pageable.class));

		assertEquals(expectedPage, result);
	}

	@Test
	private Category createMockedCategory() {
		Category category = Mockito.mock(Category.class);
		category.setId(UUID.randomUUID());
		category.setRatings((RatingEnum.GOOD));
		category.setDescription("A five star hotel with the best location for concerts");
		category.setImage_url("https://images.unsplash.com/photo-1696189324359-0587ec599b71?auto=format&fit=crop&q=80&w=1035&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");

		return category;
	}


//	private Category createMockedHotel() {
//		Hotel hotel = Mockito.mock(Hotel.class);
//
//
//		hotel.setId(UUID.randomUUID());
//		hotel.setNome("Ibis");
//
//
//
//		return hotel;
//	}



	@Test
	void findCategoryById() {
		UUID id = UUID.randomUUID();
		Category expectedCategory = mock(Category.class);
		when(categoryRepository.findById(id)).thenReturn(Optional.of(expectedCategory));

		Category result = categoryService.findCategoryById(id);

		assertEquals(expectedCategory, result);
	}

	@Test
	void createCategory() {
		Category categoryInput = mock(Category.class);

		// Configure the createCategory method to return categoryInput when called with any Category object
		when(categoryService.createCategory(any(Category.class))).thenReturn(categoryInput);

		// Set up behavior for the Category mock methods as needed
		when(categoryInput.getId()).thenReturn(UUID.randomUUID());
		when(categoryInput.getRatings()).thenReturn(RatingEnum.EXCELLENT);
		when(categoryInput.getDescription()).thenReturn("A five stars hotel");
		when(categoryInput.getImage_url()).thenReturn("https://images.unsplash.com/photo-1696189324359-0587ec599b71?auto=format&fit=crop&q=80&w=1035&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");

		Category result = categoryService.createCategory(categoryInput);

		assertEquals(categoryInput, result);
	}



	@Test
	void updateCategory() {
		UUID id = UUID.randomUUID();
		Category existingCategory = mock(Category.class);
		Map<String, Object> fields = mock(Map.class);

		ObjectMapper objectMapper = mock(ObjectMapper.class);

		CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository, objectMapper);

		when(categoryRepository.findById(id)).thenReturn(Optional.of(existingCategory));
		when(categoryRepository.save(existingCategory)).thenReturn(existingCategory);

		Category result = categoryService.updateCategoryById(id, fields);

		assertEquals(existingCategory, result);
	}

	@Test
	void deleteCategoryById() {
		UUID id = UUID.randomUUID();
		Category existingCategory = mock(Category.class);
		when(categoryRepository.findById(id)).thenReturn(Optional.of(existingCategory));

		categoryService.deleteCategoryById(id);

		verify(categoryRepository, times(1)).delete(existingCategory);
	}

	@Test
	void deleteCategoryById_CategoryNotFound() {
		UUID id = UUID.randomUUID();
		when(categoryRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategoryById(id));
	}
}
