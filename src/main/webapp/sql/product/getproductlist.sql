SELECT
    v.prod_no         "prod_no",
    v.prod_name       "prod_name",
    v.prod_detail     "prod_detail",
    v.manufacture_day "manufacture_day",
    v.price           "price",
    v.image_file      "image_file",
    v.reg_date        "reg_date",
    v.count           "count"
FROM
    (
        SELECT
            ROW_NUMBER()
            OVER(
                ORDER BY
                    p.prod_no ASC
            )                 row_num,
            p.prod_no         prod_no,
            p.prod_name       prod_name,
            p.prod_detail     prod_detail,
            p.manufacture_day manufacture_day,
            p.price           price,
            p.image_file      image_file,
            p.reg_date        reg_date,
            COUNT(*)
            OVER()            count
        FROM
            product p
        ORDER BY
            p.reg_date DESC -- Should be flexible
    ) v
WHERE
    v.row_num BETWEEN ? AND ?