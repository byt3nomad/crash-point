CREATE TABLE round (
    id uuid primary key,
    crash_point double precision not null,
    created timestamptz not null
);