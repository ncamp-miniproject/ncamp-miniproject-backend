$(() => {
    const container = $("#page-container");
    const additionalQueryString = container.data("additional-query-string");
    const url = container.data("url");
    const currentPage = parseInt(container.data("current-page"));

    const navPrevious = $("#nav-previous");
    const previousPageSetAvailable = parseInt(navPrevious.data("previous-page-set-available")) === 1;
    const previousPageSetEntry = navPrevious.data("previous-page-set-entry");
    if (previousPageSetAvailable) {
        navPrevious.children("a").attr("href", `${url}?page=${previousPageSetEntry}&${additionalQueryString}`);
    } else {
        navPrevious.addClass("disabled");
    }

    const navNext = $("#nav-next");
    const nextPageSetAvailable = parseInt(navNext.data("next-page-set-available")) === 1;
    const nextPageSetEntry = navNext.data("next-page-set-entry");
    if (nextPageSetAvailable) {
        navNext.children("a").attr("href", `${url}?page=${nextPageSetEntry}&${additionalQueryString}`);
    } else {
        navNext.addClass("disabled");
    }

    $(".page-number").each((idx, elem) => {
        const je = $(elem);
        const page = parseInt(je.data("page"));
        if (page === currentPage) {
            je.addClass("active");
        } else {
            je.children("a").attr("href", `${url}?page=${page}&${additionalQueryString}`);
        }
    });
});