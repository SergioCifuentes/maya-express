-- Departamentos

INSERT INTO destination (name, region) 
    VALUES 
        ('Guatemala', 'Metropolitana'), 
        ('Baja Verapaz', 'Norte'),
        ('Alta Verapaz', 'Norte'),
        ('El Progreso', 'Nororiental'),
        ('Izabal', 'Nororiental'),
        ('Zacapa', 'Nororiental'),
        ('Chiquimula', 'Nororiental'),
        ('Santa Rosa', 'Suroriental'),
        ('Jalapa', 'Suroriental'),
        ('Jutiapa', 'Suroriental'),
        ('Sacatepéquez', 'Central'),
        ('Chimaltenango', 'Central'),
        ('Escuintla', 'Central'),
        ('Sololá', 'Suroccidental'),
        ('Totonicapán', 'Suroccidental'),
        ('Quetzaltenango', 'Suroccidental'),
        ('Suchitepéquez', 'Suroccidental'),
        ('Retalhuleu', 'Suroccidental'),
        ('San Marcos', 'Suroccidental'),
        ('Huehuetenango', 'Noroccidental'),
        ('Quiché', 'Noroccidental'),
        ('Petén', 'Petén');

-- Honorarios

INSERT INTO wage (id, per_hour) 
    VALUES 
        (1, 3500.00),
        (2, 4500.00),
        (3, 5500.00),
        (4, 4000.00),
        (5, 5000.00),
        (6, 6000.00),
        (7, 7500.00),
        (8, 8500.00),
        (9, 9000.00),
        (10, 9500.00);

-- Cargos

INSERT INTO position (name, description, wage_id) 
    VALUES 
        ('Secretaria', 'Persona que se encarga de las labores administrativas de un organismo, institución o corporación y desempeña las funciones de extender actas, dar fe de los acuerdos y custodiar los documentos de esa entidad.', 2), 
        ('Bodeguero', 'Operador de equipo especial como montacargas y cargadores hidráulicos para recibir, entregar y acomodar mercadería en la bodega.', 1), 
        ('Conserje', 'Persona que tiene a su cargo las llaves de una sucursal, cuida de su mantenimiento, vigilancia y limpieza y realiza otros trabajos no especializados.', 1), 
        ('Operador de Logística', 'Gestiona y controla de forma óptima los flujos de recursos de cualquier empresa: materiales, productos, servicios e información.', 1), 
        ('Guardia', 'Protege los bienes y a los empleados de la empresa manteniendo un entorno seguro y protegido.', 1), 
        ('Piloto', 'Responsable de conducir diversos camiones de 1 a 5 toneladas y llevar mercadería dentro de la Ciudad Capital y al Interior de la República.', 2), 
        ('Auxiliar de Piloto', 'Desempeña un papel crucial en asegurar que las operaciones de transporte se lleven a cabo de manera eficiente y segura, contribuyendo al éxito de la cadena de suministro de una empresa.', 4), 
        ('Gerente', 'Responsable de planear y dirigir el trabajo de un grupo de individuos, de monitorear su desempeño y tomar acción correctiva cuando es necesario.', 10), 
        ('Informatico', 'Estudia y analiza cómo los ordenadores almacenan y utilizan la información.', 8), 
        ('Contador', 'Maneja la contabilidad de una compañía. Esto implica la preparación de todo tipo de documentos financieros como declaraciones de impuestos y balances.', 6), 
        ('Recepcionista', 'Gestiona la recepción de una sucursal, tiene una comunicación eficaz y es la creadora de una imagen positiva de la empresa ante los visitantes.', 5);

-- Tipo de Gasto

INSERT INTO cost_type (name, is_permanent, description) 
    VALUES 
        ('Internet', true, 'Servicios de internet.'),
        ('Alquiler', true, 'Renta de local para bodega o sucursal.'),
        ('Luz', false, 'Servicios de Energía Eléctrica.'),
        ('Teléfono', true, 'Servicios de Telefonía.'),
        ('Gasolina de Vehículos Asociados', false, 'Combustible de los vehículos asociados.'),
        ('Salario o sueldo.', true, 'Salario o sueldo del personal de la empresa.'),
        ('Servicios Públicos', true, 'Servicios de públicos de sucursal o empresa.'),
        ('Publicidad', false, 'Servicios de publicidad.');

-- Tarifario

insert into price (cost_x_lb, sending_cost, destination, origin) values (15.41, 39.02, 1, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.84, 40.57, 1, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.85, 28.11, 1, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.6, 48.69, 1, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.27, 25.02, 1, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.46, 46.55, 1, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.32, 41.38, 1, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.86, 37.55, 1, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.51, 46.5, 1, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.35, 38.25, 1, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.88, 41.0, 1, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.5, 46.56, 1, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.22, 42.78, 1, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.84, 37.93, 1, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.1, 39.39, 1, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.29, 38.41, 1, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.26, 38.07, 1, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.66, 31.28, 1, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.3, 43.65, 1, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.56, 31.11, 1, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.86, 43.66, 1, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.77, 34.94, 1, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.99, 26.5, 2, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.55, 31.47, 2, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.44, 49.44, 2, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.51, 42.44, 2, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.15, 28.92, 2, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.21, 27.88, 2, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.37, 27.57, 2, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.63, 36.43, 2, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.63, 26.65, 2, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.53, 49.42, 2, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.87, 39.72, 2, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.61, 28.96, 2, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.8, 29.14, 2, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.6, 25.24, 2, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.39, 26.86, 2, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.94, 31.72, 2, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.86, 37.63, 2, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.98, 31.26, 2, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.58, 37.4, 2, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.47, 49.49, 2, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.65, 39.67, 2, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.25, 49.17, 2, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.61, 27.88, 3, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.86, 48.68, 3, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.24, 32.2, 3, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.05, 47.77, 3, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.9, 47.96, 3, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.3, 36.16, 3, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.97, 49.0, 3, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.35, 48.3, 3, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.5, 49.63, 3, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.54, 42.03, 3, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.36, 35.98, 3, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.88, 26.75, 3, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.65, 48.38, 3, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.77, 28.99, 3, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.25, 28.64, 3, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.62, 32.64, 3, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.02, 37.58, 3, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.7, 32.52, 3, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.96, 47.64, 3, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.5, 29.48, 3, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.01, 41.65, 3, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.51, 40.65, 3, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.19, 47.51, 4, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.72, 42.87, 4, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.75, 36.56, 4, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.92, 47.13, 4, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.44, 29.0, 4, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.26, 38.07, 4, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.39, 43.4, 4, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.47, 28.86, 4, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.55, 44.72, 4, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.28, 49.91, 4, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.67, 49.9, 4, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.49, 48.07, 4, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.17, 44.14, 4, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.06, 29.25, 4, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.85, 26.95, 4, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.15, 37.23, 4, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.45, 38.43, 4, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.31, 32.62, 4, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.38, 31.21, 4, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.41, 49.33, 4, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.86, 36.16, 4, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.42, 35.9, 4, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.7, 29.02, 5, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.53, 27.33, 5, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.87, 30.83, 5, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.1, 27.9, 5, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.51, 29.88, 5, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.69, 48.22, 5, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.24, 44.58, 5, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.11, 25.14, 5, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.91, 33.84, 5, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.33, 32.94, 5, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.31, 35.7, 5, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.67, 48.38, 5, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.78, 46.06, 5, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.42, 37.04, 5, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.2, 27.61, 5, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.88, 38.44, 5, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.07, 45.72, 5, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.08, 27.85, 5, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.53, 46.89, 5, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.78, 48.86, 5, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.64, 27.09, 5, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.85, 27.56, 5, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.29, 39.93, 6, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.53, 48.84, 6, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.38, 46.33, 6, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.5, 39.59, 6, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.59, 33.34, 6, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.96, 41.88, 6, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.97, 25.2, 6, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.55, 41.14, 6, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.58, 44.75, 6, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.48, 47.43, 6, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.22, 26.18, 6, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.57, 36.94, 6, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.44, 47.38, 6, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.32, 48.81, 6, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.2, 38.08, 6, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.43, 35.17, 6, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.22, 43.71, 6, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.93, 34.43, 6, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.62, 45.56, 6, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.99, 44.19, 6, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.54, 29.37, 6, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.6, 40.52, 6, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.57, 25.76, 7, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.07, 49.2, 7, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.87, 42.34, 7, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.89, 29.69, 7, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.83, 34.66, 7, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.36, 29.25, 7, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.74, 36.58, 7, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.78, 39.52, 7, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.14, 31.84, 7, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.11, 31.44, 7, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.06, 46.49, 7, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.48, 34.54, 7, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.38, 31.72, 7, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.83, 29.75, 7, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.7, 39.35, 7, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.76, 25.41, 7, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.58, 48.33, 7, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.7, 32.29, 7, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.34, 36.23, 7, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.98, 28.48, 7, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.94, 33.22, 7, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.77, 29.49, 7, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.58, 28.34, 8, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.36, 25.29, 8, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.46, 25.75, 8, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.01, 39.43, 8, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.29, 42.42, 8, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.49, 46.6, 8, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.3, 42.62, 8, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.26, 42.76, 8, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.01, 41.27, 8, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.01, 25.1, 8, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.54, 30.35, 8, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.26, 27.07, 8, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.17, 34.55, 8, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.47, 30.62, 8, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.49, 27.04, 8, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.68, 49.3, 8, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.38, 38.46, 8, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.98, 28.08, 8, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.71, 31.78, 8, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.63, 45.21, 8, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.81, 48.85, 8, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.14, 25.43, 8, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.51, 35.42, 9, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.19, 41.8, 9, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.67, 29.33, 9, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.37, 28.56, 9, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.55, 34.74, 9, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.81, 30.78, 9, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.32, 41.2, 9, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.29, 30.4, 9, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.75, 46.15, 9, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.28, 42.82, 9, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.29, 38.95, 9, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.72, 45.18, 9, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.67, 44.89, 9, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.38, 31.09, 9, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.0, 27.55, 9, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.0, 43.21, 9, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.58, 25.01, 9, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.86, 37.03, 9, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.32, 49.24, 9, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.4, 28.77, 9, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.03, 32.79, 9, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.61, 36.61, 9, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.23, 37.16, 10, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.51, 45.79, 10, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.41, 45.26, 10, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.14, 44.86, 10, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.06, 30.76, 10, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.49, 29.26, 10, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.85, 45.31, 10, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.32, 47.84, 10, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.77, 37.3, 10, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.35, 33.76, 10, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.72, 36.25, 10, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.17, 31.31, 10, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.86, 49.26, 10, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.72, 32.39, 10, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.49, 44.53, 10, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.12, 31.3, 10, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.74, 35.8, 10, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.93, 36.18, 10, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.87, 43.92, 10, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.92, 29.11, 10, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.45, 25.65, 10, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.7, 33.1, 10, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.89, 38.78, 11, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.02, 25.89, 11, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.6, 45.64, 11, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.8, 35.6, 11, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.36, 37.54, 11, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.76, 41.2, 11, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.42, 33.18, 11, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.22, 32.12, 11, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.31, 36.36, 11, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.32, 40.4, 11, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.84, 25.29, 11, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.55, 39.24, 11, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.21, 31.96, 11, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.42, 31.65, 11, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.58, 45.33, 11, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.27, 39.81, 11, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.04, 33.01, 11, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.32, 28.14, 11, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.56, 36.98, 11, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.94, 30.91, 11, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.62, 41.57, 11, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.36, 30.32, 11, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.18, 28.58, 12, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.16, 48.29, 12, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.08, 34.14, 12, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.79, 36.19, 12, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.22, 31.33, 12, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.26, 25.04, 12, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.4, 38.03, 12, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.18, 25.86, 12, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.26, 38.32, 12, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.83, 45.06, 12, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.82, 40.87, 12, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.92, 31.09, 12, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.86, 40.85, 12, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.12, 33.24, 12, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.48, 25.98, 12, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.09, 46.32, 12, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.22, 31.26, 12, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.62, 39.93, 12, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.13, 41.11, 12, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.73, 43.04, 12, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.03, 47.98, 12, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.53, 39.57, 12, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.64, 29.6, 13, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.04, 47.75, 13, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.07, 38.97, 13, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.59, 47.78, 13, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.93, 49.64, 13, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.67, 42.88, 13, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.91, 29.31, 13, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.94, 35.59, 13, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.22, 35.07, 13, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.37, 33.03, 13, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.38, 42.32, 13, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.59, 49.3, 13, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.37, 33.04, 13, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.03, 31.89, 13, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.94, 43.18, 13, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.17, 37.95, 13, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.21, 35.14, 13, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.16, 35.8, 13, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.07, 34.8, 13, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.8, 34.42, 13, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.94, 48.6, 13, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.2, 28.87, 13, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.82, 37.95, 14, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.84, 45.94, 14, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.2, 44.44, 14, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.51, 48.46, 14, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.74, 40.57, 14, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.2, 43.72, 14, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.76, 30.42, 14, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.47, 28.66, 14, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.24, 26.23, 14, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.94, 25.83, 14, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.96, 28.87, 14, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.09, 28.71, 14, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.81, 47.47, 14, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.72, 33.98, 14, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.59, 42.76, 14, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.92, 42.19, 14, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.14, 28.13, 14, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.9, 25.53, 14, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.27, 34.25, 14, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.32, 38.9, 14, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.85, 25.95, 14, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.55, 31.64, 14, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.93, 35.68, 15, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.66, 48.59, 15, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.35, 42.11, 15, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.74, 45.8, 15, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.23, 48.63, 15, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.02, 29.54, 15, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.01, 28.08, 15, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.44, 36.1, 15, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.31, 38.64, 15, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.7, 34.48, 15, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.06, 27.21, 15, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.05, 34.35, 15, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.75, 35.29, 15, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.22, 45.1, 15, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.47, 28.37, 15, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.93, 35.47, 15, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.6, 49.39, 15, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.27, 40.2, 15, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.52, 38.58, 15, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.68, 43.16, 15, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.11, 39.68, 15, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.26, 46.4, 15, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.82, 37.55, 16, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.36, 27.27, 16, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.98, 41.76, 16, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.47, 32.23, 16, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.64, 45.08, 16, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.36, 37.34, 16, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.76, 35.73, 16, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.51, 29.16, 16, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.23, 32.1, 16, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.67, 40.55, 16, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.49, 30.34, 16, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.09, 36.82, 16, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.89, 26.35, 16, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.98, 30.59, 16, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.13, 33.58, 16, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.65, 43.46, 16, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.61, 28.66, 16, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.47, 32.41, 16, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.03, 38.8, 16, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.12, 44.38, 16, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.33, 31.77, 16, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.89, 31.36, 16, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.22, 43.42, 17, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.81, 44.07, 17, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.93, 32.53, 17, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.06, 26.57, 17, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.09, 41.36, 17, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.38, 27.16, 17, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.67, 32.59, 17, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.17, 47.93, 17, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.83, 29.51, 17, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.93, 32.3, 17, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.95, 43.02, 17, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.23, 42.01, 17, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.64, 41.92, 17, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.67, 36.58, 17, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.79, 41.35, 17, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.64, 32.95, 17, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.27, 48.37, 17, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.55, 35.92, 17, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.84, 37.09, 17, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.55, 26.56, 17, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.04, 27.62, 17, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.84, 46.94, 17, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.95, 31.16, 18, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.25, 31.07, 18, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.08, 31.26, 18, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.42, 43.77, 18, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.84, 49.47, 18, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.32, 29.43, 18, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.68, 44.61, 18, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.95, 34.73, 18, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.71, 35.86, 18, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.27, 32.14, 18, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.73, 33.58, 18, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.99, 38.83, 18, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.04, 25.83, 18, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.04, 26.12, 18, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.96, 49.01, 18, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.63, 48.08, 18, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.35, 47.88, 18, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.04, 34.43, 18, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.91, 45.26, 18, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.7, 29.02, 18, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.76, 41.0, 18, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.78, 31.24, 18, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.24, 45.47, 19, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.59, 27.39, 19, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.76, 31.87, 19, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.07, 40.85, 19, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.7, 34.41, 19, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.36, 35.37, 19, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.69, 35.91, 19, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.47, 40.06, 19, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.14, 44.4, 19, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.47, 44.98, 19, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.42, 31.78, 19, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.43, 37.5, 19, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.18, 37.12, 19, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.03, 26.24, 19, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.14, 48.37, 19, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.36, 27.6, 19, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.96, 32.69, 19, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.25, 33.65, 19, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.61, 28.08, 19, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.71, 38.71, 19, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.19, 25.3, 19, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.52, 42.77, 19, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.76, 35.14, 20, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.67, 27.9, 20, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.79, 47.75, 20, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.92, 48.58, 20, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.46, 49.88, 20, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.35, 26.92, 20, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.58, 45.06, 20, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.7, 41.3, 20, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.68, 36.91, 20, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.76, 41.13, 20, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.73, 26.75, 20, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.06, 48.96, 20, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.99, 38.62, 20, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.73, 42.63, 20, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.1, 40.01, 20, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.88, 30.16, 20, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.37, 37.24, 20, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.32, 31.87, 20, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.49, 34.04, 20, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.11, 41.57, 20, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.41, 25.07, 20, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.92, 43.72, 20, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.9, 30.93, 21, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.58, 27.27, 21, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.17, 33.36, 21, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.96, 48.84, 21, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.41, 28.29, 21, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.34, 49.84, 21, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.81, 45.01, 21, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.79, 40.03, 21, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.82, 47.61, 21, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.92, 45.65, 21, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.71, 41.76, 21, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.03, 30.53, 21, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.87, 47.76, 21, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.16, 41.26, 21, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.27, 38.93, 21, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.84, 35.16, 21, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.91, 29.06, 21, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.69, 40.79, 21, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.04, 32.91, 21, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.43, 39.37, 21, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.82, 41.4, 21, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.0, 46.37, 21, 22);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.8, 29.25, 22, 1);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.16, 32.74, 22, 2);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.72, 47.68, 22, 3);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.07, 42.69, 22, 4);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.69, 28.13, 22, 5);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.9, 34.29, 22, 6);
insert into price (cost_x_lb, sending_cost, destination, origin) values (11.21, 28.84, 22, 7);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.91, 41.9, 22, 8);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.14, 46.1, 22, 9);
insert into price (cost_x_lb, sending_cost, destination, origin) values (14.56, 25.8, 22, 10);
insert into price (cost_x_lb, sending_cost, destination, origin) values (18.59, 33.7, 22, 11);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.42, 29.32, 22, 12);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.58, 38.77, 22, 13);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.98, 39.03, 22, 14);
insert into price (cost_x_lb, sending_cost, destination, origin) values (12.54, 45.28, 22, 15);
insert into price (cost_x_lb, sending_cost, destination, origin) values (10.63, 39.46, 22, 16);
insert into price (cost_x_lb, sending_cost, destination, origin) values (19.15, 38.59, 22, 17);
insert into price (cost_x_lb, sending_cost, destination, origin) values (16.5, 49.79, 22, 18);
insert into price (cost_x_lb, sending_cost, destination, origin) values (17.3, 48.45, 22, 19);
insert into price (cost_x_lb, sending_cost, destination, origin) values (13.17, 45.74, 22, 20);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.04, 46.05, 22, 21);
insert into price (cost_x_lb, sending_cost, destination, origin) values (15.32, 32.28, 22, 22);


--Warehouse

insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (1,'2a Ave, Zona 1', true, true, '10000','500',1);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (2,'3a Calle, Zona 12', false, true, '15000','700',1);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (3,'1a Ave, Zona 10', true, true, '6000','300',1);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (4,'2a Ave, Zona 2', true, true, '10000','500',2);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (5,'4a Ave, Zona 1', true, true, '9000','400',3);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (6,'5a Calle, Zona 1', true, true, '9000','400',4);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (7,'6a Calle, Zona 1', true, true, '1000','250',5);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (8,'6a Ave, Zona 4', false, true, '9000','400',5);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (9,'5a Ave, Zona 2', true, true, '9000','400',6);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (10,'5a Ave, Zona 4', true, true, '9000','400',7);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (11,'4a Ave, Zona 5', true, true, '5000','250',8);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (12,'2a Ave, Zona 1', true, true, '4000','200',9);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (13,'1a Calle, Zona 2', true, true, '9000','400',10);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (14,'2a Calle, Zona 5', true, true, '9000','400',11);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (15,'3a Calle, Zona 4', true, true, '10000','500',12);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (16,'1a Ave, Zona 2', true, true, '1000','100',13);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (17,'2a Ave, Zona 3', true, true, '9000','400',14);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (18,'4a Ave, Zona 1', true, true, '9000','400',15);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (19,'4a Ave, Zona 1', true, true, '4000','150',16);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (20,'2a Ave, Zona 3', false, true, '10000','500',16);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (21,'3a Ave, Zona 3', true, true, '6000','300',17);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (22,'1a Calle, Zona 5', true, true, '8000','300',18);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (23,'4a Ave, Zona 4', true, true, '8000','300',19);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (24,'5a Calle, Zona 2', true, true, '8000','300',20);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (25,'3a Calle, Zona 1', true, true, '8000','300',21);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (26,'1a Ave, Zona 10', true, true, '800','200',22);
insert into warehouse(id, address, is_branch, is_enable, max_weight_lbs, square_meters, department_id)
values (27,'1a Calle, Zona 1', false, true, '10000','500',22);

--Vehicle
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(1,200,'P123ABC',0,1);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(2,800,'P124ABD',1,1);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(3,800,'P125ABD',1,2);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(4,200,'P126ABD',0,3);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(5,200,'P127ABC',0,4);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(6,300,'P128ABC',0,5);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(7,250,'P129ABC',0,6);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(31,250,'P130ABC',0,7);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(8,1000,'P131ABC',1,8);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(9,250,'P132ABC',0,8);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(10,250,'P133ABC',0,9);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(11,300,'P134ABC',0,10);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(12,250,'P135ABC',0,11);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(13,150,'P136ABC',0,12);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(14,200,'P137ABC',0,13);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(15,250,'P138ABC',0,14);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(16,200,'P139ABC',0,15);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(17,200,'P140ABC',0,16);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(18,1000,'P141ABC',1,17);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(19,200,'P142ABC',0,18);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(20,200,'P143ABC',0,19);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(21,250,'P144ABC',0,20);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(22,300,'P145ABC',0,20);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(23,200,'P146ABC',0,21);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(24,200,'P147ABC',0,22);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(25,200,'P148ABC',0,23);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(26,200,'P149ABC',0,24);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(27,200,'P150ABC',0,25);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(28,200,'P151ABC',0,26);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(29,800,'P152ABC',1,27);
insert into vehicle(id, max_weight, plate, vehicle_type, warehouse_id)
values(30,250,'P153ABC',0,27);