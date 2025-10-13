-- Pharmacies
INSERT INTO pharmacies (tax_id, name, address, phone_number) VALUES 
('12345678901', 'Farmacia Central', 'Av. Principal 123, Centro', 5551234567),
('98765432109', 'Farmacia Norte', 'Calle Norte 456, Zona Norte', 5559876543),
('11122233344', 'Farmacia Sur', 'Av. Sur 789, Zona Sur', 5555555555);

-- Categories
INSERT INTO categories (name, image_url) VALUES 
('Medicamentos', 'https://example.com/medicamentos.jpg'),
('Vitaminas', 'https://example.com/vitaminas.jpg'),
('Cuidado Personal', 'https://example.com/cuidado.jpg'),
('Primeros Auxilios', 'https://example.com/primeros.jpg'),
('Bebés y Niños', 'https://example.com/bebes.jpg');

-- Suppliers
INSERT INTO suppliers (name, identification_number, phone_number, address) VALUES 
('Laboratorios ABC S.A.', '12345678901', 5551111111, 'Zona Industrial 123'),
('Distribuidora XYZ Ltda.', '98765432109', 5552222222, 'Av. Comercio 456'),
('Farmacéutica Global', '11122233344', 5553333333, 'Centro Empresarial 789');

-- Users
INSERT INTO users (first_name, last_name, email, password, role, identification_number, phone_number, salary, start_date, pharmacy_id) VALUES 
('Admin', 'Sistema', 'admin@pharmacy.com', 'admin123', 'ADMIN', 12345678, 5550000000, 100000.0, '2024-01-01', 1),
('Carlos', 'Rodriguez', 'carlos@pharmacy.com', 'carlos123', 'PHARMACIST', 87654321, 5551111111, 80000.0, '2024-01-15', 1),
('Ana', 'Martinez', 'ana@pharmacy.com', 'ana123', 'PHARMACIST', 11223344, 5552222222, 75000.0, '2024-02-01', 2),
('Luis', 'Gonzalez', 'luis@pharmacy.com', 'luis123', 'CASHIER', 44556677, 5553333333, 45000.0, '2024-03-01', 1),
('Sofia', 'Hernandez', 'sofia@pharmacy.com', 'sofia123', 'MANAGER', 99887766, 5554444444, 90000.0, '2024-01-20', 2);

-- Products
INSERT INTO products (code, name, description, unit, weight, purchase_price, sale_price, current_stock, desired_stock, category_id) VALUES 
('MED001', 'Paracetamol 500mg', 'Analgésico y antipirético de uso común', 'TABLET', 0.5, 0.50, 1.20, 150, 300, 1),
('MED002', 'Ibuprofeno 400mg', 'Antiinflamatorio no esteroideo', 'TABLET', 0.6, 0.80, 2.00, 100, 200, 1),
('MED003', 'Amoxicilina 500mg', 'Antibiótico de amplio espectro', 'CAPSULE', 0.7, 2.50, 5.00, 80, 150, 1),
('VIT001', 'Vitamina C 1000mg', 'Suplemento vitamínico antioxidante', 'TABLET', 0.8, 2.00, 4.50, 60, 120, 2),
('VIT002', 'Complejo B', 'Vitaminas del complejo B', 'TABLET', 0.9, 3.00, 6.00, 40, 80, 2),
('VIT003', 'Omega 3', 'Ácidos grasos esenciales', 'SOFTGEL', 1.2, 5.00, 12.00, 30, 60, 2),
('CP001', 'Shampoo Anticaspa', 'Tratamiento para caspa y seborrea', 'BOTTLE', 250.0, 8.00, 15.00, 25, 50, 3),
('CP002', 'Crema Hidratante', 'Hidratación profunda para piel seca', 'TUBE', 100.0, 6.00, 12.00, 35, 70, 3),
('PA001', 'Vendas Elásticas', 'Vendaje elástico 10cm x 4.5m', 'ROLL', 50.0, 3.00, 7.00, 20, 40, 4),
('PA002', 'Alcohol Antiséptico', 'Alcohol etílico 70% - 250ml', 'BOTTLE', 250.0, 2.50, 5.50, 45, 90, 4),
('BB001', 'Pañales Talla M', 'Pañales desechables talla mediana', 'PACK', 800.0, 12.00, 18.00, 15, 30, 5),
('BB002', 'Leche de Fórmula', 'Fórmula infantil 0-6 meses - 900g', 'CAN', 900.0, 15.00, 25.00, 10, 25, 5);

-- Customers
INSERT INTO customers (first_name, last_name, email, phone_number, identification_number, date_of_birth, is_active) VALUES 
('Ana', 'López', 'ana.lopez@email.com', 5553333333, 11111111, '1990-05-15', 1),
('Pedro', 'Martínez', 'pedro.martinez@email.com', 5554444444, 22222222, '1985-08-20', 1),
('Carmen', 'Ruiz', 'carmen.ruiz@email.com', 5555555555, 33333333, '1992-12-10', 1),
('Roberto', 'Silva', 'roberto.silva@email.com', 5556666666, 44444444, '1988-03-25', 1),
('Elena', 'Torres', 'elena.torres@email.com', 5557777777, 55555555, '1995-07-08', 1),
('Miguel', 'Vargas', 'miguel.vargas@email.com', 5558888888, 66666666, '1983-11-30', 1);

-- Purchase Details
INSERT INTO purchase_details (total_amount, purchase_date, supplier_id, user_id) VALUES 
(1500.00, '2024-01-10', 1, 2),
(2300.50, '2024-01-15', 2, 2),
(890.75, '2024-02-01', 3, 3),
(1200.00, '2024-02-10', 1, 2),
(750.25, '2024-02-20', 2, 3);

-- Batches
INSERT INTO batches (quantity, expiration_date, product_id, purchase_detail_id) VALUES 
(100, '2025-12-31', 1, 1),
(50, '2025-11-30', 2, 1),
(75, '2026-06-15', 3, 2),
(60, '2025-10-31', 4, 2),
(40, '2025-09-30', 5, 3),
(30, '2026-03-31', 6, 3),
(25, '2025-08-31', 7, 4),
(35, '2025-07-31', 8, 4),
(20, '2025-12-31', 9, 5),
(45, '2025-11-30', 10, 5);

-- Invoices
INSERT INTO invoices (invoice_date, subtotal, discount, total, customer_id, user_id) VALUES 
('2024-01-20', 25.50, 2.50, 23.00, 1, 2),
('2024-01-21', 45.00, 0.00, 45.00, 2, 4),
('2024-01-22', 78.50, 5.00, 73.50, 3, 2),
('2024-02-01', 32.00, 3.00, 29.00, 4, 3),
('2024-02-02', 156.00, 10.00, 146.00, 5, 4),
('2024-02-03', 89.50, 4.50, 85.00, 6, 2),
('2024-02-05', 67.00, 0.00, 67.00, 1, 3);

-- Sale Details
INSERT INTO sale_details (quantity, invoice_id, product_id) VALUES 
(2, 1, 1),
(1, 1, 4),
(3, 2, 2),
(1, 2, 7),
(2, 3, 3),
(1, 3, 5),
(2, 3, 8),
(1, 4, 6),
(2, 4, 9),
(1, 5, 11),
(2, 5, 12),
(3, 5, 1),
(1, 6, 10),
(2, 6, 2),
(1, 7, 7),
(1, 7, 8);