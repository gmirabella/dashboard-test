\connect empatica;

CREATE sequence sq_download;

CREATE TABLE download (
  id              int8  primary key not null default nextval('sq_download'),
  lat             float4            not null,
  lon             float4            not null,
  app_id          text              not null,
  country_name    text              not null,
  day_part        text              not null,
  downloaded_at   timestamptz       not null default now()
);

INSERT INTO public.download (id, lat, lon, app_id, country_name, day_part, downloaded_at) VALUES
 (1,   41.894802,   12.4853384, 'IOS_MATE'  , 'Italy' , 'DAY_PART_3',        '2019-12-11 21:10:27.877')
,(2,  48.8566969,    2.3514616, 'IOS_ALLERT', 'France', 'DAY_PART_0',        '2019-06-24 03:10:27.877')
,(3,     45.4668,       9.1905, 'IOS_MATE'  , 'Italy', 'DAY_PART_2',         '2019-01-24 14:10:27.877')
,(4,  52.5170365,   13.3888599, 'IOS_MATE'  , 'Germany', 'DAY_PART_1',       '2019-12-02 09:10:27.877')
,(5, 40.7127281,   -74.0060152, 'IOS_ALLERT', 'United States', 'DAY_PART_2', '2019-07-20 15:10:27.877')
;

select setval('sq_download', 6);