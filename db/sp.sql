CREATE OR REPLACE FUNCTION get_package_counts(
    is_origin BOOLEAN, 
    start_date_str VARCHAR, 
    end_date_str VARCHAR
)
RETURNS TABLE(packages_count BIGINT, region VARCHAR) AS $$
DECLARE
    start_date DATE;
    end_date DATE;
BEGIN
    -- Convertir las cadenas de fecha a tipo DATE
    start_date := TO_DATE(start_date_str, 'DD/MM/YYYY');
    end_date := TO_DATE(end_date_str, 'DD/MM/YYYY');

    RETURN QUERY
    SELECT COUNT(destination.region) AS packages_count, destination.region
    FROM (
        SELECT COUNT(package.shipment_id) AS packages, package.shipment_id
        FROM package
        INNER JOIN shipment ON shipment.id = package.shipment_id
        WHERE shipment.send_date BETWEEN start_date AND end_date
        GROUP BY package.shipment_id
        ORDER BY package.shipment_id
    ) AS packages
    INNER JOIN shipment ON shipment.id = packages.shipment_id
    INNER JOIN warehouse ON
        CASE
            WHEN is_origin THEN warehouse.id = shipment.sending_warehouse_id
            ELSE warehouse.id = shipment.receive_warehouse_id
        END
    INNER JOIN destination ON destination.id = warehouse.department_id
    GROUP BY destination.region;

END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_package_counts_by_destination(
    is_origin BOOLEAN, 
    start_date_str VARCHAR, 
    end_date_str VARCHAR
)
RETURNS TABLE(packages_count BIGINT, region VARCHAR) AS $$
DECLARE
    start_date DATE;
    end_date DATE;
	
BEGIN
    -- Convertir las cadenas de fecha a tipo DATE
    start_date := TO_DATE(start_date_str, 'DD/MM/YYYY');
    end_date := TO_DATE(end_date_str, 'DD/MM/YYYY');
    RETURN QUERY
    SELECT COUNT(destination.name) AS packages_count, destination.name
    FROM (
        SELECT COUNT(package.shipment_id) AS packages, package.shipment_id
        FROM package
        INNER JOIN shipment ON shipment.id = package.shipment_id
        WHERE shipment.send_date BETWEEN start_date AND end_date
        GROUP BY package.shipment_id
        ORDER BY package.shipment_id
    ) AS packages
    INNER JOIN shipment ON shipment.id = packages.shipment_id
    INNER JOIN warehouse ON
	 	CASE
            WHEN is_origin THEN warehouse.id = shipment.sending_warehouse_id
            ELSE warehouse.id = shipment.receive_warehouse_id
        END
    INNER JOIN destination ON destination.id = warehouse.department_id
    GROUP BY destination.name
	ORDER BY packages_count DESC;

END;
$$ LANGUAGE plpgsql;