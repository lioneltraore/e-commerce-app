
-- Insert categories into the category table
INSERT INTO category (id, name, description) VALUES (nextval('category_seq'), 'Electronics', 'Devices and gadgets');
INSERT INTO category (id, name, description) VALUES (nextval('category_seq'), 'Home Appliances', 'Appliances for home use');
INSERT INTO category (id, name, description) VALUES (nextval('category_seq'), 'Books', 'Printed and digital books');

-- Check the inserted categories
SELECT * FROM category;

-- Insert products into the product table
INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Smartphone', 'Latest model with high resolution camera', 50, 699.99, 751);

INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Washing Machine', 'Front-load washing machine with 10kg capacity', 20, 499.99, 801);

INSERT INTO product (id, name, description, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Programming Book', 'Comprehensive guide to SQL and databases', 100, 39.99, 851);
