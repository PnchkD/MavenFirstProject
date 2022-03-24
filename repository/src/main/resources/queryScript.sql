select * from employee;
select * from department;

/* Вывести список сотрудников, получающих заработную плату большую чем у непосредственного руководителя */
select emp2.name, emp2.chief_id 
from employee as emp1
join employee as emp2
on (emp1.id = emp2.chief_id)
where emp2.salary > emp1.salary;

/*Вывести список сотрудников, получающих максимальную заработную плату в своем отделе*/
select emp1.name, emp1.department_id 
from employee as emp1
where emp1.salary = (select max(emp2.salary) 
					 from employee as emp2 
					 where emp2.department_id = emp1.department_id)
					 
/* Вывести список ID отделов, количество сотрудников в которых не превышает 3 человек */
select dep.id 
from department as dep
where (select count(*) 
	from employee as emp 
	where emp.department_id = dep.id)
	< 3;
	 
/* Вывести список сотрудников, не имеющих назначенного руководителя, который работает в том-же отделе */
select emp2.name, emp2.chief_id, emp2.department_id 
from employee as emp1
join employee as emp2
on (emp1.id = emp2.chief_id and emp1.department_id != emp2.department_id)

/* Найти список ID отделов с максимальной суммарной зарплатой сотрудников */
select dep.id, sum(emp.salary) 
	from employee as emp 
	join department as dep
	on emp.department_id = dep.id
	group by dep.id