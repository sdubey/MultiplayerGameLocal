--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
CREATE TABLE IF NOT EXISTS `player` (
  `player_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `online` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `play_time` int(10) unsigned NOT NULL DEFAULT '0',
  `last_login` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00',
  `last_logout` timestamp NOT NULL DEFAULT '1970-01-01 00:00:00',
  `last_ip` varchar(15) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`player_id`),
  UNIQUE KEY `user_name` (`username`),
  UNIQUE KEY `email` (`email`),
  KEY `email_password` (`email`,`password`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`player_id`, `username`, `email`, `password`, `first_name`, `last_name`, `online`, `play_time`, `last_login`, `last_logout`, `last_ip`, `create_time`) VALUES
(1, '1', '1', 'c4ca4238a0b923820dcc509a6f75849b', '', '', 0, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00', '127.0.0.1', '1970-01-01 00:00:00'),
(2, '2', '2', 'c81e728d9d4c2f636f067f89cc14862c', '', '', 0, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00', '127.0.0.1', '1970-01-01 00:00:00'),
(3, '3', '3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '', '', 0, 0, '1970-01-01 00:00:00', '1970-01-01 00:00:00', '127.0.0.1', '1970-01-01 00:00:00');
