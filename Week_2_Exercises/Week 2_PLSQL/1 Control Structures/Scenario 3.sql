SET SERVEROUTPUT ON;

DECLARE
  CURSOR c_loans_due IS
    SELECT c.name, l.loanid, l.enddate
    FROM customers c
    JOIN loans l ON c.customerid = l.customerid
    WHERE l.enddate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
  FOR rec IN c_loans_due LOOP
    DBMS_OUTPUT.PUT_LINE('Reminder for ' || rec.name || ': Loan ' || rec.loanid || ' is due on ' || TO_CHAR(rec.enddate, 'YYYY-MM-DD'));
  END LOOP;
END;
/
