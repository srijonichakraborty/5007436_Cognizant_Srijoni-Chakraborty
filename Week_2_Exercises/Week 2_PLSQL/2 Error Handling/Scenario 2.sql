CREATE OR REPLACE PROCEDURE UpdateSalary (
    employee_id IN NUMBER,
    percentage IN NUMBER
)
IS
    employee_not_found EXCEPTION;
    v_count NUMBER;
    error_msg VARCHAR2(4000);
    error_code NUMBER;
BEGIN
    -- Check if employee exists
    SELECT COUNT(*) INTO v_count
    FROM Employees
    WHERE employeeid = employee_id;

    
    IF v_count = 0 THEN
        RAISE employee_not_found;
    ELSE
        -- Update the employee's salary
        UPDATE Employees
        SET salary = salary * (1 + percentage / 100)
        WHERE employeeid = employee_id;

    END IF;

    COMMIT;

EXCEPTION
    WHEN employee_not_found THEN
        INSERT INTO error_logs (log_time, message)
        VALUES (SYSDATE, 'Employee ID not found');
        COMMIT;
    WHEN OTHERS THEN
        error_msg := SQLERRM;
        error_code := SQLCODE;
        INSERT INTO error_logs (log_time, message, error_code)
        VALUES (SYSDATE, error_msg, error_code);
        COMMIT;
END UpdateSalary;
