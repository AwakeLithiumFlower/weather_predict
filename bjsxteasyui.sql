/*
MySQL Data Transfer
Source Host: localhost
Source Database: bjsxteasyui
Target Host: localhost
Target Database: bjsxteasyui
Date: 2013-1-30 1:22:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for city
-- ----------------------------
CREATE TABLE `city` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `pro_id` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for org
-- ----------------------------
CREATE TABLE `org` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `iconCls` varchar(50) default NULL,
  `principal` varchar(50) default NULL,
  `count` int(10) default NULL,
  `description` varchar(500) default NULL,
  `state` varchar(50) default NULL,
  `parent_id` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for province
-- ----------------------------
CREATE TABLE `province` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
CREATE TABLE `resource` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(50) default NULL,
  `url` varchar(100) default NULL,
  `checked` int(10) default NULL,
  `icon` varchar(50) default NULL,
  `parent_id` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `id` int(10) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `sex` varchar(10) default NULL,
  `age` int(10) default NULL,
  `birthday` varchar(50) default NULL,
  `city` int(10) default NULL,
  `salary` varchar(50) default NULL,
  `starttime` varchar(100) default NULL,
  `endtime` varchar(100) default NULL,
  `description` varchar(500) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `city` VALUES ('1', '长春', '1');
INSERT INTO `city` VALUES ('2', '吉林', '1');
INSERT INTO `city` VALUES ('3', '成都', '2');
INSERT INTO `city` VALUES ('4', '攀枝花', '2');
INSERT INTO `city` VALUES ('5', '长沙', '3');
INSERT INTO `city` VALUES ('6', '岳阳', '3');
INSERT INTO `city` VALUES ('7', '湘潭', '3');
INSERT INTO `city` VALUES ('8', '沈阳', '4');
INSERT INTO `city` VALUES ('9', '大连', '4');
INSERT INTO `org` VALUES ('1', '总公司', null, '张三', '100', '我是总公司', null, '999999');
INSERT INTO `org` VALUES ('2', '北京分公司', null, '李四', '50', '我是北京分公司', null, '1');
INSERT INTO `org` VALUES ('3', '上海分公司', null, '王五', '50', '我是上海分公司', null, '1');
INSERT INTO `org` VALUES ('5', '北京财务部', null, '小1', '20', '北京财务部门', null, '2');
INSERT INTO `org` VALUES ('6', '北京产品部', null, '小2', '20', '北京产品部门', null, '2');
INSERT INTO `org` VALUES ('7', '北京研发部', null, '小3', '20', '北京研发部门', null, '2');
INSERT INTO `org` VALUES ('8', '上海产品部', null, '小4', '20', '上海产品部门', null, '3');
INSERT INTO `org` VALUES ('9', '上海研发部', null, '小5', '0', '上海研发部门', 'open', '3');
INSERT INTO `org` VALUES ('13', '上海销售部', null, '小6', '20', '我是上海销售部门', 'open', '3');
INSERT INTO `province` VALUES ('1', '吉林省');
INSERT INTO `province` VALUES ('2', '四川省');
INSERT INTO `province` VALUES ('3', '湖南省');
INSERT INTO `province` VALUES ('4', '辽宁省');
INSERT INTO `resource` VALUES ('1', '权限菜单', null, '0', null, '999999');
INSERT INTO `resource` VALUES ('2', '用户管理', null, '0', null, '1');
INSERT INTO `resource` VALUES ('3', '岗位管理', null, '0', null, '1');
INSERT INTO `resource` VALUES ('4', '资源管理', null, '0', null, '1');
INSERT INTO `resource` VALUES ('5', '用户功能1', null, '0', null, '2');
INSERT INTO `resource` VALUES ('7', '岗位功能1', '3333', '0', null, '3');
INSERT INTO `resource` VALUES ('60', '资源功能1', '111', '0', null, '4');
INSERT INTO `resource` VALUES ('61', '资源功能2', '223233232', '0', null, '4');
INSERT INTO `resource` VALUES ('62', '资源功能3', '432423', '0', null, '4');
INSERT INTO `resource` VALUES ('63', '资源功能4', '5555', '0', null, '4');
INSERT INTO `resource` VALUES ('64', '资源功能4的孩子1', '1111', '0', null, '63');
INSERT INTO `resource` VALUES ('65', '资源功能4的孩子2', '1111', '0', null, '63');
INSERT INTO `resource` VALUES ('70', '资源功能4的孩子3', '3333', '0', null, '63');
INSERT INTO `user` VALUES ('1', 'admin', '1111', '1', '20', '2013-01-02', '4', '1000.00', '2013-01-01 01:40:22', '2013-01-31 01:40:24', '我是管理员');
INSERT INTO `user` VALUES ('6', '张三', '1234', '1', '20', '2013-01-24', '1', '2000.00', '2013-01-03 01:50:52', '2013-01-31 01:50:54', '我是张小4');
INSERT INTO `user` VALUES ('8', '李四', '1234', '2', '20', '2013-01-01', '3', '1400.00', '2013-01-01 16:18:49', '2013-01-19 16:18:54', '我是李小四我是李小四我是李小四我是李小四我是李小四');
INSERT INTO `user` VALUES ('9', '王五', '1234', '2', '26', '2013-01-02', '1', '2000.00', '2013-01-04 16:23:47', '2013-01-31 16:23:49', '8888888888888888111111111111111111111111');
INSERT INTO `user` VALUES ('10', '赵六', '1234', '1', '20', '2013-01-10', '4', '2000.00', '2013-01-17 16:29:23', '2013-01-09 16:29:26', '我是赵小六');
INSERT INTO `user` VALUES ('34', '小4', '3333', '1', '20', '2013-01-10', '2', '3000.00', '2013-01-09 03:31:26', '2013-01-29 03:31:27', '我是小4');
INSERT INTO `user` VALUES ('35', '小5', '4444', '1', '30', '2013-01-02', '1', '4000.00', '2013-01-01 03:46:35', '2013-01-30 03:46:38', '我是小5');
