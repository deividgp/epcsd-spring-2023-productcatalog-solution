package edu.uoc.epcsd.productcatalog.exercise1;

import edu.uoc.epcsd.productcatalog.domain.Product;
import edu.uoc.epcsd.productcatalog.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductCatalogServiceUnitTest {

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void whenFindProductByIdWithValidId_thenCorrectProductReturned() {
        // Arrange
        Long id = 1L;
        Product product = Product.builder()
                .id(id)
                .build();
        Mockito.when(productRepository.findProductById(id)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> found = productRepository.findProductById(id);

        // Assert
        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(id);
        verifyFindByIdIsCalledOnce(id);
    }

    @Test
    public void whenFindProductByIdWithInvalidId_thenExceptionIsThrown() {
        // Arrange
        Long id = 1L;
        Mockito.when(productRepository.findProductById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> productRepository.findProductById(id).orElseThrow(IllegalArgumentException::new));
        verifyFindByIdIsCalledOnce(id);
    }

    private void verifyFindByIdIsCalledOnce(Long id) {
        Mockito.verify(productRepository, VerificationModeFactory.times(1)).findProductById(id);
        Mockito.reset(productRepository);
    }
}
