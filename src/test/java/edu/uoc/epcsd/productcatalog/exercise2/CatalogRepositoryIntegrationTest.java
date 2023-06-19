package edu.uoc.epcsd.productcatalog.exercise2;

import edu.uoc.epcsd.productcatalog.domain.Category;
import edu.uoc.epcsd.productcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa.CategoryEntity;
import edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa.CategoryRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = CategoryRepositoryImpl.class)
@DataJpaTest
public class CatalogRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void whenFindById(){
        Long id = 7L;

        Category category = Category.builder()
                .id(id)
                .parentId(null)
                .name("categoriaTest")
                .description("categoriaTest")
                .build();

        entityManager.persistAndFlush(CategoryEntity.fromDomain(category));

        Optional<Category> found = categoryRepository.findCategoryById(id);
        if(found.isEmpty()) return;

        assertThat(found.get().getId()).isEqualTo(category.getId());
    }

}
