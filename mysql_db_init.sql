CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10001 ;

INSERT INTO `users` (`user_id`, `username`,  `password`) VALUES
(1, 'magician', '@AbraKaDabr@');

CREATE TABLE IF NOT EXISTS `betting_data` (
  `bet_uid` int(11) NOT NULL AUTO_INCREMENT,
  `id` int,
  `total_bets` int,
  `game` varchar(50) DEFAULT NULL,
  `stake` DECIMAL(12, 2),
  `total_returns` DECIMAL(12, 2),
  `client_id` int,
  `game_date` DATE,
  PRIMARY KEY (`bet_uid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10001 ;

CREATE INDEX search_filter
ON `betting_data` (`game`, `client_id`, `game_date`);