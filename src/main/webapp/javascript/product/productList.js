const searchConditionFormMap = new Map();

const currentDisplaySetting = new Map();

const PAGE = "page";
const PAGE_SIZE = "pageSize";
const SEARCH_KEYWORD = "searchKeyword";
const SEARCH_CONDITION = "searchCondition";
const MENU = "menu";
const CATEGORY_NO = "categoryNo";
const ORDER_BY = "orderBy";
const ASCEND = "ascend";

const queryParamNames = [
    PAGE,
    PAGE_SIZE,
    SEARCH_KEYWORD,
    SEARCH_CONDITION,
    MENU,
    CATEGORY_NO,
    ORDER_BY,
    ASCEND
];

function setDefaultDisplaySetting(page,
                                  pageSize,
                                  searchKeyword,
                                  searchCondition,
                                  menu) {
    currentDisplaySetting.set(PAGE, page);
    currentDisplaySetting.set(PAGE_SIZE, pageSize);
    currentDisplaySetting.set(SEARCH_KEYWORD, searchKeyword);
    currentDisplaySetting.set(SEARCH_CONDITION, searchCondition);
    currentDisplaySetting.set(MENU, menu);
}

$(() => {
    const contextPath = $("body").data("context-path");
    const menu = $("body").data("menu");

    const searchForm = $("form[name=search]");
    const searchCondition = searchForm.data("search-condition");
    const searchKeyword = searchForm.data("search-keyword");

    const getItemElem = (prodNo, prodName, price, stock, imageFileName, categoryName) => `
        <div class="panel panel-default item"
             data-prod-no="${prodNo}"
             data-stock="${stock}">
            <div class="panel-body">
                <div class="row prod-item">
                    <img src="${contextPath}/images/uploadFiles/${imageFileName}"
                         alt="Product Image">
                    <div>
                        <p>${prodName}</p>
                        <p>${price}</p>
                        <p>${categoryName}</p>
                    </div>
                </div>
            </div>
        </div>
    `;


    setDefaultDisplaySetting(1, 3, searchKeyword, searchCondition, menu);
    fetchDataAndUpdateProductList();

    const textSearchInput =
        $(`<input type="text"
                name="searchKeyword"
                value="${searchKeyword}"
                placeholder="검색어 입력"
                id="search-keyword-box">`);

    let lowerBound = 0;
    let upperBound = 0;
    if (searchKeyword !== "") {
        const rangeValues = searchKeyword.split('-');
        if (rangeValues.length === 2) {
            lowerBound = parseInt(rangeValues[0]);
            upperBound = parseInt(rangeValues[1]);
        } else if (searchKeyword.startsWith("-")) {
            upperBound = parseInt(rangeValues[0]);
        } else {
            lowerBound = parseInt(rangeValues[0]);
            upperBound = "";
        }
    }
    const intRangeSearchInput =
        $(`<div id="search-keyword-box"><input type="number"
                name="searchKeyword"
                value="${lowerBound}">
         ~
         <input type="number"
                    name="searchKeyword"
                value="${upperBound}"></div>`);

    searchConditionFormMap["-1"] = textSearchInput;
    searchConditionFormMap["0"] = textSearchInput;
    searchConditionFormMap["1"] = textSearchInput;
    searchConditionFormMap["2"] = intRangeSearchInput;

    const conditionSelect = $("form[name=search] select");
    conditionSelect.after(searchConditionFormMap[searchCondition]);

    conditionSelect.on("change", (e) => {
        $("#search-keyword-box").remove();
        $(e.target).after(searchConditionFormMap[$("select[name=searchCondition] option:selected").val()]);
    });

    $(".list .item").each((idx, elem) => {
        const prodNo = $(elem).data("prodNo");
        const stock = $(elem).data("stock");
        if (parseInt(stock) > 0) {
            $(elem).children("td").children(".prod-no").attr("href", `${contextPath}/products/${prodNo}?menu=${menu}`);
        }
    });

    $("#search-button").on("click", () => {
        currentDisplaySetting.set(SEARCH_CONDITION, $("form[name=search] select").val());

        const arr = [];
        $("form[name=search] input[name=searchKeyword]").each((idx, elem) => {
            arr.push($(elem).val());
        });
        const searchKeyword = arr.join("-");

        currentDisplaySetting.set(SEARCH_KEYWORD, searchKeyword);

        fetchDataAndUpdateProductList();
    });

    $(".order-button").on("click", e => {
        const je = $(e.target);
        const parentButtonGroup = je.parents(".order-button-group");

        const orderBy = parentButtonGroup.data("order-by");
        const ascend = je.data("ascend");

        currentDisplaySetting.set(ORDER_BY, orderBy);
        currentDisplaySetting.set(ASCEND, ascend);

        fetchDataAndUpdateProductList();

        $(".order-dropdown-button").filter(".active").removeClass("active");

        const dropdownBtn = parentButtonGroup.children(".order-dropdown-button");
        dropdownBtn.addClass("active");
        dropdownBtn.text(je.text());
    });

    function fetchDataAndUpdateProductList() {
        const itemList = $(".item-list");
        itemList.children().remove();

        requestProductList(itemList);
    }

    function requestProductList(itemList) {
        const queryParameters = {};
        for (let param of queryParamNames) {
            const setting = currentDisplaySetting.get(param);
            if (setting) {
                queryParameters[param] = setting;
            }
        }

        $.ajax(`${contextPath}/api/products`, {
            data: queryParameters,
            method: "GET",
            success: (data, textStatus, jqXHR) => {
                console.log(data);
                data.products.forEach(p => {
                    const itemElem = getItemElem(p.prodNo,
                        p.prodName,
                        p.price,
                        p.stock,
                        "sample_ive.webp",
                        p.category.categoryName);
                    itemList.append(itemElem);
                });
                updatePage(data.pageInfo)
                $("#count-display").text(data.count);
            }
        });
    }

    function updatePage(pageInfo) {
        const pagination = $("#pagination");
        pagination.children().remove();
        pagination.append(generatePageElem(pageInfo));
        $(".page-navigator").on("click", (e) => {
            e.preventDefault();
            console.log($(e.target));
            const page = $(e.target).data("page");
            console.log("page=" + page);
            currentDisplaySetting.set(PAGE, page);
            fetchDataAndUpdateProductList();
        });
        $("#current-page-display").text(pageInfo.currentPage.toString());
    }

    function generatePageElem(pageInfo) {
        let pages = "";
        console.log(pageInfo);
        for (let page of pageInfo.pagesToDisplay) {
            const pageItem = `
                <li class="page-number ${page === pageInfo.currentPage ? "active" : ""}" data-page="${page}">
                    <a class="page-navigator" ${page === pageInfo.currentPage ? "" : `data-page=${page}`}>${page}</a>
                </li>
            `;
            pages += pageItem;
        }
        return `
            <li${pageInfo.previousPageSetAvailable ? ` data-previous-page-set-entry="${pageInfo.previousPageSetEntry}"` : ` class="disabled"`}>
                <a aria-label="Previous" class="page-navigator" ${pageInfo.previousPageSetAvailable ? `data-page=${pageInfo.previousPageSetEntry}` : ""}>
                    <span aria-hidden="true" ${pageInfo.previousPageSetAvailable ? `data-page=${pageInfo.previousPageSetEntry}` : ""}>&laquo;</span>
                </a>
            </li>
            ${pages}
            <li${pageInfo.nextPageSetAvailable ? ` data-next-page-set-entry="${pageInfo.nextPageSetEntry}"` : ` class="disabled"`}>
                <a aria-label="Next" class="page-navigator" ${pageInfo.nextPageSetAvailable ? `data-page=${pageInfo.nextPageSetEntry}` : ""}>
                    <span aria-hidden="true" ${pageInfo.nextPageSetAvailable ? `data-page=${pageInfo.nextPageSetEntry}` : ""}>&raquo;</span>
                </a>
            </li>
        `;
    }

    fetchCategories();

    function fetchCategories() {
        $.ajax(`${contextPath}/api/categories`, {
            method: "GET",
            success: (data, textStatus, jqXHR) => {
                const categoryDisplay = $(".category-display");
                categoryDisplay.children().remove();
                categoryDisplay.append(generateCategoryElem("NaN", "모든 상품", true));
                for (let category of data) {
                    categoryDisplay.append(generateCategoryElem(category.categoryNo, category.categoryName, false));
                }
                $(".all-category").addClass("active");
                setOnClickHandlerToCategories();
            }
        });
    }

    const generateCategoryElem = (categoryNo, categoryName, good) => `
        <a data-category-no="${categoryNo}" class="category-item list-group-item ${good ? "all-category" : ""}">${categoryName}</a>
    `;

    function setOnClickHandlerToCategories() {
        $(".category-item").on("click", e => {
            const categoryNoData = $(e.target).data("category-no");
            currentDisplaySetting.set(CATEGORY_NO, categoryNoData === "NaN" ? undefined : parseInt(categoryNoData));
            updateCategory(currentDisplaySetting.get(CATEGORY_NO));

            fetchDataAndUpdateProductList();
        });
    }

    function updateCategory(categoryNoToSelect) {
        $(".category-display .active").removeClass("active");

        if (!categoryNoToSelect) {
            $(".all-category").addClass("active");
        } else {
            $(".category-item").each((idx, elem) => {
                if (parseInt($(elem).data("category-no")) === categoryNoToSelect) {
                    $(elem).addClass("active");
                }
            });
        }
    }
});