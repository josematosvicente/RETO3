CREATE TABLE `cronograma` (
  `id_cronograma` int(11) NOT NULL,
  `fecha_vencimiento` varchar(10) DEFAULT NULL,
  `interes` decimal(15,2) DEFAULT NULL,
  `nro_cuota` int(11) NOT NULL,
  `capital` decimal(15,2) DEFAULT NULL,
  `amortizacion` decimal(15,2) DEFAULT NULL,
  `cuota` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id_cronograma`,`nro_cuota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$





delimiter $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_CREAR_CRONOGRAMA`(
IN V_VALOR_PRESTAMO DECIMAL, -- valor prestamo
IN V_TASA_PACTADA DECIMAL, -- tasa anual
IN V_CANTIDAD_CUOTAS INTEGER,
IN V_FECHA_VENCIMIENTO VARCHAR(10)--  cantidad cuotas(meses)
)
BEGIN

  declare v_counter int default 1;
  declare v_cronograma int;
  declare v_monto decimal(12,6);
  declare v_cuotas int;
  declare v_interes decimal(12,6);
  declare v_amortizacion decimal(12,6);
  
  declare v_interesMensual decimal(12,6);
  declare v_pagoTotal decimal(12,6);
  declare v_cuota decimal(12,6);
  declare v_falta decimal(12,6);
  
  declare v_potencia decimal(12,6);
  declare v_fecha varchar(10);
  
  
  

  
  select IFNULL(max(id_cronograma),0)+1 into v_cronograma from cronograma;
  

  
  start transaction;
  
  set v_monto = V_VALOR_PRESTAMO;
  set v_cuotas = V_CANTIDAD_CUOTAS;
  set v_interes = V_TASA_PACTADA/12;
  set v_interesMensual=v_interes/100;
  set v_pagoTotal=v_monto+(v_monto*v_cuotas*v_interesMensual/100);
  set v_falta=v_pagoTotal;
  
  set v_potencia = (Pow((1+v_interesMensual),(v_cuotas*-1)));
  set v_cuota = (v_monto*v_interesMensual)/(1-v_potencia);
  
  while v_counter <= v_cuotas do
  
    
    
    set v_interes=v_monto*v_interesMensual;
    set v_amortizacion = v_cuota - v_interes;
    select DATE_FORMAT(DATE_ADD(STR_TO_DATE(V_FECHA_VENCIMIENTO, "%Y-%m-%d") , INTERVAL v_counter MONTH), "%Y-%m-01") into v_fecha;
  
    insert into cronograma (id_cronograma,nro_cuota,cuota,amortizacion,interes,capital,fecha_vencimiento) values ( v_cronograma,v_counter,v_cuota,v_amortizacion ,v_interes, v_monto, v_fecha);
    
    set v_monto=v_monto-v_amortizacion;
    
    set v_counter=v_counter+1;
  end while;
  commit;

 SELECT id_cronograma,nro_cuota,cuota,amortizacion,interes,capital,fecha_vencimiento FROM cronograma 
 WHERE ID_CRONOGRAMA=v_cronograma;
END$$

delimiter $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_LEER_CRONOGRAMA`(IN V_id_cronograma varchar(100) )
BEGIN
 SELECT id_cronograma,nro_cuota,cuota,amortizacion,interes,capital,fecha_vencimiento FROM cronograma
 WHERE ID_CRONOGRAMA=V_id_cronograma;
END$$


