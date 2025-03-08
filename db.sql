create table if not exists medicine_2.Accounts
(
    id       int auto_increment
    primary key,
    username varchar(255) not null,
    password varchar(255) not null
    );

create table if not exists medicine_2.Patients
(
    id      int auto_increment
    primary key,
    name    varchar(255)                     not null,
    age     int                              not null,
    gender  enum ('Male', 'Female', 'Other') not null,
    address varchar(255)                     null,
    phone   varchar(15)                      null
    );

create table if not exists medicine_2.Supplements
(
    id            int auto_increment
    primary key,
    medicine_name varchar(255)                                       not null,
    quantity      int                                                not null,
    dose_per_day  int                                                not null,
    dosage        varchar(255)                                       not null,
    expired_date  date                                               not null,
    availability  enum ('Out of stock', '30 days left', 'Available') not null,
    open_date     date                                               null
    );

create table if not exists medicine_2.PatientMedications
(
    id              int auto_increment
    primary key,
    patient_id      int  not null,
    supplement_id   int  null,
    medication_time time null,
    quantity        int  null,
    constraint patientmedications_ibfk_1
    foreign key (patient_id) references medicine_2.Patients (id)
    on delete cascade,
    constraint patientmedications_ibfk_2
    foreign key (supplement_id) references medicine_2.Supplements (id)
    on delete cascade
    );

create index patient_id
    on medicine_2.PatientMedications (patient_id);

create index supplement_id
    on medicine_2.PatientMedications (supplement_id);

create definer = root@`%` trigger medicine_2.update_dose_per_day
    after insert
    on medicine_2.PatientMedications
    for each row
BEGIN
UPDATE Supplements s
SET s.dose_per_day = (
    SELECT COALESCE(SUM(pm.quantity), 0)
    FROM PatientMedications pm
    WHERE pm.supplement_id = NEW.supplement_id
)
WHERE s.id = NEW.supplement_id;
END;

create definer = root@`%` trigger medicine_2.update_dose_per_day_on_update
    after update
                     on medicine_2.PatientMedications
                     for each row
BEGIN
    IF OLD.supplement_id IS NOT NULL THEN
UPDATE Supplements s
SET s.dose_per_day = (
    SELECT COALESCE(SUM(pm.quantity), 0)
    FROM PatientMedications pm
    WHERE pm.supplement_id = OLD.supplement_id
)
WHERE s.id = OLD.supplement_id;
END IF;

    IF NEW.supplement_id IS NOT NULL THEN
UPDATE Supplements s
SET s.dose_per_day = (
    SELECT COALESCE(SUM(pm.quantity), 0)
    FROM PatientMedications pm
    WHERE pm.supplement_id = NEW.supplement_id
)
WHERE s.id = NEW.supplement_id;
END IF;
END;

