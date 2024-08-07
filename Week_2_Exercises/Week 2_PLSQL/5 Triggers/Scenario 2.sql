CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (TransactionID, AccountID, TransactionDate, Amount, TransactionType, Action, LogTime)
  VALUES (:NEW.TransactionID, :NEW.AccountID, :NEW.TransactionDate, :NEW.Amount, :NEW.TransactionType, 'INSERT', CURRENT_TIMESTAMP);
END;
/

--This table need to be created before executing the trigger:
--   CREATE TABLE AuditLog (
--  	AuditID NUMBER GENERATED ALWAYS AS IDENTITY,
--  	TransactionID NUMBER,
--  	AccountID NUMBER,
--  	TransactionDate DATE,
--  	Amount NUMBER,
--  	TransactionType VARCHAR2(10),
--  	Action VARCHAR2(10),
--  	LogTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
--   );

