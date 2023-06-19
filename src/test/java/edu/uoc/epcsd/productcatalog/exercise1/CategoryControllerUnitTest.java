package edu.uoc.epcsd.productcatalog.exercise1;

import edu.uoc.epcsd.productcatalog.application.rest.CategoryRESTController;
import edu.uoc.epcsd.productcatalog.domain.Category;
import edu.uoc.epcsd.productcatalog.domain.service.CategoryService;
import edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa.SpringDataCategoryRepository;
import edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa.SpringDataItemRepository;
import edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa.SpringDataProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryRESTController.class)
public class CategoryControllerUnitTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private SpringDataCategoryRepository springDataCategoryRepository;

    @MockBean
    private SpringDataItemRepository springDataItemRepository;

    @MockBean
    private SpringDataProductRepository springDataProductRepository;

    @Test
    public void whenGetAllCategories() throws Exception {
        // Arrange
        Category category1 = Category.builder()
                .id(1L)
                .name("coses1")
                .description("coses1")
                .parentId(null)
                .build();

        Category category2 = Category.builder()
                .id(2L)
                .name("coses2")
                .description("coses2")
                .parentId(null)
                .build();

        List<Category> allCategories = Arrays.asList(category1, category2);

        BDDMockito.given(categoryService.findAllCategories()).willReturn(allCategories);

        // Act and Assert
        mvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verifyFindAllCategoriesIsCalledOnce();
    }

    private void verifyFindAllCategoriesIsCalledOnce() {
        Mockito.verify(categoryService, VerificationModeFactory.times(1)).findAllCategories();
        Mockito.reset(categoryService);
    }
}
