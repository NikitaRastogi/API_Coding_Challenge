ALTER TABLE user ADD username varchar(10);
ALTER TABLE user ADD password varchar(10);
ALTER TABLE user ADD loggedIn boolean;
select * from user;
ALTER TABLE user DROP name, DROP age, DROP ADDRESS, DROP gender;
Alter TABLE user DROP ID;
insert into user(username,password,id) values ("abc","abc",1);  
delete from user where username IS NULL;
ALTER TABLE user ADD primary key(id);
truncate user;
ALTER TABLE user ADD unique(username);

Alter TABLE user DROP loggedIn;
ALTER TABLE user ADD loggedin boolean;
