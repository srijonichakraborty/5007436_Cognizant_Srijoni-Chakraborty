CREATE OR REPLACE PACKAGE BODY AccountOperations AS
  PROCEDURE OpenAccount(cust_id NUMBER, p_acc_type VARCHAR2) IS
  BEGIN
    INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
    VALUES (Accounts_Seq.NEXTVAL, cust_id, p_acc_type, 0, SYSDATE);
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
      RAISE_APPLICATION_ERROR(-20001, 'Account ID already exists.');
    WHEN OTHERS THEN
      RAISE;
  END;

  PROCEDURE CloseAccount(p_acc_id NUMBER) IS
  BEGIN
    DELETE FROM Accounts WHERE AccountID = p_acc_id;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE_APPLICATION_ERROR(-20002, 'Account not found.');
    WHEN OTHERS THEN
      RAISE;
  END;

  FUNCTION GetCustomerTotalBalance(cust_id NUMBER) RETURN NUMBER IS
    v_total_balance NUMBER := 0;
  BEGIN
    SELECT SUM(Balance) INTO v_total_balance
    FROM Accounts
    WHERE CustomerID = cust_id;
    RETURN v_total_balance;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN 0; -- Or handle as needed
    WHEN OTHERS THEN
      RAISE;
  END;
END AccountOperations;
