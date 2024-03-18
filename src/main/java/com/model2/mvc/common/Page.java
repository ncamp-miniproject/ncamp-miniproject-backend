package com.model2.mvc.common;

import com.model2.mvc.common.util.ListPageUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Page {
    private boolean previousPageSetAvailable;
    private boolean nextPageSetAvailable;
    private int previousPageSetEntry;
    private int nextPageSetEntry;
    private List<Integer> pagesToDisplay;
    private int currentPage;
    private int pageSize;

    private Page(boolean previousPageSetBtnVisible,
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

    public static Page of(int currentPage, int dataCount, int pageSize, int pageDisplay) {
        List<Integer> pageToDisplay = ListPageUtil.getPageSet(dataCount, currentPage, pageSize, pageDisplay);
        boolean previousPageSetBtnVisible = ListPageUtil.isPreviousPageSetAvailable(dataCount,
                                                                                    currentPage,
                                                                                    pageSize,
                                                                                    pageDisplay);
        boolean nextPageSetBtnVisible = ListPageUtil.isNextPageSetAvailable(dataCount,
                                                                            currentPage,
                                                                            pageSize,
                                                                            pageDisplay);

        return new Page(previousPageSetBtnVisible,
                        nextPageSetBtnVisible,
                        ListPageUtil.getPreviousPageSetEntry(currentPage, pageDisplay),
                        ListPageUtil.getNextPageSetEntry(currentPage, pageDisplay),
                        pageToDisplay,
                        currentPage,
                        pageSize);
    }
}
