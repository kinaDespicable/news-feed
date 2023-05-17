DROP TABLE IF EXISTS "news";
DROP TABLE IF EXISTS "topic";
DROP TABLE IF EXISTS "site_user";
DROP TABLE IF EXISTS "role";
DROP TABLE IF EXISTS "news_source";

CREATE TABLE IF NOT EXISTS "news_source" (
  "id" BIGSERIAL,
  "name" VARCHAR (64) NOT NULL,
  "source_name" VARCHAR (255) NOT NULL,
  "url" VARCHAR (255) NOT NULL,
  "description" TEXT,
  "registered_at" DATE DEFAULT CURRENT_DATE,
  PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "role" (
  "id" BIGSERIAL,
  "role" VARCHAR (64) NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "topic" (
  "id" BIGSERIAL,
  "topic" VARCHAR (64) NOT NULL,
  "description" VARCHAR (255) NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "site_user" (
  "id" BIGSERIAL,
  "first_name" VARCHAR (32) NOT NULL,
  "last_name" VARCHAR (32) NOT NULL,
  "username" VARCHAR (64) NOT NULL,
  "password" VARCHAR (255) NOT NULL,
  "registered_at" DATE DEFAULT CURRENT_DATE,
  "role_id" BIGSERIAL,
  PRIMARY KEY ("id"),
  CONSTRAINT "FK_site_user.role_id"
    FOREIGN KEY ("role_id")
      REFERENCES "role"("id")
);

CREATE TABLE IF NOT EXISTS "news" (
  "id" BIGSERIAL,
  "title" VARCHAR (150) NOT NULL,
  "content" TEXT NOT NULL,
  "published_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  "topic_id" BIGSERIAL,
  "author_id" BIGSERIAL,
  "news_source_id" BIGSERIAL,
  PRIMARY KEY ("id"),
  CONSTRAINT "FK_news.author_id"
    FOREIGN KEY ("author_id")
      REFERENCES "site_user"("id"),
  CONSTRAINT "FK_news.news_source_id"
    FOREIGN KEY ("news_source_id")
      REFERENCES "news_source"("id"),
  CONSTRAINT "FK_news.topic_id"
    FOREIGN KEY ("topic_id")
      REFERENCES "topic"("id")
);

