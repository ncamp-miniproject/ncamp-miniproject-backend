SELECT
    t.ROW_NUM "row_num",
    t.TRAN_NO "tran_no",
    t.BUYER_ID "buyer_id",
    t.PAYMENT_OPTION "payment_option",
    t.RECEIVER_NAME "receiver_name",
    t.RECEIVER_PHONE "receiver_phone",
    t.DEMAILADDR "demailaddr",
    t.DLVY_REQUEST "dlvy_request",
    t.TRAN_STATUS_CODE "tran_status_code",
    t.ORDER_DATE "order_date",
    t.DLVY_DATE "dlvy_date",
    t.COUNT "count",
    tp.prod_no "prod_no",
    tp.quantity "quantity",
    p.prod_name "prod_name",
    p.prod_detail "prod_detail",
    p.manufacture_day "manufacture_day",
    p.price "price",
    p.image_file "image_file",
    p.reg_date "reg_date",
    p.stock "stock"
FROM
    (
        SELECT
            ROW_NUMBER() OVER (
                ORDER BY
                    order_date DESC
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
        WHERE
            buyer_id %s
        ORDER BY
            order_date DESC
    ) t
    INNER JOIN transaction_prod tp ON tp.tran_no = t.tran_no
    INNER JOIN product p ON tp.prod_no = p.prod_no
WHERE
    v.ROW_NUM BETWEEN ?