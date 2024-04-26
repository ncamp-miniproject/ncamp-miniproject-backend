package com.model2.mvc.product.service.helper;

import com.model2.mvc.common.SearchCondition;
import com.model2.mvc.common.util.IntegerUtil;
import com.model2.mvc.common.util.OptionalHashMap;
import com.model2.mvc.common.util.StringUtil;
import com.model2.mvc.product.domain.Product;
import com.model2.mvc.product.dto.request.ListProductRequestDto;
import com.model2.mvc.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Component
public class ListQueryHelperImpl implements ListQueryHelper {
    private static final OptionalHashMap<SearchCondition, BiFunction<ProductRepository, ListProductRequestDto, List<Product>>>
            listTaskMapper = new OptionalHashMap<>();
    private static final OptionalHashMap<SearchCondition, BiFunction<ProductRepository, ListProductRequestDto, Integer>>
            countTaskMapper = new OptionalHashMap<>();

    static {
        listTaskMapper.put(SearchCondition.NO_CONDITION,
                           (repository, dto) -> repository.findAllInCategory(IntegerUtil.getOneIfNull(dto.getPage()),
                                                                             IntegerUtil.getOneIfNull(dto.getPageSize()),
                                                                             dto.getCategoryNo(),
                                                                             dto.getOrderBy(),
                                                                             dto.getAscend(),
                                                                             dto.getSeller()));
        listTaskMapper.put(SearchCondition.BY_NAME,
                           (repository, dto) -> repository.findListByProdName(StringUtil.null2nullStr(dto.getSearchKeyword()),
                                                                              false,
                                                                              IntegerUtil.getOneIfNull(dto.getPage()),
                                                                              IntegerUtil.getOneIfNull(dto.getPageSize()),
                                                                              dto.getCategoryNo(),
                                                                              dto.getOrderBy(),
                                                                              dto.getAscend(),
                                                                              dto.getSeller()));
        listTaskMapper.put(SearchCondition.BY_INTEGER_RANGE, (repository, dto) -> {
            List<Integer> boundPair = generateBoundPair(dto.getSearchKeyword());
            return repository.findListByPriceRange(boundPair.get(0),
                                                   boundPair.get(1),
                                                   IntegerUtil.getOneIfNull(dto.getPage()),
                                                   IntegerUtil.getOneIfNull(dto.getPageSize()),
                                                   dto.getCategoryNo(),
                                                   dto.getOrderBy(),
                                                   dto.getAscend(),
                                                   dto.getSeller());
        });

        countTaskMapper.put(SearchCondition.NO_CONDITION,
                            (repository, dto) -> repository.countAll(dto.getCategoryNo(), dto.getSeller()));

        countTaskMapper.put(SearchCondition.BY_NAME,
                            (repository, dto) -> repository.countByProdName(StringUtil.null2nullStr(dto.getSearchKeyword()),
                                                                            false,
                                                                            dto.getCategoryNo(),
                                                                            dto.getSeller()));

        countTaskMapper.put(SearchCondition.BY_INTEGER_RANGE, (repository, dto) -> {
            List<Integer> boundPair = generateBoundPair(dto.getSearchKeyword());
            return repository.countByPriceRange(boundPair.get(0),
                                                boundPair.get(1),
                                                dto.getCategoryNo(),
                                                dto.getSeller());
        });
    }

    private static List<Integer> generateBoundPair(String boundStr) {
        List<Integer> pair = Arrays.stream(boundStr.split("-")).map(Integer::parseInt).collect(Collectors.toList());
        if (pair.size() != 2) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        } else {
            return pair;
        }
    }

    @Override
    public List<Product> findProductList(ProductRepository repository, ListProductRequestDto dto) {
        Optional<BiFunction<ProductRepository, ListProductRequestDto, List<Product>>> funcOptional
                = listTaskMapper.getOptional(dto.getSearchCondition());
        BiFunction<ProductRepository, ListProductRequestDto, List<Product>> func
                = funcOptional.orElse(listTaskMapper.get(SearchCondition.NO_CONDITION));
        return func.apply(repository, dto);
    }

    @Override
    public int count(ProductRepository repository, ListProductRequestDto dto) {
        Optional<BiFunction<ProductRepository, ListProductRequestDto, Integer>> funcOptional
                = countTaskMapper.getOptional(dto.getSearchCondition());
        BiFunction<ProductRepository, ListProductRequestDto, Integer> func = funcOptional.orElse(countTaskMapper.get(
                SearchCondition.NO_CONDITION));
        return func.apply(repository, dto);
    }
}
