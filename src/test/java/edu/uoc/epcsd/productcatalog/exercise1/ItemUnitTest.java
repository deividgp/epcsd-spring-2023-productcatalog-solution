package edu.uoc.epcsd.productcatalog.exercise1;

import edu.uoc.epcsd.productcatalog.domain.Item;
import edu.uoc.epcsd.productcatalog.domain.ItemStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ItemUnitTest {

    @Test
    public void whenNewItem_nonOperational(){
        Item testItem = Item
                .builder()
                .productId(1L)
                .serialNumber("554457454")
                .status(ItemStatus.NON_OPERATIONAL)
                .build();

        assertThat(testItem.getStatus()).isEqualTo(ItemStatus.NON_OPERATIONAL);
    }
}
