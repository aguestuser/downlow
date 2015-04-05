# --- !Ups

# --- Worker Schema

create sequence worker_id_seq;

create table workers(
    id          bigint      not null primary key default nextval('worker_id_seq')
    ip          text        not null
    status      text        not null
);

# --- ScrapeJob Schema

create sequence scrape_job_id_seq;

create table scrape_jobs(
    id          bigint      not null primary key default nextval('scrape_job_id_seq')
    bbl         char(10)    not null
    status      text        not null
    worker_id   bigint      references workers(id)
);

# --- ScrapePayload Schema

create sequence scrape_payload_id_seq;

create table scrape_payloads(
    id          bigint      not null primary key default nextval('scrape_payload_id_seq')
    bbl         char(10)    not null
    text        text        not null
);

# --- !Downs

drop table scrape_payloads;
drop sequence scrape_payload_id_seq;

drop table scrape_jobs;
drop sequence scrape_job_id_seq;

drop table workers;
drop sequence worker_id_seq;