package edu.miu.simpleshop.config;

import edu.miu.simpleshop.domain.Admin;
import edu.miu.simpleshop.domain.Buyer;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.domain.User;
import edu.miu.simpleshop.domain.enums.Role;
import edu.miu.simpleshop.service.AdminService;
import edu.miu.simpleshop.service.BuyerService;
import edu.miu.simpleshop.service.SellerService;
import edu.miu.simpleshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CurrentUserCatcher implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private AdminService adminService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println(authentication);
            User user = userService.getByUserName(authentication.getName());
            if (user.getRole().equals(Role.BUYER)) {
                Buyer loggedInBuyer = buyerService.getByUser(user);
                session.setAttribute("loggedInBuyer", loggedInBuyer);
            } else if (user.getRole().equals(Role.SELLER)) {
                Seller loggedInSeller = sellerService.getByUser(user);
                session.setAttribute("loggedInSeller", loggedInSeller);
            } else if (user.getRole().equals(Role.ADMIN)) {
                Admin loggedInAdmin = adminService.getByUser(user);
                session.setAttribute("loggedInAdmin", loggedInAdmin);
            } else {
                throw new IllegalStateException("Roles are out of bound");
            }
        }
    }
}
