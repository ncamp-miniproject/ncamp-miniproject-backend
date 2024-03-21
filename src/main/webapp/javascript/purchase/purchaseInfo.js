$(() => {
    const contextPath = $("body").data("context-path");

    $(".prod-item").each((idx, element) => {
        const jElem = $(element);
        const prodNo = jElem.data("prod-no");

        $(".prod-no", jElem)
            .attr("href", `${contextPath}/products/${prodNo}?menu=search`);
    });

    $(".btn--update").on("click", (e) => {
        const tranNo = $(e.target).data("tran-no");
        self.location = `${contextPath}/purchases/${tranNo}/update-form`;
    });

    $(".btn--cancel").on("click", () => {
        history.go(-1);
    });
});