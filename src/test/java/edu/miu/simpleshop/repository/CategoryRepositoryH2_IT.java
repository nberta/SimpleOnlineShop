package edu.miu.simpleshop.repository;//package edu.miu.simpleshoptest.repository;
//
//import edu.miu.simpleshoptest.domain.Category;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class CategoryRepositoryMySQL_IT {
//
//    @Autowired
//    CategoryRepositoryMySQL categoryRepositoryMySQL;
//
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @Test
////    @DirtiesContext
//    public void findByName() throws Exception{
//        Optional<Category> categoryOptional = categoryRepositoryMySQL.findByName("Electronics");
//        assertEquals("Electronics", categoryOptional.get().getName());
//    }
//
//    @Test
//    public void findByName2() throws Exception{
//        Optional<Category> categoryOptional = categoryRepositoryMySQL.findByName("Fruits");
//        assertEquals("Fruits", categoryOptional.get().getName());
//
//    }
//}