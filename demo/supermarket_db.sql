-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 24, 2026 at 08:57 PM
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
-- Database: `supermarket_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(2, 'Bakery'),
(6, 'Beverages'),
(8, 'Cleaning Supplies'),
(1, 'Dairy'),
(5, 'Frozen Foods'),
(4, 'Fruits & Vegetables'),
(3, 'Meat & Poultry'),
(7, 'Snacks');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `expiration_status` enum('EXPIRED','NOT_EXPIRED') NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `quantity`, `expiration_status`, `category_id`, `supplier_id`, `created_at`) VALUES
(1, 'Whole Milk 1L', 1.99, 150, 'NOT_EXPIRED', 1, 1, '2026-06-24 20:58:18'),
(2, 'Cheddar Cheese 500g', 4.49, 80, 'NOT_EXPIRED', 1, 1, '2026-06-24 20:58:18'),
(3, 'Sourdough Bread', 3.29, 60, 'NOT_EXPIRED', 2, 2, '2026-06-24 20:58:18'),
(4, 'Croissant (6-pack)', 2.99, 40, 'NOT_EXPIRED', 2, 2, '2026-06-24 20:58:18'),
(5, 'Chicken Breast 1kg', 7.99, 90, 'NOT_EXPIRED', 3, 3, '2026-06-24 20:58:18'),
(6, 'Ground Beef 500g', 5.49, 70, 'NOT_EXPIRED', 3, 3, '2026-06-24 20:58:18'),
(7, 'Bananas (bunch)', 1.29, 200, 'NOT_EXPIRED', 4, 4, '2026-06-24 20:58:18'),
(8, 'Tomatoes 1kg', 2.19, 130, 'NOT_EXPIRED', 4, 4, '2026-06-24 20:58:18'),
(9, 'Frozen Pizza', 4.99, 50, 'NOT_EXPIRED', 5, 2, '2026-06-24 20:58:18'),
(10, 'Orange Juice 1L', 2.79, 110, 'NOT_EXPIRED', 6, 1, '2026-06-24 20:58:18'),
(11, 'Sparkling Water 6-pack', 3.49, 85, 'NOT_EXPIRED', 6, 3, '2026-06-24 20:58:18'),
(12, 'Potato Chips 200g', 1.89, 160, 'NOT_EXPIRED', 7, 2, '2026-06-24 20:58:18'),
(13, 'All-Purpose Cleaner', 3.99, 45, 'NOT_EXPIRED', 8, 4, '2026-06-24 20:58:18'),
(14, 'Expired Yoghurt 200g', 0.99, 10, 'EXPIRED', 1, 1, '2026-05-25 20:58:18');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `role` enum('ADMIN','STAFF') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `first_name`, `last_name`, `email`, `password`, `phone`, `role`) VALUES
(2, 'Default', 'Admin', 'admin@supermarket.local', '$2a$10$OyzivpGKRbASsgFutqeR0eIfVSsK8zfHUfa8NkAhgdYwGkd3p5jG2', NULL, 'ADMIN'),
(3, 'John', 'Doe', 'john@example.com', '$2a$10$qRQt6wwJ57xCeGzt4NpsG.3qrcfCs5aPb2j/SS/aXgt1hlo6vnaRC', '555-1234', 'STAFF');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `contact_email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id`, `name`, `contact_email`) VALUES
(1, 'FreshFarm Co.', 'contact@freshfarm.example.com'),
(2, 'GlobalFoods Ltd.', 'orders@globalfoods.example.com'),
(3, 'QuickDeliver Inc.', 'support@quickdeliver.example.com'),
(4, 'NaturalGoods Supply', 'hello@naturalgoods.example.com'),
(5, 'Meat Supply', 'hello@meat.example.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_category_name` (`name`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_product_category` (`category_id`),
  ADD KEY `fk_product_supplier` (`supplier_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_staff_email` (`email`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_product_supplier` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
