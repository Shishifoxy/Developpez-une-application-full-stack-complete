CREATE DATABASE IF NOT EXISTS mdd_db;
USE mdd_db;

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL UNIQUE,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL
);

CREATE TABLE theme (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255),
                       description VARCHAR(255)
);

CREATE TABLE article (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255),
                         content TEXT,
                         created_at DATETIME,
                         author_id BIGINT NOT NULL,
                         theme_id BIGINT NOT NULL,
                         CONSTRAINT fk_article_author FOREIGN KEY (author_id) REFERENCES user(id),
                         CONSTRAINT fk_article_theme FOREIGN KEY (theme_id) REFERENCES theme(id)
);

CREATE TABLE comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         content TEXT NOT NULL,
                         created_at DATETIME,
                         article_id BIGINT NOT NULL,
                         author_id BIGINT NOT NULL,
                         CONSTRAINT fk_comment_article FOREIGN KEY (article_id) REFERENCES article(id),
                         CONSTRAINT fk_comment_author FOREIGN KEY (author_id) REFERENCES user(id)
);

CREATE TABLE subscription (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT NOT NULL,
                              theme_id BIGINT NOT NULL,
                              subscribed_at DATETIME,
                              CONSTRAINT fk_subscription_user FOREIGN KEY (user_id) REFERENCES user(id),
                              CONSTRAINT fk_subscription_theme FOREIGN KEY (theme_id) REFERENCES theme(id)
);

CREATE TABLE user_theme (
                            user_id BIGINT NOT NULL,
                            theme_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, theme_id),
                            CONSTRAINT fk_user_theme_user FOREIGN KEY (user_id) REFERENCES user(id),
                            CONSTRAINT fk_user_theme_theme FOREIGN KEY (theme_id) REFERENCES theme(id)
);
