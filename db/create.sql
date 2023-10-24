use forum;

create table users
(
    user_id    int auto_increment primary key,
    username   varchar(50) not null,
    password   varchar(50) not null,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    email      varchar(50) not null,
    is_admin   boolean     not null,
    is_blocked boolean     not null default false
);

create table posts
(
    post_id int auto_increment primary key,
    title   varchar(256) not null,
    content text(8192)   not null,
    user_id int          not null,
    constraint posts_users_user_id_fk
        foreign key (user_id) references users (user_id)
            ON DELETE CASCADE

);

create table comments
(
    comment_id int auto_increment primary key,
    content    varchar(256) not null,
    user_id    int          not null,
    post_id    int          not null,
    constraint comments_users_user_id_fk
        foreign key (user_id) references users (user_id)
            ON DELETE CASCADE,
    constraint comments_posts_post_id_fk
        foreign key (post_id) references posts (post_id)
            ON DELETE CASCADE

);

create table admin_phone_number
(
    admin_phone_number_id int auto_increment primary key,
    phone_number          varchar(50) not null,
    user_id               int         not null,
    constraint admin_phone_number_users_user_id_fk
        foreign key (user_id) references users (user_id)
            ON DELETE CASCADE

);

create table likes
(
    like_id int auto_increment primary key,
    post_id int not null,
    user_id int not null,
    constraint likes_posts_post_id_fk
        foreign key (post_id) references posts (post_id)
            ON DELETE CASCADE,
    constraint likes_users_user_id_fk
        foreign key (user_id) references users (user_id)
            ON DELETE CASCADE

);

