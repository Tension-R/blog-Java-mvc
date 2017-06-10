CREATE TABLE user (
  `username` VARCHAR(255) NOT NULL COMMENT '用户名',
  `password` VARCHAR(255) NOT NULL COMMENT '密码',
  `sex` INT DEFAULT 0 COMMENT '性别 1：男 0：女',
  `telephone` BIGINT NOT NULL COMMENT '手机号',
  PRIMARY KEY (`username`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '用户表';

CREATE TABLE article(
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL COMMENT '标题',
  `content` TEXT NOT NULL COMMENT '正文',
  `date` DATE NOT NULL COMMENT '时间',
  `author_username` VARCHAR(255) NOT NULL COMMENT '作者用户名',
  PRIMARY KEY (`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '博文表';

CREATE TABLE user_article(
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_username` VARCHAR(255) NOT NULL COMMENT '作者用户名',
  `article_id` INT NOT NULL COMMENT '博文ID',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_username`) REFERENCES user(`username`),
  FOREIGN KEY (`article_id`) REFERENCES article(`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '用户博文对应表';

INSERT INTO user VALUE ('tension','password',1,15311231534);

INSERT INTO article VALUE (1,'java','学习java中','2017-06-09');
INSERT INTO article VALUE (2,'java','写项目中','2017-06-10');

INSERT INTO user_article VALUE (1,'tension',1);
INSERT INTO user_article VALUE (2,'tension',2);
