INSERT INTO groups(name) VALUES('computer_science');
INSERT INTO groups(name) VALUES('linguistics');

INSERT INTO students(name, group_id) VALUES('Alex', 1);
INSERT INTO students(name, group_id) VALUES('Bohdan', 1);
INSERT INTO students(name, group_id) VALUES('Dmitry', 1);
INSERT INTO students(name, group_id) VALUES('Max', 1);
INSERT INTO students(name, group_id) VALUES('Sergey', 1);
INSERT INTO students(name, group_id) VALUES('Anna', 1);
INSERT INTO students(name, group_id) VALUES('Boris', 2);
INSERT INTO students(name, group_id) VALUES('Vitaliy', 2);
INSERT INTO students(name, group_id) VALUES('Anton', 2);
INSERT INTO students(name, group_id) VALUES('Olga', 2);
INSERT INTO students(name, group_id) VALUES('Victoria', 2);
INSERT INTO students(name, group_id) VALUES('Anastasiia', 2);
INSERT INTO students(name, group_id) VALUES('Victor', 1);
INSERT INTO students(name, group_id) VALUES('Pavel', 1);
INSERT INTO students(name, group_id) VALUES('Mikhail', 2);
INSERT INTO students(name, group_id) VALUES('Ivan', 2);

INSERT INTO subjects(name, description) VALUES('math','math');
INSERT INTO subjects(name, description) VALUES('physics','physics');
INSERT INTO subjects(name, description) VALUES('programming','programming');
INSERT INTO subjects(name, description) VALUES('history','history');
INSERT INTO subjects(name, description) VALUES('philosophy','philosophy');
INSERT INTO subjects(name, description) VALUES('english','english');
INSERT INTO subjects(name, description) VALUES('law','law');
INSERT INTO subjects(name, description) VALUES('systems_engineering','systems_engineering');
INSERT INTO subjects(name, description) VALUES('information_security','information_security');
INSERT INTO subjects(name, description) VALUES('computer_arch','computer_arch');
INSERT INTO subjects(name, description) VALUES('french','french');
INSERT INTO subjects(name, description) VALUES('spanish','spanish');

INSERT INTO teachers(name, experience, subject_id) VALUES('Ivanov',10,1);
INSERT INTO teachers(name, experience, subject_id) VALUES('Petrov',15,2);
INSERT INTO teachers(name, experience, subject_id) VALUES('Sydorov',14,3);
INSERT INTO teachers(name, experience, subject_id) VALUES('Stepanov',20,4);
INSERT INTO teachers(name, experience, subject_id) VALUES('Devterov',7,5);
INSERT INTO teachers(name, experience, subject_id) VALUES('Leonova',17,6);
INSERT INTO teachers(name, experience, subject_id) VALUES('Bylyk',9,7);
INSERT INTO teachers(name, experience, subject_id) VALUES('Shapoval',12,8);
INSERT INTO teachers(name, experience, subject_id) VALUES('Sydorenko',5,9);
INSERT INTO teachers(name, experience, subject_id) VALUES('Doniy',18,10);
INSERT INTO teachers(name, experience, subject_id) VALUES('Timoshenko',3,11);
INSERT INTO teachers(name, experience, subject_id) VALUES('Gerasimova',22,12);

INSERT INTO education_programmes(group_id, subject_id) VALUES(1,1);
INSERT INTO education_programmes(group_id, subject_id) VALUES(1,2);
INSERT INTO education_programmes(group_id, subject_id) VALUES(1,3);
INSERT INTO education_programmes(group_id, subject_id) VALUES(1,8);
INSERT INTO education_programmes(group_id, subject_id) VALUES(1,9);
INSERT INTO education_programmes(group_id, subject_id) VALUES(1,10);
INSERT INTO education_programmes(group_id, subject_id) VALUES(2,4);
INSERT INTO education_programmes(group_id, subject_id) VALUES(2,5);
INSERT INTO education_programmes(group_id, subject_id) VALUES(2,6);
INSERT INTO education_programmes(group_id, subject_id) VALUES(2,7);
INSERT INTO education_programmes(group_id, subject_id) VALUES(2,11);
INSERT INTO education_programmes(group_id, subject_id) VALUES(2,12);