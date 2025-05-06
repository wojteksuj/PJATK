-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2025-01-02 15:37:46.731

-- tables
-- Table: Address

-- Dropping dependent tables first (those with foreign key constraints)
DROP TABLE Transaction_Item CASCADE CONSTRAINTS;
DROP TABLE Transaction CASCADE CONSTRAINTS;
DROP TABLE Item CASCADE CONSTRAINTS;
DROP TABLE Employee_Role CASCADE CONSTRAINTS;
DROP TABLE Employee CASCADE CONSTRAINTS;
DROP TABLE Customer CASCADE CONSTRAINTS;
DROP TABLE Person CASCADE CONSTRAINTS;
DROP TABLE Address CASCADE CONSTRAINTS;

-- Dropping the independent tables next
DROP TABLE PaymentMethod CASCADE CONSTRAINTS;
DROP TABLE Role CASCADE CONSTRAINTS;
DROP TABLE Category CASCADE CONSTRAINTS;
DROP TABLE Condition CASCADE CONSTRAINTS;
DROP TABLE CountryOfOrigin CASCADE CONSTRAINTS;
DROP TABLE City CASCADE CONSTRAINTS;


CREATE TABLE Address
(
    IdAddress   integer       NOT NULL,
    Street      nvarchar2(20) NOT NULL,
    HouseNumber varchar2(20)  NOT NULL,
    PostCode    nvarchar2(5)  NOT NULL,
    City_IdCity integer       NOT NULL,
    CONSTRAINT Address_pk PRIMARY KEY (IdAddress)
);

-- Table: Category
CREATE TABLE Category
(
    IdCategory integer       NOT NULL,
    Category   nvarchar2(20) NOT NULL,
    CONSTRAINT Category_pk PRIMARY KEY (IdCategory)
);

-- Table: City
CREATE TABLE City
(
    IdCity integer       NOT NULL,
    City   nvarchar2(20) NOT NULL,
    CONSTRAINT City_pk PRIMARY KEY (IdCity)
);

-- Table: Condition
CREATE TABLE Condition
(
    IdCondition integer       NOT NULL,
    Condition   nvarchar2(20) NOT NULL,
    CONSTRAINT Condition_pk PRIMARY KEY (IdCondition)
);

-- Table: CountryOfOrigin
CREATE TABLE CountryOfOrigin
(
    IdCountryOfOrigin integer       NOT NULL,
    Country           nvarchar2(20) NOT NULL,
    CONSTRAINT CountryOfOrigin_pk PRIMARY KEY (IdCountryOfOrigin)
);

-- Table: Customer
CREATE TABLE Customer
(
    IdCustomer        integer NOT NULL,
    Person_IdPerson   integer NOT NULL,
    DiscountAvaliable integer NOT NULL,
    CONSTRAINT Customer_pk PRIMARY KEY (IdCustomer)
);

-- Table: Employee
CREATE TABLE Employee
(
    IdEmployee      integer      NOT NULL,
    Person_IdPerson integer      NOT NULL,
    Salary          number(4, 0) NOT NULL,
    Hiredate        date         NOT NULL,
    CONSTRAINT Employee_pk PRIMARY KEY (IdEmployee)
);

-- Table: Employee_Role
CREATE TABLE Employee_Role
(
    Employee_IdEmployee integer NOT NULL,
    Role_IdRole         integer NOT NULL,
    CONSTRAINT Employee_Role_pk PRIMARY KEY (Employee_IdEmployee)
);

-- Table: Item
CREATE TABLE Item
(
    IdItem                integer        NOT NULL,
    Name                  nvarchar2(20)  NOT NULL,
    Category_IdCategory   integer        NOT NULL,
    Age                   number(3, 0)   NOT NULL,
    CountryOfOrigin       integer        NOT NULL,
    Price                 number(5, 2)   NOT NULL,
    Condition_IdCondition integer        NOT NULL,
    Description           nvarchar2(200) NULL,
    CONSTRAINT Item_pk PRIMARY KEY (IdItem)
);

-- Table: PaymentMethod
CREATE TABLE PaymentMethod
(
    IdPaymentMethod integer       NOT NULL,
    PaymentMethod   nvarchar2(20) NOT NULL,
    CONSTRAINT PaymentMethod_pk PRIMARY KEY (IdPaymentMethod)
);

-- Table: Person
CREATE TABLE Person
(
    IdPerson          integer       NOT NULL,
    Name              nvarchar2(20) NOT NULL,
    Surname           nvarchar2(20) NOT NULL,
    PhoneNumber       nvarchar2(11) NOT NULL,
    Email             nvarchar2(50) NOT NULL,
    Address_IdAddress integer       NOT NULL,
    CONSTRAINT Person_pk PRIMARY KEY (IdPerson)
);

-- Table: Role
CREATE TABLE Role
(
    IdRole integer       NOT NULL,
    Role   nvarchar2(20) NOT NULL,
    CONSTRAINT Role_pk PRIMARY KEY (IdRole)
);

-- Table: Transaction
CREATE TABLE Transaction
(
    IdTransaction       integer      NOT NULL,
    Customer_IdCustomer integer      NOT NULL,
    FinalPrice          number(5, 2) NOT NULL,
    DateOfTransaction   date         NOT NULL,
    PaymentMethod       integer      NOT NULL,
    CONSTRAINT Transaction_pk PRIMARY KEY (IdTransaction)
);

-- Table: Transaction_Item
CREATE TABLE Transaction_Item
(
    Transaction_IdTransaction integer NOT NULL,
    Item_IdItem               integer NOT NULL,
    CONSTRAINT Transaction_Item_pk PRIMARY KEY (Transaction_IdTransaction, Item_IdItem)
);

-- foreign keys
-- Reference: Address_City (table: Address)
ALTER TABLE Address
    ADD CONSTRAINT Address_City
        FOREIGN KEY (City_IdCity)
            REFERENCES City (IdCity);

-- Reference: Customer_Person (table: Customer)
ALTER TABLE Customer
    ADD CONSTRAINT Customer_Person
        FOREIGN KEY (Person_IdPerson)
            REFERENCES Person (IdPerson);

-- Reference: Employee_Person (table: Employee)
ALTER TABLE Employee
    ADD CONSTRAINT Employee_Person
        FOREIGN KEY (Person_IdPerson)
            REFERENCES Person (IdPerson);

-- Reference: Employee_Role_Employee (table: Employee_Role)
ALTER TABLE Employee_Role
    ADD CONSTRAINT Employee_Role_Employee
        FOREIGN KEY (Employee_IdEmployee)
            REFERENCES Employee (IdEmployee);

-- Reference: Employee_Role_Role (table: Employee_Role)
ALTER TABLE Employee_Role
    ADD CONSTRAINT Employee_Role_Role
        FOREIGN KEY (Role_IdRole)
            REFERENCES Role (IdRole);

-- Reference: Item_Category (table: Item)
ALTER TABLE Item
    ADD CONSTRAINT Item_Category
        FOREIGN KEY (Category_IdCategory)
            REFERENCES Category (IdCategory);

-- Reference: Item_Condition (table: Item)
ALTER TABLE Item
    ADD CONSTRAINT Item_Condition
        FOREIGN KEY (Condition_IdCondition)
            REFERENCES Condition (IdCondition);

-- Reference: Item_CountryOfOrigin (table: Item)
ALTER TABLE Item
    ADD CONSTRAINT Item_CountryOfOrigin
        FOREIGN KEY (CountryOfOrigin)
            REFERENCES CountryOfOrigin (IdCountryOfOrigin);

-- Reference: Person_Address (table: Person)
ALTER TABLE Person
    ADD CONSTRAINT Person_Address
        FOREIGN KEY (Address_IdAddress)
            REFERENCES Address (IdAddress);

-- Reference: Transaction_Customer (table: Transaction)
ALTER TABLE Transaction
    ADD CONSTRAINT Transaction_Customer
        FOREIGN KEY (Customer_IdCustomer)
            REFERENCES Customer (IdCustomer);

-- Reference: Transaction_Item_Item (table: Transaction_Item)
ALTER TABLE Transaction_Item
    ADD CONSTRAINT Transaction_Item_Item
        FOREIGN KEY (Item_IdItem)
            REFERENCES Item (IdItem);

-- Reference: Transaction_Item_Transaction (table: Transaction_Item)
ALTER TABLE Transaction_Item
    ADD CONSTRAINT Transaction_Item_Transaction
        FOREIGN KEY (Transaction_IdTransaction)
            REFERENCES Transaction (IdTransaction);

-- Reference: Transaction_PaymentMethod (table: Transaction)
ALTER TABLE Transaction
    ADD CONSTRAINT Transaction_PaymentMethod
        FOREIGN KEY (PaymentMethod)
            REFERENCES PaymentMethod (IdPaymentMethod);

-- End of file.

--CITIES
INSERT INTO City (IdCity, City)
VALUES (1, 'New York');

INSERT INTO City (IdCity, City)
VALUES (2, 'Los Angeles');

--ADDRESS
INSERT INTO Address (IdAddress, Street, HouseNumber, PostCode, City_IdCity)
VALUES (1, 'Maple St', '13', '12345', 1);

INSERT INTO Address (IdAddress, Street, HouseNumber, PostCode, City_IdCity)
VALUES (2, 'Oak Ave', '46', '67890', 2);

INSERT INTO Address (IdAddress, Street, HouseNumber, PostCode, City_IdCity)
VALUES (3, 'Beverly Ave', '33', '67990', 2);

INSERT INTO Address (IdAddress, Street, HouseNumber, PostCode, City_IdCity)
VALUES (4, 'Lamp St', '92', '70152', 2);

--CATEGORY
INSERT INTO Category (IdCategory, Category)
VALUES (1, 'Furniture');

INSERT INTO Category (IdCategory, Category)
VALUES (2, 'Electronics');

--PERSON
INSERT INTO Person (IdPerson, Name, Surname, PhoneNumber, Email, Address_IdAddress)
VALUES (1, 'John', 'Doe', '12345678901', 'john.doe@gmail.com', 1);

INSERT INTO Person (IdPerson, Name, Surname, PhoneNumber, Email, Address_IdAddress)
VALUES (2, 'Jane', 'Smith', '09876543210', 'jane.smith@gmail.com', 2);

INSERT INTO Person (IdPerson, Name, Surname, PhoneNumber, Email, Address_IdAddress)
VALUES (3, 'Agnes', 'Black', '1285237322', 'agnes.black@gmail.com', 4);

INSERT INTO Person (IdPerson, Name, Surname, PhoneNumber, Email, Address_IdAddress)
VALUES (4, 'William', 'Clarkson', '8529610921', 'will.clarkson@gmail.com', 3);

--CUSTOMER
INSERT INTO Customer (IdCustomer, Person_IdPerson, DiscountAvaliable)
VALUES (1, 1, 0);

INSERT INTO Customer (IdCustomer, Person_IdPerson, DiscountAvaliable)
VALUES (2, 4, 0);

--EMPLOYEE
INSERT INTO Employee (IdEmployee, Person_IdPerson, Salary, Hiredate)
VALUES (1, 2, 4000, TO_DATE('2020-01-01', 'YYYY-MM-DD'));

INSERT INTO Employee (IdEmployee, Person_IdPerson, Salary, Hiredate)
VALUES (2, 3, 5000, TO_DATE('2019-06-15', 'YYYY-MM-DD'));

--ROLE
INSERT INTO Role (IdRole, Role)
VALUES (1, 'Manager');

INSERT INTO Role (IdRole, Role)
VALUES (2, 'Salesperson');

--ROLE-EMPLOYEE
INSERT INTO Employee_Role (Employee_IdEmployee, Role_IdRole)
VALUES (1, 2);

INSERT INTO Employee_Role (Employee_IdEmployee, Role_IdRole)
VALUES (2, 1);

--CONDITION
INSERT INTO Condition (IdCondition, Condition)
VALUES (1, 'Good');

INSERT INTO Condition (IdCondition, Condition)
VALUES (2, 'Bad');

--COUNTRY OF ORIGIN
INSERT INTO CountryOfOrigin (IdCountryOfOrigin, Country)
VALUES (1, 'USA');

INSERT INTO CountryOfOrigin (IdCountryOfOrigin, Country)
VALUES (2, 'Germany');

--ITEM
INSERT INTO Item (IdItem, Name, Category_IdCategory, Age, CountryOfOrigin, Price, Condition_IdCondition, Description)
VALUES (1, 'Chair', 1, 120, 1, 200.50, 1, 'An antic chair in great condition.');

INSERT INTO Item (IdItem, Name, Category_IdCategory, Age, CountryOfOrigin, Price, Condition_IdCondition, Description)
VALUES (2, 'Lamp', 2, 90, 2, 150.00, 2, null);

INSERT INTO Item (IdItem, Name, Category_IdCategory, Age, CountryOfOrigin, Price, Condition_IdCondition, Description)
VALUES (3, 'Watch', 2, 85, 1, 350.00, 2, 'A used but fully functional watch.');

INSERT INTO Item (IdItem, Name, Category_IdCategory, Age, CountryOfOrigin, Price, Condition_IdCondition, Description)
VALUES (4, 'Carpet', 1, 150, 2, 110.00, 2, 'A bit worn but beautiful ancient carpet.');

--PAYMENTMETHOD
INSERT INTO PaymentMethod (IdPaymentMethod, PaymentMethod)
VALUES (1, 'Credit Card');

INSERT INTO PaymentMethod (IdPaymentMethod, PaymentMethod)
VALUES (2, 'Cash');

--TRANSACTION
INSERT INTO Transaction (IdTransaction, Customer_IdCustomer, FinalPrice, DateOfTransaction, PaymentMethod)
VALUES (1, 1, 200.50, TO_DATE('2024-12-01', 'YYYY-MM-DD'), 1);

INSERT INTO Transaction (IdTransaction, Customer_IdCustomer, FinalPrice, DateOfTransaction, PaymentMethod)
VALUES (2, 2, 150.00, TO_DATE('2024-11-21', 'YYYY-MM-DD'), 2);

INSERT INTO Transaction (IdTransaction, Customer_IdCustomer, FinalPrice, DateOfTransaction, PaymentMethod)
VALUES (3, 2, 350.00, TO_DATE('2024-10-02', 'YYYY-MM-DD'), 2);

INSERT INTO Transaction (IdTransaction, Customer_IdCustomer, FinalPrice, DateOfTransaction, PaymentMethod)
VALUES (4, 2, 110.00, TO_DATE('2024-12-18', 'YYYY-MM-DD'), 1);


--TRANSACTION-ITEM
INSERT INTO Transaction_Item (Transaction_IdTransaction, Item_IdItem)
VALUES (1, 1);

INSERT INTO Transaction_Item (Transaction_IdTransaction, Item_IdItem)
VALUES (2, 2);

INSERT INTO Transaction_Item (Transaction_IdTransaction, Item_IdItem)
VALUES (3, 3);

INSERT INTO Transaction_Item (Transaction_IdTransaction, Item_IdItem)
VALUES (4, 4);
