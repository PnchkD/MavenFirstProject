insert into users(id, date_of_creation, date_of_last_update, email, first_name, last_name, login, password)
    values(0, current_timestamp, current_timestamp, 'diamondpinchuk@gmail.com', 'admin', 'admin', 'master', '$2a$10$/PMI5Ud64FX8nkN.3phx5uZPOy6r/11wvYqeLr0mZZ4q/8xpy81x6');

insert into roles (id, date_of_creation, date_of_last_update, name)
    values (0, current_timestamp, current_timestamp, 'ADMIN');

insert into roles (id, date_of_creation, date_of_last_update, name)
    values (1, current_timestamp, current_timestamp, 'USER');

insert into roles (id, date_of_creation, date_of_last_update, name)
values (2, current_timestamp, current_timestamp, 'AUTOPICKER');

insert into user_roles (user_id, role_id)
    values (0, 0);

insert into car_brands (id, date_of_creation, date_of_last_update, name)
values (1, current_timestamp, current_timestamp, 'Nissan');

insert into car_brands (id, date_of_creation, date_of_last_update, name)
values (2, current_timestamp, current_timestamp, 'Porsche');

insert into car_brands (id, date_of_creation, date_of_last_update, name)
values (3, current_timestamp, current_timestamp, 'Audi');

insert into car_brands (id, date_of_creation, date_of_last_update, name)
values (4, current_timestamp, current_timestamp, 'Hyundai');

insert into car_brands (id, date_of_creation, date_of_last_update, name)
values (5, current_timestamp, current_timestamp, 'Ford');

insert into car_brands (id, date_of_creation, date_of_last_update, name)
values (6, current_timestamp, current_timestamp, 'Volkswagen');

insert into car_brands (id, date_of_creation, date_of_last_update, name)
values (7, current_timestamp, current_timestamp, 'Honda');

insert into car_brands (id, date_of_creation, date_of_last_update, name)
values (8, current_timestamp, current_timestamp, 'BMW');

insert into car_brands (id, date_of_creation, date_of_last_update, name)
values (9, current_timestamp, current_timestamp, 'Mercedes-Benz');

insert into car_brands (id, date_of_creation, date_of_last_update, name)
values (10, current_timestamp, current_timestamp, 'Toyota');