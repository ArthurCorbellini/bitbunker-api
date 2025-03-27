CREATE TABLE IF NOT EXISTS `assets` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,

  `ucid` bigint NULL,
  `name` varchar(30) NOT NULL,
  `symbol` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `tier` varchar(10) NOT NULL,

  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,

  `asset_id` bigint NOT NULL,
  `type` varchar(10) NOT NULL,
  `amount` decimal(20, 7) NOT NULL,
  `unitPrice` decimal(20, 7) NOT NULL,
  `totalValue` decimal(20, 7) NOT NULL,
  `dateTime` date NOT NULL,
  `notes` varchar(255) NULL,

  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,

  FOREIGN KEY (`asset_id`) REFERENCES `assets`(`id`)
);

INSERT INTO `assets` (`ucid`, `name`, `symbol`, `type`, `tier`, `created_at`, `created_by`, `updated_at`, `updated_by`)  
VALUES 
  (1, 'Bitcoin', 'BTC', 'CRYPTO', 'TIER_S', '2025-02-24', 'admin', NULL, NULL),
  (2, 'Solana', 'SOL', 'CRYPTO', 'TIER_A', '2025-02-24', 'admin', NULL, NULL),
  (3, 'Ethereum', 'ETH', 'CRYPTO', 'TIER_A', '2025-02-24', 'admin', NULL, NULL),
  (null, 'Real', 'BRL', 'FIAT', 'NO_TIER', '2025-02-24', 'admin', NULL, NULL);

INSERT INTO `transactions` (`asset_id`, `type`, `amount`, `unitPrice`, `totalValue`, `dateTime`, `notes`, `created_at`, `created_by`, `updated_at`, `updated_by`)  
VALUES 
  (1, 'BUY', 0.0050000, 150000.0000000, 150000.0000000, '2025-02-24', 'Compra de Bitcoin', '2025-02-24', 'admin', NULL, NULL),
  (1, 'SELL', 0.0020000, 100000.0000000, 150000.0000000, '2025-02-24', NULL, '2025-02-24', 'admin', NULL, NULL),
  (3, 'DEPOSIT', 5000, 5000, 150000.0000000, '2025-02-24', NULL, '2025-02-24', 'admin', NULL, NULL);
