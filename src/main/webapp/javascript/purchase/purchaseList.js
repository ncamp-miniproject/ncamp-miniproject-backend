$(() => {
    const contextPath = $("body").data("context-path");
    $(".data-row").each((idx, element) => {
        const jElem = $(element);
        const tranNo = jElem.data("tran-no");
        const buyerId = jElem.data("buyer-id");

        $(".tran-no", jElem)
            .attr("href", `${contextPath}/purchases/${tranNo}`);

        $(".buyer-id", jElem)
            .attr("href", `${contextPath}/users/${buyerId}`);
    });
});