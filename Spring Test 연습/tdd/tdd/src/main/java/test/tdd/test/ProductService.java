package test.tdd.test;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductService {
    private final ProductPort productPort;

    public ProductService(ProductPort productPort) {
        this.productPort = productPort;
    }

    @Transactional
    public void addProduct(AddProductRequest request) {
        final Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);

    }
}
