create table journal
(
    id    bigserial primary key,
    title varchar(255) not null
);

create table subject
(
    id         bigserial primary key,
    title      varchar(255) not null,
    journal_id bigint       not null
);

create table student
(
    id   bigserial primary key,
    name varchar(255) not null
);

create table student_subject
(
    id         bigserial primary key,
    student_id bigint not null,
    subject_id bigint not null
);

create table mark
(
    id         bigserial primary key,
    point      int    not null,
    is_present boolean default false,
    student_id bigint not null,
    subject_id bigint not null
);

-- Insert data into the journal table
INSERT INTO journal (title)
VALUES ('Journal of Science');

-- Insert data into the subject table
INSERT INTO subject (title, journal_id)
VALUES ('Biology', 1),
       ('Chemistry', 1),
       ('Physics', 1);

INSERT INTO student (name)
VALUES ('Emma'),
       ('Liam'),
       ('Olivia'),
       ('Noah'),
       ('Ava');

INSERT INTO student_subject (student_id, subject_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 2),
       (3, 3),
       (4, 1),
       (4, 2),
       (4, 3),
       (5, 1),
       (5, 2),
       (5, 3);

INSERT INTO mark (point, is_present, student_id, subject_id)
VALUES (85, true, 1, 1),
       (78, true, 1, 2),
       (92, true, 1, 3),
       (90, true, 2, 1),
       (82, true, 2, 2),
       (88, true, 2, 3),
       (79, true, 3, 1),
       (85, true, 3, 2),
       (90, true, 3, 3),
       (88, true, 4, 1),
       (90, true, 4, 2),
       (85, true, 4, 3),
       (92, true, 5, 1),
       (80, true, 5, 2),
       (86, true, 5, 3);
