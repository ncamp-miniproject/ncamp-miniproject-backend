$(() => {
    const contextPath = $("body").data("context-path");

    $(".btn--submit").on("click", () => {
        $("form[name=purchase]")
            .attr("action", `${contextPath}/purchases/new`)
            .attr("method", "POST")
            .trigger("submit");
    });
});