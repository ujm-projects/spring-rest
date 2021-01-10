
INSERT INTO BUILDING(id, name, outside_temperature) VALUES(-10, 'BLOCK A', 21.6);
INSERT INTO BUILDING(id, name, outside_temperature) VALUES(-9, 'BLOCK B', 22.5);

INSERT INTO ROOM(id, name, floor, current_temperature, target_temperature, building_id) VALUES(-10, 'Room1', 1, 22.3, 20.0, -10);
INSERT INTO ROOM(id, name, floor, building_id) VALUES(-9, 'Room1', 1 , -9);
INSERT INTO ROOM(id, name, floor, building_id) VALUES(-8, 'Room2', 1 , -9);
INSERT INTO ROOM(id, name, floor, current_temperature, target_temperature, building_id) VALUES(-7, 'Room2', 2, 22.3, 20.0 ,  -10);
INSERT INTO ROOM(id, name, floor, current_temperature, target_temperature, building_id) VALUES(-6, 'Room3', 2, 21.8, 20.0 ,  -10);
INSERT INTO ROOM(id, name, floor, current_temperature, target_temperature, building_id) VALUES(-5, 'Room3', 3, 20.6, 20.0 ,  -9);

INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-10, 'OFF', 'Heater1', 2000, -10);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-9, 'ON', 'Heater2', null, -10);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-8, 'OFF', 'Heater3', 2000, -10);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-7, 'ON', 'Heater4', null, -10);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-6, 'OFF', 'Heater1', 2000, -9);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-5, 'ON', 'Heater2', null, -9);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-4, 'OFF', 'Heater3', 2000, -9);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-3, 'ON', 'Heater4', null, -9);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-2, 'OFF', 'Heater1', 2000, -8);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(-1, 'ON', 'Heater2', null, -8);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(1, 'OFF', 'Heater1', 2000, -7);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(2, 'ON', 'Heater2', null, -7);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(3, 'OFF', 'Heater1', 2000, -6);
INSERT INTO HEATER(id, heater_status, name, power, room_id) VALUES(4, 'ON', 'Heater1', null, -5);


INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-10, 'CLOSED', 'Window 1', -10);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-9, 'CLOSED', 'Window 2', -10);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-8, 'OPEN', 'Window 1', -9);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-7, 'CLOSED', 'Window 2', -9);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-13, 'OPEN', 'Window 3', -9);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-6, 'CLOSED', 'Window 1', -8);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-5, 'CLOSED', 'Window 2', -8);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-4, 'OPEN', 'Window 1', -7);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-3, 'CLOSED', 'Window 2', -7);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-2, 'OPEN', 'Window 1', -6);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-1, 'CLOSED', 'Window 2', -6);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-11, 'OPEN', 'Window 1', -5);
INSERT INTO RWINDOW(id, window_status, name, room_id) VALUES(-12, 'CLOSED', 'Window 2', -5);