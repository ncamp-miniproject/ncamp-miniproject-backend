package com.model2.mvc.common.propertyeditor;

import com.model2.mvc.common.SearchCondition;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

public class SearchConditionEditor extends PropertyEditorSupport {
    private static final SearchConditionEditor singleton = new SearchConditionEditor();

    private SearchConditionEditor() {
    }

    public static SearchConditionEditor getInstance() {
        return singleton;
    }

    @Override
    public String getAsText() {
        SearchCondition searchCondition = (SearchCondition)super.getValue();
        return searchCondition.getConditionCode();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Optional<SearchCondition> searchCondition = SearchCondition.findConditionCode(text);
        super.setValue(searchCondition.orElse(SearchCondition.NO_CONDITION));
    }
}
