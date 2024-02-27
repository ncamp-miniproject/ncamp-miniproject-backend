SELECT
    t.tran_no          "tran_no",
    t.buyer_id         "buyer_id",
    t.payment_option   "payment_option",
    t.receiver_name    "receiver_name",
    t.receiver_phone   "receiver_phone",
    t.demailaddr       "demailaddr",
    t.dlvy_request     "dlvy_request",
    t.tran_status_code "tran_status_code",
    t.order_date       "order_date",
    t.dlvy_date        "dlvy_date",
    tp.prod_no         "prod_no",
    tp.quantity        "quantity",
    p.prod_name        "prod_name",
    p.prod_detail      "prod_detail",
    p.manufacture_day  "manufacture_day",
    p.price            "price",
    p.image_file       "image_file",
    p.reg_date         "reg_date",
    p.stock            "stock",
    COUNT(*)
    OVER()             "count"
FROM
         transaction t
    INNER JOIN transaction_prod tp ON tp.tran_no = t.tran_no
    INNER JOIN product          p ON tp.prod_no = p.prod_no
WHERE
    t.tran_no = ?