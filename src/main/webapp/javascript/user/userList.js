$(() => {
    const contextPath = $("main:first").data("context-path");

    $(".user-id").each((idx, te) => {
        const userId = $(te).data("user-id");
        $(te).attr("href", `${contextPath}/users/${userId}`);
    });

    const currentPage = $(".page-nav:last").data("current-page");
    $(".page-nav-item").each((idx, te) => {
        const page = $(te).data("page");
        if (page !== currentPage) {
            $(te).attr("href", `${contextPath}/users?page=${page}`);
        }
    });
});