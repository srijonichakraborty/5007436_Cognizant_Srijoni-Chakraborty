CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
  loan_amount IN NUMBER,
  annual_interest_rate IN NUMBER,
  loan_duration_yrs IN NUMBER
) RETURN NUMBER
IS
  monthly_interest_rate NUMBER;
  no_of_payments NUMBER;
  monthly_installment NUMBER;
BEGIN
  IF loan_amount <= 0 OR annual_interest_rate < 0 OR loan_duration_yrs <= 0 THEN
    RAISE_APPLICATION_ERROR(-20001, 'Invalid input parameters');
  END IF;

  monthly_interest_rate := annual_interest_rate / 12 / 100;
  no_of_payments := loan_duration_yrs * 12;

  IF monthly_interest_rate = 0 THEN
    monthly_installment := loan_amount / no_of_payments;
  ELSE
    monthly_installment := loan_amount * monthly_interest_rate /
      (1 - POWER(1 + monthly_interest_rate, -no_of_payments));
  END IF;

  RETURN monthly_installment;
EXCEPTION
  WHEN OTHERS THEN
    RAISE; 
END;
/
