-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 12, 2021 at 10:47 AM
-- Server version: 5.7.24
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `treeofskillsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `competence`
--

CREATE TABLE `competence` (
  `idRef` int(11) DEFAULT NULL,
  `idComp` int(11) NOT NULL,
  `idNiveau` int(11) DEFAULT NULL,
  `titreComp` varchar(254) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `competence`
--

INSERT INTO `competence` (`idRef`, `idComp`, `idNiveau`, `titreComp`, `status`) VALUES
(2, 1, 0, 'Maquettage', 0),
(1, 2, 0, 'conception db', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `competence`
--
ALTER TABLE `competence`
  ADD PRIMARY KEY (`idComp`),
  ADD KEY `FK_Association_4` (`idRef`),
  ADD KEY `FK_Association_5` (`idNiveau`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `competence`
--
ALTER TABLE `competence`
  MODIFY `idComp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `competence`
--
ALTER TABLE `competence`
  ADD CONSTRAINT `FK_Association_4` FOREIGN KEY (`idRef`) REFERENCES `refereniel` (`idRef`),
  ADD CONSTRAINT `FK_Association_5` FOREIGN KEY (`idNiveau`) REFERENCES `niveau` (`idNiveau`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
