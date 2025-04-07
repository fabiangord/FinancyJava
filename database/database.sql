CREATE TABLE budgets (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value BIGINT NOT NULL,
    date DATE NOT NULL DEFAULT (CURDATE())
);

CREATE TABLE incomes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value BIGINT NOT NULL,
    category ENUM("DWELLING", "TRANSPORT", "FOOD", "SALARY", "INVERSION", "INCOME", "OTHER") NOT NULL,
    date DATE NOT NULL DEFAULT (CURDATE())
);

CREATE TABLE savings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value BIGINT NOT NULL,
    category ENUM("DWELLING", "TRANSPORT", "FOOD", "SALARY", "INVERSION", "INCOME", "OTHER") NOT NULL,
    date DATE NOT NULL DEFAULT (CURDATE())
);

CREATE TABLE expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    value BIGINT NOT NULL,
    category ENUM("DWELLING", "TRANSPORT", "FOOD", "SALARY", "INVERSION", "INCOME", "OTHER") NOT NULL,
    date DATE NOT NULL DEFAULT (CURDATE())
);

CREATE TABLE investments(
    id INT AUTO_INCREMENT PRIMARY KEY,
    concept varchar(50) NOT NULL, 
    investment BIGINT NOT NULL, 
    interest float NOT NULL, 
    months int NOT NULL, 
    feeBack BIGINT
);

CREATE TABLE projections (
    id INT AUTO_INCREMENT PRIMARY KEY,
    projected_budgets BIGINT NOT NULL,
    projected_expenses BIGINT NOT NULL,
    projected_incomes BIGINT NOT NULL,
    projected_savings BIGINT NOT NULL,
    months INT(12) NOT NULL
);

INSERT INTO budgets (name, value) VALUES
('Presupuesto Mensual', 1000000),
('Presupuesto Anual', 12000000),
('Presupuesto Extra', 500000);

-- Tabla incomes
INSERT INTO incomes (name, value) VALUES
('Salario', 1500000),
('Bonificación', 200000),
('Freelance', 800000);

-- Tabla savings
INSERT INTO savings (name, value, category) VALUES
('Fondo de Emergencia', 500000, 'INCOME'),
('Inversión en Bolsa', 1000000, 'INVERSION'),
('Ahorro para Vivienda', 2000000, 'DWELLING');

-- Tabla expenses
INSERT INTO expenses (name, value, category) VALUES
('Compra en Supermercado', 200000, 'OTHER'),
('Pago de Servicios', 300000, 'OTHER'),
('Inversión en Capacitación', 150000, 'INVERSION');

-- Tabla projections
INSERT INTO projections (projected_budgets, projected_expenses, projected_incomes, projected_savings, months) VALUES
(1000000, 800000, 1200000, 400000, 1),
(5000000, 4500000, 6000000, 1500000, 6),
(12000000, 10000000, 15000000, 5000000, 12);