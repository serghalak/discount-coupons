alter table `event`
    add `full_description` varchar(2508);
 alter table `event`
    add `date_of_creation` date;

ALTER TABLE `heroku_a75daef564eac27`.`event`
      CHANGE COLUMN `date_of_creation` `date_of_creation` DATE NULL DEFAULT NULL AFTER `date_end`,
      CHANGE COLUMN `full_description` `full_description` VARCHAR(2508) NULL DEFAULT NULL AFTER `description`;