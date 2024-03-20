$(() => {
    const idForm = $("input[name=userId]");
    const pwForm = $("input[name=password]");

    idForm.trigger("focus");

    const isIdAndPwEmpty = () => {
        return !idForm.val() || !pwForm.val();
    }

    const signInBtn = $(".btn--sign-in");
    signInBtn.attr("disabled", "true");
    const formChangeHandler = () => {
        if (isIdAndPwEmpty()) {
            signInBtn.attr("disabled", "true")
        } else {
            signInBtn.removeAttr("disabled");
        }
    }

    idForm.on("change", formChangeHandler);
    pwForm.on("change", formChangeHandler);

    const contextPath = $("body").data("context-path");

    const submitForm = () => {
        $("form[name=login-form]")
            .attr("action", `${contextPath}/users/account/sign-in`)
            .attr("method", "POST")
            .submit();
    };

    signInBtn.on("click", () => {
        submitForm();
    });

    $("form[name=login-form]").on("keydown", (event) => {
        if (event.which === 13) {
            submitForm();
        }
    });

    $(".btn--sign-up").on("click", () => {
        self.location = `${contextPath}/users/account/email-auth`;
    });
});