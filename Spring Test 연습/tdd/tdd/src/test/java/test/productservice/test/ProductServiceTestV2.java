package test.productservice.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.tdd.TddApplication;
import test.tdd.test.*;

@SpringBootTest(classes = TddApplication.class)
public class ProductServiceTestV2 {

    @Autowired
    private ProductService productService;
    @Test
    public void 상품등록_테스트() throws Exception {
        final AddProductRequest request = 상품생성();
        productService.addProduct(request);
    }

    private static AddProductRequest 상품생성() {
        String name = "상품명";
        int price = 1000;
        return new AddProductRequest(name, price, DiscountPolicy.NONE);
    }

}
