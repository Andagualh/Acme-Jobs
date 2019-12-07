
    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `application` 
       drop 
       foreign key `FKoa6p4s2oyy7tf80xwc4r04vh6`;

    alter table `application` 
       drop 
       foreign key `FKmbjdoxi3o93agxosoate4sxbt`;

    alter table `audit_record` 
       drop 
       foreign key `FKdcrrgv6rkfw2ruvdja56un4ji`;

    alter table `audit_record` 
       drop 
       foreign key `FKlbvbyimxf6pxvbhkdd4vfhlnd`;

    alter table `auditor` 
       drop 
       foreign key FK_clqcq9lyspxdxcp6o4f3vkelj;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `commercial` 
       drop 
       foreign key `FK2jw28sba4n2gi3xdkdqqhm870`;

    alter table `consumer` 
       drop 
       foreign key FK_6cyha9f1wpj0dpbxrrjddrqed;

    alter table `descriptor` 
       drop 
       foreign key `FKgfulfilmwi4hhaquiu7fr5g0g`;

    alter table `duty` 
       drop 
       foreign key `FK3cc3garl37bl7gswreqwr7pj4`;

    alter table `employer` 
       drop 
       foreign key FK_na4dfobmeuxkwf6p75abmb2tr;

    alter table `job` 
       drop 
       foreign key `FK3rxjf8uh6fh2u990pe8i2at0e`;

    alter table `message` 
       drop 
       foreign key `FKq851een84mnkrhyssa05q7je`;

    alter table `message_thread_authenticated` 
       drop 
       foreign key `FKsnymblhgu3dixq3t2qhptr4x2`;

    alter table `message_thread_authenticated` 
       drop 
       foreign key `FKjb0tx79q4dpibs3mnkp6wfqvf`;

    alter table `non_commercial` 
       drop 
       foreign key `FKqo73ln7f61vbg9r4a06esfujd`;

    alter table `provider` 
       drop 
       foreign key FK_b1gwnjqm6ggy9yuiqm0o4rlmd;

    alter table `spamword` 
       drop 
       foreign key `FKrk7poykhk0ukf2dm6oqv3rejm`;

    alter table `sponsor` 
       drop 
       foreign key FK_20xk0ev32hlg96kqynl6laie2;

    alter table `worker` 
       drop 
       foreign key FK_l5q1f33vs2drypmbdhpdgwfv3;

    drop table if exists `administrator`;

    drop table if exists `announcements`;

    drop table if exists `anonymous`;

    drop table if exists `application`;

    drop table if exists `audit_record`;

    drop table if exists `auditor`;

    drop table if exists `authenticated`;

    drop table if exists `banners`;

    drop table if exists `challenge`;

    drop table if exists `commercial`;

    drop table if exists `company_records`;

    drop table if exists `consumer`;

    drop table if exists `descriptor`;

    drop table if exists `duty`;

    drop table if exists `employer`;

    drop table if exists `investor_record`;

    drop table if exists `job`;

    drop table if exists `message`;

    drop table if exists `message_thread`;

    drop table if exists `message_thread_authenticated`;

    drop table if exists `non_commercial`;

    drop table if exists `offer`;

    drop table if exists `provider`;

    drop table if exists `request`;

    drop table if exists `spamlist`;

    drop table if exists `spamword`;

    drop table if exists `sponsor`;

    drop table if exists `user_account`;

    drop table if exists `worker`;

    drop table if exists `hibernate_sequence`;
