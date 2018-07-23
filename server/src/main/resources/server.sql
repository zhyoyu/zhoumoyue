DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `open_id` varchar(64) NOT NULL COMMENT '用户唯一识别ID',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `icon_url` varchar(256) DEFAULT NULL COMMENT '头像url',
  `sex` int(1) DEFAULT NULL COMMENT '性别 1 男 2 女',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `active_value` int(11) DEFAULT '0' COMMENT '活跃度',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `last_login_time` datetime NOT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '话题自增id',
  `author_uid` varchar(64) NOT NULL COMMENT '创建者唯一id',
  `author_nick_name` varchar(255) NOT NULL COMMENT '创建者昵称',
  `author_icon_url` varchar(255) NOT NULL COMMENT '头像url',
  `content` longtext NOT NULL COMMENT '发表的内容',
  `images` longtext COMMENT 'topic图片地址',
  `create_time` datetime NOT NULL COMMENT '发表时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='话题表';

DROP TABLE IF EXISTS `topic_comment`;
CREATE TABLE `topic_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `topic_id` bigint(20) NOT NULL COMMENT '话题id',
  `comment_user_id` varchar(64) NOT NULL COMMENT '评论用户id',
  `comment_user_name` varchar(64) NOT NULL COMMENT '评论用户名',
  `content` longtext NOT NULL COMMENT '评论内容',
  `reply_user_id` varchar(64) NOT NULL DEFAULT '' COMMENT '回复给某用户id',
  `reply_user_name` varchar(64) NOT NULL COMMENT '回复目标用户名',
  `reply_time` datetime NOT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`),
  KEY `hash_topic_id` (`topic_id`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='话题评论表';

DROP TABLE IF EXISTS `topic_info`;
CREATE TABLE `topic_info` (
  `topic_id` bigint(20) NOT NULL COMMENT '话题id',
  `comment_users` longtext COMMENT '评论的用户(uid1,uid2)',
  `like_users` longtext COMMENT '点赞的用户(uid1,uid2)',
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='话题相关统计';

DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `create_user` varchar(20) NOT NULL COMMENT '活动创建者(用户唯一标识id)',
  `title` varchar(20) NOT NULL COMMENT '活动标题',
  `address` varchar(256) NOT NULL COMMENT '活动地址',
  `describe` varchar(512) NOT NULL COMMENT '活动描述',
  `activity_time` datetime NOT NULL COMMENT '活动时间',
  `num_limit` int(3) NOT NULL DEFAULT '0' COMMENT '报名人数限制(0 不限制)',
  `join_users` varchar(512) DEFAULT NULL COMMENT 'open_id;open_id;....',
  `create_time` datetime NOT NULL COMMENT '活动创建时间',
  PRIMARY KEY (`activity_id`,`create_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动表';