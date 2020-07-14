package edu.miu.simpleshop.config;


import edu.miu.simpleshop.domain.*;
import edu.miu.simpleshop.domain.enums.Role;
import edu.miu.simpleshop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {

    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private BuyerRepository buyerRepository;
    private SellerRepository sellerRepository;
    private CartItemRepository cartItemRepository;
    private CategoryRepository categoryRepository;
    private FollowRepository followRepository;
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;
    private ProductRepository productRepository;
    private ReviewRepository reviewRepository;

    public DbInit(
            UserRepository userRepository,
            AdminRepository adminRepository,
            BuyerRepository buyerRepository,
            SellerRepository sellerRepository,
            CartItemRepository cartItemRepository,
            CategoryRepository categoryRepository,
            FollowRepository followRepository,
            OrderRepository orderRepository,
            PaymentRepository paymentRepository,
            ProductRepository productRepository,
            ReviewRepository reviewRepository){

        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.cartItemRepository = cartItemRepository;
        this.categoryRepository = categoryRepository;
        this.followRepository = followRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        //Initializing Table Data

        Address address1 = new Address("O'Haire Street", "Chicago", "Illinois", 12359);
        Address address2 = new Address("21 Jump St", "Rabbit", "New York", 11000);
        Address address3 = new Address("55 Run St", "Mountain", "Everest", 10023);

        User userAdmin = new User("admin", "rabbit", "rabbit@jump.com", Role.ADMIN);
        User userBuyer = new User("buyer", "rabbit", "rabbit@jump.com", Role.BUYER);
        User userSeller = new User("seller", "rabbit", "rabbit@jump.com", Role.SELLER);

        Admin admin = new Admin(userAdmin);
        Buyer buyer1 = new Buyer(userBuyer, 40);
        Seller seller1 = new Seller(userSeller, false);
        adminRepository.save(admin);
        buyerRepository.save(buyer1);
        sellerRepository.save(seller1);

        Category category1 = new Category("Electronics");
        Category category2 = new Category("Fruits");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        Product guava = new Product("Guava", "A Fruit", 40, false, 99, category2);
        Product laptop = new Product("Laptop", "An Electronics", 3, true, 1400, category1);
        Product mobile = new Product("Mobile", "An Electronics", 5, true, 5000, category1);

        List<Product> products = Arrays.asList(guava, laptop, mobile);

        CartItem cartItem = new CartItem(laptop, 5);
        CartItem cartItem1 = new CartItem(mobile, 44);
        CartItem cartItem2 = new CartItem(guava, 1);

        List<CartItem> cartItems = Arrays.asList(cartItem, cartItem1, cartItem2);

        ShoppingCart shoppingCart = new ShoppingCart(cartItems);

        ProductReview old = new ProductReview(laptop, buyer1, "This one isn't that good", 4 );

        BillingInfo billingInfo = new BillingInfo(address2);

        OrderLine orderLine = new OrderLine(cartItem);
        OrderLine orderLine1 = new OrderLine(cartItem1);
        OrderLine orderLine2 = new OrderLine(cartItem2);
        List<OrderLine> orderLines = Arrays.asList(orderLine, orderLine1, orderLine2);


        Order order1 = new Order(orderLines, billingInfo, address3);

        orderRepository.save(order1);



    }
}
