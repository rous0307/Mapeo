CREATE TABLE proyecto (
                          id_proyecto INT PRIMARY KEY AUTO_INCREMENT,
                          nombre VARCHAR(100) NOT NULL,
                          fecha_inicio DATE NOT NULL,
                          fecha_fin_estimada DATE NOT NULL
);

CREATE TABLE etapa (
                       id_etapa INT PRIMARY KEY AUTO_INCREMENT,
                       id_proyecto INT NOT NULL,
                       fase VARCHAR(100) NOT NULL,
                       fecha_inicio DATE NOT NULL,
                       fecha_fin DATE NOT NULL,
                       FOREIGN KEY (id_proyecto) REFERENCES proyecto(id_proyecto)
);

CREATE TABLE presupuesto (
                             id_presupuesto INT PRIMARY KEY AUTO_INCREMENT,
                             id_etapa INT UNIQUE NOT NULL,
                             monto_total_estimado DOUBLE NOT NULL,
                             monto_utilizado DOUBLE DEFAULT 0,
                             FOREIGN KEY (id_etapa) REFERENCES etapa(id_etapa)
);
