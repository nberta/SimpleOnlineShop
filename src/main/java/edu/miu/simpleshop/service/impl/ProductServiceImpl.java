package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.OrderLine;
import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.domain.enums.OrderStatus;
import edu.miu.simpleshop.exception.IncorrectFileTypeException;
import edu.miu.simpleshop.exception.UndeletableProductException;
import edu.miu.simpleshop.repository.CategoryRepository;
import edu.miu.simpleshop.repository.OrderLineRepository;
import edu.miu.simpleshop.repository.ProductRepository;
import edu.miu.simpleshop.service.ProductService;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Collection<Product> save(Collection<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Product delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Collection<OrderLine> orderLines = orderLineRepository.findAllByProductId(id);
        for (OrderLine o : orderLines)
            if (o.getStatus().equals(OrderStatus.CREATED))
                throw new UndeletableProductException("This product is currently being processed. Cancel the order before attempting to remove the product");
        productRepository.delete(product);
        return product;
    }

    @Override
    public List<Product> getAllUnconfirmedProducts() {
        List<Product> result;
        result = productRepository.findAll();
        return result;
    }

    @Override
    public List<Product> getBySellerId(Long id) {
        List<Product> result;
        result = productRepository.findBySellerId(id);
        return result;
    }

    @Override
    public Collection<Product> getByCategoryId(Long id) {
        Collection<Product> result;
        result = productRepository.findByCategoryId(id);
        return result;
    }

    @Override
    public Product getProduct(Long id) {

        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public File processImage(Product product, String storagePath) {
        MultipartFile productImage = product.getProductImage();
        File file = null;
        if (productImage != null && !productImage.isEmpty()) {
            try {
                Tika tika = new Tika();
                String type = tika.detect(productImage.getBytes());
                if (!type.equals("image/png"))
                    throw new IncorrectFileTypeException("The uploaded file is an invalid type. Please enter an image.");
                file = new File(storagePath + "images\\products\\"
                                + product.getImageIdentifier() + ".png");
                productImage.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
