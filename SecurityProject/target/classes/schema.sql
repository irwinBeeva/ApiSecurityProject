DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS roles;

CREATE TABLE usuario(
	id int PRIMARY KEY  auto_increment,
	username VARCHAR(50),
	password VARCHAR(50),
	account_non_expired bit,
	account_non_locked bit,
	credentials_non_expired bit,
	enabled bit
);

create table roles(
	id int primary key auto_increment,
	authority varchar(50),
	user_id int,
	FOREIGN KEY (user_id) REFERENCES usuario(id)
);