-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE IF NOT EXISTS GAME
(
    id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50)     NOT NULL,
    trial_count INT             NOT NULL default 0,
    created_at  DATETIME        NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS PLAYER
(
    id       BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    game_id  BIGINT UNSIGNED NOT NULL,
    name     VARCHAR(5)      NOT NULL,
    position INT             NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES GAME (id)
);
