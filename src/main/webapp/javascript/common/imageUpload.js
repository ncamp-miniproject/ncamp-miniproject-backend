let imageInput;

$(() => {
    $("#btn-upload-image").on("click", () => {
        const i = $(`<input type="file" name="imageFiles">`);
        console.log(i);
        i.on("change", e => {
            const reader = new FileReader();
            reader.onload = () => {
                $("#upload-image").attr("src", reader.result);
            }
            reader.readAsDataURL(e.target.files[0]);
        });
        i.trigger("click");
        imageInput = i;
    });

    $("#btn-select").on("click", () => {
        $("#sample", window.opener.document).attr("src", $("#upload-image").attr("src"));
        if (!imageInput) {
            alert("이미지를 선택해 주세요");
            return;
        }
        $("#product-register-form", window.opener.document).append(imageInput);
        imageInput.css("display", "none");

        const imageDescText = $("#image-desc").val();
        const newImage = $(`
            <div class="col-sm-6 col-md-3">
                <div class="thumbnail">
                    <img alt="No image">
                    <div class="caption">
                        <p>${imageDescText}</p>
                        <input type="hidden" name="image-desc" value="${imageDescText}">
                    </div>
                </div>
            </div>
        `);
        $("img", newImage).attr("src", $("#upload-image").attr("src"));
        $("#image-container", window.opener.document).append(newImage);
        window.close();
    });
});