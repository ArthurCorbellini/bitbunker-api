CREATE TABLE IF NOT EXISTS `tokens` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,

  `ucid` bigint NOT NULL,
  `name` varchar(30) NOT NULL,
  `symbol` varchar(10) NOT NULL,
  `classification` varchar(10) NOT NULL,

  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,

  `token_id` bigint NOT NULL,
  `type` varchar(10) NOT NULL,
  `quantity` decimal(20, 7) NOT NULL,
  `fiat_currency_moved` decimal(20, 7) NOT NULL,
  `notes` varchar(255) NULL,

  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,

  FOREIGN KEY (`token_id`) REFERENCES `tokens`(`id`)
);
