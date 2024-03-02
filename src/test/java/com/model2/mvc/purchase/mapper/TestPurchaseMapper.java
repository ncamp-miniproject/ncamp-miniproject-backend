package com.model2.mvc.purchase.mapper;

import static org.assertj.core.api.Assertions.*;

import com.model2.mvc.common.ListData;
import com.model2.mvc.common.MapperWithoutSpringInitializer;
import com.model2.mvc.common.dto.Search;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.purchase.domain.PaymentOption;
import com.model2.mvc.purchase.domain.Purchase;
import com.model2.mvc.purchase.domain.TranStatusCode;
import com.model2.mvc.purchase.domain.TransactionProduction;
import com.model2.mvc.user.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class TestPurchaseMapper {
    private static final Logger log = LoggerFactory.getLogger(TestPurchaseMapper.class);
    private SqlSession sqlSession;
    private List<User> sampleUsers = Arrays.asList(new User("user001", "name1", "1q2w3e4r"),
                                                   new User("user002", "name2", "2w3e4r5t"),
                                                   new User("user003", "name3", "asdf1234"));
    private List<Product> sampleProducts = Arrays.asList(new Product(20000, "prod1", 100, 10),
                                                         new Product(20001, "prod2", 100, 10),
                                                         new Product(20002, "prod3", 103, 23));

    @Before
    public void init() {
        this.sqlSession = MapperWithoutSpringInitializer.initUnitTest("PurchaseMapper.clearTranProd");
        this.sqlSession.delete("PurchaseMapper.clearTransaction");
        this.sqlSession.delete("ProductMapper.clear");
        this.sqlSession.delete("UserMapper.clear");
        sampleUsers.forEach(u -> this.sqlSession.insert("UserMapper.insert", u));
        sampleProducts.forEach(p -> this.sqlSession.insert("ProductMapper.insert", p));
    }

    @After
    public void destroy() {
        this.sqlSession.delete("PurchaseMapper.clearTranProd");
        this.sqlSession.delete("PurchaseMapper.clearTransaction");
        this.sqlSession.delete("ProductMapper.clear");
        MapperWithoutSpringInitializer.afterUnitTest(this.sqlSession, "UserMapper.clear");
    }

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
        samplePurchases.add(purchase2);
    }

    @Test
    public void insertTransaction() {
        Purchase purchase = samplePurchases.get(0);
        this.sqlSession.insert("PurchaseMapper.insertTransaction", purchase);
        int tranNo = purchase.getTranNo();
        List<TransactionProduction> tranProds = getSampleTranProd(tranNo);
        tranProds.forEach(tp -> this.sqlSession.insert("PurchaseMapper.insertTranProd", tp));
    }

    private List<TransactionProduction> getSampleTranProd(int tranNo) {
        return Arrays.asList(new TransactionProduction(tranNo, new Product(20000), 10),
                             new TransactionProduction(tranNo, new Product(20001), 15));
    }

    @Test
    public void findById() {
        Purchase testPurchase = samplePurchases.get(0);
        this.sqlSession.insert("PurchaseMapper.insertTransaction", testPurchase);
        int tranNo = testPurchase.getTranNo();
        List<TransactionProduction> tranProds = getSampleTranProd(tranNo);
        tranProds.forEach(tp -> this.sqlSession.insert("PurchaseMapper.insertTranProd", tp));

        Purchase found = this.sqlSession.selectOne("PurchaseMapper.findById", tranNo);
        log.debug("found: {}", found);
        assertThat(found.getTranNo()).isEqualTo(testPurchase.getTranNo());
        assertThat(found.getTranStatusCode()).isEqualTo(testPurchase.getTranStatusCode());
        assertThat(found.getPaymentOption()).isEqualTo(testPurchase.getPaymentOption());
        assertThat(found.getBuyer()).isEqualTo(testPurchase.getBuyer());
        assertThat(found.getTransactionProductions().size()).isEqualTo(2);
    }

    @Test
    public void findPurchases() {
        samplePurchases.forEach(p -> {
            this.sqlSession.insert("PurchaseMapper.insertTransaction", p);
            int tranNo = p.getTranNo();
            List<TransactionProduction> tranProds = getSampleTranProd(tranNo);
            tranProds.forEach(tp -> this.sqlSession.insert("PurchaseMapper.insertTranProd", tp));
        });

        Search search = new Search();
        search.setPage(1);
        search.setPageUnit(3);
        ListData<Purchase> result = this.sqlSession.selectOne("PurchaseMapper.findList", search);

        assertThat(result.getCount()).isEqualTo(2);
        assertThat(result.getList().size()).isEqualTo(2);

        Search search2 = new Search();
        search2.setPage(1);
        search2.setPageUnit(3);
        search2.setSearchCondition("0");
        search2.setSearchKeyword("user001");
        ListData<Purchase> result2 = this.sqlSession.selectOne("PurchaseMapper.findList", search2);

        assertThat(result2.getCount()).isEqualTo(2);
        assertThat(result2.getList().size()).isEqualTo(2);
    }

    @Test
    public void update() {
        Purchase purchase = samplePurchases.get(0);
        this.sqlSession.insert("PurchaseMapper.insertTransaction", purchase);

        Purchase forBuyer = new Purchase(purchase.getTranNo());
        forBuyer.setBuyer(new User("user002"));
        singleUpdateTest(forBuyer, Purchase::getBuyer);

        Purchase forPaymentOption = new Purchase(purchase.getTranNo());
        forPaymentOption.setPaymentOption(PaymentOption.CREDIT_CARD);
        singleUpdateTest(forPaymentOption, Purchase::getPaymentOption);

        Purchase forReceiverName = new Purchase(purchase.getTranNo());
        forReceiverName.setReceiverName("New Receiver Name");
        singleUpdateTest(forReceiverName, Purchase::getReceiverName);
    }

    private <E> void singleUpdateTest(Purchase to, Function<Purchase, E> propertyExtractor) {
        Purchase purchase = samplePurchases.get(0);
        purchase.setDivyRequest("New Request");
        this.sqlSession.update("PurchaseMapper.update", to);
        int tranNo = purchase.getTranNo();
        Purchase updated = this.sqlSession.selectOne("PurchaseMapper.findById", tranNo);

        log.debug("updated: {}", updated);

        E updatedProperty = propertyExtractor.apply(updated);
        E toProperty = propertyExtractor.apply(to);
        E prototypeProperty = propertyExtractor.apply(purchase);

        assertThat(updatedProperty).isNotEqualTo(prototypeProperty);
        assertThat(updatedProperty).isEqualTo(toProperty);
    }
}