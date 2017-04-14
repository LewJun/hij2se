/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50041
Source Host           : localhost:3306
Source Database       : scott

Target Server Type    : MYSQL
Target Server Version : 50041
File Encoding         : 65001

Date: 2016-09-05 00:05:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `bonus`
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus` (
  `ID` int(11) NOT NULL auto_increment,
  `EMPNO` int(11) NOT NULL COMMENT '雇员编号',
  `SAL` decimal(7,2) NOT NULL COMMENT '雇员工资',
  `COMM` decimal(7,2) NOT NULL COMMENT '雇员奖金',
  `PAYTIME` date NOT NULL COMMENT '支付时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bonus
-- ----------------------------
INSERT INTO `bonus` VALUES ('1', '7839', '10000.00', '5000.00', '2016-09-12');

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `DEPTNO` int(4) NOT NULL COMMENT '部门编号',
  `DNAME` varchar(14) NOT NULL COMMENT '部门名称',
  `LOC` varchar(13) NOT NULL COMMENT '部门所在位置',
  PRIMARY KEY  (`DEPTNO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('10', 'ACCOUNTING', 'NEW YORK');
INSERT INTO `dept` VALUES ('20', 'RESEARCH', 'DALLAS');
INSERT INTO `dept` VALUES ('30', 'SALES', 'CHICAGO');
INSERT INTO `dept` VALUES ('40', 'OPERATIONS', 'BOSTON');

-- ----------------------------
-- Table structure for `emp`
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `EMPNO` int(4) NOT NULL auto_increment COMMENT '雇员编号',
  `ENAME` varchar(10) default NULL COMMENT '雇员姓名',
  `JOB` varchar(9) default NULL COMMENT '雇员职位',
  `MGR` int(4) default NULL COMMENT '领导编号',
  `HIREDATE` date default NULL COMMENT '雇佣日期',
  `DEPTNO` int(4) default NULL COMMENT '所在部门编号',
  PRIMARY KEY  (`EMPNO`)
) ENGINE=MyISAM AUTO_INCREMENT=7935 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('7369', 'SMITH', 'CLERK', '7902', '1980-12-17', '20');
INSERT INTO `emp` VALUES ('7499', 'ALLEN', 'SALESMAN', '7698', '1981-02-20', '30');
INSERT INTO `emp` VALUES ('7521', 'WARD', 'SALESMAN', '7698', '1981-02-22', '30');
INSERT INTO `emp` VALUES ('7566', 'JONES', 'MANAGER', '7839', '1981-04-02', '20');
INSERT INTO `emp` VALUES ('7654', 'MARTIN', 'SALESMAN', '7698', '1981-09-28', '30');
INSERT INTO `emp` VALUES ('7698', 'BLAKE', 'MANAGER', '7839', '1981-05-01', '30');
INSERT INTO `emp` VALUES ('7782', 'CLARK', 'MANAGER', '7839', '1981-06-09', '10');
INSERT INTO `emp` VALUES ('7839', 'KING', 'PRESIDENT', null, '1981-11-17', '10');
INSERT INTO `emp` VALUES ('7844', 'TURNER', 'SALESMAN', '7698', '1981-09-08', '30');
INSERT INTO `emp` VALUES ('7900', 'JAMES', 'CLERK', '7698', '1981-12-03', '30');
INSERT INTO `emp` VALUES ('7902', 'FORD', 'ANALYST', '7566', '1981-12-03', '20');
INSERT INTO `emp` VALUES ('7934', 'MILLER', 'CLERK', '7782', '1982-01-23', '10');

-- ----------------------------
-- Table structure for `salgrade`
-- ----------------------------
DROP TABLE IF EXISTS `salgrade`;
CREATE TABLE `salgrade` (
  `GRADE` int(11) NOT NULL COMMENT '工资等级',
  `LOSAL` int(11) NOT NULL COMMENT '最低工资',
  `HISAL` int(11) NOT NULL COMMENT '最高工资',
  PRIMARY KEY  (`GRADE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salgrade
-- ----------------------------
INSERT INTO `salgrade` VALUES ('1', '700', '1200');
INSERT INTO `salgrade` VALUES ('2', '1201', '1400');
INSERT INTO `salgrade` VALUES ('3', '1401', '2000');
INSERT INTO `salgrade` VALUES ('4', '2001', '3000');
INSERT INTO `salgrade` VALUES ('5', '3001', '9999');
