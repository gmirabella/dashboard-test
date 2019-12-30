\connect empatica;

CREATE  sequence sq_download;

CREATE TABLE download (
  id              int8  primary key not null default nextval('sq_download'),
  lat             int8              not null,
  lon             int8              not null,
  app_id          text              not null,
  downloaded_at   timestamptz       not null default now()
);

INSERT INTO public.download (id, lat, lon, app_id, downloaded_at) VALUES
 (1,   41.894802,   12.4853384, 'IOS_MATE'  , '2019-12-24 18:10:27.877')
,(2,  37.5022355,     15.08738, 'IOS_ALLERT', '2019-12-24 18:10:27.877')
,(3,     45.4668,       9.1905, 'IOS_MATE'  , '2019-12-24 18:10:27.877')
,(4,  40.7127281,  -74.0060152, 'IOS_MATE'  , '2019-12-24 18:10:27.877')
,(5, -33.8548157,  151.2164539, 'IOS_ALLERT', '2019-12-24 18:10:27.877')
;