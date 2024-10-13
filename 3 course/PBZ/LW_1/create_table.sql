CREATE TABLE suppliers (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    status INTEGER NOT NULL,
    city VARCHAR(30) NOT NULL
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    color VARCHAR(30) NOT NULL,
    size INTEGER NOT NULL,
    city VARCHAR(30) NOT NULL
);

CREATE TABLE projects (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    city VARCHAR(30) NOT NULL
);

CREATE TABLE counts (
    supplier_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    project_id INTEGER NOT NULL,
    count INTEGER NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);