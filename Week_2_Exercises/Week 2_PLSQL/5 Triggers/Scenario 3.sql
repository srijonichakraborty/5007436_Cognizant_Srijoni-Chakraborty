CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
  current_balance NUMBER;
BEGIN
  -- Check if the transaction is a withdrawal
  IF :NEW.TransactionType = 'WITHDRAWAL' THEN
    -- Retrieve the current balance of the account
    SELECT Balance INTO current_balance 
    FROM Accounts 
    WHERE AccountID = :NEW.AccountID 
    FOR UPDATE;

    -- Ensure the withdrawal amount does not exceed the current balance
    IF :NEW.Amount > current_balance THEN
      RAISE_APPLICATION_ERROR(-20001, 'Withdrawal amount exceeds current balance.');
    END IF;
  ELSIF :NEW.TransactionType = 'DEPOSIT' THEN
    -- Ensure the deposit amount is positive
    IF :NEW.Amount <= 0 THEN
      RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
    END IF;
  END IF;
END CheckTransactionRules;
/


--OUTPUT:-
--	SQL> INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
--	  2  VALUES (trans_id_seq.NEXTVAL, 1, SYSDATE, 100, 'DEPOSIT');
--	
--	1 row created.
--
--	SQL> INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
--	  2  VALUES (trans_id_seq.NEXTVAL, 1, SYSDATE, 100, 'WITHDRAWAL');
--	
--	1 row created.
--	
--	SQL> INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
--	  2  VALUES (trans_id_seq.NEXTVAL, 1, SYSDATE, 10000, 'WITHDRAWAL');
--	VALUES (trans_id_seq.NEXTVAL, 1, SYSDATE, 10000, 'WITHDRAWAL')
--	        *
--	ERROR at line 2:
--	ORA-20001: Withdrawal amount exceeds current balance.
--	ORA-06512: at "SYSTEM.CHECKTRANSACTIONRULES", line 14
--	ORA-04088: error during execution of trigger 'SYSTEM.CHECKTRANSACTIONRULES'