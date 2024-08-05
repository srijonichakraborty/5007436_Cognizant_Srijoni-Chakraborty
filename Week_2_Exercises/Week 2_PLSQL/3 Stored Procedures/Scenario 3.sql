CREATE OR REPLACE PROCEDURE TransferFunds(src_acc_id NUMBER, target_acc_id NUMBER, p_amount NUMBER)
AS
  src_balance NUMBER;
BEGIN
  -- Begin a transaction
  BEGIN
    -- Check source account balance
    SELECT Balance INTO src_balance
    FROM Accounts
    WHERE AccountID = src_acc_id;

    IF src_balance < p_amount THEN
      RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds');
    END IF;

    -- Update account balance
    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = src_acc_id;

    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = target_acc_id;

    -- Insert transaction records
    INSERT INTO Transactions (TRANSACTIONID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (trans_id_seq.NEXTVAL, src_acc_id, SYSDATE, -p_amount, 'TransOut');

    INSERT INTO Transactions (TRANSACTIONID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (trans_id_seq.NEXTVAL, target_acc_id, SYSDATE, p_amount, 'TransIn');

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END;
END;
/


--Steps before executing the procedure--
--To ensure the TRANSACTIONID is unique, the sequence trans_id_seq is created, 
--as there is a unique constraint on the transactionid column in the transactions table.
--Using this below query first the max value of transactionid currently is found:
--    SELECT MAX(TRANSACTIONID) AS max_trans_id FROM Transactions;
--then the Sequence with the correct starting value(in our data, 3) is created by doing below query:
--    CREATE SEQUENCE trans_id_seq
--    START WITH 11
--    INCREMENT BY 1
--    NOCACHE
--    NOCYCLE;
