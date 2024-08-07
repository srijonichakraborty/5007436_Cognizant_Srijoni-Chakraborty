SET SERVEROUTPUT ON;

DECLARE
  CURSOR c_loans IS
    SELECT LoanID, InterestRate
    FROM Loans
    FOR UPDATE; -- clause for locking

  v_loanID Loans.LoanID%TYPE;
  currentInterestRate Loans.InterestRate%TYPE;
  newInterestRate Loans.InterestRate%TYPE;

  -- Example function to calculate new interest rate
  FUNCTION calculate_new_interest_rate(p_current_rate NUMBER) RETURN NUMBER IS
  BEGIN
    RETURN p_current_rate + 0.01; -- Example increase interest rate by 1%
  END;

BEGIN
  OPEN c_loans;

  LOOP
    FETCH c_loans INTO v_loanID, currentInterestRate;
    EXIT WHEN c_loans%NOTFOUND;

    newInterestRate := calculate_new_interest_rate(currentInterestRate);

    UPDATE Loans
    SET InterestRate = newInterestRate
    WHERE CURRENT OF c_loans; -- Use WHERE CURRENT OF for efficiency

    -- Optional logging
    DBMS_OUTPUT.PUT_LINE('Updated LoanID: ' || v_loanID || ' to new interest rate: ' || newInterestRate);
  END LOOP;

  CLOSE c_loans;
END;
/
