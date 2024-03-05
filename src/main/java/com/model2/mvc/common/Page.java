package com.model2.mvc.common;

import java.util.Collections;
import java.util.List;

public class Page {
    private final boolean previousPageSetAvailable;
    private final boolean nextPageSetAvailable;
    private final int previousPageSetEntry;
    private final int nextPageSetEntry;
    private final List<Integer> pagesToDisplay;
    private final int currentPage;
    private final int pageSize;

    public Page(boolean previousPageSetBtnVisible,
                boolean nextPageSetBtnVisible,
                int previousPageSetEntry,
                int nextPageSetEntry,
                List<Integer> pagesToDisplay,
                int currentPage,
                int pageSize) {
        this.previousPageSetAvailable = previousPageSetBtnVisible;
        this.nextPageSetAvailable = nextPageSetBtnVisible;
        this.previousPageSetEntry = previousPageSetEntry;
        this.nextPageSetEntry = nextPageSetEntry;
        this.pagesToDisplay = pagesToDisplay;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public boolean isPreviousPageSetAvailable() {
        return this.previousPageSetAvailable;
    }

    public boolean isNextPageSetAvailable() {
        return this.nextPageSetAvailable;
    }

    public int getPreviousPageSetEntry() {
        return previousPageSetEntry;
    }

    public int getNextPageSetEntry() {
        return nextPageSetEntry;
    }

    public List<Integer> getPagesToDisplay() {
        return Collections.unmodifiableList(this.pagesToDisplay);
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }
}
