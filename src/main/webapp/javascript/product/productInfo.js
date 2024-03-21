$(() => {
    const contextPath = $("body").data("context-path");

    $("#loadOnCart").on("click", () => {
        $("form[name=cart]")
            .attr("method", "POST")
            .attr("action", `${contextPath}/cart/items/new`)
            .trigger("submit");
    });

    $("#quantityInput").on("change", e => {
        if ($(e.target).val() <= 0) {

        }
    });

    $(".btn--back").on("click", () => {
        history.go(-1);
    });
});