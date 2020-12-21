-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 19 Juin 2018 à 17:28
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `gottra`
--

-- --------------------------------------------------------

--
-- Structure de la table `acteur`
--

CREATE TABLE IF NOT EXISTS `acteur` (
  `ID_acteur` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_acteur`),
  UNIQUE KEY `nom` (`nom`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `acteur`
--

INSERT INTO `acteur` (`ID_acteur`, `nom`) VALUES
(8, 'Administrateur'),
(9, 'Contributeur'),
(7, 'Internaute');

-- --------------------------------------------------------

--
-- Structure de la table `action`
--

CREATE TABLE IF NOT EXISTS `action` (
  `ID_action` int(11) NOT NULL AUTO_INCREMENT,
  `ID_cas` int(11) NOT NULL,
  `action` text NOT NULL,
  `output` text NOT NULL,
  `full_text` text NOT NULL,
  PRIMARY KEY (`ID_action`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Contenu de la table `action`
--

INSERT INTO `action` (`ID_action`, `ID_cas`, `action`, `output`, `full_text`) VALUES
(1, 1, 'Si l’utilisateur clique', '', 'Si l’utilisateur clique'),
(2, 1, 'sur le logo depuis une page intérieure', ' le système redirige vers la page d’accueil du site sans jouer la vidéo du bloc identitaire et sans les informations temps réels..', 'sur le logo depuis une page intérieure, le système redirige vers la page d’accueil du site sans jouer la vidéo du bloc identitaire et sans les informations temps réels..'),
(3, 1, 'Sur le logo depuis la page d’accueil', ' le système affiche le bloc d’information temps réel et le bloc identitaire', 'Sur le logo depuis la page d’accueil, le système affiche le bloc d’information temps réel et le bloc identitaire'),
(4, 1, 'Si l’utilisateur clique sur le bouton d’accessibilité', ' le système redirige vers une page éditoriale.', 'Si l’utilisateur clique sur le bouton d’accessibilité, le système redirige vers une page éditoriale.'),
(5, 1, 'Un clic sur un bouton de réseaux sociaux ', 'permet à l’utilisateur d’accéder au réseau social concerné (dans une nouvelle fenêtre) :', 'Un clic sur un bouton de réseaux sociaux permet à l’utilisateur d’accéder au réseau social concerné (dans une nouvelle fenêtre) :'),
(6, 1, 'Un clic sur le bouton facebook ', 'redirige vers la page suivante :', 'Un clic sur le bouton facebook redirige vers la page suivante :'),
(7, 1, 'https://www.facebook.com/M*', '', 'https://www.facebook.com/M*'),
(8, 1, 'Un clic sur le bouton twitter redirige vers la page suivante :', '', 'Un clic sur le bouton twitter redirige vers la page suivante :'),
(9, 1, 'https://twitter.com/M*', '', 'https://twitter.com/M*'),
(10, 2, 'Au clic sur la langue sélectionnée', ' le système affiche les autres langues', 'Au clic sur la langue sélectionnée, le système affiche les autres langues'),
(11, 2, 'Si la langue sélectionnée est français', ' le système affiche', 'Si la langue sélectionnée est français, le système affiche'),
(12, 2, '•   En deuxième langue', '  le bouton Anglais (EN) à la place du bouton réseau social Twitter', '•   En deuxième langue,  le bouton Anglais (EN) à la place du bouton réseau social Twitter'),
(13, 2, '•   En troisième langue', ' le bouton Espagnol (ES) à la place bouton réseau social Facebook', '•   En troisième langue, le bouton Espagnol (ES) à la place bouton réseau social Facebook'),
(14, 2, '•   Le bouton +  (voir cas d’utilisation ', 'Afficher la page Langue)', '•   Le bouton +  (voir cas d’utilisation Afficher la page Langue)'),
(15, 2, 'Si la langue sélectionnée est anglais', ' le système affiche', 'Si la langue sélectionnée est anglais, le système affiche'),
(16, 2, '•   En deuxième langue', '  le bouton Francais (FR) à la place du bouton réseau social Twitter', '•   En deuxième langue,  le bouton Francais (FR) à la place du bouton réseau social Twitter'),
(17, 2, '•   En troisième langue', ' le bouton Espagnol (ES) à la place bouton réseau social Facebook', '•   En troisième langue, le bouton Espagnol (ES) à la place bouton réseau social Facebook'),
(18, 2, '•   Le bouton +  (voir cas d’utilisation ', 'Afficher la page Langue)', '•   Le bouton +  (voir cas d’utilisation Afficher la page Langue)'),
(19, 2, 'Si la langue sélectionnée est espagnol', ' le système affiche', 'Si la langue sélectionnée est espagnol, le système affiche'),
(20, 2, '•   En deuxième langue', '  le bouton Francais (FR) à la place du bouton réseau social Twitter', '•   En deuxième langue,  le bouton Francais (FR) à la place du bouton réseau social Twitter'),
(21, 2, '•   En troisième langue', ' le bouton Anglais (EN) à la place bouton réseau social Facebook', '•   En troisième langue, le bouton Anglais (EN) à la place bouton réseau social Facebook'),
(22, 2, '•   Le bouton +  (voir cas d’utilisation ', 'Afficher la page Langue)', '•   Le bouton +  (voir cas d’utilisation Afficher la page Langue)'),
(23, 2, 'Au clic sur la deuxième ou troisième langue', ' le système réaffiche  les boutons réseaux sociaux et le bouton + est caché', 'Au clic sur la deuxième ou troisième langue, le système réaffiche  les boutons réseaux sociaux et le bouton + est caché');

-- --------------------------------------------------------

--
-- Structure de la table `fichier`
--

CREATE TABLE IF NOT EXISTS `fichier` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL DEFAULT '',
  `id_projet` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmgjb8d9ulq1ner1ragwiass6q` (`id_projet`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `fichier`
--

INSERT INTO `fichier` (`id`, `nom`, `id_projet`) VALUES
(1, 'spec_test.txt', 5);

-- --------------------------------------------------------

--
-- Structure de la table `projet`
--

CREATE TABLE IF NOT EXISTS `projet` (
  `ID_projet` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `titre` varchar(100) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_projet`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `projet`
--

INSERT INTO `projet` (`ID_projet`, `titre`, `type`) VALUES
(5, 'Projet 1', 'standard');

-- --------------------------------------------------------

--
-- Structure de la table `rgestion`
--

CREATE TABLE IF NOT EXISTS `rgestion` (
  `ID_rg` int(11) NOT NULL AUTO_INCREMENT,
  `ID_cas` int(11) DEFAULT NULL,
  `texte` text NOT NULL,
  PRIMARY KEY (`ID_rg`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `rgestion`
--

INSERT INTO `rgestion` (`ID_rg`, `ID_cas`, `texte`) VALUES
(2, 2, 'Si l’utilisateur clique sur Anglais / Espagnol depuis une page française :Si la page est traduite, celle-ci est affichée.Si la page n’est pas traduite•   le système redirige vers la page parente traduite de niveau N-1•   Si la page parente de niveau N-1 n’est pas traduite, le système redirige vers la page parente traduite de niveau N-2•   Si la page parente de niveau N-2 n’est pas traduite, le système redirige vers la page d’accueil');

-- --------------------------------------------------------

--
-- Structure de la table `usecase`
--

CREATE TABLE IF NOT EXISTS `usecase` (
  `ID_cas` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL DEFAULT '',
  `titre` varchar(100) NOT NULL,
  `ID_fichier` int(11) unsigned NOT NULL,
  `ID_acteur` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_cas`),
  KEY `fk_cas_acteur` (`ID_acteur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `usecase`
--

INSERT INTO `usecase` (`ID_cas`, `nom`, `titre`, `ID_fichier`, `ID_acteur`) VALUES
(1, 'CU001-001', 'Afficher le header', 1, 7),
(2, 'CU001-002', 'Afficher le bouton de langue', 1, 7),
(3, 'CU001-0021', 'Afficher la page Langue', 1, 7);

-- --------------------------------------------------------

--
-- Structure de la table `usersecurity`
--

CREATE TABLE IF NOT EXISTS `usersecurity` (
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `actived` bit(1) NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `usersecurity`
--

INSERT INTO `usersecurity` (`username`, `password`, `actived`, `email`) VALUES
('admin', '$2a$10$WhAISbv9SNI4RhdVTaIE0ecIsx.C8Pac5A15kKf0b5ZHE5tE4AgGG', b'1', ''),
('prof1', '$2a$10$WhAISbv9SNI4RhdVTaIE0ecIsx.C8Pac5A15kKf0b5ZHE5tE4AgGG', b'1', ''),
('etd1', '$2a$10$WhAISbv9SNI4RhdVTaIE0ecIsx.C8Pac5A15kKf0b5ZHE5tE4AgGG', b'1', 'etd1@free.Fr'),
('prof', '$2a$10$WhAISbv9SNI4RhdVTaIE0ecIsx.C8Pac5A15kKf0b5ZHE5tE4AgGG', b'1', ''),
('etd2', '123', b'1', ''),
('sco1', '123', b'1', ''),
('sco2', '123', b'1', ''),
('AZER', '123', b'1', ''),
('''bob1''', '''bob1''', b'0', ''),
('prof3', '123', b'0', ''),
('prof4', '123', b'0', ''),
('dfb', 'dfg bsfg ', b'1', ''),
('Amigo', '123', b'1', ''),
('11111', '1111', b'1', ''),
('quality', '123', b'0', ''),
('azert', '$2a$10$WhAISbv9SNI4RhdVTaIE0ecIsx.C8Pac5A15kKf0b5ZHE5tE4AgGG', b'1', ''),
('admin1', '$2a$10$9Yko2MEWqs/ab7Q/kc2O9O4vsaDHBEIMrcALDQ6kGqF2XGNa8OeYq', b'1', 'admin1@free.fr'),
('admin3', '$2a$10$AFc22jPvFdARjULM0R0/veIpAzZrc6ULwN9GwB0HMLvctR1ammHl.', b'1', 'admin3@free.fr');

-- --------------------------------------------------------

--
-- Structure de la table `users_roles`
--

CREATE TABLE IF NOT EXISTS `users_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roles_role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_security_username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=14 ;

--
-- Contenu de la table `users_roles`
--

INSERT INTO `users_roles` (`id`, `roles_role`, `user_security_username`) VALUES
(1, 'ADMIN', 'admin'),
(2, 'USER', 'admin'),
(3, 'USER', 'etd1'),
(4, 'USER', 'etd2'),
(5, 'ADMIN', 'bob'),
(6, 'SCOLARITE', 'sco1'),
(9, 'USER', 'prof1'),
(11, 'TEST', 'admin'),
(12, 'ADMIN', 'admin3'),
(13, 'ADMIN', 'admin1');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `fichier`
--
ALTER TABLE `fichier`
  ADD CONSTRAINT `FKmgjb8d9ulq1ner1ragwiass6q` FOREIGN KEY (`id_projet`) REFERENCES `projet` (`ID_projet`);

--
-- Contraintes pour la table `usecase`
--
ALTER TABLE `usecase`
  ADD CONSTRAINT `fk_cas_acteur` FOREIGN KEY (`ID_acteur`) REFERENCES `acteur` (`ID_acteur`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
