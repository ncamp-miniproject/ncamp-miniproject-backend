package com.model2.mvc.product.service;

import com.model2.mvc.common.CommonConstants;
import com.model2.mvc.common.ListData;
import com.model2.mvc.common.dto.Page;
import com.model2.mvc.common.util.ListPageUtil;
import com.model2.mvc.product.dao.ProductDAO;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.AddProductRequestDTO;
import com.model2.mvc.product.dto.request.ListProductRequestDTO;
import com.model2.mvc.product.dto.request.UpdateProductRequestDTO;
import com.model2.mvc.product.dto.response.AddProductResponseDTO;
import com.model2.mvc.product.dto.response.GetProductResponseDTO;
import com.model2.mvc.product.dto.response.ListProductResponseDTO;
import com.model2.mvc.product.dto.response.UpdateProductResponseDTO;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    private ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public AddProductResponseDTO addProduct(AddProductRequestDTO toInsert) {
//        Product product = new Product().builder()
//                .fileName(toInsert.getFileName())
//                .manuDate(StringUtil.parseDate(toInsert.getManuDate(), "-"))
//                .price(toInsert.getPrice())
//                .prodDetail(toInsert.getProdDetail())
//                .prodName(toInsert.getProdName())
//                .regDate(new Date(System.currentTimeMillis()))
//                .stock(toInsert.getStock())
//                .build();
//        this.productDAO.insertProduct(product);
//        return AddProductResponseDTO.from(product);
        throw new UnsupportedOperationException();
    }

    private Date parseDate(String dateStr) {
        int[] each = Arrays.stream(dateStr.split("-")).mapToInt(Integer::parseInt).toArray();
        return new Date(each[0], each[1], each[2]);
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
        ListData<Product> resultMap = productDAO.findProductsByProdName(requestDTO.getSearch());


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

        return ListProductResponseDTO.from(resultMap, pageInfo, requestDTO);
    }

    @Override
    public UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO requestDTO) {

//        Product previous = this.productDAO.findById(requestDTO.getProdNo())
//                .orElseThrow(() -> new IllegalArgumentException("No such record for given prodNo: " +
//                                                                requestDTO.getProdNo()));
//        Product to = new Product().builder()
//                .prodNo(requestDTO.getProdNo())
//                .fileName(requestDTO.getFileName())
//                .manuDate(StringUtil.parseDate(requestDTO.getManuDate(), "-"))
//                .price(requestDTO.getPrice())
//                .prodDetail(requestDTO.getProdDetail())
//                .prodName(requestDTO.getProdName())
//                .regDate(new Date(System.currentTimeMillis()))
//                .stock(requestDTO.getStock())
//                .build();
//        this.productDAO.updateProduct(to);
//        return UpdateProductResponseDTO.from(previous);
        throw new UnsupportedOperationException();
    }
}
