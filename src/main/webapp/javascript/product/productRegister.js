function validateForm() {
    const prodNameForm = $("input[name=prodName]");
    const priceForm = $("input[name=price]");
    const stockForm = $("input[name=stock]");

    if (!prodNameForm.val()) {
        alert("상품명은 반드시 입력하여야 합니다.");
        return false;
    }
    if (!priceForm.val()) {
        alert("가격은 반드시 입력하여야 합니다.");
        return false;
    }
    if (!stockForm.val()) {
        alert("재고는 반드시 입력하여야 합니다.");
        return false;
    }
    return true;
}

$(() => {
    const contextPath = $("body").data("context-path");

    $(".btn--submit").on("click", () => {
        if (validateForm()) {
            $("form[name=product-register-form]")
                .attr("action", `${contextPath}/products`)
                .attr("method", "POST")
                .trigger("submit");
        }
    });

    $(".btn--cancel").on("click", () => {
        history.go(-1);
    });

    const manuDateForm = $("#manu-date");
    manuDateForm.datepicker();
    manuDateForm
        .datepicker("option", "dateFormat", "yy-mm-dd");

    $("#image-upload-window-button").on("click", () => {
        console.log("here");
        window.open(`${contextPath}/view/fragment/image-upload.jsp`, "_blank", "width=500,height=500");
    });
});