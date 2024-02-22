function fncAddProduct() {
	const detailForm = document.detailForm;

	//Form 유효성 검증
	var name = detailForm.prodName.value;
	var detail = detailForm.prodDetail.value;
	var manuDate = detailForm.manuDate.value;
	var price = detailForm.price.value;

	console.log("name=" + name);
	console.log("detail=" + detail);
	console.log("manuDate=" + manuDate);
	console.log("price=" + price);

	if (name == null || name.length < 1) {
		alert("상품명은 반드시 입력하여야 합니다.");
		return;
	}
	if (detail == null || detail.length < 1) {
		alert("상품상세정보는 반드시 입력하여야 합니다.");
		return;
	}
	if (manuDate == null || manuDate.length < 1) {
		alert("제조일자는 반드시 입력하셔야 합니다.");
		return;
	}
	if (price == null || price.length < 1) {
		alert("가격은 반드시 입력하셔야 합니다.");
		return;
	}

	detailForm.submit();
}

function resetData() {
	document.detailForm.reset();
}