CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(dept VARCHAR2, bonus_percent NUMBER)
AS
  error_msg VARCHAR2(4000);
BEGIN
  IF bonus_percent <= 0 THEN
    INSERT INTO error_logs (log_time, message, error_code)
    VALUES (SYSTIMESTAMP, 'Bonus percentage must be greater than zero', -20001);
    RETURN;
  END IF;

  BEGIN
    UPDATE Employees
    SET Salary = Salary * (1 + bonus_percent / 100)
    WHERE Department = dept
    AND Salary > 0;

    IF SQL%ROWCOUNT = 0 THEN
      INSERT INTO error_logs (log_time, message, error_code)
      VALUES (SYSTIMESTAMP, 'No employees found in department ' || dept, -20002);
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      error_msg := 'An unexpected error occurred: ' || SQLERRM;
      INSERT INTO error_logs (log_time, message, error_code)
      VALUES (SYSTIMESTAMP, error_msg, -20003);
  END;
END;
/
