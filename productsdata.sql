use shopforhome;
show tables;
INSERT INTO `category` (`id`, `catname`) VALUES
(1, 'electronics'),
(2, 'fashion'),
(3, 'grocery'),
(4, 'kitchen');
-- elecronics
INSERT INTO `product` (`id`, `descr`, `photo`, `pname`, `price`, `cat_id`, `stocks`) VALUES
(1,'mobilephone', 'assets/images/products/electronics/e1.webp', 'samsung', 10000, 1, 10),
(2,'smart Watch', 'assets/images/products/electronics/e2.webp', 'mi', 1500, 1, 5),
(3,'smart Tv', 'assets/images/products/electronics/e3.webp', 'oneplus', 15000, 1, 6),
(4,'refridgerator', 'assets/images/products/electronics/e4.webp', 'LG', 12000, 1, 8),
(5,'fan', 'assets/images/products/electronics/e5.webp', 'usha', 1200, 1, 12),
(6,'ac', 'assets/images/products/electronics/e6.webp', 'Blue Star', 22000, 1, 15),
(7,'cooler', 'assets/images/products/electronics/e7.webp', 'Bajaj', 9000, 1, 9),
(8,'smart Light ', 'assets/images/products/electronics/e8.webp', 'sisca', 600, 1, 13),
(9,'Laptop ', 'assets/images/products/electronics/e9.webp', 'Hp', 50000, 1, 14),
(10,'Light ', 'assets/images/products/electronics/e10.webp', 'wipro', 500, 1, 16),
(11,'Study Lamp ', 'assets/images/products/electronics/e11.webp', 'crompton', 800, 1, 13),
(12,'tabs ', 'assets/images/products/electronics/e12.webp', 'samsung', 16000, 1, 6),
(13,' Hair Dryer', 'assets/images/products/electronics/e13.webp', 'Nova', 1600, 1, 7);
-- grocery
INSERT INTO `product` (`id`, `descr`, `photo`, `pname`, `price`, `cat_id`, `stocks`) VALUES
(26,'Surf', 'assets/images/products/grocery/g1.webp', 'surf Excel', 120, 3, 6),
(27,'liquid Surf', 'assets/images/products/grocery/g2.webp', 'Arel', 200, 3, 5),
(28,'Disher washer', 'assets/images/products/grocery/g3.webp', 'vim', 150, 3, 7),
(29,'Tooth Paste', 'assets/images/products/grocery/g4.webp', 'sensodyne', 120, 3, 9),
(30,'choclates', 'assets/images/products/grocery/g5.webp', 'Dairy Milk', 150, 3, 8),
(31,'noodles', 'assets/images/products/grocery/g6.webp', 'maggie', 200, 3, 12),
(32,'Food Oil', 'assets/images/products/grocery/g7.webp', 'freedom', 1100, 3, 11),
(33,'Biscuits ', 'assets/images/products/grocery/g8.webp', 'Dark Fantasy', 400, 2, 12),
(34,'Coffee ', 'assets/images/products/grocery/g9.webp', 'Home Blend', 430, 3, 15),
(35,'Hair Shapoo ', 'assets/images/products/grocery/e10.webp', 'Dove', 500, 3, 14);

INSERT INTO `product` (`id`, `descr`, `photo`, `pname`, `price`, `cat_id`, `stocks`) VALUES
(36,'Oil Dispenser', 'assets/images/products/kitchen/k1.jpg', 'Star Work', 297, 4, 100),
(37,'3 Burner Gas Stove', 'assets/images/products/kitchen/k2.jpg', 'Butterfly', 3099, 4, 50),
(38,'Micro Oven', 'assets/images/products/kitchen/k3.jpg', 'Samsung', 11690, 4, 45),
(39,'Sandwich Maker', 'assets/images/products/kitchen/k4.jpg', 'Havells', 2299, 4, 50),
(40,'waffle Maker', 'assets/images/products/kitchen/k5.jpg', 'Prestige', 1525, 4, 70),
(41,'Dish Washer', 'assets/images/products/kitchen/k6.jpg', 'Bosch', 42990, 4, 55),
(42,'Pressure Cooker', 'assets/images/products/kitchen/k7.jpg', 'Hawkins', 2739, 4, 115),
(43,'Dinner Set', 'assets/images/products/kitchen/k8.jpg', 'Jenvan', 900, 4, 234),
(44,'knives', 'assets/images/products/kitchen/k9.webp', 'Pigeon', 309, 4, 432),
(45,'Egg Cooker', 'assets/images/products/kitchen/k10.jpg', 'Kent', 1149, 4, 135),
(46,'Coffee Bean Grinder', 'assets/images/products/kitchen/k11.jpg', 'InstaCuppa', 2499, 4, 55),
(47,'Juicer', 'assets/images/products/kitchen/k12.jpg', 'Philips', 8699, 4, 75),
(48,'Kettle', 'assets/images/products/kitchen/k13.jpg', 'Borosil', 1595, 4, 53);

INSERT INTO `product` (`id`, `descr`, `photo`, `pname`, `price`, `cat_id`, `stocks`) VALUES
(14,'shirt', 'assets/images/products/fashion/f1.webp', 'indo', 1400, 2, 56),
(15,'shoes', 'assets/images/products/fashion/f2.webp', 'redtap', 1700, 2, 6),
(16,'tshirt', 'assets/images/products/fashion/f3.webp', 'Hrx', 2000, 2, 58),
(17,'watch', 'assets/images/products/fashion/f4.webp', 'foosil', 25400, 2, 47),
(18,'jeans', 'assets/images/products/fashion/f5.webp', 'redtap', 1700, 2, 48),
(19,'top', 'assets/images/products/fashion/f6.webp', 'aurelia', 1900, 2, 23),
(20,'kurta', 'assets/images/products/fashion/f7.webp', 'libas', 1800, 2, 56),
(21,'tshirt', 'assets/images/products/fashion/f8.webp', 'team sprit', 200, 2, 46),
(22,'cap', 'assets/images/products/fashion/f9.webp', 'puma', 500, 2, 36),
(23,'crose', 'assets/images/products/fashion/f10.webp', 'crose', 2500, 2, 56),
(24,'belt', 'assets/images/products/fashion/f11.webp', 'indo', 1500, 2, 51),
(25,'braclelets', 'assets/images/products/fashion/f12.webp', 'diva', 1000, 2, 53);