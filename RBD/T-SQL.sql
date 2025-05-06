--PROCEDURE 1 (Provides the crucial information about a chosen customer)
CREATE PROCEDURE PrintCustomerHistory @customerId INTEGER
AS
    BEGIN
        DECLARE @customerName NVARCHAR(20), @totalSpent INTEGER, @itemsBought INTEGER, @hasDiscount INTEGER
        SET @customerName = (SELECT Name FROM Person p JOIN Customer c ON c.Person_IdPerson = P.IdPerson WHERE @customerId = c.IdCustomer);
        SET @hasDiscount = (SELECT DiscountAvaliable FROM Customer WHERE IdCustomeR = @customerId);
        SET @totalSpent = (SELECT SUM(FinalPrice) FROM [Transaction] WHERE Customer_IdCustomer = @customerId);
        SET @itemsBought = (SELECT COUNT(Item_IdItem) FROM Transaction_Item i JOIN [Transaction] t ON t.IdTransaction = i.Transaction_IdTransaction WHERE t.Customer_IdCustomer = @customerId) ;

        PRINT 'HISTORY FOR CUSTOMER NUMBER: ' + CONVERT(NVARCHAR, @customerId);
        PRINT 'NAME OF CUSTOMER: ' + @customerName;
        PRINT 'ITEMS BOUGHT:  ' + CONVERT(NVARCHAR, @itemsBought);
        PRINT 'TOTAL SPENT: ' + CONVERT(NVARCHAR, @totalSpent);
        IF (@hasDiscount = 1)
            BEGIN
                PRINT 'HAS DISCOUNT?: YES'
            END
        ELSE
            BEGIN
                PRINT 'HAS DISCOUNT?: NO'
            END
END;

EXECUTE PrintCustomerHistory @customerId=2;

--PROCEDURE 2 (Allows for increasing/decreasing the price by a specified percentage for a specific category of items)
CREATE PROCEDURE adjustPrice @raise INTEGER, @category NVARCHAR(20), @percentage INTEGER
    AS
    BEGIN
        DECLARE @categoryId INTEGER;
        SET @categoryId = (SELECT IdCategory FROM Category WHERE Category = @category);

        DECLARE c1 CURSOR FOR SELECT IdItem, Category_IdCategory, Price FROM Item;
        DECLARE @idItem INTEGER, @categoryItem INTEGER, @price DECIMAL(5,2);

        OPEN c1;
        FETCH NEXT FROM c1 INTO @idItem, @categoryItem, @price;

        WHILE @@FETCH_STATUS = 0
        BEGIN
            IF (@categoryItem = @categoryId)
                BEGIN
                    IF(@raise = 1)
                        BEGIN
                            SET @price += @price * (CAST(@percentage AS DECIMAL(5, 2)) / 100);
                            UPDATE ITEM
                            SET Price = @price
                            WHERE IdItem = @idItem;
                        END
                    IF(@raise = 0)
                        BEGIN
                            SET @price -= @price * (CAST(@percentage AS DECIMAL(5, 2)) / 100);
                            UPDATE ITEM
                            SET Price = @price
                            WHERE IdItem = @idItem
                        END
                END
            FETCH NEXT FROM c1 INTO @idItem, @categoryItem, @price;
        END

        CLOSE c1;
        DEALLOCATE c1;
END;

--TRIGGER 1 (Checks if the new price of an item is not smaller than the specified minimal price)
CREATE TRIGGER checkPrice ON Item
INSTEAD OF UPDATE,INSERT
AS
BEGIN
    DECLARE @minPrice DECIMAL(5,2);
        SET @minPrice = 50.00;
    -- UPDATE
    IF EXISTS(SELECT 1 FROM inserted) AND EXISTS(SELECT 1 FROM deleted)
    BEGIN
        IF((SELECT inserted.Price FROM inserted) < @minPrice)
            BEGIN
                RAISERROR('The updated price of an item is too low!', 10, 1);
                ROLLBACK;
            END
    END;
    -- INSERT
    ELSE IF EXISTS(SELECT 1 FROM inserted) AND NOT EXISTS(SELECT 1 FROM deleted)
    BEGIN
        IF((SELECT inserted.Price FROM inserted) < @minPrice)
            BEGIN
                RAISERROR('The inserted price of an item is too low!', 11, 1);
                ROLLBACK;
            END

    END
END;

--TRIGGER 2 (Reminds about sending an email to the customer when the price of a transaction is higher then specified value and also prevents from deleting the transaction from history
CREATE TRIGGER sendEmail ON [Transaction]
FOR INSERT,UPDATE,DELETE
AS
BEGIN
    DECLARE @customerEmail NVARCHAR(50), @priceBound DECIMAL(5,2);
    SET @priceBound = 200.00;
    -- UPDATE
    IF EXISTS(SELECT 1 FROM inserted) AND EXISTS(SELECT 1 FROM deleted)
    BEGIN
        SET @customerEmail = (SELECT Email FROM Person p
                                JOIN Customer c ON c.Person_IdPerson = p.IdPerson
                                JOIN inserted i ON i.Customer_IdCustomer = c.IdCustomer
                                WHERE i.Customer_IdCustomer = c.IdCustomer);
        IF((SELECT FinalPrice FROM inserted) > @priceBound)
            BEGIN
                PRINT 'REMINDER: Send an email to a customer! Email: ' + @customerEmail;
            END
    END;
    -- INSERT
    ELSE IF EXISTS(SELECT 1 FROM inserted) AND NOT EXISTS(SELECT 1 FROM deleted)
    BEGIN
        SET @customerEmail = (SELECT Email FROM Person p
                                JOIN Customer c ON c.Person_IdPerson = p.IdPerson
                                JOIN inserted i ON i.Customer_IdCustomer = c.IdCustomer
                                WHERE i.Customer_IdCustomer = c.IdCustomer);
        IF((SELECT FinalPrice FROM inserted) > @priceBound)
            BEGIN
                PRINT 'REMINDER: Send an email to a customer! Email: ' + @customerEmail;
            END
        END;
    -- DELETE
    ELSE IF EXISTS(SELECT 1 FROM deleted) AND NOT EXISTS(SELECT 1 FROM inserted)
    BEGIN
        RAISERROR('CAN NOT DELETE THE TRANSACTION FROM HISTORY!', 12, 1);
	ROLLBACK;
    END;
END;