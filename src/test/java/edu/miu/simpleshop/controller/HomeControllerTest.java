package edu.miu.simpleshop.controller;

import edu.miu.simpleshop.builder.ProductListBuilder;
import edu.miu.simpleshop.domain.Product;
import edu.miu.simpleshop.service.CategoryService;
import edu.miu.simpleshop.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class HomeControllerTest {

    @Mock
    private ProductService productServiceMock;

    @Mock
    private CategoryService categoryServiceMock;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController HomeController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void listProducts() {
        List<Product> productList = new ProductListBuilder().build();
        when(productServiceMock.getAllUnconfirmedProducts()).thenReturn(productList);

        ArgumentCaptor<List<Product>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        String viewName = HomeController.getAllProducts(model);
        assertEquals("product/productList", viewName);
        verify(productServiceMock, times(1)).getAllUnconfirmedProducts();
        verify(model, times(1)).addAttribute(eq("productsAll"), argumentCaptor.capture());
        List<Product> setInController = argumentCaptor.getValue();
        assertEquals(productList.size(), setInController.size());
    }
}
