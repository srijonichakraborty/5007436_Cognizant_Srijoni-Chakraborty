CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS
  update_interest_rate CONSTANT NUMBER := 0.01; 
BEGIN
  UPDATE Accounts
  SET Balance = Balance * (1 + update_interest_rate),
      LastModified = SYSDATE
  WHERE AccountType = 'Savings'
  AND LastModified < TRUNC(SYSDATE); 
END;
/
