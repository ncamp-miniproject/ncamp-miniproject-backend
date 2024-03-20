$(() => {
    const contextPath = $("body").data("context-path");

    $(".btn--update").on("click", () => {
        $("form[name=update-form]")
            .attr("method", "POST")
            .attr("action", `${contextPath}/users/update`)
            .trigger("submit");
    });

    $(".btn--cancel").on("click", () => {
        history.go(-1);
    });
});