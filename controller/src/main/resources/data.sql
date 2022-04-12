insert into users(id, date_of_creation, date_of_last_update, email, first_name, last_name, login, password)
    values(0, current_timestamp, current_timestamp, 'diamondpinchuk@gmail.com', 'admin', 'admin', 'master', '$2a$10$/PMI5Ud64FX8nkN.3phx5uZPOy6r/11wvYqeLr0mZZ4q/8xpy81x6');

insert into users(id, date_of_creation, date_of_last_update, email, first_name, last_name, login, password)
values(1, current_timestamp, current_timestamp, 'user1@gmail.com', 'aaaaaa', 'aaaaa', 'useruser', '$2a$10$mN3BDp61yIuqFAmquP.sTemA2F0uX7ncekGEchSxrUuq6lC7l996y');

insert into users(id, date_of_creation, date_of_last_update, email, first_name, last_name, login, password)
values(2, current_timestamp, current_timestamp, 'user2@gmail.com', 'bbbb', 'bbbb', 'useruseruser', '$2a$10$sk3KxgieVacO4o1NRv6n4.qxuBKm53J78PBf1pYQ8TN5sKICXssaG');

insert into roles (id, date_of_creation, date_of_last_update, name)
    values (0, current_timestamp, current_timestamp, 'ADMIN');

insert into roles (id, date_of_creation, date_of_last_update, name)
    values (1, current_timestamp, current_timestamp, 'USER');

insert into user_roles (user_id, role_id)
    values (0, 0);
insert into user_roles (user_id, role_id)
    values (1, 1);
insert into user_roles (user_id, role_id)
    values (2, 1);
