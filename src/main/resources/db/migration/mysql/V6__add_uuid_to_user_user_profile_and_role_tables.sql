ALTER TABLE `user` ADD COLUMN `uuid` INT NOT NULL AFTER `id`;
ALTER TABLE `user_profile` ADD COLUMN `uuid` INT NOT NULL AFTER `id`;
ALTER TABLE `role` ADD COLUMN `uuid` INT NOT NULL AFTER `id`;