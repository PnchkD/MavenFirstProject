insert into users(id, date_of_creation, date_of_last_update, email, first_name, last_name, login, password)
    values(0, current_timestamp, current_timestamp, 'diamondpinchuk@gmail.com', 'admin', 'admin', 'master', '$2a$10$/PMI5Ud64FX8nkN.3phx5uZPOy6r/11wvYqeLr0mZZ4q/8xpy81x6');

insert into user_roles (id, date_of_creation, date_of_last_update, name, user_id)
    values (0, current_timestamp, current_timestamp, 'ADMIN', 0);
