-- Wastewater table
CREATE TABLE IF NOT EXISTS wastewater_samples
(
    id int PRIMARY KEY AUTO_INCREMENT,
    sector_route_name varchar(255),
    sector_display_name varchar(255),
    facility_name varchar(255),
    sample_date varchar(255),
    contaminant_route_name varchar(255),
    contaminant_display_name varchar(255),
    reported_as varchar(255),
    component_type varchar(255),
    value float,
    units varchar(255)
);


insert into wastewater_samples(id, sector_route_name, sector_display_name, facility_name, sample_date, contaminant_route_name, contaminant_display_name, reported_as, component_type, value, units)
    values (1, "test_sector_route", "test_sector", "test_facility_name", "2020-01-01", "test_contaminant_route", "test_contaminant", "test_substance", "MAXIMUM", 10, "test_units")

insert into wastewater_samples(id, sector_route_name, sector_display_name, facility_name, sample_date, contaminant_route_name, contaminant_display_name, reported_as, component_type, value, units)
    values (2, "test_sector_route", "test_sector", "test_facility_name", "2020-01-01", "test_contaminant_route", "test_contaminant", "test_substance", "MINIMUM", 1, "test_units")

insert into wastewater_samples(id, sector_route_name, sector_display_name, facility_name, sample_date, contaminant_route_name, contaminant_display_name, reported_as, component_type, value, units)
    values (3, "test_sector_route", "test_sector", "test_facility_name", "2020-01-01", "test_contaminant_route", "test_contaminant", "test_substance", "AVERAGE", 5, "test_units")


-- Sewage test data
create table if not exists sewage_data
(
    id int auto_increment primary key,
    sector_route_name varchar(255) comment 'Sector Route Name',
    sector_display_name varchar(255) comment 'Sector Display Name',
    contaminant_route_name varchar(255) comment 'Contaminant Route Name',
    contaminant_display_name varchar(255) comment 'Contaminant Display Name',
    facility varchar(255) comment 'Facility Owner',
    contaminant_limit int comment 'Contaminant Limit',
    contaminant_unit varchar(255) comment 'Contaminant Unit',
    exceedance_start_date varchar(255) comment 'Exceedance Start Date',
    exceedance_end_date varchar(255) comment 'Exceedance End Date',
    num_exceedances int comment 'No of Exceedances'
);


insert into sewage_data(id, sector_route_name, sector_display_name, contaminant_route_name, contaminant_display_name, facility, contaminant_limit, contaminant_unit, exceedance_start_date, exceedance_end_date, num_exceedances)
    values (1, "test_industrial", "Industrial", "test_ammonia", "Ammonia", "test_facility", 15, "test_units", "2024-02-01", "2024-02-15", 5)

insert into sewage_data(id, sector_route_name, sector_display_name, contaminant_route_name, contaminant_display_name, facility, contaminant_limit, contaminant_unit, exceedance_start_date, exceedance_end_date, num_exceedances)
    values (2, "test_commercial", "Commercial", "test_sulphur", "Sulphur", "test_facility", 15, "test_units", "2024-02-01", "2024-02-15", 5)

insert into sewage_data(id, sector_route_name, sector_display_name, contaminant_route_name, contaminant_display_name, facility, contaminant_limit, contaminant_unit, exceedance_start_date, exceedance_end_date, num_exceedances)
    values (3, "test_industrial", "Industrial", "test_sulphur", "Sulphur", "test_facility", 15, "test_units", "2024-02-01", "2024-02-15", 5)

insert into sewage_data(id, sector_route_name, sector_display_name, contaminant_route_name, contaminant_display_name, facility, contaminant_limit, contaminant_unit, exceedance_start_date, exceedance_end_date, num_exceedances)
    values (4, "test_industrial", "Industrial", "test_ammonia", "Ammonia", "test_facility", 15, "test_units", "2024-02-01", "2024-02-15", 5)
