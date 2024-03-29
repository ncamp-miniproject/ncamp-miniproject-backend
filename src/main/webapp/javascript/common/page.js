function updatePage(pageInfo, callback) {
    const pagination = $("#pagination");
    pagination.children().remove();
    pagination.append(generatePageElem(pageInfo));
    $(".page-navigator:not(.disabled):not(.active)").on("click", (e) => {
        e.preventDefault();
        const page = $(e.target).data("page");
        currentDisplaySetting.set(PAGE, page);
        callback();
    });
    $("#current-page-display").text(pageInfo.currentPage.toString());
}

function generatePageElem(pageInfo) {
    let pages = "";
    for (let page of pageInfo.pagesToDisplay) {
        const pageItem = `
                <li class="page-number ${page === pageInfo.currentPage ? "active" : ""}" data-page="${page}">
                    <a class="page-navigator ${page === pageInfo.currentPage ? "active" : ""}" ${page === pageInfo.currentPage ? "" : `data-page=${page}`}>${page}</a>
                </li>
            `;
        pages += pageItem;
    }
    return `
        <li${pageInfo.previousPageSetAvailable ? ` data-previous-page-set-entry="${pageInfo.previousPageSetEntry}"` : ` class="disabled"`}>
            <a aria-label="Previous" class="page-navigator ${pageInfo.previousPageSetAvailable ? "" : "disabled"}" ${pageInfo.previousPageSetAvailable ? `data-page=${pageInfo.previousPageSetEntry}` : ""}>
                <span aria-hidden="true" ${pageInfo.previousPageSetAvailable ? `data-page=${pageInfo.previousPageSetEntry}` : ""}>&laquo;</span>
            </a>
        </li>
        ${pages}
        <li${pageInfo.nextPageSetAvailable ? ` data-next-page-set-entry="${pageInfo.nextPageSetEntry}"` : ` class="disabled"`}>
            <a aria-label="Next" class="page-navigator ${pageInfo.nextPageSetAvailable ? "" : "disabled"}" ${pageInfo.nextPageSetAvailable ? `data-page=${pageInfo.nextPageSetEntry}` : ""}>
                <span aria-hidden="true" ${pageInfo.nextPageSetAvailable ? `data-page=${pageInfo.nextPageSetEntry}` : ""}>&raquo;</span>
            </a>
        </li>
    `;
}