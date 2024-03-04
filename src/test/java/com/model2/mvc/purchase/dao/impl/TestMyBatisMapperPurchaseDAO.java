package com.model2.mvc.purchase.dao.impl;

import static org.assertj.core.api.Assertions.*;

import com.model2.mvc.common.ListData;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.dao.PurchaseDAO;
import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.domain.TransactionProduction;
import com.model2.mvc.user.domain.User;
import junit.framework.TestCase;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config/common.xml" })
public class TestMyBatisMapperPurchaseDAO extends TestCase {

    @Autowired
    @Qualifier("myBatisMapperPurchaseDAO")
    private PurchaseDAO purchaseDAO;

    @Autowired
    @Qualifier("sqlSessionTemplate")
    private SqlSession sqlSession;

    @Before
    public void before() {
        clear();
        sampleUsers.forEach(u -> this.sqlSession.insert("UserMapper.insert", u));
        sampleProducts.forEach(p -> this.sqlSession.insert("ProductMapper.insert", p));
    }

    @After
    public void after() {
        clear();
    }

    private void clear() {
        this.sqlSession.delete("PurchaseMapper.clearTranProd");
        this.sqlSession.delete("PurchaseMapper.clearTransaction");
        this.sqlSession.delete("ProductMapper.clear");
        this.sqlSession.delete("UserMapper.clear");
    }

    private List<User> sampleUsers = Arrays.asList(new User("user001", "name1", "1q2w3e4r"),
                                                   new User("user002", "name2", "2w3e4r5t"),
                                                   new User("user003", "name3", "asdf1234"));
    private List<Product> sampleProducts = Arrays.asList(new Product(20000, "prod1", 100, 10),
                                                         new Product(20001, "prod2", 100, 10),
                                                         new Product(20002, "prod3", 103, 23));

    private static final List<Purchase> samplePurchases = new ArrayList<>();

    static {
        Purchase purchase1 = new Purchase();
        purchase1.setBuyer(new User("user001"));
        purchase1.setPaymentOption(PaymentOption.CASH);
        purchase1.setReceiverName("Receiver1");
        purchase1.setReceiverPhone("010-1111-1111");
        purchase1.setDivyAddr("Seoul");
        purchase1.setDivyRequest("No request");
        purchase1.setTranStatusCode(TranStatusCode.PURCHASE_DONE);
        purchase1.setOrderDate(new Date(2023, 3, 17));
        purchase1.setDivyDate(new Date(2025, 3, 15));
        purchase1.setTransactionProductions(Arrays.asList(new TransactionProduction(new Product(20000), 10),
                                                          new TransactionProduction(new Product(20001), 15)));
        samplePurchases.add(purchase1);

        Purchase purchase2 = new Purchase();
        purchase2.setBuyer(new User("user001"));
        purchase2.setPaymentOption(PaymentOption.CASH);
        purchase2.setReceiverName("Receiver2");
        purchase2.setReceiverPhone("010-1131-1111");
        purchase2.setDivyAddr("Tokyo");
        purchase2.setDivyRequest("No request");
        purchase2.setTranStatusCode(TranStatusCode.PURCHASE_DONE);
        purchase2.setOrderDate(new Date(2023, 3, 17));
        purchase2.setDivyDate(new Date(2025, 3, 15));
        purchase2.setTransactionProductions(Arrays.asList(new TransactionProduction(new Product(20000), 10),
                                                          new TransactionProduction(new Product(20001), 15)));
        samplePurchases.add(purchase2);
    }

    @Test
    public void insertPurchase() {
        Purchase purchase = samplePurchases.get(0);
        this.purchaseDAO.insertPurchase(purchase);
    }

    @Test
    public void findById() {
        Purchase purchase = samplePurchases.get(0);
        this.purchaseDAO.insertPurchase(purchase);

        Optional<Purchase> foundOptional = this.purchaseDAO.findById(purchase.getTranNo());
        Purchase found = foundOptional.orElseThrow(RuntimeException::new);
        System.out.println(found);
        assertThat(found).isEqualTo(purchase);
        assertThat(found.getTransactionProductions().size()).isEqualTo(2);
    }

    @Test
    public void findAllInPageSize() {
        samplePurchases.forEach(this.purchaseDAO::insertPurchase);
        ListData<Purchase> found = this.purchaseDAO.findAllInPageSize(1, 3);
        assertThat(found.getCount()).isEqualTo(2);
    }

    @Test
    public void findAllInPageSize_NoResult() {
        samplePurchases.forEach(this.purchaseDAO::insertPurchase);
        ListData<Purchase> found = this.purchaseDAO.findAllInPageSize(2, 3);
        assertThat(found.getCount()).isEqualTo(0);
        assertThat(found.getList()).isEmpty();
    }
}