$(() => {
    const contextPath = $("body:first").data("context-path");

    const userIdElements = $(".list .user_id");
    userIdElements.each((idx, e) => {
        const userId = $(e).data("user-id");
        $(e).attr("href", `${contextPath}/users/${userId}`);
    });

    const currentPage = $(".page-nav:last").data("current-page");
    const pageElements = $(".page-nav-item");
    pageElements.each((idx, obj) => {
        const page = obj.data("page");
        if (page !== currentPage) {
            obj.attr("href", `${contextPath}/users?page=${page}`);
        }
    });
});