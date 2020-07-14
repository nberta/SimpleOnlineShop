//package edu.miu.simpleshop.config;
//
//import edu.miu.simpleshop.domain.enums.Role;
//import edu.miu.simpleshop.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private DataSource dataSource;
//
////    @Value("${spring.queries.users-query}")
////    private String usersQuery;
////
////    @Value("${spring.queries.roles-query}")
////    private String rolesQuery;
//
//
//    // Authentication Manager that specified where we get the user and password
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
////        auth.inMemoryAuthentication()
////                .withUser("admin")
////                .password("admin")
////                .roles("ADMIN");
////        auth.jdbcAuthentication()
////                .dataSource(dataSource)
////                .usersByUsernameQuery("SELECT username, password FROM user WHERE username =?");
//        //.authoritiesByUsernameQuery("select username, authority from authorities where username =?");
//    }
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        // ignored homepage, account area from authentication. and h2 database console
//        http.authorizeRequests()
//            .antMatchers("/", "/sellers/**", "/buyers/**", "/h2/**", "/**", "/admin/**").permitAll()
//            // allow access to all area until security module finishp
//
//            // checking permission on areas
//            //.antMatchers("/admin/**").hasAuthority("ADMIN")
//            /.antMatchers("/sellers/**").hasAuthority("SELLER")
//            .antMatchers("/buyers/**").hasAuthority("BUYER")
//            .and()
//            .formLogin()
//            .loginPage("/login")
//            .failureUrl("/login?error=true")
//            .defaultSuccessUrl("/")
//            .usernameParameter("username")
//            .passwordParameter("password")
//            .and()
//            .rememberMe()
//            .tokenValiditySeconds(86400)
//            .and()
//            .logout()
//            .logoutRequestMatcher(new AntPathRequestMatcher("/account/logout"))
//            .logoutSuccessUrl("/")
//            .and()
//            .exceptionHandling()
//            .accessDeniedPage("/403")
//            .and()
//            .csrf()
//            .disable();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//           .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/h2-console/**");
//    }
//
////    @Bean
////    DaoAuthenticationProvider authenticationProvider() {
////        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
////        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
////
////        return daoAuthenticationProvider;
////    }
////
////    @Bean
////    PasswordEncoder passwordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
//
//}
