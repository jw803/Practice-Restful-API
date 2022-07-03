package com.paul.demo.service;

import com.paul.demo.auth.UserIdentity;
import com.paul.demo.converter.ProductConverter;
import com.paul.demo.entity.product.Product;
import com.paul.demo.entity.product.ProductRequest;
import com.paul.demo.entity.product.ProductResponse;
import com.paul.demo.exception.NotFoundException;
import com.paul.demo.parameter.ProductQueryParameter;
import com.paul.demo.repository.ProductRepository;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {

    private ProductRepository repository;
    private MailService mailService;
    private UserIdentity userIdentity;

    public ProductService(ProductRepository repository, MailService mailService,  UserIdentity userIdentity) {
        this.repository = repository;
        this.mailService = mailService;
        this.userIdentity = userIdentity;
    }

    public Product getProduct(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
    }

    public ProductResponse getProductResponse(String id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
        return ProductConverter.toProductResponse(product);
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCreator(userIdentity.getName());
        product = repository.insert(product);

        mailService.sendNewProductMail(product.getId());

        return ProductConverter.toProductResponse(product);
    }

    public ProductResponse replaceProduct(String id, ProductRequest request) {
        Product oldProduct = getProduct(id);
        Product newProduct = ProductConverter.toProduct(request);
        newProduct.setId(oldProduct.getId());
        newProduct.setCreator(oldProduct.getCreator());

        repository.save(newProduct);

        return ProductConverter.toProductResponse(newProduct);
    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
        mailService.sendDeleteProductMail(id);
    }

    public List<ProductResponse> getProductResponses(ProductQueryParameter param) {
        String nameKeyword = Optional.ofNullable(param.getKeyword()).orElse("");
        int priceFrom = Optional.ofNullable(param.getPriceFrom()).orElse(0);
        int priceTo = Optional.ofNullable(param.getPriceTo()).orElse(Integer.MAX_VALUE);
        Sort sort = genSortingStrategy(param.getOrderBy(), param.getSortRule());

        List<Product> products = repository.findByPriceBetweenAndNameLikeIgnoreCase(priceFrom, priceTo, nameKeyword, sort);

        return products.stream()
                .map(ProductConverter::toProductResponse)
                .collect(Collectors.toList());
    }

    private Sort genSortingStrategy(String orderBy, String sortRule) {
        Sort sort = Sort.unsorted();
        if (Objects.nonNull(orderBy) && Objects.nonNull(sortRule)) {
            Sort.Direction direction = Sort.Direction.fromString(sortRule);
            sort = Sort.by(direction, orderBy);
        }

        return sort;
    }

}
