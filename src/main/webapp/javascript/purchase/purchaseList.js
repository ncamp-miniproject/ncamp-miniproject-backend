const buttonText = new Map();
buttonText.set("1", "배송하기");
buttonText.set("2", "물건도착");

function getTranCodeUpdateElement(tranNo, tranCode) {
    return `
        <form name="tran-code">
            <input type="hidden" name="tranNo" value="${tranNo}">
            <input type="hidden" name="tranCode" value="${parseInt(tranCode) + 1}">
            <button type="button">${buttonText[tranCode]}</button>
        </form>
    `;
}

$(() => {
    const contextPath = $("body").data("context-path");
    const role = $(".purchase-table").data("login-user-role");

    $(".data-row").each((idx, element) => {
        const jElem = $(element);
        const tranNo = jElem.data("tran-no");
        const buyerId = jElem.data("buyer-id");

        $(".tran-no", jElem)
            .attr("href", `${contextPath}/purchases/${tranNo}`);

        $(".buyer-id", jElem)
            .attr("href", `${contextPath}/users/${buyerId}`);
    });

    $("form[name=tran-code] button").on("click", e => {
        const form = $(e.target).parent("form[name=tran-code]");
        const tranNo = parseInt($("input[name=tranNo]", form).val());
        const tranCode = $("input[name=tranCode]", form).val();
        $.ajax(`${contextPath}/api/purchases/${tranNo}/tran-code?tranCode=${tranCode}`, {
            method: "PATCH",
            success: (data, status) => {
                // alert(`Succeeded to change tran code\n
                // HTTP status: ${status}\n
                // code=${data.code}\n
                // status=${data.status}`);
                updateTranStatus(data, tranNo);
            }
        });
    });

    function updateTranStatus(data, tranNo) {
        const purchaseItem = $(`#tran-no-${tranNo}`);
        $(".tran-status", purchaseItem).text(data.status);
        const tranStatusUpdate = $(".tran-status-update", purchaseItem);
        tranStatusUpdate.children().remove();
        switch (data.code) {
            case "1":
            case "2":
                tranStatusUpdate.append(getTranCodeUpdateElement(tranNo, data.code));
                break;
        }
    }
});