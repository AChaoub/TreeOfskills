-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 21 jan. 2021 à 11:49
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `treeofskillsdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `apprenant`
--

CREATE TABLE `apprenant` (
  `idApprenant` int(11) NOT NULL,
  `nomApprenant` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL,
  `prenomApprenant` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL,
  `emailApprenant` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL,
  `telApprenant` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL,
  `passeword` varchar(10) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `apprenant`
--

INSERT INTO `apprenant` (`idApprenant`, `nomApprenant`, `prenomApprenant`, `emailApprenant`, `telApprenant`, `passeword`) VALUES
(1, 'Chaoub', 'Achraf', NULL, NULL, 'achraf'),
(2, 'Boutayeb', 'Ahmed', NULL, NULL, 'ahmed'),
(3, 'Abdo', NULL, NULL, NULL, ''),
(4, 'Zouhair', NULL, NULL, NULL, 'zouhair'),
(5, 'Marroua', NULL, NULL, NULL, 'marroua'),
(6, 'Mery', NULL, NULL, NULL, 'mery'),
(7, 'Ayoub', 'Rekki', NULL, NULL, 'ayoub'),
(8, 'Amine', 'AMine', NULL, NULL, 'amine');

-- --------------------------------------------------------

--
-- Structure de la table `compapprenant`
--

CREATE TABLE `compapprenant` (
  `idApprenant` int(11) NOT NULL,
  `idComp` int(11) NOT NULL,
  `idNiveau` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `compapprenant`
--

INSERT INTO `compapprenant` (`idApprenant`, `idComp`, `idNiveau`) VALUES
(1, 1, 0),
(1, 2, 0),
(1, 3, 0),
(1, 4, 0),
(1, 1, 3),
(2, 1, 2),
(2, 3, 1),
(5, 2, 3),
(5, 3, 2),
(4, 2, 1),
(4, 5, 3),
(4, 1, 3),
(4, 3, 1),
(1, 2, 1),
(1, 1, 1),
(1, 1, 3),
(1, 2, 3),
(1, 1, 2),
(2, 2, 3),
(6, 1, 1),
(6, 2, 3),
(6, 2, 1),
(6, 1, 3),
(6, 1, 2),
(4, 2, 3),
(7, 1, 1),
(7, 2, 3),
(7, 1, 2),
(1, 3, 3),
(1, 3, 1),
(1, 3, 2),
(1, 2, 2),
(1, 1, 2),
(1, 3, 1),
(1, 2, 1),
(1, 1, 1),
(1, 4, 2),
(1, 4, 3);

-- --------------------------------------------------------

--
-- Structure de la table `competence`
--

CREATE TABLE `competence` (
  `idRef` int(11) DEFAULT NULL,
  `idComp` int(11) NOT NULL,
  `idNiveau` int(11) DEFAULT 0,
  `titreComp` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `competence`
--

INSERT INTO `competence` (`idRef`, `idComp`, `idNiveau`, `titreComp`, `status`) VALUES
(1, 1, 1, 'Maquettage', 0),
(1, 2, 1, 'Acces aux donnees', 0),
(2, 3, 0, 'BACKEND', NULL),
(1, 4, 0, 'FRONTEND', NULL),
(2, 5, 0, 'CMS WordPress', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

CREATE TABLE `niveau` (
  `idNiveau` int(11) NOT NULL,
  `titreNiveau` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `niveau`
--

INSERT INTO `niveau` (`idNiveau`, `titreNiveau`) VALUES
(0, 'None'),
(1, 'Debutant'),
(2, 'Intermediare'),
(3, 'Avancé');

-- --------------------------------------------------------

--
-- Structure de la table `promoapprenant`
--

CREATE TABLE `promoapprenant` (
  `id_Promo` int(11) DEFAULT NULL,
  `idApprenant` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `promoapprenant`
--

INSERT INTO `promoapprenant` (`id_Promo`, `idApprenant`) VALUES
(1, 1),
(1, 2),
(2, 3),
(1, 4),
(1, 5),
(2, 6),
(1, 7),
(2, 8);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

CREATE TABLE `promotion` (
  `id_Promo` int(11) NOT NULL,
  `titre_Promo` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL,
  `annee_Promo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`id_Promo`, `titre_Promo`, `annee_Promo`) VALUES
(1, 'MaryJackson', 2),
(2, 'AdaLovelace', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `refereniel`
--

CREATE TABLE `refereniel` (
  `id_Promo` int(11) DEFAULT NULL,
  `idRef` int(11) NOT NULL,
  `titreRef` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `refereniel`
--

INSERT INTO `refereniel` (`id_Promo`, `idRef`, `titreRef`) VALUES
(1, 1, 'Developpeur Web & Mobile'),
(1, 2, 'Concepteur Web & Mobile'),
(2, 3, 'Developpement Web & Mobile');

-- --------------------------------------------------------

--
-- Structure de la table `staff`
--

CREATE TABLE `staff` (
  `id_Promo` int(11) DEFAULT NULL,
  `idStaff` int(11) NOT NULL,
  `nomStaff` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL,
  `prenomStaff` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL,
  `specialiteStaff` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL,
  `nbrAppStaff` int(11) DEFAULT NULL,
  `pswdStaff` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `isStaff` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Déchargement des données de la table `staff`
--

INSERT INTO `staff` (`id_Promo`, `idStaff`, `nomStaff`, `prenomStaff`, `specialiteStaff`, `nbrAppStaff`, `pswdStaff`, `isStaff`) VALUES
(1, 1, 'Hanae', NULL, NULL, NULL, 'hanae', 1),
(2, 2, 'Kamal', NULL, NULL, NULL, 'kamal', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `apprenant`
--
ALTER TABLE `apprenant`
  ADD PRIMARY KEY (`idApprenant`);

--
-- Index pour la table `compapprenant`
--
ALTER TABLE `compapprenant`
  ADD KEY `FK_Association_7` (`idComp`),
  ADD KEY `FK_Association_9` (`idNiveau`),
  ADD KEY `FK_Association_8` (`idApprenant`);

--
-- Index pour la table `competence`
--
ALTER TABLE `competence`
  ADD PRIMARY KEY (`idComp`),
  ADD KEY `FK_Association_4` (`idRef`),
  ADD KEY `FK_Association_5` (`idNiveau`);

--
-- Index pour la table `niveau`
--
ALTER TABLE `niveau`
  ADD PRIMARY KEY (`idNiveau`);

--
-- Index pour la table `promoapprenant`
--
ALTER TABLE `promoapprenant`
  ADD KEY `FK_Association_1` (`idApprenant`),
  ADD KEY `FK_PromoApprenant` (`id_Promo`);

--
-- Index pour la table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id_Promo`);

--
-- Index pour la table `refereniel`
--
ALTER TABLE `refereniel`
  ADD PRIMARY KEY (`idRef`),
  ADD KEY `FK_Association_3` (`id_Promo`);

--
-- Index pour la table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`idStaff`),
  ADD KEY `FK_Association_2` (`id_Promo`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `competence`
--
ALTER TABLE `competence`
  MODIFY `idComp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `staff`
--
ALTER TABLE `staff`
  MODIFY `idStaff` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compapprenant`
--
ALTER TABLE `compapprenant`
  ADD CONSTRAINT `FK_Association_7` FOREIGN KEY (`idComp`) REFERENCES `competence` (`idComp`),
  ADD CONSTRAINT `FK_Association_8` FOREIGN KEY (`idApprenant`) REFERENCES `apprenant` (`idApprenant`),
  ADD CONSTRAINT `FK_Association_9` FOREIGN KEY (`IdNiveau`) REFERENCES `niveau` (`idNiveau`);

--
-- Contraintes pour la table `competence`
--
ALTER TABLE `competence`
  ADD CONSTRAINT `FK_Association_4` FOREIGN KEY (`idRef`) REFERENCES `refereniel` (`idRef`),
  ADD CONSTRAINT `FK_Association_5` FOREIGN KEY (`idNiveau`) REFERENCES `niveau` (`idNiveau`);

--
-- Contraintes pour la table `promoapprenant`
--
ALTER TABLE `promoapprenant`
  ADD CONSTRAINT `FK_Association_1` FOREIGN KEY (`idApprenant`) REFERENCES `apprenant` (`idApprenant`),
  ADD CONSTRAINT `FK_PromoApprenant` FOREIGN KEY (`id_Promo`) REFERENCES `promotion` (`id_Promo`);

--
-- Contraintes pour la table `refereniel`
--
ALTER TABLE `refereniel`
  ADD CONSTRAINT `FK_Association_3` FOREIGN KEY (`id_Promo`) REFERENCES `promotion` (`id_Promo`);

--
-- Contraintes pour la table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `FK_Association_2` FOREIGN KEY (`id_Promo`) REFERENCES `promotion` (`id_Promo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
