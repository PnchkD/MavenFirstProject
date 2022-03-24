CREATE TABLE DEPARTMENT
(
    ID numeric PRIMARY KEY,
    NAME CHARACTER VARYING(100)
);

CREATE TABLE EMPLOYEE
(
    ID numeric PRIMARY KEY,
	DEPARTMENT_ID numeric,
	CHIEF_ID numeric,
    NAME CHARACTER VARYING(100),
	SALARY numeric,
	FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENT (ID),
	FOREIGN KEY (CHIEF_ID) REFERENCES EMPLOYEE (ID)
);

Insert into DEPARTMENT (ID, NAME)
Values (1, 'Руководство');

Insert into DEPARTMENT (ID, NAME)
Values (2, 'Секритариат');

Insert into DEPARTMENT (ID, NAME)
Values (3, 'IT');

Insert into DEPARTMENT (ID, NAME)
Values (4, 'Хоз.Отдел');
COMMIT;

Insert into EMPLOYEE (ID, DEPARTMENT_ID, NAME, CHIEF_ID, SALARY)
Values (1, 1, 'Иванов И.И.', NULL, 100000);

Insert into EMPLOYEE (ID, DEPARTMENT_ID, NAME, CHIEF_ID, SALARY)
Values (2, 1, 'Петров П.И.', 1, 90000);

Insert into EMPLOYEE (ID, DEPARTMENT_ID, NAME, CHIEF_ID, SALARY)
Values (3, 1, 'Сидоров А.Л.', 1, 95000);

Insert into EMPLOYEE (ID, DEPARTMENT_ID, NAME, CHIEF_ID, SALARY)
Values (4, 2, 'Смирнова О.П.', 1, 20000);

Insert into EMPLOYEE (ID, DEPARTMENT_ID, NAME, CHIEF_ID, SALARY)
Values (5, 3, 'Константинов Л.И.', 2, 80000);

Insert into EMPLOYEE (ID, DEPARTMENT_ID, NAME, CHIEF_ID, SALARY)
Values (6, 3, 'Федоров С.И.', 5, 100000);

Insert into EMPLOYEE (ID, DEPARTMENT_ID, NAME, CHIEF_ID, SALARY)
Values (7, 3, 'Самойлов Н.И.', 5, 100000);

Insert into EMPLOYEE (ID, DEPARTMENT_ID, NAME, CHIEF_ID, SALARY)
Values (8, 3, 'Никифоров С.П.', 7, 20000);

Insert into EMPLOYEE (ID, DEPARTMENT_ID, NAME, CHIEF_ID, SALARY)
Values (9, 4, 'Митрофанова М.С.', 3, 10000);
commit;