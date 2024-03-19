$(() => {
    const contextPath = $("body:first").data("context-path");
    const updateBtn = $(".btn--update:first");
    console.log(updateBtn);
    const userId = updateBtn.data("user-id");
    updateBtn.on("click", () => {
        self.location = `${contextPath}/users/${userId}/update-form`;
    });

    $(".btn--back").on("click", () => {
        history.go(-1);
    });
});