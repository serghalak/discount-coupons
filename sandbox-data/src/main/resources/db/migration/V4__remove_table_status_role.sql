ALTER TABLE `user` DROP FOREIGN KEY `FK_user_role_id`;

DROP TABLE if EXISTS `role`;

ALTER TABLE `event` DROP FOREIGN KEY `FK_event_status_id`;
DROP TABLE if EXISTS `status`;

ALTER TABLE `user` DROP COLUMN `role_id`;

ALTER TABLE `user`  ADD COLUMN `role` varchar (255);

ALTER TABLE `event` DROP COLUMN `status_id`;

ALTER TABLE `event`  ADD COLUMN `status` varchar(255);