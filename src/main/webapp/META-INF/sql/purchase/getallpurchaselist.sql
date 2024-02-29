SELECT
    t.row_num          "row_num",
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
    t.count            "count",
    tp.prod_no         "prod_no",
    tp.quantity        "quantity",
    p.prod_name        "prod_name",
    p.prod_detail      "prod_detail",
    p.manufacture_day  "manufacture_day",
    p.price            "price",
    p.image_file       "image_file",
    p.reg_date         "reg_date",
    p.stock            "stock"
FROM
    (
        SELECT
            ROW_NUMBER() OVER (
                ORDER BY
                    tran_no DESC
            ) ROW_NUM,
                t.tran_no TRAN_NO,
            t.buyer_id BUYER_ID,
            t.payment_option PAYMENT_OPTION,
            t.receiver_name RECEIVER_NAME,
            t.receiver_phone RECEIVER_PHONE,
            t.demailaddr DEMAILADDR,
            t.dlvy_request DLVY_REQUEST,
            t.tran_status_code TRAN_STATUS_CODE,
            t.order_date ORDER_DATE,
            t.dlvy_date DLVY_DATE,
            COUNT(*) OVER () COUNT
        FROM
            transaction t
        ORDER BY
            tran_no DESC
    ) t
        INNER JOIN transaction_prod tp ON tp.tran_no = t.tran_no
        INNER JOIN product p ON tp.prod_no = p.prod_no
WHERE t.row_num between ? AND ?
ORDER BY t.row_num