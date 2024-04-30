-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 30 avr. 2024 à 12:55
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `jo_2024_2`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE `administrateur` (
  `id_administrateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `billet`
--

CREATE TABLE `billet` (
  `idbillet` int(11) NOT NULL,
  `cle_billet` varchar(255) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `validite` bit(1) NOT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `offre_id` int(11) DEFAULT NULL,
  `id_reservation` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `id` bigint(20) NOT NULL,
  `categorie` varchar(255) DEFAULT NULL,
  `date_event` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_disponible` bit(1) DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `nombre_de_places_disponibles` int(11) DEFAULT NULL,
  `nombre_de_places_max` int(11) DEFAULT NULL,
  `prix_unitaire` double NOT NULL,
  `titre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `event_billets`
--

CREATE TABLE `event_billets` (
  `event_id` bigint(20) NOT NULL,
  `billets_idbillet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `event_offres`
--

CREATE TABLE `event_offres` (
  `events_id` bigint(20) NOT NULL,
  `offres_idoffre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `offre`
--

CREATE TABLE `offre` (
  `idoffre` int(11) NOT NULL,
  `categorie` varchar(100) DEFAULT NULL,
  `date_event` date NOT NULL,
  `description` tinytext NOT NULL,
  `nb_actual_place` int(11) DEFAULT NULL,
  `nb_max_place` int(11) DEFAULT NULL,
  `prix` float NOT NULL,
  `type` varchar(100) DEFAULT NULL,
  `nb_place` int(11) NOT NULL,
  `remise` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `offredanspanier`
--

CREATE TABLE `offredanspanier` (
  `idoffre_dans_panier` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `id_offre` int(11) DEFAULT NULL,
  `id_panier` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `id_panier` int(11) NOT NULL,
  `id_utilisateur` int(11) DEFAULT NULL,
  `sommme_total` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `qrcode`
--

CREATE TABLE `qrcode` (
  `id_qr` int(11) NOT NULL,
  `data` tinytext DEFAULT NULL,
  `billet_id` int(11) DEFAULT NULL,
  `qr_image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `prix` double NOT NULL,
  `id_event` bigint(20) DEFAULT NULL,
  `id_offre` int(11) DEFAULT NULL,
  `id_panier` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_utilisateur` int(11) NOT NULL,
  `cle_utilisateur` varchar(100) DEFAULT NULL,
  `id_panier` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `cle_utilisateur`, `id_panier`) VALUES
(1, 'A8qTBqIjtkicNZ2kqpwRuCh9JZboUYZ7FuZMLkkaBlnhTKlCDoYXfuJ4KfkyxHvwrxtkBEOmarFzyVSfOYDojMyeUWR6Jyv30P8W', NULL),
(2, 'oVUdvcE1jjlUI9w2iPF9AnORLsCrBiFVB260ZaX0QfjhIKz5erRQUl5GmeHy2J45IuAom5ehJRwEfonJyRXeRYrw2Wom0fhiIieY', NULL),
(3, 'ozePnLIYmJY1NuZXu0OrOwA7EuYD79ppzkpAe7unCQVrFV8zacPW8jAOxTsCYjgDW4ZW5YtR7XmmbaHQFQHWdkh8NDdB2fVfXAaX', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurprincipal`
--

CREATE TABLE `utilisateurprincipal` (
  `id_utilisateur` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `role` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateurprincipal`
--

INSERT INTO `utilisateurprincipal` (`id_utilisateur`, `email`, `mot_de_passe`, `nom`, `prenom`, `role`) VALUES
(1, 'aliii@gmail.com', '$2a$10$QTqhPyM9dt1gGB32euV94ODvKrj6/2KaVqv0JwftPNPL5YR0curUi', 'ali', 'ali', 'Admin'),
(2, 'a@gmail.com', '$2a$10$YOrO1LFqmfEJtDVVOeTCBubD/RWHjMqVDbld2z6nvsvF95IzUrt4G', 'dzzd', 'azd', 'ADMIN'),
(3, 'b@gmail.com', '$2a$10$3PM1YDW8vZSodRw.Lh98LeG0WqxiSwscvhr2KadVmj9BLi5b2ZXdu', 'ezce', 'zcze', 'USER');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD PRIMARY KEY (`id_administrateur`);

--
-- Index pour la table `billet`
--
ALTER TABLE `billet`
  ADD PRIMARY KEY (`idbillet`),
  ADD UNIQUE KEY `UK_9pqerysp5hdh17lqf3quhkhh2` (`id_reservation`),
  ADD KEY `FKsr2fu6wgop47tbahcwmvn0dpb` (`id_utilisateur`),
  ADD KEY `FKddmamhgjmc0u04pj1s1i49ymd` (`offre_id`);

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `event_billets`
--
ALTER TABLE `event_billets`
  ADD UNIQUE KEY `UK_jbd42ecenwiulp1vpi1rho3rj` (`billets_idbillet`),
  ADD KEY `FKcp7q1st4e08wi4exaci3f68o7` (`event_id`);

--
-- Index pour la table `event_offres`
--
ALTER TABLE `event_offres`
  ADD KEY `FKqtc280p0j9j7s7ts39thyeh12` (`offres_idoffre`),
  ADD KEY `FKsy3q9fq5ys9rv43avnropje3y` (`events_id`);

--
-- Index pour la table `offre`
--
ALTER TABLE `offre`
  ADD PRIMARY KEY (`idoffre`);

--
-- Index pour la table `offredanspanier`
--
ALTER TABLE `offredanspanier`
  ADD PRIMARY KEY (`idoffre_dans_panier`),
  ADD KEY `FKj3t18iws7gkbl7ap9gkhvl7d` (`id_offre`),
  ADD KEY `FKilai11swgew72oytg41p6flqo` (`id_panier`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id_panier`),
  ADD KEY `FKkh3wxce1l4xl63vqmh0mm23y0` (`id_utilisateur`);

--
-- Index pour la table `qrcode`
--
ALTER TABLE `qrcode`
  ADD PRIMARY KEY (`id_qr`),
  ADD UNIQUE KEY `UK_34q6fmfd6u8tjsnvc93goljyd` (`billet_id`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjfan28uuxtyrdilbfinf0maha` (`id_event`),
  ADD KEY `FKq2nh309unwbs8q711pmpwodg4` (`id_offre`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`),
  ADD UNIQUE KEY `UK_hyjnfr2yer0lwt71c4hfmj08f` (`id_panier`);

--
-- Index pour la table `utilisateurprincipal`
--
ALTER TABLE `utilisateurprincipal`
  ADD PRIMARY KEY (`id_utilisateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `billet`
--
ALTER TABLE `billet`
  MODIFY `idbillet` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `offre`
--
ALTER TABLE `offre`
  MODIFY `idoffre` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `offredanspanier`
--
ALTER TABLE `offredanspanier`
  MODIFY `idoffre_dans_panier` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `panier`
--
ALTER TABLE `panier`
  MODIFY `id_panier` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `qrcode`
--
ALTER TABLE `qrcode`
  MODIFY `id_qr` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateurprincipal`
--
ALTER TABLE `utilisateurprincipal`
  MODIFY `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD CONSTRAINT `FK25uyh9ag429vh7c0c7l9ebq0e` FOREIGN KEY (`id_administrateur`) REFERENCES `utilisateurprincipal` (`id_utilisateur`);

--
-- Contraintes pour la table `billet`
--
ALTER TABLE `billet`
  ADD CONSTRAINT `FKddmamhgjmc0u04pj1s1i49ymd` FOREIGN KEY (`offre_id`) REFERENCES `offre` (`idoffre`),
  ADD CONSTRAINT `FKl4s2xtembxl3d0v69t51l39re` FOREIGN KEY (`id_reservation`) REFERENCES `reservation` (`id`),
  ADD CONSTRAINT `FKsr2fu6wgop47tbahcwmvn0dpb` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateurprincipal` (`id_utilisateur`);

--
-- Contraintes pour la table `event_billets`
--
ALTER TABLE `event_billets`
  ADD CONSTRAINT `FKcp7q1st4e08wi4exaci3f68o7` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `FKgln78f69ajj04xm04lrawwmvq` FOREIGN KEY (`billets_idbillet`) REFERENCES `billet` (`idbillet`);

--
-- Contraintes pour la table `event_offres`
--
ALTER TABLE `event_offres`
  ADD CONSTRAINT `FKqtc280p0j9j7s7ts39thyeh12` FOREIGN KEY (`offres_idoffre`) REFERENCES `offre` (`idoffre`),
  ADD CONSTRAINT `FKsy3q9fq5ys9rv43avnropje3y` FOREIGN KEY (`events_id`) REFERENCES `event` (`id`);

--
-- Contraintes pour la table `offredanspanier`
--
ALTER TABLE `offredanspanier`
  ADD CONSTRAINT `FKilai11swgew72oytg41p6flqo` FOREIGN KEY (`id_panier`) REFERENCES `panier` (`id_panier`),
  ADD CONSTRAINT `FKj3t18iws7gkbl7ap9gkhvl7d` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`idoffre`);

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `FKkh3wxce1l4xl63vqmh0mm23y0` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateurprincipal` (`id_utilisateur`);

--
-- Contraintes pour la table `qrcode`
--
ALTER TABLE `qrcode`
  ADD CONSTRAINT `FKbfao2v1c6sa2n70lrfwjll97e` FOREIGN KEY (`billet_id`) REFERENCES `billet` (`idbillet`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FKjfan28uuxtyrdilbfinf0maha` FOREIGN KEY (`id_event`) REFERENCES `event` (`id`),
  ADD CONSTRAINT `FKq2nh309unwbs8q711pmpwodg4` FOREIGN KEY (`id_offre`) REFERENCES `offre` (`idoffre`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FKjlxdyfygs49ssf3s0ul248gjv` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateurprincipal` (`id_utilisateur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
