insert into Users (login, password, emailAddress)
values ('raku', 'abe31fe1a2113e7e8bf174164515802806d388cf4f394cceace7341a182271ab', 'raku@raku.pl');
insert into Users (login, password, emailAddress)
values ('rzepa', 'abe31fe1a2113e7e8bf174164515802806d388cf4f394cceace7341a182271ab', 'rzepa@raku.pl');
insert into Users (login, password, emailAddress)
values ('polo', 'abe31fe1a2113e7e8bf174164515802806d388cf4f394cceace7341a182271ab', 'polo@polo.pl');
insert into Users (login, password, emailAddress)
values ('seba', 'abe31fe1a2113e7e8bf174164515802806d388cf4f394cceace7341a182271ab', 'seba@seba.pl');
insert into Users (login, password, emailAddress)
values ('jozef', 'abe31fe1a2113e7e8bf174164515802806d388cf4f394cceace7341a182271ab', 'jozef@jozef.pl');
insert into Users (login, password, emailAddress)
values ('test', 'abe31fe1a2113e7e8bf174164515802806d388cf4f394cceace7341a182271ab', 'test@raku.pl');

insert into Categories (categoryName)
values ('Ogłoszenia');
insert into Categories (categoryName)
values ('Podstawy Informatyki');
insert into Categories (categoryName)
values ('Różne');

insert into Topics (userID, categoryID, topicName, date)
values (1, 1, 'Temat 1', '2015-04-14 15:34:09' );
insert into Topics (userID, categoryID, topicName, date)
values (5, 1, 'Temat 2', '2015-04-14 15:34:09' );
insert into Topics (userID, categoryID, topicName, date)
values (2, 1, 'Temat 3', '2015-04-14 15:34:09' );
insert into Topics (userID, categoryID, topicName, date)
values (3, 1, 'Temat 4', '2015-04-14 15:34:09' );
insert into Topics (userID, categoryID, topicName, date)
values (3, 1, 'Temat 5', '2015-04-14 15:34:09' );
insert into Topics (userID, categoryID, topicName, date)
values (3, 1, 'Temat 6', '2015-04-14 15:34:09' );
insert into Topics (userID, categoryID, topicName, date)
values (4, 1, 'Temat 7', '2015-04-14 15:34:09' );
insert into Topics (userID, categoryID, topicName, date)
values (4, 1, 'Temat 8', '2015-04-14 15:34:09' );
insert into Topics (userID, categoryID, topicName, date)
values (5, 1, 'Temat 9', '2015-04-14 15:34:09' );
insert into Topics (userID, categoryID, topicName, date)
values (6, 1, 'I like bananana', '2015-04-14 15:34:09' );

insert into Reply (userID, topicID, replyText, date) 
values (1, 1, 'Reply 1', '2015-04-14 16:39:09');
insert into Reply (userID, topicID, replyText, date) 
values (4, 1, 'Reply 1', '2015-04-14 16:49:09');
insert into Reply (userID, topicID, replyText, date) 
values (6, 1, 'Reply 2', '2015-04-14 16:49:20');
insert into Reply (userID, topicID, replyText, date) 
values (3, 1, 'Reply 1.1', '2015-04-15 10:00:00');

insert into Reply (userID, topicID, replyText, date) 
values (5, 2, 'Reply 1.1', '2015-04-15 11:00:00');

insert into Reply (userID, topicID, replyText, date) 
values (2, 3, 'Reply 1.1', '2015-04-15 11:00:00');

insert into Reply (userID, topicID, replyText, date) 
values (3, 4, 'Reply 1.1', '2015-04-15 11:00:00');

insert into Reply (userID, topicID, replyText, date) 
values (3, 5, 'I like banana.', '2015-04-15 11:00:00');

insert into Reply (userID, topicID, replyText, date) 
values (3, 6, 'Reply 1.1', '2015-04-15 11:00:00');

insert into Reply (userID, topicID, replyText, date) 
values (4, 7, 'Reply 1.1', '2015-04-15 11:00:00');

insert into Reply (userID, topicID, replyText, date) 
values (4, 8, 'Topic text', '2015-04-14 15:34:09');
insert into Reply (userID, topicID, replyText, date) 
values (2, 8, 'Reply', '2015-04-14 15:54:09');
insert into Reply (userID, topicID, replyText, date) 
values (1, 8, 'Reply 2', '2015-04-14 16:35:09');
insert into Reply (userID, topicID, replyText, date) 
values (2, 8, 'Reply 3', '2015-04-14 16:39:09');

insert into Reply (userID, topicID, replyText, date) 
values (5, 9, 'Reply start', '2015-04-14 16:39:09');

insert into Reply (userID, topicID, replyText, date) 
values (6, 10, 'Reply start', '2015-04-14 16:39:09');

