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
    currentDisplaySetting.set(MENU, menu);
}

$(() => {
    const contextPath = $("body").data("context-path");
    const menu = $("body").data("menu");

    const searchForm = $("form[name=search]");
    searchForm.data("search-condition");

    const getItemElem = (prodNo, prodName, price, stock, imageFileName, categoryName) => `
        <div class="panel panel-default item"
             data-prod-no="${prodNo}"
             data-stock="${stock}">
            <div class="panel-body">
                <div class="row prod-item">
                    <img src="${contextPath}/images/uploadFiles/${imageFileName}"
                         alt="Product Image">
                    <div>
                        <a>${prodName}</a>
                        <p>${price}</p>
                        <p>${categoryName}</p>
                        ${stock === 0 ? "<p>재고 없음</p>" : ""}
                    </div>
                </div>
            </div>
        </div>
    `;


    setDefaultDisplaySetting(1, 3, "", "1", menu);
    fetchDataAndUpdateProductList();

    const textSearchInput =
        $(`<input type="text"
                name="searchKeyword"
                value=""
                placeholder="검색어 입력"
                id="search-keyword-box">`);

    const intRangeSearchInput =
        $(`<div id="search-keyword-box"><input type="number"
                name="searchKeyword"
                value="">
         ~
         <input type="number"
                    name="searchKeyword"
                value=""></div>`);

    searchConditionFormMap["-1"] = textSearchInput;
    searchConditionFormMap["0"] = textSearchInput;
    searchConditionFormMap["1"] = textSearchInput;
    searchConditionFormMap["2"] = intRangeSearchInput;

    const conditionSelect = $("form[name=search] select");
    conditionSelect.after(searchConditionFormMap["-1"]);

    conditionSelect.on("change", (e) => {
        $("#search-keyword-box").remove();
        $(e.target).after(searchConditionFormMap[$("select[name=searchCondition] option:selected").val()]);
    });

    $("#search-button").on("click", () => {
        doSearch();
    });

    searchForm.on("keypress", e => {
        e.preventDefault();
        if (e.which === 13) {
            doSearch();
        }
    });

    function doSearch() {
        currentDisplaySetting.set(SEARCH_CONDITION, $("form[name=search] select").val());

        const arr = [];
        $("form[name=search] input[name=searchKeyword]").each((idx, elem) => {
            arr.push($(elem).val());
        });
        const searchKeyword = arr.join("-");

        currentDisplaySetting.set(SEARCH_KEYWORD, searchKeyword);
        currentDisplaySetting.set(PAGE, 1);

        fetchDataAndUpdateProductList();
    }

    $(".order-button").on("click", e => {
        const je = $(e.target);
        const parentButtonGroup = je.parents(".order-button-group");

        const orderBy = parentButtonGroup.data("order-by");
        const ascend = je.data("ascend");

        currentDisplaySetting.set(ORDER_BY, orderBy);
        currentDisplaySetting.set(ASCEND, ascend);
        currentDisplaySetting.set(PAGE, 1);

        fetchDataAndUpdateProductList();

        $(".order-dropdown-button").filter(".active").removeClass("active");

        const dropdownBtn = parentButtonGroup.children(".order-dropdown-button");
        dropdownBtn.addClass("active");
        dropdownBtn.text(je.text());
    });

    $(".page-size-button").on("click", e => {
        const pageSize = parseInt($(e.target).data("page-size"));
        console.log(pageSize);

        currentDisplaySetting.set(PAGE, 1);
        currentDisplaySetting.set(PAGE_SIZE, pageSize);

        fetchDataAndUpdateProductList();
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
                updateProductList(itemList, data.products);
                updatePage(data.pageInfo, fetchDataAndUpdateProductList)
                $("#count-display").text(data.count);
            }
        });
    }

    function updateProductList(itemList, products) {
        products.forEach(p => {
            const thumbnail = p.productImages.filter(pi => pi.thumbnail)[0];
            const itemElem = $(getItemElem(p.prodNo,
                p.prodName,
                p.price,
                p.stock,
                thumbnail ? thumbnail.fileName : "",
                p.category ? p.category.categoryName : "카테고리 없음"));
            const jItem = $("a", itemElem);
            if (p.stock > 0) {
                const menu = currentDisplaySetting.get(MENU);
                const queryParam = menu ? `?menu=${menu}` : "";
                jItem.attr("href", `${contextPath}/products/${p.prodNo}${queryParam}`)
                    .attr("target", "_blank")
                    .attr("rel", "noopener noreferrer");
            } else {
                jItem.replaceWith($(`<p>${p.prodName}</p>`));
            }
            itemList.append(itemElem);
        });
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
            currentDisplaySetting.set(PAGE, 1);

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