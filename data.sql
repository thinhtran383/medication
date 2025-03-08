-- Insert data into Patients
INSERT INTO Patients (name, age, gender, address, phone)
VALUES ('John Doe', 30, 'Male', '123 Elm Street', '1234567890'),
       ('Jane Smith', 28, 'Female', '456 Oak Avenue', '0987654321'),
       ('Alice Johnson', 45, 'Female', '789 Pine Road', '1122334455'),
       ('Bob Brown', 52, 'Male', '321 Cedar Lane', '2233445566'),
       ('Charlie Davis', 37, 'Other', '654 Maple Street', '3344556677'),
       ('Diana White', 41, 'Female', '987 Birch Boulevard', '4455667788'),
       ('Ethan Hall', 29, 'Male', '741 Spruce Court', '5566778899'),
       ('Fiona Green', 33, 'Female', '852 Redwood Drive', '6677889900'),
       ('George King', 50, 'Male', '963 Cypress Way', '7788990011'),
       ('Hannah Scott', 26, 'Female', '147 Sequoia Square', '8899001122');

-- Insert data into Supplements
INSERT INTO Supplements (medicine_name, quantity, dose_per_day, dosage, expired_date, availability, open_date)
VALUES ('Paracetamol', 100, 2, '500mg', '2026-01-01', 'Available', '2025-03-01'),
       ('Ibuprofen', 150, 3, '200mg', '2025-12-15', 'Available', '2025-02-20'),
       ('Aspirin', 200, 1, '300mg', '2025-11-30', '30 days left', '2025-02-10'),
       ('Amoxicillin', 80, 2, '250mg', '2026-02-28', 'Available', '2025-03-02'),
       ('Cough Syrup', 50, 1, '10ml', '2025-10-10', 'Out of stock', NULL),
       ('Vitamin C', 300, 1, '500mg', '2026-03-15', 'Available', '2025-03-03'),
       ('Metformin', 90, 2, '850mg', '2025-09-20', '30 days left', '2025-02-25'),
       ('Lisinopril', 60, 1, '10mg', '2025-08-25', 'Available', '2025-02-18'),
       ('Atorvastatin', 120, 1, '20mg', '2026-05-05', 'Available', '2025-03-05'),
       ('Cetirizine', 140, 1, '10mg', '2025-07-12', 'Available', '2025-02-22');

-- Insert data into PatientMedications
INSERT INTO PatientMedications (patient_id, supplement_id, medication_time, quantity)
VALUES (1, 1, '08:00:00', 2),
       (2, 2, '12:00:00', 3),
       (3, 3, '18:00:00', 1),
       (4, 4, '09:00:00', 2),
       (5, 5, '22:00:00', 1),
       (6, 6, '07:30:00', 1),
       (7, 7, '11:45:00', 2),
       (8, 8, '20:15:00', 1),
       (9, 9, '16:00:00', 1),
       (10, 10, '14:30:00', 1);