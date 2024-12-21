CREATE TABLE category (
    id BIGSERIAL PRIMARY KEY ,
    title VARCHAR(255) NOT NULL
);
CREATE TABLE news(
    id BIGSERIAL PRIMARY KEY ,
    title VARCHAR(255) NOT NULL ,
    text VARCHAR(255) NOT NULL ,
    date TIMESTAMPTZ NOT NULL ,
    category_id BIGINT NOT NULL ,
    CONSTRAINT fk_news_category FOREIGN KEY (category_id) REFERENCES category(id)
);