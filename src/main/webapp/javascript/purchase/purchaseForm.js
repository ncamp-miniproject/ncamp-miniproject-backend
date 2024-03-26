$(() => {
    const contextPath = $("body").data("context-path");

    $(".btn--submit").on("click", () => {
        $("form[name=purchase]")
            .attr("action", `${contextPath}/purchases/new`)
            .attr("method", "POST")
            .trigger("submit");
    });

    const divyDate = $("#divy-date")
    divyDate.datepicker();
    divyDate.datepicker("option", "dateFormat", "yy-mm-dd");

    $.ajax(`${contextPath}/api/users/account`, {
        method: "GET",
        success: data => {
            $("input[name=buyerId]").val(data.userId);
            $("input[name=receiverName]").val(data.userName);
            $("input[name=receiverPhone]").val(data.phone);
            $("input[name=divyAddr]").val(data.addr);
        }
    });

    const cart = new Map();

    document.cookie.split(/;\s*/)
        .map(c => c.split("="))
        .filter(pair => pair[0] === "cart")
        .map(pair => pair[1]).pop()
        .split("&")
        .map(item => item.split("-"))
        .forEach(pair => cart.set(pair[0], parseInt(pair[1])));

    const productElem = (product, quantity) => `
        <tr>
            <input type="hidden" name="tranProds" value="${product.prodNo}%DFS${quantity}">
            <td>${product.prodNo}</td>
            <td>${product.prodName}</td>
            <td>${product.prodDetail}</td>
            <td>${product.manuDate}</td>
            <td>${product.price}</td>
            <td>${product.regDate}</td>
            <td>${quantity}</td>
        </tr>
    `;

    const url = `${contextPath}/api/products/selected?${[...cart.keys()].map(v => `prodNos=${v}`).join("&")}`;

    $.ajax(url, {
        method: "GET",
        success: data => {
            data.forEach(d => {
                const elem = $(productElem(d, cart.get(d.prodNo.toString())));
                $(".cart-info tbody").append(elem);
            });

            let priceSum = 0;
            let quantitySum = 0;
            data.forEach(item => {
                priceSum += !item.price? 0 : item.price;
                quantitySum += !cart.get(item.prodNo.toString())? 0 : cart.get(item.prodNo.toString());
            });
            $("#price-sum").text(priceSum);
            $("#quantity-sum").text(quantitySum);
        }
    });
});