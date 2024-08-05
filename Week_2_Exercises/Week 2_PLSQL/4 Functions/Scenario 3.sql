CREATE OR REPLACE FUNCTION HasSufficientBalance(
  p_account_id NUMBER,
  p_amount NUMBER
) RETURN NUMBER
IS
  v_balance NUMBER;
BEGIN
  SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;

  IF v_balance >= p_amount THEN
    RETURN 1; -- TRUE
  ELSE
    RETURN 0; -- FALSE
  END IF;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN 0; -- FALSE
  WHEN OTHERS THEN
    RETURN 0; -- FALSE
END;
/
