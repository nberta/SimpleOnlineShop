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
    private ProductReviewRepository productReviewRepository;

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
            ProductReviewRepository productReviewRepository){

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
        this.productReviewRepository = productReviewRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        //Initializing Table Data


        Category category1 = new Category("Electronics");
        Category category2 = new Category("Fruits");
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        Address address1 = new Address("O'Haire Street", "Chicago", "Illinois", 12359);
        Address address2 = new Address("21 Jump St", "Rabbit", "New York", 11000);
        Address address3 = new Address("55 Run St", "Mountain", "Everest", 10023);

        User userAdmin = new User("admin", "rabbit", "rabbit@jump.com", Role.ADMIN);
        User userBuyer1 = new User("buyer", "rabbit", "rabbit@jump.com", Role.BUYER);
        User userBuyer2 = new User("buyer2", "rabbit", "rabbit@jump.com", Role.BUYER);
        User userBuyer3 = new User("buyer3", "rabbit", "rabbit@jump.com", Role.BUYER);
        User userSeller1 = new User("seller", "rabbit", "rabbit@jump.com", Role.SELLER);
        User userSeller2 = new User("seller2", "rabbit", "rabbit@jump.com", Role.SELLER);

        Admin admin = new Admin(userAdmin);
        Buyer buyer1 = new Buyer(userBuyer1, 40);
        Buyer buyer2 = new Buyer(userBuyer2, 16);
        Buyer buyer3 = new Buyer(userBuyer3, 123);
        Seller seller1 = new Seller(userSeller1, true);
        Seller seller2 = new Seller(userSeller2, false);




        Product guava = new Product("Guava", "A Fruit", 40, false, 99, category2, seller1);
        Product laptop = new Product("Laptop", "An Electronics", 3, true, 1400, category1, seller2);
        Product mobile = new Product("Mobile", "An Electronics", 5, true, 5000, category1, seller2);

        List<Product> products = Arrays.asList(guava, laptop, mobile);

        CartItem cartItem = new CartItem(laptop, 5);
        CartItem cartItem1 = new CartItem(mobile, 44);
        CartItem cartItem2 = new CartItem(guava, 1);

        List<CartItem> cartItems = Arrays.asList(cartItem, cartItem1, cartItem2);

        ShoppingCart shoppingCart = new ShoppingCart(cartItems);

        buyer1.setShoppingCart(shoppingCart);

        new ProductReview(laptop, buyer1, "This one isn't that good", 4 );

        BillingInfo billingInfo = new BillingInfo(address2);

        OrderLine orderLine = new OrderLine(cartItem);
        OrderLine orderLine1 = new OrderLine(cartItem1);
        OrderLine orderLine2 = new OrderLine(cartItem2);
        List<OrderLine> orderLines = Arrays.asList(orderLine, orderLine1, orderLine2);


        Order order1 = new Order(orderLines, billingInfo, address3);

        adminRepository.save(admin);
        buyerRepository.saveAll(Arrays.asList(buyer1, buyer2, buyer3));
        sellerRepository.saveAll(Arrays.asList(seller1, seller2));

        //orderRepository.save(order1);



    }
}
