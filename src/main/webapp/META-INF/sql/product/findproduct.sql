SELECT
    p.prod_no         "prod_no",
    p.prod_name       "prod_name",
    p.prod_detail     "prod_detail",
    p.manufacture_day "manufacture_day",
    p.price           "price",
    p.image_file      "image_file",
    p.reg_date        "reg_date",
    p.stock           "stock"
FROM
    product p
WHERE
    p.prod_no = ?