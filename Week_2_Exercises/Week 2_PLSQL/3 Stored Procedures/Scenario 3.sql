CREATE OR REPLACE PROCEDURE AddNewCustomer (
    customer_id IN NUMBER,
    customer_name IN VARCHAR2,
    dob IN DATE,
    balance IN NUMBER
)
IS
    customer_exists EXCEPTION;
    v_count NUMBER;
    error_msg VARCHAR2(4000);
    error_code NUMBER;
BEGIN
    -- Check if customer already exists
    SELECT COUNT(*) INTO v_count 
    FROM Customers 
    WHERE customerid = customer_id;

    IF v_count > 0 THEN
        RAISE customer_exists;
    ELSE
        INSERT INTO Customers (customerid, name, dob, balance, lastmodified, isvip) 
        VALUES (customer_id, customer_name, dob, balance, SYSDATE, 0);
    END IF;

    COMMIT;

EXCEPTION
    WHEN customer_exists THEN
        error_msg := 'Customer ID already exists';
        error_code := SQLCODE;
        INSERT INTO error_logs (log_time, message, error_code)
        VALUES (SYSDATE, error_msg, error_code);
    WHEN OTHERS THEN
        error_msg := SQLERRM;
        error_code := SQLCODE;
        INSERT INTO error_logs (log_time, message, error_code)
        VALUES (SYSDATE, error_msg, error_code);
    ROLLBACK;
END AddNewCustomer;
