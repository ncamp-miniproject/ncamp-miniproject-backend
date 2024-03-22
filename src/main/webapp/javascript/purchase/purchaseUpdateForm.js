$(() => {
    const contextPath = $("body").data("context-path");

    $(".btn--submit").on("click", () => {
        $("form[name=purchase-update]")
            .attr("method", "POST")
            .attr("action", `${contextPath}/purchases/update`)
            .trigger("submit");
    });

    const divyDate = $("#divy-date")
    divyDate.datepicker();
    divyDate.datepicker("option", "dateFormat", "yy-mm-dd");
});