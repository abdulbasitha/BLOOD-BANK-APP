-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 18, 2020 at 06:31 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bloodbank`
--

-- --------------------------------------------------------

--
-- Table structure for table `blood_bank_app`
--

CREATE TABLE `blood_bank_app` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phno` varchar(100) NOT NULL,
  `gp` varchar(100) NOT NULL,
  `place` varchar(100) NOT NULL,
  `age` varchar(1000) DEFAULT NULL,
  `expiry` date DEFAULT '2000-01-01'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `blood_bank_app`
--

INSERT INTO `blood_bank_app` (`id`, `name`, `phno`, `gp`, `place`, `age`, `expiry`) VALUES
(1, 'name', '9876543210', 'B+', 'place', '20', '2020-07-18');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blood_bank_app`
--
ALTER TABLE `blood_bank_app`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blood_bank_app`
--
ALTER TABLE `blood_bank_app`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
