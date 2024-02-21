package com.model2.mvc.service.product.impl;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.ListPageUtil;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.domain.Product;
import com.model2.mvc.service.purchase.TranCode;

public class ProductServiceImpl implements ProductService {
    private static final ProductService instance = new ProductServiceImpl();

    private ProductDAO productDAO;

    private ProductServiceImpl() {
        this.productDAO = ProductDAO.getInstance();
    }

    public static ProductService getInstance() {
        return instance;
    }

    @Override
    public Product addProduct(Product toInsert) {
        toInsert.setManuDate(toInsert.getManuDate().replace("-", ""));
        validateDataForUpdateProduct(toInsert);
        this.productDAO.insertProduct(toInsert);
        return toInsert;
    }

    private void validateDataForUpdateProduct(Product data) {
        if (!data.getManuDate().matches("[0-9]{8}")
                || data.getProdName() == null || data.getProdName().isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Product getProduct(int prodNo) {
        Product result = productDAO.findProduct(prodNo);
        if (result == null) {
            throw new IllegalArgumentException("No record for the given prodNo: " +
                                               prodNo);
        }

        result.setProTranStatus(TranCode
                .getTranStatus(result.getProTranCode()));

        System.out.println("-- ProductServiceImpl.getProduct() --");
        System.out.println(result + "\n");
        return result;
    }

    @Override
    public Map<String, Object> getProductList(Search searchVO) {
        Map<String, Object> resultMap = productDAO.getProductList(searchVO);

        ((List<Product>)resultMap.get("productList")).forEach(p -> p
                .setProTranStatus(TranCode.getTranStatus(p.getProTranCode())));

        int currentPage = searchVO.getPage();
        List<Integer> pageToDisplay = ListPageUtil
                .getPageSet((int)resultMap.get("count"),
                            currentPage,
                            CommonConstants.PAGE_SIZE,
                            CommonConstants.PAGE_DISPLAY);
        boolean previousPageSetBtnVisible = ListPageUtil
                .isPreviousPageSetAvailable((int)resultMap.get("count"),
                                            currentPage,
                                            CommonConstants.PAGE_SIZE,
                                            CommonConstants.PAGE_DISPLAY);
        boolean nextPageSetBtnVisible = ListPageUtil
                .isNextPageSetAvailable((int)resultMap.get("count"),
                                        currentPage,
                                        CommonConstants.PAGE_SIZE,
                                        CommonConstants.PAGE_DISPLAY);
        resultMap
                .put("page",
                     new Page(previousPageSetBtnVisible,
                              nextPageSetBtnVisible,
                              ListPageUtil
                                      .getPreviousPageSetEntry(currentPage,
                                                               CommonConstants.PAGE_DISPLAY),
                              ListPageUtil
                                      .getNextPageSetEntry(currentPage,
                                                           CommonConstants.PAGE_DISPLAY),
                              pageToDisplay,
                              currentPage,
                              CommonConstants.PAGE_SIZE));

        System.out.println("-- ProductServiceImpl.getProductList() --");
        System.out.println(resultMap + "\n");
        return resultMap;
    }

    @Override
    public Product updateProduct(Product to) {
        Product previous = this.productDAO.findProduct(to.getProdNo());
        if (previous == null) {
            throw new IllegalArgumentException("No such record for given prodNo: " +
                                               to.getProdNo());
        }
        to.setManuDate(to.getManuDate().replace("-", ""));
        validateDataForUpdateProduct(to);
        this.productDAO.updateProduct(to);
        return previous;
    }
}
