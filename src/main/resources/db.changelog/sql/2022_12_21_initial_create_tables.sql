CREATE SCHEMA IF NOT EXISTS transport_system;

CREATE TABLE transport_system.t_town
(
    town_id            BIGSERIAL PRIMARY KEY NOT NULL,
    name               VARCHAR(50)        UNIQUE NOT NULL,
    priority           INT,
    created_at         TIMESTAMP          NOT NULL,
    updated_at         TIMESTAMP
);

COMMENT ON COLUMN transport_system.t_town.town_id             IS 'Id города';
COMMENT ON COLUMN transport_system.t_town.name                IS 'Название города';
COMMENT ON COLUMN transport_system.t_town.priority          IS 'Приоритет в справочнике (для отображения порядка)';
COMMENT ON COLUMN transport_system.t_town.created_at          IS 'Запись создана в ...';
COMMENT ON COLUMN transport_system.t_town.updated_at          IS 'Запись изменена в ...';


--- other tables