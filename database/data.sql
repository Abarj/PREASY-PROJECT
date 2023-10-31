INSERT INTO users (username, password, name, surname, company_email, personal_email, city, active, created_date) VALUES
('usuario1', 'password123', 'Nombre1', 'Apellido1', 'usuario1@company.com', 'usuario1@example.com', 'Ciudad1', true, CURRENT_TIMESTAMP),
('usuario2', 'password123', 'Nombre2', 'Apellido2', 'usuario2@company.com', 'usuario2@example.com', 'Ciudad2', true, CURRENT_TIMESTAMP),
('usuario3', 'password123', 'Nombre3', 'Apellido3', 'usuario3@company.com', 'usuario3@example.com', 'Ciudad3', true, CURRENT_TIMESTAMP),
('usuario4', 'password123', 'Nombre4', 'Apellido4', 'usuario4@company.com', 'usuario4@example.com', 'Ciudad4', true, CURRENT_TIMESTAMP),
('usuario5', 'password123', 'Nombre5', 'Apellido5', 'usuario5@company.com', 'usuario5@example.com', 'Ciudad5', true, CURRENT_TIMESTAMP);

INSERT INTO project (project_name, description, start_date, estimated_end_date, status, users_id) VALUES
('Proyecto 1', 'Descripción 1', '2023-10-01 00:00:00', '2023-12-01 00:00:00', 'IN_PROGRESS', 1),
('Proyecto 2', 'Descripción 2', '2023-10-02 00:00:00', '2023-12-02 00:00:00', 'IN_PROGRESS', 2),
('Proyecto 3', 'Descripción 3', '2023-10-03 00:00:00', '2023-12-03 00:00:00', 'IN_PROGRESS', 3),
('Proyecto 4', 'Descripción 4', '2023-10-04 00:00:00', '2023-12-04 00:00:00', 'IN_PROGRESS', 4),
('Proyecto 5', 'Descripción 5', '2023-10-05 00:00:00', '2023-12-05 00:00:00', 'IN_PROGRESS', 5),
('Proyecto 6', 'Descripción 6', '2023-10-06 00:00:00', '2023-12-06 00:00:00', 'IN_PROGRESS', 1),
('Proyecto 7', 'Descripción 7', '2023-10-07 00:00:00', '2023-12-07 00:00:00', 'IN_PROGRESS', 1),
('Proyecto 8', 'Descripción 8', '2023-10-08 00:00:00', '2023-12-08 00:00:00', 'IN_PROGRESS', 1),
('Proyecto 9', 'Descripción 9', '2023-10-09 00:00:00', '2023-12-09 00:00:00', 'IN_PROGRESS', 4),
('Proyecto 10', 'Descripción 10', '2023-10-10 00:00:00', '2023-12-10 00:00:00', 'IN_PROGRESS', 5);

INSERT INTO task (task_name, description, start_date, due_date, status, priority, project_id)
VALUES
('Tarea 1', 'Descripción Tarea 1', '2023-10-25 10:00:00', '2023-10-30 17:00:00', 'TO_DO', 3, FLOOR(RANDOM() * 5 + 1)),
('Tarea 2', 'Descripción Tarea 2', '2023-10-25 10:15:00', '2023-10-30 17:15:00', 'IN_PROGRESS', 2, FLOOR(RANDOM() * 5 + 1)),
('Tarea 3', 'Descripción Tarea 3', '2023-10-26 10:00:00', '2023-11-01 17:00:00', 'TO_DO', 1, FLOOR(RANDOM() * 5 + 1)),
('Tarea 4', 'Descripción Tarea 4', '2023-10-26 11:00:00', '2023-11-02 17:00:00', 'IN_PROGRESS', 2, FLOOR(RANDOM() * 5 + 1)),
('Tarea 5', 'Descripción Tarea 5', '2023-10-27 10:00:00', '2023-11-03 17:00:00', 'DONE', 3, FLOOR(RANDOM() * 5 + 1)),
('Tarea 6', 'Descripción Tarea 6', '2023-10-28 10:00:00', '2023-11-04 17:00:00', 'TO_DO', 4, FLOOR(RANDOM() * 5 + 1)),
('Tarea 7', 'Descripción Tarea 7', '2023-10-29 10:00:00', '2023-11-05 17:00:00', 'IN_PROGRESS', 5, FLOOR(RANDOM() * 5 + 1)),
('Tarea 8', 'Descripción Tarea 8', '2023-10-30 10:00:00', '2023-11-06 17:00:00', 'DONE', 1, FLOOR(RANDOM() * 5 + 1)),
('Tarea 9', 'Descripción Tarea 9', '2023-10-31 10:00:00', '2023-11-07 17:00:00', 'TO_DO', 2, FLOOR(RANDOM() * 5 + 1)),
('Tarea 10', 'Descripción Tarea 10', '2023-11-01 10:00:00', '2023-11-08 17:00:00', 'IN_PROGRESS', 3, FLOOR(RANDOM() * 5 + 1)),
('Tarea 11', 'Descripción Tarea 11', '2023-11-02 10:00:00', '2023-11-09 17:00:00', 'DONE', 4, FLOOR(RANDOM() * 5 + 1)),
('Tarea 12', 'Descripción Tarea 12', '2023-11-03 10:00:00', '2023-11-10 17:00:00', 'TO_DO', 5, FLOOR(RANDOM() * 5 + 1)),
('Tarea 13', 'Descripción Tarea 13', '2023-11-04 10:00:00', '2023-11-11 17:00:00', 'IN_PROGRESS', 1, FLOOR(RANDOM() * 5 + 1)),
('Tarea 14', 'Descripción Tarea 14', '2023-11-05 10:00:00', '2023-11-12 17:00:00', 'DONE', 2, FLOOR(RANDOM() * 5 + 1)),
('Tarea 15', 'Descripción Tarea 15', '2023-11-06 10:00:00', '2023-11-13 17:00:00', 'TO_DO', 3, FLOOR(RANDOM() * 5 + 1)),
('Tarea 16', 'Descripción Tarea 16', '2023-11-07 10:00:00', '2023-11-14 17:00:00', 'IN_PROGRESS', 4, FLOOR(RANDOM() * 5 + 1)),
('Tarea 17', 'Descripción Tarea 17', '2023-11-08 10:00:00', '2023-11-15 17:00:00', 'DONE', 5, FLOOR(RANDOM() * 5 + 1)),
('Tarea 18', 'Descripción Tarea 18', '2023-11-09 10:00:00', '2023-11-16 17:00:00', 'TO_DO', 1, FLOOR(RANDOM() * 5 + 1)),
('Tarea 19', 'Descripción Tarea 19', '2023-11-10 10:00:00', '2023-11-17 17:00:00', 'IN_PROGRESS', 2, FLOOR(RANDOM() * 5 + 1)),
('Tarea 20', 'Descripción Tarea 20', '2023-11-11 10:00:00', '2023-11-18 17:00:00', 'DONE', 3, FLOOR(RANDOM() * 5 + 1));
