create database if not exists apache_dbcp;
use apache_dbcp;

create table user(
  user_id int unsigned auto_increment,
  username char(20) not null ,
  password varchar(30) not null ,
  create_datetime datetime default current_timestamp()
    on update current_timestamp(),
  primary key (user_id),
  unique (username)
);

insert into user(username, password)
VALUES('Jon Snow', '124asf%d')
;
select * from user;

