CREATE OR REPLACE PACKAGE EmployeeManagement AS
  PROCEDURE HireEmployee(p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2, p_hiredate DATE);
  PROCEDURE UpdateEmployee(employee_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2);
  FUNCTION CalculateAnnualSalary(employee_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
  PROCEDURE HireEmployee(p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2, p_hiredate DATE) IS
  BEGIN
    INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
    VALUES (Employees_Seq.NEXTVAL, p_name, p_position, p_salary, p_dept, p_hiredate);
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
      RAISE_APPLICATION_ERROR(-20001, 'Employee ID already exists.');
    WHEN OTHERS THEN
      RAISE; -- Re-raise the exception for detailed handling
  END;

  PROCEDURE UpdateEmployee(employee_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2) IS
  BEGIN
    UPDATE Employees
    SET Name = p_name, Position = p_position, Salary = p_salary, Department = p_dept
    WHERE EmployeeID = employee_id;
  END;

  FUNCTION CalculateAnnualSalary(employee_id NUMBER) RETURN NUMBER IS
    v_salary NUMBER;
  BEGIN
    SELECT Salary INTO v_salary
    FROM Employees
    WHERE EmployeeID = employee_id;
    RETURN v_salary * 12;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN -1; -- Or handle the exception appropriately
  END;

END EmployeeManagement;
/
