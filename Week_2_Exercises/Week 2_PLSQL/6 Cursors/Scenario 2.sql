SET SERVEROUTPUT ON;
DECLARE
  CURSOR accounts_cursor IS
    SELECT AccountID, Balance
    FROM Accounts;

  v_acc_id Accounts.AccountID%TYPE;
  v_balance Accounts.Balance%TYPE;
  v_annual_fee NUMBER := 10; -- Example of annual fee
BEGIN
  OPEN accounts_cursor;
  LOOP
    FETCH accounts_cursor INTO v_acc_id, v_balance;
    EXIT WHEN accounts_cursor%NOTFOUND;

    UPDATE Accounts
    SET Balance = Balance - v_annual_fee
    WHERE AccountID = v_acc_id;
  END LOOP;
  CLOSE accounts_cursor;
END;
/
