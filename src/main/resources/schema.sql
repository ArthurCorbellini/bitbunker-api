--------------------------------------------
-- Estrutura de tabelas 
--------------------------------------------
--------------------------------------------
-- Tabela: users (SERÁ IMPLEMENTADO POSTERIORMENTE)
-- Descrição:
--   - Armazena os dados dos usuários cadastrados no sistema.
--   - Cada usuário pode possuir um ou mais portfólios de criptoativos.
-- Regras:
--   - O e-mail deve ser único.
--   - A senha deve ser armazenada em formato criptografado.
--   - Campos de auditoria permitem rastrear criação e atualização do registro.
-- Observações:
--   - Será desenvolvido posteriormente, junto com o Spring Security;

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
-- Tabela: portfolios
-- Descrição:
--   - Armazena os portfólios individuais de cada usuário.
--   - Permite organizar as transações em diferentes carteiras,
--     possibilitando dashboards e visões personalizadas por portfólio.
-- Regras:
--   - Cada portfólio pertence a um único usuário (relacionamento via chave estrangeira).
--   - Todos os usuários têm permissão para cadastrar seus próprios portfólios.
-- Observações:
--   - Um usuário pode possuir múltiplos portfólios.
--   - Campos de auditoria básicos estão incluídos (data de criação e atualização).

CREATE TABLE IF NOT EXISTS `portfolios` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  `name` varchar(30) NOT NULL,
  -- `owner_id` bigint NOT NULL,

  -- FOREIGN KEY (`owner_id`) REFERENCES `users`(`id`)
);
--------------------------------------------
--------------------------------------------
-- Tabela: asset_categories
-- Descrição:
--   - Armazena as categorias de ativos recomendados dentro de um portfólio.
--   - Permite definir percentuais sugeridos para rebalanceamento da carteira.
-- Regras:
--   - Cada categoria pertence a um portfólio específico.
-- Observações:
--   - Usada na definição da carteira recomendada.

CREATE TABLE IF NOT EXISTS `asset_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  `name` varchar(30) NOT NULL,
  `recommended_percentage` decimal(5, 2) NOT NULL,
  `portfolio_id` bigint NOT NULL,

  FOREIGN KEY (`portfolio_id`) REFERENCES `portfolios`(`id`)
);
--------------------------------------------
--------------------------------------------
-- Tabela: assets
-- Descrição:
--   - Armazena os ativos que compõem a carteira recomendada.
--   - Cada ativo pertence a uma categoria de ativo.
-- Regras:
--   - Um par (ucid, category_id) deve ser único.
--   - Cada ativo deve estar vinculado a uma categoria, que por sua vez está vinculada
--     a um portfolio.
-- Observações:
--   - Os dados inseridos aqui representam os ativos recomendados,
--     não necessariamente os que o usuário já possui em carteira.

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
--   - Registra todas as transações financeiras feitas com os ativos.
--   - Essencial para o cálculo de performance e histórico do portfólio.
-- Regras:
--   - Cada transação deve estar vinculada a um ativo.
--   - Qualquer usuário pode cadastrar transações dentro de seus portfólios.
--   - Cada transação deve estar vinculado a um ativo, que por sua vez está vinculado
--     a uma categoria, que por sua vez está vinculada a um portfolio.
-- Observações:
--   - Tipos permitidos: DEPOSIT, WITHDRAWAL, BUY, SELL.

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
