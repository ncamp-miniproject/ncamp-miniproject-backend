SELECT 
    v.TRAN_NO "tran_no", 
    v.PROD_NO "prod_no", 
    v.BUYER_ID "buyer_id", 
    v.PAYMENT_OPTION "payment_option", 
    v.RECEIVER_NAME "receiver_name", 
    v.RECEIVER_PHONE "receiver_phone", 
    v.DEMAILADDR "demailaddr", 
    v.DLVY_REQUEST "dlvy_request", 
    v.TRAN_STATUS_CODE "tran_status_code", 
    v.ORDER_DATE "order_date", 
    v.DLVY_DATE "dlvy_date", 
    v.COUNT "count" 
FROM ( 
    SELECT 
        ROWNUM ROW_NUM, 
        tran_no TRAN_NO, 
        prod_no PROD_NO, 
        buyer_id BUYER_ID, 
        payment_option PAYMENT_OPTION, 
        receiver_name RECEIVER_NAME, 
        receiver_phone RECEIVER_PHONE, 
        demailaddr DEMAILADDR, 
        dlvy_request DLVY_REQUEST, 
        tran_status_code TRAN_STATUS_CODE, 
        order_date ORDER_DATE, 
        dlvy_date DLVY_DATE, 
        COUNT(*) OVER () COUNT 
    FROM transaction 
    WHERE buyer_id %s
    ORDER BY order_date DESC 
) v 
WHERE v.ROW_NUM BETWEEN ? AND ?