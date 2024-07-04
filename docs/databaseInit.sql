CREATE DATABASE IF NOT EXISTS intcomex;
USE intcomex;

CREATE TABLE IF NOT EXISTS Shippers(
    Shipper_ID int NOT NULL AUTO_INCREMENT,
    Company_Name varchar(250) NOT NULL,
    Phone varchar(50) NOT NULL,
    PRIMARY KEY (Shipper_ID)
);

INSERT INTO Shippers(Company_Name,Phone) VALUES('Fedex','5556767');
INSERT INTO Shippers(Company_Name,Phone) VALUES('DHL','4553434');

CREATE TABLE IF NOT EXISTS Categories(
    Category_ID int NOT NULL AUTO_INCREMENT,
    Category_Name varchar(250) NOT NULL,
    Description varchar(250) NULL,
    Picture varchar(250) NULL,
    PRIMARY KEY (Category_ID)
);

CREATE TABLE IF NOT EXISTS Customers(
    Customer_ID int NOT NULL AUTO_INCREMENT,
    Company_Name varchar(250) NOT NULL,
    Contact_Name varchar(250) NOT NULL,
    Contact_Title varchar(30) NULL,
    Address varchar(250) NOT NULL,
    City varchar(50) NULL,
    Region varchar(50) NULL,
    Postal_Code varchar(10) NULL,
    Country varchar(50) NULL,
    Phone varchar(50) NOT NULL,
    Fax varchar(50) NULL,
    PRIMARY KEY (Customer_ID)
);

INSERT INTO Customers(Company_Name,Contact_Name,Phone) VALUES('Cosmic creations','Michael Jhonson','001538858585');
INSERT INTO Customers(Company_Name,Contact_Name,Phone) VALUES('Harmony & Revitalize','Robert Thomas','001538851122');

CREATE TABLE IF NOT EXISTS Suppliers (
    Supplier_ID int NOT NULL AUTO_INCREMENT,
    Company_Name varchar(250) NOT NULL,
    Contact_Name varchar(250) NOT NULL,
    Contact_Title varchar(30) NULL,
    Address varchar(250) NOT NULL,
    City varchar(50) NULL,
    Region varchar(50) NULL,
    Postal_Code varchar(10) NULL,
    Country varchar(50) NULL,
    Phone varchar(50) NOT NULL,
    Fax varchar(50) NULL,
    Home_Page varchar(150) NULL,
    PRIMARY KEY (Supplier_ID)
);

INSERT INTO Suppliers(Company_Name,Contact_Name,Address,Phone) VALUES('Faravelli Group','Alice Johnson','123 Main Street, Anytown, CA 98765','(555) 123-4567');
INSERT INTO Suppliers(Company_Name,Contact_Name,Address,Phone) VALUES('Parchem Fine & Specialty Chemicals','David Smith','456 Elm Avenue, Springfield, NY 54321','(555) 987-6543');
INSERT INTO Suppliers(Company_Name,Contact_Name,Address,Phone) VALUES('Will & Co','Emily Brown','789 Oak Drive, Lakeside, TX 12345','(555) 789-0123');

CREATE TABLE IF NOT EXISTS Employees (
    Employee_ID int NOT NULL AUTO_INCREMENT,
    Last_Name varchar(100) NOT NULL,
    First_Name varchar(100) NOT NULL,
    Title varchar(30) NULL,
    Title_Of_Courtesy varchar(30) NULL,
    Birth_Date datetime NOT NULL,
    Hire_Date datetime NOT NULL,
    Address varchar(250) NOT NULL,
    City varchar(50) NULL,
    Region varchar(50) NULL,
    Postal_Code varchar(10) NULL,
    Country varchar(50) NULL,
    Home_Phone varchar(50) NOT NULL,
    Extension varchar(10) NOT NULL,
    Photo varchar(250) NULL,
    Notes text NULL,
    Reports_To int NOT NULL,
    PRIMARY KEY (Employee_ID)
);

INSERT INTO Employees(Last_Name,First_Name,Birth_Date,Hire_Date,Address,Home_Phone,Extension,Reports_To) VALUES('Johnson','Alice','1985-03-15','2022-01-10','123 Main St','555-123-4567','101',1);
INSERT INTO Employees(Last_Name,First_Name,Birth_Date,Hire_Date,Address,Home_Phone,Extension,Reports_To) VALUES('Smith','David','1990-08-22','2021-05-03','456 Elm Ave','555-987-6543','102',1);
INSERT INTO Employees(Last_Name,First_Name,Birth_Date,Hire_Date,Address,Home_Phone,Extension,Reports_To) VALUES('Brown','Emily','1988-11-05','2019-09-20','789 Oak Dr','555-789-0123','103',1);
INSERT INTO Employees(Last_Name,First_Name,Birth_Date,Hire_Date,Address,Home_Phone,Extension,Reports_To) VALUES('Clark','Michael','1980-06-12','2018-03-01','567 Maple Ln','555-234-5678','104',1);
INSERT INTO Employees(Last_Name,First_Name,Birth_Date,Hire_Date,Address,Home_Phone,Extension,Reports_To) VALUES('Garcia','Sophia','1993-04-28','2020-11-15','789 Pine Rd','555-345-6789','105',1);

CREATE TABLE IF NOT EXISTS Products (
    Product_ID int NOT NULL AUTO_INCREMENT,
    Product_Name varchar(250) NULL,
    Supplier_ID int NOT NULL,
    Category_ID int NOT NULL,
    Quantity_Per_Unit int NOT NULL,
    Unit_Price double NOT NULL,
    Units_In_Stock int NOT NULL,
    Units_On_Order int NOT NULL,
    Reorder_Level int NOT NULL,
    Discontinued boolean NOT NULL,
    PRIMARY KEY (Product_ID),
    CONSTRAINT FK_ProductSupplier FOREIGN KEY (Supplier_ID) REFERENCES Suppliers(Supplier_ID),
    CONSTRAINT FK_ProductCategory FOREIGN KEY (Category_ID) REFERENCES Categories(Category_ID)
);

CREATE TABLE IF NOT EXISTS Orders(
    Order_ID int NOT NULL AUTO_INCREMENT,
    Customer_ID int NOT NULL,
    Employee_ID int NOT NULL,
    Order_Date datetime NOT NULL,
    Required_Date datetime NOT NULL,
    Shipped_Date datetime NULL,
    Ship_Via int NOT NULL,
    Shipper_ID int NOT NULL,
    Freight varchar(250) NULL,
    Ship_Name varchar(250) NULL,
    Ship_Address varchar(250) NULL,
    Ship_City varchar(50) NULL,
    Ship_Region varchar(50) NULL,
    Ship_Postal_Code varchar(10) NULL,
    Ship_Country varchar(50) NULL,
    PRIMARY KEY (Order_ID),
    CONSTRAINT FK_OrdersCustomer FOREIGN KEY (Customer_ID) REFERENCES Customers(Customer_ID),
    CONSTRAINT FK_OrdersEmployee FOREIGN KEY (Employee_ID) REFERENCES Employees(Employee_ID),
    CONSTRAINT FK_OrdersShipper FOREIGN KEY (Shipper_ID) REFERENCES Shippers(Shipper_ID)
);

CREATE TABLE IF NOT EXISTS OrderDetails(
    Order_ID int NOT NULL,
    Product_ID int NOT NULL,
    Unit_Price double NOT NULL,
    Quantity int NOT NULL,
    Discount double NOT NULL,
    PRIMARY KEY (Order_ID,Product_ID)
);