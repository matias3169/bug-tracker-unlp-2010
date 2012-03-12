DELIMITER $$

DROP PROCEDURE IF EXISTS `bugtrackerdb`.`LoadRoles` $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `LoadRoles`()
BEGIN
  INSERT INTO role(tipo,nombre,permisos) VALUES("Sistema","Administrador","Total");
  INSERT INTO role(tipo,nombre,permisos) VALUES("Sistema","Desarrollador","Total");
  INSERT INTO role(tipo,nombre,permisos) VALUES("Proyecto","Lider","Total");
  INSERT INTO role(tipo,nombre,permisos) VALUES("Proyecto","Desarrollador","Total");
  INSERT INTO role(tipo,nombre,permisos) VALUES("Proyecto","DBA","Total");
  INSERT INTO role(tipo,nombre,permisos) VALUES("Proyecto","Tester","Total");
END $$

DELIMITER ;