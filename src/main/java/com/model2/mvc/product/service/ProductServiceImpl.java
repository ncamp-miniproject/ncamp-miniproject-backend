package com.model2.mvc.product.service;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.ListData;
import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.ListPageUtil;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.AddProductRequestDTO;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;
import com.model2.mvc.product.dto.request.UpdateProductRequestDTO;
import com.model2.mvc.product.dto.response.AddProductResponseDTO;
import com.model2.mvc.product.dto.response.GetProductResponseDTO;
import com.model2.mvc.product.dto.response.ListProductResponseDTO;
import com.model2.mvc.product.dto.response.UpdateProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public AddProductResponseDTO addProduct(AddProductRequestDTO toInsert) {
        Product product = new Product();
        product.setFileName(toInsert.getFileName());
        product.setManuDate(StringUtil.parseDate(toInsert.getManuDate(), "-"));
        product.setPrice(toInsert.getPrice());
        product.setProdDetail(toInsert.getProdDetail());
        product.setProdName(toInsert.getProdName());
        product.setRegDate(new Date(System.currentTimeMillis()));
        product.setStock(toInsert.getStock());
        this.productDAO.insertProduct(product);
        return AddProductResponseDTO.from(product);
    }

    @Override
    public GetProductResponseDTO getProduct(int prodNo) {
        Optional<Product> result = productDAO.findById(prodNo);

        result.ifPresent(p -> {
            System.out.println("-- ProductServiceImpl.getProduct() --");
            System.out.println(p + "\n");
        });
        return GetProductResponseDTO.from(result.orElseThrow(() -> new IllegalArgumentException(
                "No record for the given prodNo: " + prodNo)));
    }

    @Override
    public ListProductResponseDTO getProductList(ListProductRequestDTO requestDTO) {
        Search search = new Search();
        int page = requestDTO.getPage();
        int pageSize = requestDTO.getPageSize();
        search.setStartRowNum((page - 1) * pageSize + 1);
        search.setEndRowNum(page * pageSize);
        search.setSearchKeyword(requestDTO.getSearchKeyword());
        search.setSearchCondition(requestDTO.getSearchCondition());
        ListData<Product> resultMap = productDAO.findProductsByProdName(search);


        int currentPage = requestDTO.getPage();
        List<Integer> pageToDisplay = ListPageUtil.getPageSet(resultMap.getCount(),
                                                              currentPage,
                                                              CommonConstants.PAGE_SIZE,
                                                              CommonConstants.PAGE_DISPLAY);
        boolean previousPageSetBtnVisible = ListPageUtil.isPreviousPageSetAvailable(resultMap.getCount(),
                                                                                    currentPage,
                                                                                    CommonConstants.PAGE_SIZE,
                                                                                    CommonConstants.PAGE_DISPLAY);
        boolean nextPageSetBtnVisible = ListPageUtil.isNextPageSetAvailable(resultMap.getCount(),
                                                                            currentPage,
                                                                            CommonConstants.PAGE_SIZE,
                                                                            CommonConstants.PAGE_DISPLAY);

        Page pageInfo = new Page(previousPageSetBtnVisible,
                                 nextPageSetBtnVisible,
                                 ListPageUtil.getPreviousPageSetEntry(currentPage, CommonConstants.PAGE_DISPLAY),
                                 ListPageUtil.getNextPageSetEntry(currentPage, CommonConstants.PAGE_DISPLAY),
                                 pageToDisplay,
                                 currentPage,
                                 CommonConstants.PAGE_SIZE);

        return ListProductResponseDTO.from(resultMap, pageInfo, requestDTO, search);
    }

    @Override
    public UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO requestDTO) {
        Product previous = this.productDAO.findById(requestDTO.getProdNo())
                .orElseThrow(() -> new IllegalArgumentException("No such record for given prodNo:" + requestDTO.getProdNo()));
        Product to = new Product();
        to.setProdNo(requestDTO.getProdNo());
        to.setFileName(requestDTO.getFileName());
        to.setManuDate(StringUtil.parseDate(requestDTO.getManuDate(), "-"));
        to.setPrice(requestDTO.getPrice());
        to.setProdDetail(requestDTO.getProdDetail());
        to.setProdName(requestDTO.getProdName());
        to.setRegDate(new Date(System.currentTimeMillis()));
        to.setStock(requestDTO.getStock());
        this.productDAO.updateProduct(to);
        return UpdateProductResponseDTO.from(previous);
    }
}
