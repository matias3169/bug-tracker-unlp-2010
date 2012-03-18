DELIMITER $$

DROP PROCEDURE IF EXISTS `bugtrackerdb`.`LoadRoles` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `LoadRoles`()
BEGIN
  INSERT INTO role(tipo,nombre) VALUES("Sistema","Administrador");
  INSERT INTO role(tipo,nombre) VALUES("Sistema","Desarrollador");
  INSERT INTO role(tipo,nombre) VALUES("Proyecto","Lider");
  INSERT INTO role(tipo,nombre) VALUES("Proyecto","Desarrollador");
  INSERT INTO role(tipo,nombre) VALUES("Proyecto","DBA");
  INSERT INTO role(tipo,nombre) VALUES("Proyecto","Tester");
END $$

DELIMITER ;