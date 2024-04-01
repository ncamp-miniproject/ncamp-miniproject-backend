const buttonText = new Map();
buttonText.set("1", "배송하기");
buttonText.set("2", "물건도착");

const PAGE = "page";
const PAGE_SIZE = "pageSize";
const SEARCH_KEYWORD = "searchKeyword";
const SEARCH_CONDITION = "searchCondition";
const MENU = "menu";
const BUYER_ID = "buyerId";

const queryParamNames = [
    PAGE,
    PAGE_SIZE,
    SEARCH_KEYWORD,
    SEARCH_CONDITION,
    MENU,
    BUYER_ID
];

const currentDisplaySetting = new Map();

function setDefaultDisplaySetting(page,
                                  pageSize,
                                  menu,
                                  buyerId) {
    currentDisplaySetting.set(PAGE, page);
    currentDisplaySetting.set(PAGE_SIZE, pageSize);
    currentDisplaySetting.set(MENU, menu);
    currentDisplaySetting.set(BUYER_ID, buyerId);
}

function getTranCodeUpdateElement(tranNo, tranCode) {
    return `
        <form name="tran-code">
            <input type="hidden" name="tranNo" value="${tranNo}">
            <input type="hidden" name="tranCode" value="${parseInt(tranCode) + 1}">
            <button type="button">${buttonText.get(tranCode)}</button>
        </form>
    `;
}

$(() => {
    const body = $("body");
    const contextPath = body.data("context-path");
    const role = body.data("user-role");
    const userId = body.data("user-id");
    const menu = body.data("menu");

    function getRecordElement(purchase) {
        return `
        <tr class="data-row"
            id="tran-no-${purchase.tranNo}"
            data-tran-no="${purchase.tranNo}"
            data-buyer-id="${purchase.buyer.userId}"
            data-tran-code="${purchase.tranStatusCode.code}">

            <td><a class="tran-no">${purchase.tranNo}</a></td>
            <td><a class="buyer-id">${purchase.buyer.userId}</a></td>
            <td>${purchase.receiverName}</td>
            <td>${purchase.receiverPhone}</td>
            <td class="tran-status">${purchase.tranStatusCode.status}</td>
            
            
            ${role === "admin" ? `
                <td class="tran-status-update">
                    ${purchase.tranStatusCode.code === "1" ? `
                        <form name="tran-code"
                              action="${contextPath}/purchases/tran-code/update"
                              method="POST">
                            <input type="hidden" name="tranNo" value="${purchase.tranNo}">
                            <input type="hidden" name="tranCode" value="2">
                            <button type="button">배송하기</button>
                        </form>
                    ` : "" }
                    ${purchase.tranStatusCode.code === "2" ? `
                        <form name="tran-code"
                              action="${contextPath}/purchases/tran-code/update"
                              method="POST">
                            <input type="hidden" name="tranNo" value="${purchase.tranNo}">
                            <input type="hidden" name="tranCode" value="3">
                            <button type="button">물건도착</button>
                        </form>
                    ` : ""}
                </td>
                ` : ""}
            </c:if>
        </tr>
    `;
    }

    setDefaultDisplaySetting(1, 3, menu, userId);
    setLink();

    fetchDataAndUpdatePurchaseList();

    function setLink() {
        $(".data-row").each((idx, element) => {
            const jElem = $(element);
            const tranNo = jElem.data("tran-no");
            const buyerId = jElem.data("buyer-id");

            $(".tran-no", jElem)
                .attr("href", `${contextPath}/purchases/${tranNo}`);

            $(".buyer-id", jElem)
                .attr("href", `${contextPath}/users/${buyerId}`);
        });
    }

    function fetchDataAndUpdatePurchaseList() {
        const itemList = $("#purchase-item-list");
        itemList.children().remove();

        requestPurchaseList(itemList);
    }

    function requestPurchaseList(itemList) {
        console.log("here");
        const queryParameters = {};
        for (let param of queryParamNames) {
            const setting = currentDisplaySetting.get(param);
            if (setting) {
                queryParameters[param] = setting;
            }
        }

        const url = menu === "search"
            ? `${contextPath}/api/purchases`
            : `${contextPath}/api/purchases/sale`;

        $.ajax(url, {
            method: "GET",
            data: queryParameters,
            success: (data, textStatus, jqXHR) => {
                console.log(data);
                data.purchaseList.forEach(purchase => {
                    const elem = $(getRecordElement(purchase));
                    elem.on("click", onClickTranCodeFormButton)
                    itemList.append(elem);
                });
                updatePage(data.pageInfo, fetchDataAndUpdatePurchaseList);
                $("#count-display").text(data.count);

                // TODO: Maybe remove this
                if (data.menu) {
                    currentDisplaySetting.set(MENU, data.menu);
                }

                setLink();
            }
        });
    }

    const onClickTranCodeFormButton = e => {
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
    };

    $("form[name=tran-code] button").on("click", onClickTranCodeFormButton);

    function updateTranStatus(data, tranNo) {
        const purchaseItem = $(`#tran-no-${tranNo}`);
        $(".tran-status", purchaseItem).text(data.status);
        const tranStatusUpdate = $(".tran-status-update", purchaseItem);
        tranStatusUpdate.children().remove();
        switch (data.code) {
            case "1":
            case "2":
                tranStatusUpdate.append(getTranCodeUpdateElement(tranNo, data.code));
                $("form[name=tran-code] button").on("click", onClickTranCodeFormButton);
                break;
        }
    }
});