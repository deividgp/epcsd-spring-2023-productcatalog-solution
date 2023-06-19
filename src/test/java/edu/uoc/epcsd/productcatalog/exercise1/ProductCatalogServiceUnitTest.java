package edu.uoc.epcsd.productcatalog.exercise1;

import edu.uoc.epcsd.productcatalog.domain.Product;
import edu.uoc.epcsd.productcatalog.domain.repository.ProductRepository;
import edu.uoc.epcsd.productcatalog.domain.service.ItemService;
import edu.uoc.epcsd.productcatalog.domain.service.ProductService;
import edu.uoc.epcsd.productcatalog.domain.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductCatalogServiceUnitTest {

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void findProductById() {
        Long id = 1L;

        Optional<Product> found = productRepository.findProductById(id);

        if(found.isEmpty()) return;

        assertThat(found.get().getId()).isEqualTo(id);
        verifyFindByIdIsCalledOnce(id);
        Mockito.when(productRepository.findProductById(id)).thenReturn();
    }

    private void verifyFindByIdIsCalledOnce(Long id) {
        Mockito.verify(productRepository, VerificationModeFactory.times(1)).findProductById(id);
        Mockito.reset(productRepository);
    }
}
