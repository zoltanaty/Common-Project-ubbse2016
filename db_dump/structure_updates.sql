ALTER TABLE `result` ADD `thinking_time` INT NOT NULL AFTER `date`;
ALTER TABLE `result` ADD `is_correct` BIT(1) NOT NULL AFTER `thinking_time`;