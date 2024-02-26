INSERT INTO transaction (
    tran_no,
    buyer_id,
    payment_option,
    receiver_name,
    receiver_phone,
    demailaddr,
    dlvy_request,
    tran_status_code,
    order_date,
    dlvy_date
) VALUES (
    seq_transaction_tran_no.NEXTVAL,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?
)