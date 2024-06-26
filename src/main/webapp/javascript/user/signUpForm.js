function validateId() {
    const idForm = $($("#id-input")[0]);
    console.log(idForm);
    if (idForm.val().length < 1) {
        alert("아이디는 반드시 입력해야 합니다.");
        idForm.trigger("focus");
        return false;
    }
    return true;
}

function validatePw() {
    const pwForm = $($("#pw-input")[0]);
    const pwCheckForm = $($("#pw-check-input")[0]);

    console.log(pwForm.val());
    console.log(pwCheckForm.val().length);

    if (pwForm.val().length === 0 || pwCheckForm.val().length === 0) {
        alert("비밀번호와 비밀번호 재입력은 반드시 입력하셔야 합니다.");
        return false;
    }
    if (pwForm.val() !== pwCheckForm.val()) {
        alert("재입력된 비밀번호가 일치하지 않습니다.");
        pwCheckForm.trigger("focus");
        return false;
    }
    return true;
}

function validateFormData() {
    const result = validateId() && validatePw();
    console.log(result);
    return result;
}

$(() => {
    const contextPath = $("body:first").data("context-path");

    const submitButton = $(".btn--submit");
    submitButton.on("click", () => {
        if (validateFormData()) {
            const form = $($("form[name=sign-up]")[0]);
            const phoneNumber = $("select[name=phone1] option:checked").val() + $("input[name=phone2]").val();
            $("input[name=phone]").val(phoneNumber);
            form.attr("method", "POST").attr("action", `${contextPath}/users/new`).trigger("submit");
        }
    });

    $(".btn--reset").on("click", () => {
        $("form[name=sign-up]").reset();
    });

    $(".btn--check-duplicate").on("click", () => {
        const popWin = window.open(`${contextPath}/users/check-duplicate`, "popWin", "left=300,top=200,width=300,height=200,marginwidth=0,marginheight=0," +
            "scrollbars=no,scrolling=no,menubar=no,resizable=no");
    });

    $("#email-authentication").on("click", e => {
        console.log("here");
        const email = $("#email").val();
        $.ajax(`${contextPath}/users/account/email-auth`, {
            method: "POST",
            data: {
                emailAddress: email
            },
            success: data => {
                $("#email-form").after("<div class='alert alert-info'>인증 메일이 전송됐습니다.</div>");
            }
        });
    });
});