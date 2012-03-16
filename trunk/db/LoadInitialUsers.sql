DELIMITER $$

DROP PROCEDURE IF EXISTS `bugtrackerdb`.`LoadInitialUsers` $$
CREATE PROCEDURE `bugtrackerdb`.`LoadInitialUsers` ()
BEGIN
  INSERT INTO usuario(Nombre,clave,role) VALUES("admin","admin",1);
END $$

DELIMITER ;