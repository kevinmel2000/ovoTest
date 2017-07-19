/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost
 Source Database       : ovo

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 07/19/2017 16:19:02 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `krs`
-- ----------------------------
DROP TABLE IF EXISTS `krs`;
CREATE TABLE `krs` (
  `mahasiswa_id` int(11) NOT NULL,
  `matakuliah_id` int(11) NOT NULL,
  PRIMARY KEY (`mahasiswa_id`,`matakuliah_id`),
  KEY `FKjj8klsvwkwx8ffyokjclrar44` (`matakuliah_id`),
  CONSTRAINT `FK4kf4kowm56fm379coy0axt9aa` FOREIGN KEY (`mahasiswa_id`) REFERENCES `mahasiswa` (`id`),
  CONSTRAINT `FKjj8klsvwkwx8ffyokjclrar44` FOREIGN KEY (`matakuliah_id`) REFERENCES `matakuliah` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `krs`
-- ----------------------------
BEGIN;
INSERT INTO `krs` VALUES ('1', '1'), ('1', '2');
COMMIT;

-- ----------------------------
--  Table structure for `mahasiswa`
-- ----------------------------
DROP TABLE IF EXISTS `mahasiswa`;
CREATE TABLE `mahasiswa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `f_name` varchar(255) NOT NULL,
  `hp` varchar(255) DEFAULT NULL,
  `l_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `mahasiswa`
-- ----------------------------
BEGIN;
INSERT INTO `mahasiswa` VALUES ('1', 'matius.prastowo@iconpln.co.id', 'matius', '085799066159', 'apry prastowo');
COMMIT;

-- ----------------------------
--  Table structure for `matakuliah`
-- ----------------------------
DROP TABLE IF EXISTS `matakuliah`;
CREATE TABLE `matakuliah` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama_mata_kuliah` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `matakuliah`
-- ----------------------------
BEGIN;
INSERT INTO `matakuliah` VALUES ('1', 'PEMROGRAMAN JAVA'), ('2', 'BASIS DATA');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
