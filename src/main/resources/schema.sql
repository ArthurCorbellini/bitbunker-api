--------------------------------------------
-- Estrutura de tabelas 
--------------------------------------------
--------------------------------------------
-- Tabela: users (SERÁ IMPLEMENTADO POSTERIORMENTE)
-- Descrição:
--   - Armazena os dados dos usuários cadastrados no sistema.
--   - Cada usuário poderá cadastrar categorias, ativos e transações.
-- Regras:
--   - O e-mail deve ser único.
--   - A senha deve ser armazenada de forma segura (hash, com criptografia).
--   - Campos de auditoria permitem rastrear criação e atualização dos registros.
-- Observações:
--   - A autenticação será implementada futuramente com Spring Security.

-- CREATE TABLE IF NOT EXISTS `users` (
--   `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
--   `created_at` date NOT NULL,
--   `updated_at` date DEFAULT NULL,
--
--   `name` varchar(255) NOT NULL,
--   `email` VARCHAR(255) NOT NULL UNIQUE,
--   `password` TEXT NOT NULL
-- );
--------------------------------------------
--------------------------------------------
-- Tabela: asset_categories
-- Descrição:
--   - Armazena as categorias de ativos criadas pelo usuário.
--   - Permite definir percentuais recomendados para rebalanceamento da carteira.
-- Regras:
--   - O nome da categoria é obrigatório.
--   - O percentual recomendado é armazenado com duas casas decimais.
-- Observações:
--   - No futuro, cada categoria será vinculada a um usuário via `owner_id`.
--   - Campos de auditoria são gerenciados automaticamente pelo banco.

CREATE TABLE IF NOT EXISTS `asset_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  `name` varchar(30) NOT NULL,
  `recommended_percentage` decimal(5, 2) NOT NULL
  -- `owner_id` bigint NOT NULL,

  -- FOREIGN KEY (`owner_id`) REFERENCES `users`(`id`)
);
--------------------------------------------
--------------------------------------------
-- Tabela: assets
-- Descrição:
--   - Armazena os ativos que fazem parte da carteira recomendada.
--   - Cada ativo é vinculado a uma categoria.
-- Regras:
--   - O símbolo, UCID e nome do ativo são obrigatórios.
--   - O par (ucid, category_id) deve ser único.
-- Observações:
--   - Os dados inseridos aqui representam o vínculo ativo com categoria,
--     não necessariamente os que o usuário possui em carteira.

CREATE TABLE IF NOT EXISTS `assets` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  `symbol` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `ucid` bigint NOT NULL,
  `category_id` bigint NOT NULL,

  UNIQUE (`ucid`, `category_id`),
  FOREIGN KEY (`category_id`) REFERENCES `asset_categories`(`id`)
);
--------------------------------------------
--------------------------------------------
-- Tabela: transactions
-- Descrição:
--   - Armazena o histórico de transações realizadas pelo usuário com ativos.
--   - Essencial para cálculo de desempenho, saldo e geração de relatórios/dashboards.
-- Regras:
--   - Cada transação deve estar vinculada a um ativo, que por sua vez está vinculada a uma
--     categoria, que por sua vez está vinculada a um usuário.
--   - O tipo de transação é restrito a: DEPOSIT, WITHDRAWAL, BUY ou SELL.
--   - Datas, quantidades e valores unitários são obrigatórios.
-- Observações:
--   - Permite armazenar anotações opcionais via campo `notes`.

CREATE TABLE IF NOT EXISTS `transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  `asset_id` bigint NOT NULL,
  `type` ENUM('DEPOSIT', 'WITHDRAWAL', 'BUY', 'SELL') NOT NULL,
  `date_time` date NOT NULL,
  `quantity` decimal(20, 7) NOT NULL,
  `unit_price` decimal(20, 7) NOT NULL,
  `fee` decimal(20, 7) NOT NULL,
  `notes` varchar(255) NULL,

  FOREIGN KEY (`asset_id`) REFERENCES `assets`(`id`)
);
--------------------------------------------

-- INSERT INTO `assets` (`ucid`, `name`, `symbol`, `type`, `tier`, `created_at`, `created_by`, `updated_at`, `updated_by`)  
-- VALUES 
--   (1, 'Bitcoin', 'BTC', 'CRYPTO', 'TIER_S', '2025-02-24', 'admin', NULL, NULL),
--   (2, 'Solana', 'SOL', 'CRYPTO', 'TIER_A', '2025-02-24', 'admin', NULL, NULL),
--   (3, 'Ethereum', 'ETH', 'CRYPTO', 'TIER_A', '2025-02-24', 'admin', NULL, NULL),
--   (null, 'Real', 'BRL', 'FIAT', 'NO_TIER', '2025-02-24', 'admin', NULL, NULL);

-- INSERT INTO `transactions` (`asset_id`, `type`, `amount`, `unit_price`, `total_value`, `date_time`, `notes`, `created_at`, `created_by`, `updated_at`, `updated_by`)
-- VALUES 
--   (1, 'BUY', 0.0050000, 150000.0000000, 150000.0000000, '2025-02-24', 'Compra de Bitcoin', '2025-02-24', 'admin', NULL, NULL),
--   (1, 'SELL', 0.0020000, 100000.0000000, 150000.0000000, '2025-02-24', NULL, '2025-02-24', 'admin', NULL, NULL),
--   (3, 'DEPOSIT', 5000, 5000, 150000.0000000, '2025-02-24', NULL, '2025-02-24', 'admin', NULL, NULL);
