insert into Users (login, password, emailAddress)
values ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin@polsl.pl');
insert into Users (login, password, emailAddress)
values ('raku', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'raku@raku.pl');
insert into Users (login, password, emailAddress)
values ('rzepa', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'rzepa@raku.pl');
insert into Users (login, password, emailAddress)
values ('polo', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'polo@polo.pl');
insert into Users (login, password, emailAddress)
values ('seba', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'seba@seba.pl');
insert into Users (login, password, emailAddress)
values ('jozef', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'jozef@jozef.pl');
insert into Users (login, password, emailAddress)
values ('test', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'test@raku.pl');
insert into Users (login, password, emailAddress)
values ('test2', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'tes2@raku.pl');

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

insert into CommandsLists(userID, isPublic, name)
values (1, true, 'Standard list');

insert into CommandsLists(userID, isPublic, name)
values (2, true, 'Test list');

insert into CommandsLists(userID, isPublic, name)
values (2, false, 'Test list 2');

insert into CommandsLists(userID, isPublic, name)
values (3, false, 'Test list 3');

insert into Commands (commandListID, commandName, commandCode, arguments, commandIndex)
values (1, 'STP', '// zakończenie programu\nCOMMAND STP;\nARGUMENTS 0;\nczyt wys wei il;\nstop;', false, 0);

insert into Commands (commandListID, commandName, commandCode, arguments, commandIndex)
values (1, 'ADD', '// (Ak)+((Ad))->Ak\nCOMMAND ADD;\nczyt wys wei il;\nwyad wea;\nczyt wys weja dod weak wyl wea;', true, 1);

insert into Commands (commandListID, commandName, commandCode, arguments, commandIndex)
values (1, 'SUB', '// (Ak)-((Ad))->Ak\nCOMMAND SUB;\nczyt wys wei il;\nwyad wea;\nczyt wys weja ode weak wyl wea;', true,  2);

insert into Commands (commandListID, commandName, commandCode, arguments, commandIndex)
values (1, 'LD', '// ((Ad))->Ak\nCOMMAND LD;\nczyt wys wei il;\nwyad wea;\nczyt wys weja przep weak wyl wea;', true, 3);

insert into Commands (commandListID, commandName, commandCode, arguments, commandIndex)
values (1, 'ST', '// ((Ad))->Ak\nCOMMAND ST;\nczyt wys wei il;\nwyad wea wyak wes;\npisz wyl wea;', true, 4);

insert into Commands (commandListID, commandName, commandCode, arguments, commandIndex)
values (1, 'JMP', '// skok bezwarunkowy\nCOMMAND JMP;\nczyt wys wei il;\nwyad wea wel;\n', true,  5);

insert into Commands (commandListID, commandName, commandCode, arguments, commandIndex)
values (1, 'JMPN', '// skok gdy (AK) < 0\nCOMMAND JMPN;\nczyt wys wei il;\nIF SIGN THEN @ujemne ELSE @dodatnie;\n@ujemne wyad wea wel END;\n@dodatnie wyl wea;', true, 6);

insert into Commands (commandListID, commandName, commandCode, arguments, commandIndex)
values (1, 'JMPZ', '// skok gdy (AK) < 0\nCOMMAND JMPZ;\nczyt wys wei il;\nIF ZERO THEN @zero ELSE @niezero;\n@zero wyad wea wel END;\n@niezero wyl wea;', true, 7);

insert into Language (language, context, textID, textValue)
values ('PL', 'Register', 'PROGRAM_COUNTER', 'L' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Register', 'INSTRUCTION', 'I' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Register', 'ACCUMULATOR', 'AK' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Register', 'MEMORY_ADDRESS', 'A' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Register', 'MEMORY_DATA', 'S' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Register', 'STACK_POINTER', 'WS' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Register', 'FLAGS', 'F' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Register', 'DATA_X', 'X' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Register', 'DATA_Y', 'Y' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Register', 'IO_PORT', 'RB' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Register', 'STROBE', 'G' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'PROGRAM_COUNTER_IN', 'wel' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'PROGRAM_COUNTER_OUT', 'wyl' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'PROGRAM_COUNTER_INCREMENT', 'il' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'PROGRAM_COUNTER_OUT_TO_DATA_BUS', 'wyls' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'INSTRUCTION_IN', 'wei' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'INSTRUCTION_OUT', 'wyad' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ACCUMULATOR_IN', 'weak' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ACCUMULATOR_OUT', 'wyak' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ACCUMULATOR_INCREMENT', 'iak' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ACCUMULATOR_DECREMENT', 'dak' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'MEMORY_ADDRESS_IN', 'wea' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'MEMORY_DATA_IN', 'wes' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'MEMORY_DATA_OUT', 'wys' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'MEMORY_WRITE', 'pisz' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'MEMORY_READ', 'czyt' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'STACK_POINTER_IN', 'wews' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'STACK_POINTER_OUT', 'wyws' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'STACK_POINTER_INCREMENT', 'iws' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'STACK_POINTER_DECREMENT', 'dws' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'BUS_CONNECTION', 'as (sa)' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ALU_ADD', 'dod' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ALU_SUBTRACT', 'ode' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ALU_MULTIPLY', 'mno' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ALU_DIVIDE', 'dziel' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ALU_COPY', 'przep' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ALU_SHIFT_RIGHT', 'shr' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ALU_CONJUNCTION', 'i' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ALU_ALTERNATIVE', 'lub' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ALU_NEGATION', 'neg' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'DATA_X_IN', 'wex' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'DATA_X_OUT', 'wyx' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'DATA_Y_IN', 'wey' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'DATA_Y_OUT', 'wyy' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'IO_PORT_IN', 'werb' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'IO_PORT_OUT', 'wyrb' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'STROBE_START', 'start' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'STROBE_OUT', 'wyg' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'ALU_IN', 'weja' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Signal', 'STOP', 'stop' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Keywords', 'COMMAND', 'COMMAND' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Keywords', 'IF', 'IF' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Keywords', 'THEN', 'THEN' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Keywords', 'ELSE', 'ELSE' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Keywords', 'END', 'END' );
insert into Language (language, context, textID, textValue)
values ('PL', 'Keywords', 'ARGUMENTS', 'ARGUMENTS' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'SITE_HEADER', 'Maszyna W' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'SIMULATOR_TAB', 'Symulator' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'USER_MENU_DROP_BOX', 'WITAJ NIEZNAJOMY!' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'USER_REGISTER_TAB', 'REJESTRACJA' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'USER_LOGIN_TAB', 'LOGOWANIE' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'MANUAL_CONTROL_CHECK_BOX', 'Sterowanie ręczne' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'TRACKING_LEVEL_PANEL', 'Poziom śledzenia' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'TRACKING_LEVEL_LOW_OPTION', 'Niski (program)' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'TRACKING_LEVEL_MEDIUM_OPTION', 'Średni (rozkaz)' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'TRACKING_LEVEL_HIGH_OPTION', 'Wysoki (takt)' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'TYPES_AND_COMPONENTS_PANEL', 'Typy i składniki' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'TYPE_W_RADIO_BUTTON', 'W' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'TYPE_W_PLUS_RADIO_BUTTON', 'W+' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'TYPE_L_RADIO_BUTTON', 'L' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'TYPE_EW_RADIO_BUTTON', 'EW' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'INC_DEC_ACCUMULATOR_CHECK_BOX', 'Połączenie międzymagistralowe' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'INC_DEC_ACCUMULATOR_CHECK_BOX', 'Inkrementacja i dekrementacja akumulatora' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'LOGICAL_OPERATIONS_ALU_CHECK_BOX', 'Operacje logiczne w JAL' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'EXTENDED_ARYTHMETICAL_ALU_OPERATIONS_CHECK_BOX', 'Rozszerzone operacje atytmetyczne w JAL' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'STACK_MANAGEMENT_CHECK_BOX', 'Obsługa stosu' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'X_REGISTER_CHECK_BOX', 'Rejestr X' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'Y_REGISTER_CHECK_BOX', 'Rejestr Y' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'INPUT_OUTPUT_CHECK_BOX', 'Wejście/wyjście' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'ADDITIONAL_MARKS_CHECK_BOX', 'Dodatkowe znaczniki' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'ARCHITECTURE_TAB', 'Architektura' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'ADDRESS_BIT_COUNT_INPUT', 'Liczba bitów adresowych' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'CODE_BIT_COUNT_INPUT', 'Liczba bitów kodu' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'ADDRESSES_TAB', 'Adresy' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'ADRESS_LABEL', 'Adres (port' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'INTERRUPT_LABEL', 'Przerwanie' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'ADDRESS_INPUT_LABEL', 'Wejście' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'ADDRESS_OUTPUT_LABEL', 'Wyjście' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'FORUM_TAB', 'Forum' );
insert into Language (language, context, textID, textValue)
values ('PL', 'TextName', 'LOGOUT_TAB', 'Wyloguj się' );


insert into Language (language, context, textID, textValue)
values ('EN', 'Register', 'PROGRAM_COUNTER', 'L' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Register', 'INSTRUCTION', 'I' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Register', 'ACCUMULATOR', 'AK' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Register', 'MEMORY_ADDRESS', 'A' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Register', 'MEMORY_DATA', 'S' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Register', 'STACK_POINTER', 'WS' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Register', 'FLAGS', 'F' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Register', 'DATA_X', 'X' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Register', 'DATA_Y', 'Y' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Register', 'IO_PORT', 'RB' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Register', 'STROBE', 'G' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'PROGRAM_COUNTER_IN', 'wel' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'PROGRAM_COUNTER_OUT', 'wyl' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'PROGRAM_COUNTER_INCREMENT', 'il' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'PROGRAM_COUNTER_OUT_TO_DATA_BUS', 'wyls' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'INSTRUCTION_IN', 'wei' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'INSTRUCTION_OUT', 'wyad' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ACCUMULATOR_IN', 'weak' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ACCUMULATOR_OUT', 'wyak' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ACCUMULATOR_INCREMENT', 'iak' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ACCUMULATOR_DECREMENT', 'dak' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'MEMORY_ADDRESS_IN', 'wea' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'MEMORY_DATA_IN', 'wes' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'MEMORY_DATA_OUT', 'wys' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'MEMORY_WRITE', 'pisz' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'MEMORY_READ', 'czyt' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'STACK_POINTER_IN', 'wews' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'STACK_POINTER_OUT', 'wyws' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'STACK_POINTER_INCREMENT', 'iws' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'STACK_POINTER_DECREMENT', 'dws' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'BUS_CONNECTION', 'as (sa)' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ALU_ADD', 'dod' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ALU_SUBTRACT', 'ode' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ALU_MULTIPLY', 'mno' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ALU_DIVIDE', 'dziel' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ALU_COPY', 'przep' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ALU_SHIFT_RIGHT', 'shr' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ALU_CONJUNCTION', 'i' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ALU_ALTERNATIVE', 'lub' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ALU_NEGATION', 'neg' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'DATA_X_IN', 'wex' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'DATA_X_OUT', 'wyx' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'DATA_Y_IN', 'wey' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'DATA_Y_OUT', 'wyy' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'IO_PORT_IN', 'werb' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'IO_PORT_OUT', 'wyrb' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'STROBE_START', 'start' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'STROBE_OUT', 'wyg' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'ALU_IN', 'weja' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Signal', 'STOP', 'stop' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Keywords', 'COMMAND', 'COMMAND' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Keywords', 'IF', 'IF' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Keywords', 'THEN', 'THEN' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Keywords', 'ELSE', 'ELSE' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Keywords', 'END', 'END' );
insert into Language (language, context, textID, textValue)
values ('EN', 'Keywords', 'ARGUMENTS', 'ARGUMENTS' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'SITE_HEADER', 'Maszyna W' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'SIMULATOR_TAB', 'Symulator' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'USER_MENU_DROP_BOX', 'WITAJ NIEZNAJOMY!' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'USER_REGISTER_TAB', 'REJESTRACJA' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'USER_LOGIN_TAB', 'LOGOWANIE' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'MANUAL_CONTROL_CHECK_BOX', 'Sterowanie ręczne' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'TRACKING_LEVEL_PANEL', 'Poziom śledzenia' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'TRACKING_LEVEL_LOW_OPTION', 'Niski (program)' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'TRACKING_LEVEL_MEDIUM_OPTION', 'Średni (rozkaz)' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'TRACKING_LEVEL_HIGH_OPTION', 'Wysoki (takt)' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'TYPES_AND_COMPONENTS_PANEL', 'Typy i składniki' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'TYPE_W_RADIO_BUTTON', 'W' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'TYPE_W_PLUS_RADIO_BUTTON', 'W+' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'TYPE_L_RADIO_BUTTON', 'L' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'TYPE_EW_RADIO_BUTTON', 'EW' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'INC_DEC_ACCUMULATOR_CHECK_BOX', 'Połączenie międzymagistralowe' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'INC_DEC_ACCUMULATOR_CHECK_BOX', 'Inkrementacja i dekrementacja akumulatora' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'LOGICAL_OPERATIONS_ALU_CHECK_BOX', 'Operacje logiczne w JAL' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'EXTENDED_ARYTHMETICAL_ALU_OPERATIONS_CHECK_BOX', 'Rozszerzone operacje atytmetyczne w JAL' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'STACK_MANAGEMENT_CHECK_BOX', 'Obsługa stosu' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'X_REGISTER_CHECK_BOX', 'Rejestr X' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'Y_REGISTER_CHECK_BOX', 'Rejestr Y' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'INPUT_OUTPUT_CHECK_BOX', 'Wejście/wyjście' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'ADDITIONAL_MARKS_CHECK_BOX', 'Dodatkowe znaczniki' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'ARCHITECTURE_TAB', 'Architektura' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'ADDRESS_BIT_COUNT_INPUT', 'Liczba bitów adresowych' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'CODE_BIT_COUNT_INPUT', 'Liczba bitów kodu' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'ADDRESSES_TAB', 'Adresy' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'ADRESS_LABEL', 'Adres (port' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'INTERRUPT_LABEL', 'Przerwanie' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'ADDRESS_INPUT_LABEL', 'Wejście' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'ADDRESS_OUTPUT_LABEL', 'Wyjście' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'FORUM_TAB', 'Forum' );
insert into Language (language, context, textID, textValue)
values ('EN', 'TextName', 'LOGOUT_TAB', 'Wyloguj się' );
