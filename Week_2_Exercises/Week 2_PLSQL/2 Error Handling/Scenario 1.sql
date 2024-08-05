CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    from_account_id IN NUMBER,
    to_account_id IN NUMBER,
    amount IN NUMBER
)
IS
  insufficient_funds EXCEPTION;
  funds_balance NUMBER;
  error_msg VARCHAR2(4000);

BEGIN
  -- Check balance of the from_account with locking
  SELECT balance INTO funds_balance
  FROM accounts
  WHERE accountid = from_account_id
  FOR UPDATE;

  -- Raise exception if insufficient funds
  IF funds_balance < amount THEN
    RAISE insufficient_funds;
  END IF;

  -- Performing transfer
  UPDATE accounts
  SET balance = balance - amount
  WHERE accountid = from_account_id;

  UPDATE accounts
  SET balance = balance + amount
  WHERE accountid = to_account_id;

  COMMIT;

EXCEPTION
  WHEN insufficient_funds THEN
    error_msg := 'Insufficient funds for transfer';
    BEGIN
      INSERT INTO error_logs (message, log_time)
      VALUES (error_msg, SYSDATE);
    EXCEPTION
      WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error logging insufficient funds: ' || SQLERRM);
    END;
  WHEN OTHERS THEN
    error_msg := SQLERRM;
    BEGIN
      INSERT INTO error_logs (message, log_time)
      VALUES (error_msg, SYSDATE);
    EXCEPTION
      WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error logging general error: ' || SQLERRM);
    END;
  ROLLBACK;
END;
/

--To show the error message, before executing the procedure, first this error_logs table has to be created. 
--The procedure should raise an insufficient_funds exception. An error message should be inserted into this table.

--CREATE TABLE error_logs (
--  log_time TIMESTAMP(6),
--  message VARCHAR2(4000),
--  error_code NUMBER
--);

--Now execute this procedure.

--Now query the error_logs table for insufficient fund case to see if an error message has been inserted.
