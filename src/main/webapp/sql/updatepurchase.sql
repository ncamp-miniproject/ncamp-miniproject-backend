UPDATE transaction 
SET buyer_id = ?, 
    payment_option = ?, 
    receiver_name = ?, 
    receiver_phone = ?, 
    demailaddr = ?, 
    dlvy_request = ?, 
    order_date = ?, 
    dlvy_date = ? 
WHERE tran_no = ?