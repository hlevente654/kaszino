-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2022. Jún 28. 16:22
-- Kiszolgáló verziója: 10.4.14-MariaDB
-- PHP verzió: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `kaszino`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `gamelog`
--

CREATE TABLE `gamelog` (
  `jatekos_id` int(50) NOT NULL,
  `nyert` int(1) DEFAULT NULL,
  `osszegValtozas` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `gamelog`
--

INSERT INTO `gamelog` (`jatekos_id`, `nyert`, `osszegValtozas`) VALUES
(2, 1, 5000),
(2, 0, -1000),
(2, 0, -1000),
(2, 1, 1000),
(2, 0, -1000),
(2, 1, 1000),
(2, 1, 1000),
(2, 1, 1000),
(2, 1, 1000),
(2, 1, 3000),
(2, 0, -1000),
(2, 1, 1000),
(2, 0, -1000),
(2, 1, 0),
(3, 1, 1019000),
(3, 1, 4000),
(3, 1, 4000),
(3, 1, 4000),
(3, 1, 4200),
(3, 0, -4000),
(3, 1, 4000),
(3, 1, 3150),
(3, 1, 3150),
(3, 1, 2100),
(3, 1, 1050),
(3, 1, 550),
(3, 1, 1050),
(3, 1, 1050),
(3, 1, 1050),
(3, 1, 1050),
(3, 1, 1050),
(3, 1, 1050),
(3, 1, 1050),
(3, 0, -2050),
(3, 1, 1000),
(3, 1, 1000),
(3, 1, 104100),
(3, 1, 105150),
(3, 1, 1050),
(3, 1, 0),
(3, 0, -10000),
(3, 0, -10000),
(3, 1, 10000),
(3, 0, -3000),
(3, 0, -3000),
(3, 1, 3000),
(3, 0, -3000),
(3, 1, 2100),
(3, 1, 0),
(3, 1, 0),
(3, 1, 80000),
(3, 0, -240000),
(3, 0, -1000),
(3, 1, 550),
(3, 0, -400000);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `jatekos`
--

CREATE TABLE `jatekos` (
  `personal_ID` int(11) NOT NULL,
  `Vezeték_Név` varchar(50) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `Kereszt_Név` varchar(50) COLLATE utf8_hungarian_ci DEFAULT NULL,
  `Email` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `LogIn` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
  `Pénze` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `jatekos`
--

INSERT INTO `jatekos` (`personal_ID`, `Vezeték_Név`, `Kereszt_Név`, `Email`, `LogIn`, `Password`, `Pénze`) VALUES
(2, 'Horvath', 'Én', 'ghjghj@dfgdfg.hu', 'Én', '123456', 0),
(3, 'Seres', 'Kristof', 'kkristof@gfh.com', 'ov', 'ov123', -331700),
(4, 'Magyar', 'Levente', 'asd@asd.com', 'overlord', 'sajt', 1000000),
(6, 'Azfgh', 'Gfghfgh', 'fdhfg@dfg.hu', 'az', 'az12345678', 1000000);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `gamelog`
--
ALTER TABLE `gamelog`
  ADD KEY `jatekos_id` (`jatekos_id`);

--
-- A tábla indexei `jatekos`
--
ALTER TABLE `jatekos`
  ADD PRIMARY KEY (`personal_ID`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `jatekos`
--
ALTER TABLE `jatekos`
  MODIFY `personal_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `gamelog`
--
ALTER TABLE `gamelog`
  ADD CONSTRAINT `gamelog_ibfk_1` FOREIGN KEY (`jatekos_id`) REFERENCES `jatekos` (`personal_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
