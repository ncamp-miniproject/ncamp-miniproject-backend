SELECT 
    tran_no "tran_no", 
    prod_no "prod_no", 
    buyer_id "buyer_id", 
    payment_option "payment_option", 
    receiver_name "receiver_name", 
    receiver_phone "receiver_phone", 
    demailaddr "demailaddr", 
    dlvy_request "dlvy_request", 
    tran_status_code "tran_status_code", 
    order_date "order_date", 
    dlvy_date "dlvy_date" 
FROM transaction 
WHERE tran_no = ?