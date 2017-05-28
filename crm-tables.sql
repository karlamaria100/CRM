CREATE TABLE `produsedinfacturi` (
  `idFactura` int(11) NOT NULL,
  `idProdus` int(11) NOT NULL,
  `cantitate` double DEFAULT NULL,
  `pret` double DEFAULT NULL,
  `nume` varchar(45) DEFAULT NULL,
  `serviciu` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idProdus`,`idFactura`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `sales` (
  `idSales` int(11) NOT NULL AUTO_INCREMENT,
  `idProdus` int(11) DEFAULT NULL,
  `stock` double DEFAULT NULL,
  `pret` double DEFAULT NULL,
  `cumparator` varchar(45) DEFAULT 'APROVIZIONARE',
  PRIMARY KEY (`idSales`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `persoana_juridica` (
  `nume` varchar(15) NOT NULL,
  `idpersoana_juridica` int(11) NOT NULL,
  PRIMARY KEY (`idpersoana_juridica`),
  UNIQUE KEY `idPersJuridical_UNIQUE` (`idpersoana_juridica`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `produse` (
  `idProduse` int(11) NOT NULL,
  `nume` varchar(45) DEFAULT NULL,
  `stock` double DEFAULT NULL,
  `pret` double DEFAULT NULL,
  `serviciu` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`idProduse`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `facturi` (
  `idFactura` int(11) NOT NULL,
  `idClient` int(11) DEFAULT NULL,
  `pretTotal` double DEFAULT NULL,
  PRIMARY KEY (`idFactura`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `persoana_fizica` (
  `idpersoana_fizica` int(11) NOT NULL,
  `nume` varchar(45) DEFAULT NULL,
  `prenume` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpersoana_fizica`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


