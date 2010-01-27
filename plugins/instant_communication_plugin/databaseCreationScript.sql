create database om_server;
use om_server;
create table logged_users ( user_hash varchar(160) primary key, last_present int(15), state int, user_data_escaped varchar(256), client_id varchar(100));
create table calls ( from_id varchar(160), to_id varchar(160), last_updated int(15), state int);