-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SeshDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SeshDB` ;

-- -----------------------------------------------------
-- Schema SeshDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SeshDB` DEFAULT CHARACTER SET utf8 ;
USE `SeshDB` ;

-- -----------------------------------------------------
-- Table `strain`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `strain` ;

CREATE TABLE IF NOT EXISTS `strain` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(2000) NOT NULL,
  `thc_percentage` INT NOT NULL,
  `cbd_percentage` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sesh`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sesh` ;

CREATE TABLE IF NOT EXISTS `sesh` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `time_length` VARCHAR(45) NULL,
  `description` VARCHAR(2000) NULL,
  `strain_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sesh_strain_idx` (`strain_id` ASC),
  CONSTRAINT `fk_sesh_strain`
    FOREIGN KEY (`strain_id`)
    REFERENCES `strain` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS sesh@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'sesh'@'localhost' IDENTIFIED BY 'sesh';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'sesh'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `strain`
-- -----------------------------------------------------
START TRANSACTION;
USE `SeshDB`;
INSERT INTO `strain` (`id`, `name`, `description`, `thc_percentage`, `cbd_percentage`) VALUES (1, 'OG Kush', 'OG Kush was first cultivated in Florida, in the early ‘90s when a strain from Northern California was crossed with a Hindu Kush plant from Amsterdam. The result was a hybrid with a unique terpene profile that boasts a complex aroma with notes of fuel, skunk, and spice. \n\nThe genetic backbone of West Coast cannabis varieties, OG Kush arrived in Los Angeles in 1996 when Matt “Bubba” Berger brought it (along with “The Bubba,” which was later used to create the famed Bubba Kush) from Florida to legendary cultivator Josh D. Since then, OG Kush has become a worldwide staple used to create numerous famous strains like GSC and Headband. There are many different phenotypes of OG Kush, including Tahoe OG, SFV OG, and Ghost OG.', 16, 1);
INSERT INTO `strain` (`id`, `name`, `description`, `thc_percentage`, `cbd_percentage`) VALUES (2, 'Jah Kush', 'Jah Kush is a true catch for any cultured cannabis consumer. Its well-rounded excellence is achieved by a distinct, exceptional aroma and long-lasting, mellow effects that are dynamic but not overwhelming. This hybrid is optimal for tension relief and mood lift, offering a rich and slow experience with a citrus aftertaste. With disputed genetics and supposed origins in Northern California, Jah Kush is a self-made success story.', 21, 1);
INSERT INTO `strain` (`id`, `name`, `description`, `thc_percentage`, `cbd_percentage`) VALUES (3, 'Grease Monkey', 'Grease Monkey is a sweet, earthy strain with strong skunky overtones. Created by Exotic Genetix, Grease Monkey is a cross of GG4 x Cookies and Cream. This strain saddles the consumer with a lazy, munchie-fueled body buzz that may soften the blow of chronic pain, nausea, and stress. While defined as a hybrid, this Grease Monkey’s deep relaxation will naturally lead some consumers toward sleep, so mind your dosage.', 22.5, 1);
INSERT INTO `strain` (`id`, `name`, `description`, `thc_percentage`, `cbd_percentage`) VALUES (4, 'Sour Diesel', 'Sour Diesel, sometimes called Sour D, is an invigorating sativa-dominant strain named after its pungent, diesel-like aroma. This fast-acting strain delivers energizing, dreamy cerebral effects that have pushed Sour Diesel to its legendary status. Stress, pain, and depression fade away in long-lasting relief that makes Sour Diesel a top choice among medical patients. This strain took root in the early \'90s, and it is believed to have descended from Chemdog 91 and Super Skunk.', 19, 1);
INSERT INTO `strain` (`id`, `name`, `description`, `thc_percentage`, `cbd_percentage`) VALUES (5, 'Blue Dream', 'Blue Dream, a sativa-dominant hybrid originating in California, has achieved legendary status among West Coast strains. Crossing Blueberry with Haze, Blue Dream balances full-body relaxation with gentle cerebral invigoration. Novice and veteran consumers alike enjoy the level effects of Blue Dream, which ease you gently into a calm euphoria. \n\nWith a sweet berry aroma redolent of its Blueberry parent, Blue Dream delivers swift symptom relief without heavy sedative effects. This makes Blue Dream a popular daytime medicine for patients treating pain, depression, nausea, and other ailments requiring a high THC strain. ', 19, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sesh`
-- -----------------------------------------------------
START TRANSACTION;
USE `SeshDB`;
INSERT INTO `sesh` (`id`, `title`, `time_length`, `description`, `strain_id`) VALUES (1, 'Home Alone', '2 hrs', 'A sad day when you\'re home alone. But my skunky momma came and made it all better. Nice outdoor aroma, earthy taste, gentle and caring. After 2 hours I fell asleep. Truly amazing.', 5);
INSERT INTO `sesh` (`id`, `title`, `time_length`, `description`, `strain_id`) VALUES (2, 'F.R.I.E.N.D.S', '7 hrs', 'OOOOOHHHHH SNAP! Party time with the Homies, we gettin litty lit sooooon. Playin some UNO, shit talkin, and eating food. We Baked.', 3);
INSERT INTO `sesh` (`id`, `title`, `time_length`, `description`, `strain_id`) VALUES (3, 'Taken', '????', 'Where am I? Who am I? What should I become? Where should I focus my attentions?', 1);
INSERT INTO `sesh` (`id`, `title`, `time_length`, `description`, `strain_id`) VALUES (4, 'Groundhog Day', 'I don\'t know anymore', 'I\'ve lived 10,000 years, everything is the same. There is nothing new under the sun.', 5);
INSERT INTO `sesh` (`id`, `title`, `time_length`, `description`, `strain_id`) VALUES (5, 'Pickle Me Rickster', '3.14159265359 hrs', '*burp* Let\'s destroy the world through technology to reset the earth.', 2);
INSERT INTO `sesh` (`id`, `title`, `time_length`, `description`, `strain_id`) VALUES (6, 'Burger\'s Bob', '4 min 27 seconds, flip 2 minutes, Done', 'food, food, food. Pizza Pockets. How much can I consume? I am a monster!', 2);
INSERT INTO `sesh` (`id`, `title`, `time_length`, `description`, `strain_id`) VALUES (7, 'Harry Pot Tour', 'Who cares, it\'s magical.', 'If only magic was real, I\'d totally be a wizard. Let\'s look into magic! ooohhhh....But I don\'t want to be possessed by a demon. Soooo how about some TV? lol', 4);
INSERT INTO `sesh` (`id`, `title`, `time_length`, `description`, `strain_id`) VALUES (8, 'My-celium Feels Like Heilum', 'Time is just a construct', 'I have a dream of creating a device similar to a meat temp checker, that you insert into the soil of a Marijuana plant which sends out a low frequency that radiates through the soil and maps out the plants root system. The data would then be sent to a database to be displayed dynamically in a sophisticated user interface. All for better care of the plant and it\'s further production of the lush THC of which I partake.', 1);
INSERT INTO `sesh` (`id`, `title`, `time_length`, `description`, `strain_id`) VALUES (9, 'Death Before Life', 'Outside of Time', 'I died tonight. Then came to life. I now understand all things.', 4);
INSERT INTO `sesh` (`id`, `title`, `time_length`, `description`, `strain_id`) VALUES (10, 'Mtn Dew-by Dew-by Dew', 'Dew hours and Dew minutes', 'I love mysteries! let\'s try to solve a mystery of the world while being fueled by MTNDEW!!!!!', 3);

COMMIT;

