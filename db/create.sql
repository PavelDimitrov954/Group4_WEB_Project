use forum;

create table users
(
    user_id    int auto_increment primary key,
    username   varchar(50) not null,
    password   varchar(50) not null,
    first_name varchar(50) not null,
    last_name  varchar(50) not null,
    email      varchar(50) not null,
    is_admin   boolean     not null
);

create table posts
(
    post_id int auto_increment primary key,
    title   varchar(256) not null,
    content text(8192)   not null,
    user_id int          not null,
    likes int   default 0,
    comments_count int   default 0,
    constraint posts_users_user_id_fk
        foreign key (user_id) references users (user_id)
);

create table comments
(
    comment_id int auto_increment primary key,
    content    varchar(256) not null,
    user_id    int          not null,
    post_id    int          not null,
    constraint comments_users_user_id_fk
        foreign key (user_id) references users (user_id),
    constraint comments_posts_post_id_fk
        foreign key (post_id) references posts (post_id)
);

create table admin_phone_number
(
    admin_phone_number_id int auto_increment primary key,
    phone_number          varchar(50) not null,
    user_id               int         not null,
    constraint admin_phone_number_users_user_id_fk
        foreign key (user_id) references users (user_id)
);

create table likes
(
    like_id int auto_increment primary key,
    post_id int not null,
    user_id int not null,
    constraint likes_posts_post_id_fk
        foreign key (post_id) references posts (post_id),
    constraint likes_users_user_id_fk
        foreign key (user_id) references users (user_id)
);

