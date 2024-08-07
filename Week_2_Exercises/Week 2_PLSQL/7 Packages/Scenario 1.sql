--1. Create the Package Specification:
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddNewCustomer(p_CustomerID NUMBER, p_Name VARCHAR2, p_DOB DATE, p_Balance NUMBER);
    PROCEDURE UpdateCustomerDetails(p_CustomerID NUMBER, p_Name VARCHAR2, p_Balance NUMBER);
    FUNCTION GetCustomerBalance(p_CustomerID NUMBER) RETURN NUMBER;
END CustomerManagement;
/

--2. Create the Package Body:
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddNewCustomer(p_CustomerID NUMBER, p_Name VARCHAR2, p_DOB DATE, p_Balance NUMBER) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_CustomerID || ' already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
    END AddNewCustomer;

    PROCEDURE UpdateCustomerDetails(p_CustomerID NUMBER, p_Name VARCHAR2, p_Balance NUMBER) IS
    BEGIN
        UPDATE Customers
        SET Name = p_Name, Balance = p_Balance, LastModified = SYSDATE
        WHERE CustomerID = p_CustomerID;
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_CustomerID || ' not found.');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance(p_CustomerID NUMBER) RETURN NUMBER IS
        v_Balance NUMBER;
    BEGIN
        SELECT Balance INTO v_Balance
        FROM Customers
        WHERE CustomerID = p_CustomerID;
        RETURN v_Balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_CustomerID || ' not found.');
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('An unexpected error occurred: ' || SQLERRM);
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/

---- Query to test the package:

--SET SERVEROUTPUT ON;
--
---- Test updating customer details
--BEGIN
--    CustomerManagement.UpdateCustomerDetails(1, 'John Doe', 2000);
--    CustomerManagement.UpdateCustomerDetails(2, 'Jane Smith', 2500);
--END;
--/
--
---- Test getting customer balance
--DECLARE
--    v_Balance NUMBER;
--BEGIN
--    v_Balance := CustomerManagement.GetCustomerBalance(1);
--    DBMS_OUTPUT.PUT_LINE('Balance for customer 1: ' || v_Balance);
--
--    v_Balance := CustomerManagement.GetCustomerBalance(2);
--    DBMS_OUTPUT.PUT_LINE('Balance for customer 2: ' || v_Balance);
--END;
--/
