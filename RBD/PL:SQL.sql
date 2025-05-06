
--1.PROCEDURE (Adding discounts to all clients who made at least 3 transactions in the store)
CREATE OR REPLACE PROCEDURE ManageDiscounts
AS
    CURSOR cursor1 IS SELECT Customer_IdCustomer, COUNT(Customer_IdCustomer) AS countTransaction
                      FROM Transaction
                      GROUP BY Customer_IdCustomer;
    CustomerId NUMBER;
    Counter    NUMBER;

BEGIN
    OPEN cursor1;
    LOOP
        FETCH cursor1 INTO CustomerId, Counter;
        EXIT WHEN cursor1%NOTFOUND;
        IF Counter >= 3 THEN
            UPDATE Customer
            SET DiscountAvaliable = 1
            WHERE IdCustomer = CustomerId;
        END IF;
    END LOOP;
    CLOSE cursor1;

END;

CALL ManageDiscounts();

--2.PROCEDURE (Adding a customer data)
CREATE OR REPLACE PROCEDURE AddCustomer(
    pName NVARCHAR2,
    pSurname NVARCHAR2,
    pPhone NVARCHAR2,
    pEmail NVARCHAR2,
    pAddressId NUMBER
)
AS
    pPersonId NUMBER;
    pCustomerId NUMBER;
BEGIN
    SELECT COUNT(*) INTO pPersonId FROM Person;
    SELECT COUNT(*) INTO pCustomerId FROM Customer;
    pPersonId := pPersonId +1;
    pCustomerId := pCustomerId +1;

    INSERT INTO Person(IdPerson, Name, Surname, PhoneNumber, Email, Address_IdAddress)
    VALUES (pPersonId, pName, pSurname, pPhone, pEmail, pAddressId);

    INSERT INTO Customer(IDCUSTOMER, PERSON_IDPERSON, DISCOUNTAVALIABLE)
    VALUES (pCustomerId, pPersonId, 0);

END;

CALL AddCustomer('Alex', 'Smith' ,'95275844491', 'a.mouse@gmail.com', 2);

--TRIGGER 1 (Checking if the email of a new person is not already in use)
CREATE OR REPLACE TRIGGER uniqueEmail
    BEFORE INSERT OR UPDATE ON Person
    FOR EACH ROW
    DECLARE
        counterEmail NUMBER;
    BEGIN
        IF INSERTING THEN
            SELECT COUNT(*) INTO counterEmail
            FROM Person
            WHERE Email = :NEW.Email;
        ELSIF UPDATING THEN
            SELECT COUNT(*) INTO counterEmail
            FROM Person
            WHERE Email = :NEW.Email
            AND IdPerson != :OLD.IdPerson;
        END IF;
        IF(counterEmail > 0) THEN
            RAISE_APPLICATION_ERROR(-20110, 'This email is already used!');
        END IF;
    END;

INSERT INTO Person(IdPerson, Name, Surname, PhoneNumber, Email, Address_IdAddress)
VALUES (5,'Jakes','Down','91294444444','john.doe@gmail.com',1);

--TRIGGER 2 (Checking if the hire date of the new employee is in the future)
CREATE OR REPLACE TRIGGER checkHireDate
BEFORE INSERT ON Employee
FOR EACH ROW
BEGIN
    IF :NEW.Hiredate > SYSDATE THEN
        RAISE_APPLICATION_ERROR(-20111, 'Wrong hire date!');
    END IF;
END;

INSERT INTO Employee(IdEmployee, Person_IdPerson, Salary, Hiredate)
VALUES (4,3, 2000, TO_DATE('2025-03-01', 'YYYY-MM-DD'))









