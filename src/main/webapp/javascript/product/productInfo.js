let currentImageIdx = 0;

$(() => {
    const contextPath = $("body").data("context-path");

    $("#loadOnCart").on("click", () => {
        $("form[name=cart]")
            .attr("method", "POST")
            .attr("action", `${contextPath}/cart/items/new`)
            .trigger("submit");
    });

    $("#quantityInput").on("change", e => {
        const quantityInputElem = $(e.target);
        if (parseInt(quantityInputElem.val()) <= 0) {
            quantityInputElem.val(1);
        }
    });

    const generateImgElem = (imageData) => `
        <img src="${contextPath}/images/uploadFiles/${imageData.fileName}"
             alt="No image">
        <p>${imageData.description}</p>
    `;

    const imageContainer = $("#image-container");
    imageContainer.append(generateImgElem(prodImageData[currentImageIdx]));

    $("#slide-left").on("click", () => {
        if (currentImageIdx <= 0) {
            return;
        }
        imageContainer.children().remove();
        imageContainer.append(generateImgElem(prodImageData[--currentImageIdx]));
    });

    $("#slide-right").on("click", () => {
        if (currentImageIdx >= prodImageData.length - 1) {
            return;
        }
        imageContainer.children().remove();
        imageContainer.append(generateImgElem(prodImageData[++currentImageIdx]));
    });
});