-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2025 at 01:38 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_product`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `harga` double NOT NULL,
  `kategori` varchar(255) NOT NULL,
  `promo` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `nama`, `harga`, `kategori`, `promo`) VALUES
('PRD001', 'Laptop ASUS', 8500000, 'Elektronik', 1),
('PRD002', 'Mouse Wireless', 150000, 'Elektronik', 0),
('PRD003', 'Keyboard Gaming', 450000, 'Elektronik', 0),
('PRD004', 'Monitor 24 inch', 2200000, 'Elektronik', 0),
('PRD005', 'Headset Gaming', 350000, 'Elektronik', 0),
('PRD006', 'Smartphone Samsung', 4500000, 'Elektronik', 0),
('PRD007', 'Charger USB-C', 85000, 'Aksesoris', 0),
('PRD008', 'Power Bank', 250000, 'Aksesoris', 0),
('PRD009', 'Webcam HD', 180000, 'Elektronik', 0),
('PRD010', 'Speaker Bluetooth', 320000, 'Elektronik', 0),
('PRD011', 'Tablet Android', 2800000, 'Elektronik', 0),
('PRD012', 'Smartwatch', 1200000, 'Aksesoris', 0),
('PRD013', 'Flash Drive 32GB', 65000, 'Penyimpanan', 0),
('PRD014', 'Hard Disk 1TB', 750000, 'Penyimpanan', 0),
('PRD015', 'Router WiFi', 420000, 'Jaringan', 0),
('PRD016', 'Kabel HDMI', 45000, 'Aksesoris', 0),
('PRD017', 'Printer Inkjet', 850000, 'Perangkat Kantor', 0),
('PRD018', 'Scanner Document', 650000, 'Perangkat Kantor', 0),
('PRD019', 'Cooling Pad', 120000, 'Aksesoris', 0),
('PRD020', 'Gaming Chair', 1800000, 'Furniture', 0),
('PRD021', 'Kompor Listrik', 300000, 'Elektronik', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
