package test.productservice.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.tdd.test.*;

public class ProductServiceTestV1 {

    private ProductService productService;
    private ProductPort productPort;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        productPort = new ProductAdapter(productRepository);
        productService = new ProductService(productPort);
    }

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
