package edu.uoc.epcsd.productcatalog.exercise1;

import edu.uoc.epcsd.productcatalog.domain.Product;
import edu.uoc.epcsd.productcatalog.domain.repository.ProductRepository;
import edu.uoc.epcsd.productcatalog.domain.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProductCatalogServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Test
    public void whenFindProductByIdWithValidId_thenCorrectProductReturned() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        Mockito.when(productRepository.findProductById(id)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> found = productServiceImpl.findProductById(id);

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
        assertThrows(IllegalArgumentException.class, () -> productServiceImpl.findProductById(id).orElseThrow(IllegalArgumentException::new));
        verifyFindByIdIsCalledOnce(id);
    }

    private void verifyFindByIdIsCalledOnce(Long id) {
        Mockito.verify(productRepository, VerificationModeFactory.times(1)).findProductById(id);
        Mockito.reset(productRepository);
    }
}
