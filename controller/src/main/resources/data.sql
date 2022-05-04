insert into users(id, date_of_creation, date_of_last_update, email, first_name, last_name, login, password)
    values(0, current_timestamp, current_timestamp, 'diamondpinchuk@gmail.com', 'admin', 'admin', 'master', '$2a$10$/PMI5Ud64FX8nkN.3phx5uZPOy6r/11wvYqeLr0mZZ4q/8xpy81x6');

insert into users(id, date_of_creation, date_of_last_update, email, first_name, last_name, login, password)
values(1, current_timestamp, current_timestamp, 'user@gmail.com', 'dmitriy', 'pinchuk', 'useruser', '$2a$10$/PMI5Ud64FX8nkN.3phx5uZPOy6r/11wvYqeLr0mZZ4q/8xpy81x6');

insert into users(id, date_of_creation, date_of_last_update, email, first_name, last_name, login, password)
values(2, current_timestamp, current_timestamp, 'autopicker@gmail.com', 'dmitriy', 'pinchuk', 'autopicker', '$2a$10$/PMI5Ud64FX8nkN.3phx5uZPOy6r/11wvYqeLr0mZZ4q/8xpy81x6');

insert into roles (id, date_of_creation, date_of_last_update, name)
    values (0, current_timestamp, current_timestamp, 'ADMIN');

insert into roles (id, date_of_creation, date_of_last_update, name)
    values (1, current_timestamp, current_timestamp, 'USER');

insert into roles (id, date_of_creation, date_of_last_update, name)
values (2, current_timestamp, current_timestamp, 'AUTOPICKER');

insert into user_roles (user_id, role_id)
    values (0, 0);
insert into user_roles (user_id, role_id)
    values (1, 1);
insert into user_roles (user_id, role_id)
    values (2, 2);
