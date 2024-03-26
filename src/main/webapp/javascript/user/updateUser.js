const roleObj = {
    "user": "유저",
    "seller": "판매자"
};

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

    $(".front-phone-number").on("click", (e) => {
        const numberValue = $(e.target).data("number");
        const phoneDropdown = $("#phone-dropdown");
        phoneDropdown.children().remove();
        phoneDropdown.html(`${numberValue} <span class="caret"></span>`);
        $("input[name=phone1]").val(numberValue);
    });

    $(".role-select").on("click", e => {
        const role = $(e.target).data("role");
        console.log(role);
        const roleDropdown = $("#role-dropdown");
        roleDropdown.children().remove();
        roleDropdown.html(`${roleObj[role]} <span class="caret"></span>`);
        $("input[name=role]").val(role);
    });
});