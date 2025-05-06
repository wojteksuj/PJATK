DROP TABLE Transaction_Item;
DROP TABLE [Transaction];
DROP TABLE Item;
DROP TABLE Employee_Role;
DROP TABLE Employee;
DROP TABLE Customer;
DROP TABLE Person;
DROP TABLE Address;

DROP TABLE PaymentMethod;
DROP TABLE Role;
DROP TABLE Category;
DROP TABLE Condition;
DROP TABLE CountryOfOrigin;
DROP TABLE City;

CREATE TABLE Address (
    IdAddress INT NOT NULL PRIMARY KEY,
    Street NVARCHAR(20) NOT NULL,
    HouseNumber VARCHAR(20) NOT NULL,
    PostCode NVARCHAR(5) NOT NULL,
    City_IdCity INT NOT NULL
);

CREATE TABLE Category (
    IdCategory INT NOT NULL PRIMARY KEY,
    Category NVARCHAR(20) NOT NULL
);

CREATE TABLE City (
    IdCity INT NOT NULL PRIMARY KEY,
    City NVARCHAR(20) NOT NULL
);

CREATE TABLE Condition (
    IdCondition INT NOT NULL PRIMARY KEY,
    Condition NVARCHAR(20) NOT NULL
);

CREATE TABLE CountryOfOrigin (
    IdCountryOfOrigin INT NOT NULL PRIMARY KEY,
    Country NVARCHAR(20) NOT NULL
);

CREATE TABLE Customer (
    IdCustomer INT NOT NULL PRIMARY KEY,
    Person_IdPerson INT NOT NULL,
    DiscountAvaliable INT NOT NULL
);

CREATE TABLE Employee (
    IdEmployee INT NOT NULL PRIMARY KEY,
    Person_IdPerson INT NOT NULL,
    Salary DECIMAL(4, 0) NOT NULL,
    Hiredate DATE NOT NULL
);

CREATE TABLE Employee_Role (
    Employee_IdEmployee INT NOT NULL,
    Role_IdRole INT NOT NULL,
    PRIMARY KEY (Employee_IdEmployee, Role_IdRole)
);

CREATE TABLE Item (
    IdItem INT NOT NULL PRIMARY KEY,
    Name NVARCHAR(20) NOT NULL,
    Category_IdCategory INT NOT NULL,
    Age DECIMAL(3, 0) NOT NULL,
    CountryOfOrigin INT NOT NULL,
    Price DECIMAL(5, 2) NOT NULL,
    Condition_IdCondition INT NOT NULL,
    Description NVARCHAR(200)
);

CREATE TABLE PaymentMethod (
    IdPaymentMethod INT NOT NULL PRIMARY KEY,
    PaymentMethod NVARCHAR(20) NOT NULL
);

CREATE TABLE Person (
    IdPerson INT NOT NULL PRIMARY KEY,
    Name NVARCHAR(20) NOT NULL,
    Surname NVARCHAR(20) NOT NULL,
    PhoneNumber NVARCHAR(11) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    Address_IdAddress INT NOT NULL
);

CREATE TABLE Role (
    IdRole INT NOT NULL PRIMARY KEY,
    Role NVARCHAR(20) NOT NULL
);

CREATE TABLE [Transaction] (
    IdTransaction INT NOT NULL PRIMARY KEY,
    Customer_IdCustomer INT NOT NULL,
    FinalPrice DECIMAL(5, 2) NOT NULL,
    DateOfTransaction DATE NOT NULL,
    PaymentMethod INT NOT NULL
);

CREATE TABLE Transaction_Item (
    Transaction_IdTransaction INT NOT NULL,
    Item_IdItem INT NOT NULL,
    PRIMARY KEY (Transaction_IdTransaction, Item_IdItem)
);

ALTER TABLE Address ADD FOREIGN KEY (City_IdCity) REFERENCES City (IdCity);
ALTER TABLE Customer ADD FOREIGN KEY (Person_IdPerson) REFERENCES Person (IdPerson);
ALTER TABLE Employee ADD FOREIGN KEY (Person_IdPerson) REFERENCES Person (IdPerson);
ALTER TABLE Employee_Role ADD FOREIGN KEY (Employee_IdEmployee) REFERENCES Employee (IdEmployee);
ALTER TABLE Employee_Role ADD FOREIGN KEY (Role_IdRole) REFERENCES Role (IdRole);
ALTER TABLE Item ADD FOREIGN KEY (Category_IdCategory) REFERENCES Category (IdCategory);
ALTER TABLE Item ADD FOREIGN KEY (Condition_IdCondition) REFERENCES Condition (IdCondition);
ALTER TABLE Item ADD FOREIGN KEY (CountryOfOrigin) REFERENCES CountryOfOrigin (IdCountryOfOrigin);
ALTER TABLE Person ADD FOREIGN KEY (Address_IdAddress) REFERENCES Address (IdAddress);
ALTER TABLE [Transaction] ADD FOREIGN KEY (Customer_IdCustomer) REFERENCES Customer (IdCustomer);
ALTER TABLE Transaction_Item ADD FOREIGN KEY (Item_IdItem) REFERENCES Item (IdItem);
ALTER TABLE Transaction_Item ADD FOREIGN KEY (Transaction_IdTransaction) REFERENCES [Transaction] (IdTransaction);
ALTER TABLE [Transaction] ADD FOREIGN KEY (PaymentMethod) REFERENCES PaymentMethod (IdPaymentMethod);

-- Insert statements
INSERT INTO City (IdCity, City) VALUES (1, 'New York');
INSERT INTO City (IdCity, City) VALUES (2, 'Los Angeles');
INSERT INTO Address (IdAddress, Street, HouseNumber, PostCode, City_IdCity) VALUES (1, 'Maple St', '13', '12345', 1);
INSERT INTO Address (IdAddress, Street, HouseNumber, PostCode, City_IdCity) VALUES (2, 'Oak Ave', '46', '67890', 2);
INSERT INTO Address (IdAddress, Street, HouseNumber, PostCode, City_IdCity) VALUES (3, 'Beverly Ave', '33', '67990', 2);
INSERT INTO Address (IdAddress, Street, HouseNumber, PostCode, City_IdCity) VALUES (4, 'Lamp St', '92', '70152', 2);
INSERT INTO Category (IdCategory, Category) VALUES (1, 'Furniture');
INSERT INTO Category (IdCategory, Category) VALUES (2, 'Electronics');
INSERT INTO Person (IdPerson, Name, Surname, PhoneNumber, Email, Address_IdAddress) VALUES (1, 'John', 'Doe', '12345678901', 'john.doe@gmail.com', 1);
INSERT INTO Person (IdPerson, Name, Surname, PhoneNumber, Email, Address_IdAddress) VALUES (2, 'Jane', 'Smith', '09876543210', 'jane.smith@gmail.com', 2);
INSERT INTO Person (IdPerson, Name, Surname, PhoneNumber, Email, Address_IdAddress) VALUES (3, 'Agnes', 'Black', '1285237322', 'agnes.black@gmail.com', 4);
INSERT INTO Person (IdPerson, Name, Surname, PhoneNumber, Email, Address_IdAddress) VALUES (4, 'William', 'Clarkson', '8529610921', 'will.clarkson@gmail.com', 3);
INSERT INTO Customer (IdCustomer, Person_IdPerson, DiscountAvaliable) VALUES (1, 1, 0);
INSERT INTO Customer (IdCustomer, Person_IdPerson, DiscountAvaliable) VALUES (2, 4, 0);
INSERT INTO Employee (IdEmployee, Person_IdPerson, Salary, Hiredate) VALUES (1, 2, 4000, '2020-01-01');
INSERT INTO Employee (IdEmployee, Person_IdPerson, Salary, Hiredate) VALUES (2, 3, 5000, '2019-06-15');
INSERT INTO Role (IdRole, Role) VALUES (1, 'Manager');
INSERT INTO Role (IdRole, Role) VALUES (2, 'Salesperson');
INSERT INTO Employee_Role (Employee_IdEmployee, Role_IdRole) VALUES (1, 2);
INSERT INTO Employee_Role (Employee_IdEmployee, Role_IdRole) VALUES (2, 1);
INSERT INTO Condition (IdCondition, Condition) VALUES (1, 'Good');
INSERT INTO Condition (IdCondition, Condition) VALUES (2, 'Bad');
INSERT INTO CountryOfOrigin (IdCountryOfOrigin, Country) VALUES (1, 'USA');
INSERT INTO CountryOfOrigin (IdCountryOfOrigin, Country) VALUES (2, 'Germany');
INSERT INTO Item (IdItem, Name, Category_IdCategory, Age, CountryOfOrigin, Price, Condition_IdCondition, Description) VALUES (1, 'Chair', 1, 120, 1, 200.50, 1, 'An antic chair in great condition.');
INSERT INTO Item (IdItem, Name, Category_IdCategory, Age, CountryOfOrigin, Price, Condition_IdCondition, Description) VALUES (2, 'Lamp', 2, 90, 2, 150.00, 2, NULL);
INSERT INTO Item (IdItem, Name, Category_IdCategory, Age, CountryOfOrigin, Price, Condition_IdCondition, Description) VALUES (3, 'Watch', 2, 85, 1, 350.00, 2, 'A used but fully functional watch.');
INSERT INTO Item (IdItem, Name, Category_IdCategory, Age, CountryOfOrigin, Price, Condition_IdCondition, Description) VALUES (4, 'Carpet', 1, 150, 2, 110.00, 2, 'A bit worn but beautiful ancient carpet.');
INSERT INTO PaymentMethod (IdPaymentMethod, PaymentMethod) VALUES (1, 'Credit Card');
INSERT INTO PaymentMethod (IdPaymentMethod, PaymentMethod) VALUES (2, 'Cash');
INSERT INTO [Transaction] (IdTransaction, Customer_IdCustomer, FinalPrice, DateOfTransaction, PaymentMethod) VALUES (1, 1, 200.50, '2024-12-01', 1);
INSERT INTO [Transaction] (IdTransaction, Customer_IdCustomer, FinalPrice, DateOfTransaction, PaymentMethod) VALUES (2, 2, 150.00, '2024-11-21', 2);
INSERT INTO [Transaction] (IdTransaction, Customer_IdCustomer, FinalPrice, DateOfTransaction, PaymentMethod) VALUES (3, 2, 350.00, '2024-10-02', 2);
INSERT INTO [Transaction] (IdTransaction, Customer_IdCustomer, FinalPrice, DateOfTransaction, PaymentMethod) VALUES (4, 2, 110.00, '2024-12-18', 1);
INSERT INTO Transaction_Item (Transaction_IdTransaction, Item_IdItem) VALUES (1, 1);
INSERT INTO Transaction_Item (Transaction_IdTransaction, Item_IdItem) VALUES (2, 2);
INSERT INTO Transaction_Item (Transaction_IdTransaction, Item_IdItem) VALUES (3, 3);
INSERT INTO Transaction_Item (Transaction_IdTransaction, Item_IdItem) VALUES (4, 4);