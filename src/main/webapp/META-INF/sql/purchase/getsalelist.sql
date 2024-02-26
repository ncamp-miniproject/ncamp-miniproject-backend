SELECT 
    t.tran_no, 
    t.prod_no, 
    t.buyer_id, 
    t.payment_option, 
    t.receiver_name, 
    t.receiver_phone, 
    t.demailaddr, 
    t.dlvy_request, 
    t.tran_status_code, 
    t.order_date, 
    t.dlvy_date, 
FROM transaction t 
WHERE t.tran_status_code = ?