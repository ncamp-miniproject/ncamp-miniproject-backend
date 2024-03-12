package com.model2.mvc.product.controller.propertyeditor;

import com.model2.mvc.common.SearchCondition;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class SearchConditionEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        SearchCondition searchCondition = (SearchCondition)super.getValue();
        return searchCondition.getConditionCode();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Optional<SearchCondition> searchCondition = SearchCondition.getSearchCondition(text.trim());
        super.setValue(searchCondition.orElse(null));
    }
}
