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
(1,   41.894802,   12.4853384, 'IOS_MATE'  , 'Italy' , 'EVENING',          '2019-12-11 21:10:27.877')
,(2,  48.8566969,    2.3514616, 'IOS_ALLERT', 'France', 'NIGHT',            '2019-06-24 03:10:27.877')
,(3,     45.4668,       9.1905, 'IOS_MATE'  , 'Italy', 'AFTERNOON',         '2019-01-24 14:10:27.877')
,(4,  52.5170365,   13.3888599, 'IOS_MATE'  , 'Germany', 'MORNING',         '2019-12-02 09:10:27.877')
,(5,  42.6511674,   -73.754968, 'IOS_ALLERT', 'United States', 'AFTERNOON', '2019-07-20 15:10:27.877')
,(6,  51.5073219,   -0.1276474, 'IOS_ALLERT', 'United Kindom', 'NIGHT',     '2019-06-24 03:10:27.877')
,(7,  51.8979282,   -8.4705806, 'IOS_MATE'  , 'Ireland', 'AFTERNOON',       '2019-01-24 14:10:27.877')
,(8,  52.5170365,   13.4288599, 'IOS_MATE'  , 'Germany', 'MORNING',         '2019-12-02 09:10:27.877')
,(9,  37.02178,     -85.889096, 'IOS_ALLERT', 'United States', 'NIGHT',     '2019-07-20 15:10:27.877')
,(10,  45.6963425,    4.735948, 'IOS_ALLERT', 'France', 'NIGHT',            '2019-06-24 03:10:27.877')
,(11,  45.6500335,  13.7706559, 'IOS_MATE'  , 'Italy', 'AFTERNOON',         '2019-01-24 14:10:27.877')
,(12,  34.5668857,  -112.5515965, 'IOS_MATE'  , 'United States', 'MORNING', '2019-12-02 09:10:27.877')
,(13,  40.40442374, -79.851541, 'IOS_ALLERT', 'United States', 'AFTERNOON', '2019-07-20 15:10:27.877')
;

select setval('sq_download', 14);