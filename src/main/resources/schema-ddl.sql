CREATE TABLE USERS (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	FIRST_NAME VARCHAR(50) NOT NULL,
	LAST_NAME VARCHAR(50) NOT NULL,
	EMAIL VARCHAR(100) UNIQUE NOT NULL,
	USERNAME VARCHAR(20) UNIQUE NOT NULL,
	PASSWORD VARCHAR(20) NOT NULL,
	CREATED_DATE DATE NOT NULL,
	UPDATED_DATE DATE NOT NULL,
	ROLE_ID BIGINT UNIQUE,
	PRIMARY KEY(ID),
	CONSTRAINT FK_UserRole FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ID);
);

CREATE TABLE ROLES (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	ROLE_NAME CHAR(10) UNIQUE NOT NULL,
	DESCRIPTION VARCHAR(250) NOT NULL,
	CREATED_BY VARCHAR(20) NOT NULL,
	CREATED_DATE DATE NOT NULL,
	UPDATED_BY VARCHAR(20) NOT NULL,
	UPDATED_DATE DATE NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE ACTIVITIES (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	USER_ID BIGINT,
	TITLE VARCHAR(20) NOT NULL,
	DESCRIPTION VARCHAR(500) NOT NULL,
	FEE DECIMAL(3,2) NOT NULL,
	CREATED_DATE DATE NOT NULL,
	UPDATED_DATE DATE NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT FK_UserActivity FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);

CREATE TABLE ACTIONS (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	ACTION_CODE CHAR(10) NOT NULL UNIQUE,
	DESCRIPTION VARCHAR(100) NOT NULL,
	CREATED_BY VARCHAR(20) NOT NULL,
	CREATED_DATE DATE NOT NULL,
	UPDATED_BY VARCHAR(20) NOT NULL,
	UPDATED_DATE DATE NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE TRANSACTIONS (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	ACTIVITY_ID BIGINT,
	ACTION_ID BIGINT,
	CREATE_DATE_TIME DATETIME NOT NULL,
	AMOUNT DECIMAL(3,2) NOT NULL,
	BALANCE DECIMAL(3,2) NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT FK_ActivityTransaction FOREIGN KEY (ACTIVITY_ID) REFERENCES ACTIVITIES (ID),
	CONSTRAINT FK_TransactionAction FOREIGN KEY (ACTION_ID) REFERENCES ACTIONS (ID)
);