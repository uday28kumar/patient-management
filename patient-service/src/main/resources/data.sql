CREATE TABLE IF NOT EXISTS patient (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    dob DATE NOT NULL,
    address TEXT NOT NULL,
    reg_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert 10 dummy patients
INSERT INTO patient (id, name, email, dob, address, reg_time)
VALUES
  ('1a2b3c4d-0000-0000-0000-000000000001', 'John Doe', 'john@example.com', '1990-01-01', '123 Main St', NOW()),
  ('1a2b3c4d-0000-0000-0000-000000000002', 'Jane Smith', 'jane@example.com', '1985-05-10', '456 Oak Ave', NOW()),
  ('1a2b3c4d-0000-0000-0000-000000000003', 'Alice Johnson', 'alice@example.com', '1978-07-21', '789 Pine Rd', NOW()),
  ('1a2b3c4d-0000-0000-0000-000000000004', 'Bob Brown', 'bob@example.com', '1992-03-12', '321 Cedar Blvd', NOW()),
  ('1a2b3c4d-0000-0000-0000-000000000005', 'Carol White', 'carol@example.com', '1988-08-08', '654 Elm St', NOW()),
  ('1a2b3c4d-0000-0000-0000-000000000006', 'David Green', 'david@example.com', '1995-09-09', '987 Birch Pl', NOW()),
  ('1a2b3c4d-0000-0000-0000-000000000007', 'Eva Black', 'eva@example.com', '1980-10-10', '159 Maple Ln', NOW()),
  ('1a2b3c4d-0000-0000-0000-000000000008', 'Frank King', 'frank@example.com', '1975-11-11', '753 Walnut Dr', NOW()),
  ('1a2b3c4d-0000-0000-0000-000000000009', 'Grace Lee', 'grace@example.com', '2000-12-12', '852 Fir Ct', NOW()),
  ('1a2b3c4d-0000-0000-0000-000000000010', 'Henry Young', 'henry@example.com', '1993-04-04', '951 Ash Ter', NOW());
