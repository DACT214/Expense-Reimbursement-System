--account table=============================
DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
account_id 			SERIAL PRIMARY KEY,
"position"  		varchar (10)  NOT NULL DEFAULT 'employee',
username 			varchar (20) UNIQUE NOT NULL,
"password" 			varchar (20) UNIQUE NOT NULL,
first_name 			VARCHAR(50) NOT NULL,
last_name 			varchar(50) NOT NULL
);


INSERT
	INTO
	accounts ("position",
	username,
	"password",
	first_name,
	last_name)
VALUES ('manager',
'JS123',
'asdf123',
'John',
'Smith');

INSERT
	INTO
	accounts (
	username,
	"password",
	first_name,
	last_name)
VALUES (
'JB098',
'asdf098',
'Jane',
'Baker');

INSERT
	INTO
	accounts (
	username,
	"password",
	first_name,
	last_name)
VALUES (
'MC456',
'asdf456',
'Mark',
'Curry');

-- request table ============================================

DROP TABLE IF EXISTS ers_db.requests;
CREATE TABLE requests (
request_id 			SERIAL PRIMARY KEY,
username 			varchar (20) NOT NULL,
first_name 			VARCHAR(50) NOT NULL,
last_name 			varchar(50) NOT NULL,
request_ammount		NUMERIC(18,2) NOT NULL,
status				varchar(10) NOT NULL DEFAULT 'pending',
 FOREIGN KEY (username) REFERENCES accounts(username)
);

INSERT 
	INTO requests
	(username,
	first_name,
	last_name,
	request_ammount)
	VALUES(
	'JB098',
	'Jane',
	'Baker',
	'500'
	)
	
--	SELECT * FROM ers_db.requests WHERE username='MC456';
INSERT INTO ers_db.requests(
				username,
				first_name,
				last_name,
				request_ammount)
				VALUES(
				'JB098',
				(SELECT first_name FROM ers_db.accounts WHERE username='JB098'),
				(SELECT last_name FROM ers_db.accounts WHERE username='JB098'),
				'321'
				);
			
			
UPDATE ers_db.requests SET status = 'accepted' WHERE request_id = '1';

DELETE FROM ers_db.requests WHERE request_id = '8';

