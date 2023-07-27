create database railway1;

use railway1;

create table accounts(
	Username nvarchar(50) primary key,
	Password varchar(50),
	Fullname nvarchar(50) not null,
	Email varchar(50),
	Telephone int,
	Birthday date,
	Sex bit,
	Photo varchar(100),
    Auth_type varchar(15)
);
create table roles(
	Id varchar(10) primary key,
	Name nvarchar(50) not null
);
create table authorities(
	Id int not null primary key AUTO_INCREMENT,
	Username_Id nvarchar(50) not null,
	Role_Id varchar(10) not null,
	foreign key (Username_Id) references accounts(Username) on update cascade,
	foreign key (Role_Id) references roles(Id) on update cascade
);
create table categories(
	Id int primary key AUTO_INCREMENT,
	Name nvarchar(50) not null
);
create table brands(
	Id int primary key AUTO_INCREMENT,
	Name nvarchar(50) not null
);
create table products(
	Id int primary key AUTO_INCREMENT,
	Name nvarchar(300) not null,
	Price float not null,
	Discount float not null,
	Image varchar(100) not null,
	CreateDate date not null,
	Available bit not null,
	Title_Description nvarchar(500),
	Guarantee int,
	Seri varchar(50),
	Category_Id int not null,
	Brand_Id int not null,
	foreign key (Category_Id) references categories(Id) on update cascade,
	foreign key (Brand_Id) references brands(Id) on update cascade
);
create table descriptions(
	Id int primary key AUTO_INCREMENT,
	Title nvarchar(200) not null,
	Contentd nvarchar(500),
	Image varchar(100),
	Product_Id int not null,
	foreign key (Product_Id) references products(Id) on update cascade
);
create table gallarys(
	Id int primary key AUTO_INCREMENT,
	Product_Id int not null,
	image varchar(100) not null,
	foreign key (Product_Id) references products(Id) on update cascade
);
create table reviews(
	Id int primary key AUTO_INCREMENT,
	Name nvarchar(50) not null,
	Email varchar(50) not null,
	Reviews nvarchar(300) not null,
	Rate int not null,
	CreateDate date not null,
	Product_Id int not null,
	foreign key (Product_Id) references products(Id) on update cascade
);
create table configs(
	Id int primary key AUTO_INCREMENT,
	Name nvarchar(50) not null
);
create table configdetails(
	Id int primary key AUTO_INCREMENT,
	Contentc varchar(100) not null,
	Config_Id int not null,
	Product_Id int not null,
	foreign key (Config_Id) references configs(Id) on update cascade,
	foreign key (Product_Id) references products(Id) on update cascade

);
create table orders(
	Id int primary key AUTO_INCREMENT,
	Fullname nvarchar(50) not null,
	Phone int not null,
	Email nvarchar(50) not null,
	City nvarchar(50),
	Address nvarchar(500) not null,
	OrderNote nvarchar(300),
	CreateDate date not null,
	Username_Id nvarchar(50) not null,
	foreign key (Username_Id) references accounts(Username) on update cascade,
    Shipper_Id int , foreign key (Shipper_Id) references shippers(Id) on update cascade,
    PayStatus_Id int, foreign key (PayStatus_Id) references pay_status(Id) on update cascade
);
create table orderdetails(
	Id int primary key AUTO_INCREMENT,
	Price float not null,
	Quantity int not null,
	Product_Id int not null,
	Order_Id int not null,
	foreign key (Product_Id) references products(Id) on update cascade,
	foreign key (Order_Id) references orders(Id) on update cascade
);
create table favorites(
	Id int primary key AUTO_INCREMENT,
	CreateDate date not null,
	Product_Id int not null,
	Username_Id nvarchar(50) not null,
	foreign key (Product_Id) references products(Id) on update cascade,
	foreign key (Username_Id) references accounts(Username) on update cascade
);
create table shippers(
Id int primary key AUTO_INCREMENT,
name nvarchar(50)
);
create table pay_status(
Id int primary key AUTO_INCREMENT,
name nvarchar(50)
);
insert into accounts
values 
	('binhbv','123456',N'Bùi Văn Bình','binh@gmail.com','0326924248','2000-10-16',0,'photo1.jpg','DATABASE'),
	('hyhq','123456',N'Huỳnh Quang Hy','hyhuynh@gmail.com','0326924248','2000-10-16',0,'photo2.jpg','DATABASE'),
	('nghianl','123456',N'Nguyễn Hữu Nghĩa','nghianl@gmail.com','0326924248','2000-10-16',0,'photo3.jpg','DATABASE'),
    ('phuonghm','123456',N'Huynh Minh Phuong','phuong@gmail.com','0326924248','2000-10-16',0,'photo4.jpg','DATABASE'),
    ('kietnh','123456',N'Nguyễn Hải Kiệt','nghianl@gmail.com','0326924248','2000-10-16',0,'photo5.jpg','DATABASE');
insert into roles
values
	('CUST','Customers'),
	('DIRE','Directors'),
	('STAF','Staffs');
insert into authorities(Username_Id,Role_Id)
values
	('binhbv','CUST'),
	('hyhq','CUST'),
	('nghianl','CUST'),
	('binhbv','DIRE'),
	('hyhq','STAF');
insert into categories(name)
values
	('Laptop'),('Smartphone'),('Accessories'),('TV'),('Speaker'),('Washing Machine'),('Air Conditional'),('Refrigerator');
insert into brands(name)
values
	('Acer'),('Asus'),('MSI'),('Dell'),('HP'),('Lenovo'),('Samsung'),('Apple'),('Xiaomi'),('Logitech'),('Corsair'),('LG'),('Sony'),('Toshiba'),('Panasonic');
insert into products(name, price, discount,image,createdate,available,title_description,guarantee,seri,category_id,brand_id)
values
	(N'Laptop ACER Nitro 5 Eagle AN515-57-54MV (i5-11400H/RAM 8GB/512GB SSD/ Windows 11)','1000','10','laptopacer1_1.png','2022-12-26',0,'
			As a gaming laptop of the Acer brand, the Acer Nitro 5 Eagle AN515-57-54MV laptop is equipped with a powerful i5 chip,
			NVIDIA GeForce RTX 3050 graphics card, 8Gb ram and large capacity. Combined with a beautiful and powerful design, this will be the
			choice for young gamers and graphic designers.','12','Nitro','1','1'),
	(N'Laptop ASUS G513IH-HN015T (Ryzen 7 4800H/RAM 8GB/512GB SSD/ Windows 10)','1100','15','laptopasus1_1.png','2022-12-26',0,'
			The ASUS G513IH-HN015T 90NR07P2-M00390 laptop is bringing an impressive generation of gaming laptops with Asus cool design and equipped 
			with powerful AMD Ryzen 7 generation processors and NVIDIA GeForce graphics cards that promise to deliver great performance. This is a 
			computer model suitable for users who specialize in gaming or graphics processing.','24','ROG','1','2'),
	(N'Laptop MSI Modern 15 A10M (i5-10210U/RAM 8GB/512GB SSD/ Windows 10)','800','5','laptopmsi1_1.png','2022-12-26',0,'
			The MSI Modern 15 A10M 667VN laptop is a stylish laptop with a compact design that carries the power of the 10th generation i5 processor 
			for incredible work and entertainment capabilities.','12','Modern Series','1','3'),
	(N'Laptop MSI Thin GF65 10UE (i5-10500H/RAM 16GB/512GB SSD/ Windows 10)','1000','10','laptopmsi2_1.png','2022-12-26',1,'
			The MSI Thin GF65 10UE 286VN laptop of the MSI brand is compactly designed with a powerful configuration with an i5 chip that provides 
			outstanding performance. This is a laptop product in the mid-range price segment that is very suitable for students, office workers or 
			gamers.','12','GF Series','1','3'),
	(N'Laptop ACER Aspire 5 A514-54-51VT (i5-1135G7/RAM 8GB/512GB SSD/ Windows 10)','800','0','laptopacer2_1.png','2022-12-26',0,'
			Acer Aspire 5 laptop A514-54-51VT (NX.A23SV.004) possesses an elegant design, combined with an advanced 14" wide screen, and can also be 
			used continuously for 11 hours up to maximum. thanks to the high battery capacity of 40Wh. This is a laptop product suitable for office 
			workers who want to get higher performance.','12','Aspire 5','1','1'),
	(N'Laptop Dell Latitude 3520 70251603 (i3-1115G4/4GB/256GB SSD/Fedora)','700','0','laptopdell1_1.png','2022-12-26',0,'
			The Dell Latitude 3520 70251603 laptop (15.6" HD/Intel Core i3-1115G4/4GB/256GB SSD/Fedora/1.8kg) is designed to perfection with a 
			luxurious, more rugged appearance that brings a premium feel to the mid-range model. With the constantly updated Fedora operating system 
			and advanced management features, users will have the best experience.','12','Latitude','1','4'),
	(N'Laptop HP 14s-fq1080AU (Ryzen 3 5300U/RAM 4GB/256GB SSD/ Windows 10)','700','0','laptophp1_1.png','2022-12-26',0,'
			HP 14s-fq1080AU 4K0Z7PA laptop from HP has a neat design and stable configuration with AMD Ryzen 3 generation processor. This promises 
			to be a quality laptop to meet the simple daily needs of users. .','12','HP14s','1','5'),
	(N'Laptop Lenovo Legion 5 15ACH6A (Ryzen 7 5800H/RAM 8GB/512GB SSD/ Windows 10)','1200','5','laptoplenovo1_1.png','2022-12-26',0,'
			Lenovo Legion 5 Laptop 15ACH6A-82NW003BVN is a new generation gaming laptop equipped with a Ryzen 7 5800H chip with AMD Radeon RX 6600M 
			8GB GDDR6 discrete card to give users a great screen experience with powerful performance. This will be the perfect choice with the need 
			to use high performance such as graphics or heavy software, high data processing speed.','24','Legion','1','6'),
	(N'Laptop ASUS FX506HCB-HN1138W (i5-11400H/RAM 8GB/512GB SSD/ Windows 11)','1100','10','laptopasus2_1.png','2022-12-26',0,'
			ASUS FX506HCB-HN1138W laptop is a version of Asus TUF Gaming laptop with impressive appearance, equipped with impressive configuration 
			with 11th generation Intel Core i5 processor, unleashing graphic creativity thanks to the addition of a video card. NVIDIA is high-end. 
			This promises to be a satisfying gaming laptop for your needs in the price segment.','24','TUF Gaming','1','2'),
	(N'Laptop HP VICTUS 16-d0204TX (i5-11400H/RAM 8GB/512GB SSD/ Windows 11)','1300','0','laptophp2_1.png','2022-12-26',0,'
			HP Victus 16-d0204TX (i5-11400H) laptop (Black) is a high-end laptop product line that is popular with many technology followers and is 
			designed with strong performance and ram memory. , standard screen, .. this laptop from the famous brand HP promises to bring users the 
			interesting things they deserve as well as with your profitable investment.','12','VICTUS','1','5'),
	(N'Smartphone Samsung Galaxy Z Flip4 8GB/128GB','1000','0','smartphonesamsung1_1.png','2022-12-26',0,'',6,'','2','7'),
	(N'iPhone 13 Pro | Chính Hãng VNA','1200','10','smartphoneapple1_1.png','2022-12-26',0,'',6,'','2','8'),
	(N'iPhone 14 Plus | Chính hãng VNA','1100','5','smartphoneapple2_1.png','2022-12-26',0,'',6,'','2','8'),
	(N'Xiaomi Redmi Note 11','400','0','smartphonexiaomi1_1.png','2022-12-26',0,'',6,'','2','9'),
	(N'ASUS ROG Phone 6','1000','0','smartphoneasus1_1.png','2022-12-26',0,'',6,'','2','2'),

	(N'Chuột gaming Logitech G102 Gen2 Lightsync','20','0','mouselogitech1_1.png','2022-12-26',0,'',6,'','3','10'),
	(N'Chuột máy tính Asus TUF Gaming M3','25','20','mouseasus1_1.png','2022-12-26',0,'',6,'','3','2'),
	(N'Bàn phím cơ Logitech Gaming G813 (Full Size/GL Clicky) (920-009098)','100','0','keyboardlogitech1_1.png','2022-12-26',0,'',6,'','3','10'),
	(N'Bàn phím cơ không dây ASUS ROG Falchion (NX Blue Switch)','100','10','keyboardasus1_1.png','2022-12-26',0,'',6,'','3','2'),
	(N'Bàn phím cơ CORSAIR K68 (Full size/Cherry MX Red) CH-9102020-NA','80','0','keyboardcorsair1_1.png','2022-12-26',0,'',6,'','3','11'),

	(N'Smart Tivi Samsung 4K UHD 50 Inch UA50AU7700KXXV','450','10','tvsamsung1_1.png','2022-12-26',0,'',6,'','4','7'),
	(N'Smart Tivi LG 4K 55 inch 55UP7550PTC (55")','400','20','tvlg1_1.png','2022-12-26',0,'',6,'','4','12'),
	(N'Google Tivi Sony 4K 55 inch KD-55X75K','600','0','tvsony1_1.png','2022-12-26',0,'',6,'','4','13'),
	(N'Smart Tivi NanoCell LG 4K 43 inch 43NANO77TPA','400','0','tvlg2_1.png','2022-12-26',0,'',6,'','4','12'),
	(N'Smart Tivi Samsung Crystal UHD 4K 55 inch','500','5','tvsamsung2_1.png','2022-12-26',0,'',6,'','4','7'),

	(N'Tai nghe On-ear Logitech H150','25','10','headphonelogitech1_1.png','2022-12-26',0,'',6,'','5','11'),
	(N'Tai nghe Sony MDR-ZX110APWC1E','45','10','headphonesony1_1.png','2022-12-26',0,'',6,'','5','13'),
	(N'Tai nghe Over-ear ASUS ROG Strix Fusion 300','160','20','headphoneasus1_1.png','2022-12-26',0,'',6,'','5','2'),
	(N'Loa 5.1 Logitech Z906','300','10','speakerlogitech1_1.png','2022-12-26',0,'',6,'','5','10'),
	(N'Loa Bluetooth Sony SRS-XB32/RC E','150','145','speakersony1_1.png','2022-12-26',1,'',6,'','5','13'),

	(N'Máy giặt Samsung Addwash Inverter 9 kg WW90K54E0UW/SV','400','0','wmsamsung1_1.png','2022-12-26',0,'',6,'','6','7'),
	(N'Máy giặt Toshiba Inverter 9.5 kg TW-BK105S3V','500','0','wmtoshiba1_1.png','2022-12-26',0,'',6,'','6','14'),
	(N'Máy giặt Panasonic Inverter 9.5 Kg NA-FD95V1BRV','500','470','wmpanasonic1_1.png','2022-12-26',1,'',6,'','6','15'),
	(N'Máy giặt sấy LG Inverter 9 kg FV1409G4V','700','0','wmlg1_1.png','2022-12-26',0,'',6,'','6','12'),
	(N'Máy giặt Samsung Inverter 10 kg WA10T5260BY/SV','300','10','wmsamsung2_1.png','2022-12-26',0,'',6,'','6','7'),

	(N'Máy lạnh Panasonic CU/CS-N12VKH-8 1.5HP (13.500 BTU)','500','5','acpanasonic1_1.png','2022-12-26',0,'',6,'','7','15'),
	(N'Máy lạnh Toshiba RAS-H18U2KSG-V 2HP (18.000 BTU)','600','10','actoshiba1_1.png','2022-12-26',1,'',6,'','7','14'),
	(N'Máy lạnh LG Inverter 1.5 HP V13ENS1','400','20','aclg1_1.png','2022-12-26',0,'',6,'','7','12'),
	(N'Máy lạnh Panasonic CU/CS-N18XKH-8 2 HP (18.000 Btu/h)','700','5','acpanasonic2_1.png','2022-12-26',0,'',6,'','7','15'),
	(N'Máy lạnh Toshiba Inverter 1 HP RAS-H10H4KCVG-V','400','20','actoshiba2_1.png','2022-12-26',1,'',6,'','7','14'),

	(N'Tủ lạnh Panasonic Inverter 268 lít','500','0','refrigeratorpanasonic1_1.png','2022-12-26',0,'',6,'','8','15'),
	(N'Tủ lạnh Toshiba Inverter 555 lít GR-AG58VA (XK)','800','10','refrigeratortoshiba1_1.png','2022-12-26',0,'',6,'','8','14'),
	(N'Tủ lạnh Samsung Inverter 680 lít RS62R5001B4/SV','700','5','refrigeratorsamsung1_1.png','2022-12-26',0,'',6,'','8','7'),
	(N'Tủ lạnh Panasonic Inverter 322 lít NR-BC360WKVN','600','0','refrigeratorpanasonic2_1.png','2022-12-26',1,'',6,'','8','15'),
	(N'Tủ lạnh Panasonic Inverter 234 lít NR-TV261APSV','400','0','refrigeratorpanasonic3_1.png','2022-12-26',0,'',6,'','8','15');
insert into gallarys(Product_Id, image)
values 
	(1,'laptopacer1_1.png'),(1,'laptopacer1_2.png'),(1,'laptopacer1_3.png'),(1,'laptopacer1_4.png'),(1,'laptopacer1_5.png'),
	(2,'laptopasus1_1.png'),(2,'laptopasus1_2.png'),(2,'laptopasus1_3.png'),(2,'laptopasus1_4.png'),(2,'laptopasus1_5.png'),
	(3,'laptopmsi1_1.png'),(3,'laptopmsi1_2.png'),(3,'laptopmsi1_3.png'),(3,'laptopmsi1_4.png'),(3,'laptopmsi1_5.png'),
	(4,'laptopmsi2_1.png'),(4,'laptopmsi2_2.png'),(4,'laptopmsi2_3.png'),(4,'laptopmsi2_4.png'),(4,'laptopmsi2_5.png'),
	(5,'laptopacer2_1.png'),(5,'laptopacer2_2.png'),(5,'laptopacer2_3.png'),(5,'laptopacer2_4.png'),(5,'laptopacer2_5.png'),
	(6,'laptopdell1_1.png'),(6,'laptopdell1_2.png'),(6,'laptopdell1_3.png'),(6,'laptopdell1_4.png'),(6,'laptopdell1_5.png'),
	(7,'laptophp1_1.png'),(7,'laptophp1_2.png'),(7,'laptophp1_3.png'),(7,'laptophp1_4.png'),
	(8,'laptoplenovo1_1.png'),(8,'laptoplenovo1_2.png'),(8,'laptoplenovo1_3.png'),(8,'laptoplenovo1_4.png'),(8,'laptoplenovo1_5.png'),
	(9,'laptopasus2_1.png'),(9,'laptopasus2_2.png'),(9,'laptopasus2_3.png'),(9,'laptopasus2_4.png'),(9,'laptopasus2_5.png'),
	(10,'laptophp2_1.png'),(10,'laptophp2_2.png'),(10,'laptophp2_3.png'),(10,'laptophp2_4.png');
insert into descriptions(Title,Contentd,Image,Product_Id)
values
	('Powerful gaming standard design, sharp Full HD screen',
		'Laptop Nitro 5 Eagle AN515-57-54MV is designed by Acer with a aggressive and powerful gaming standard with black - red tones
		extremely impressive. Compared to gaming laptops in the same segment, this is a fairly lightweight laptop, weighing 2.2 kg and
		2.39 cm thick. Users can carry it in their backpack without being difficult to move to meet the needs of use when needed.'
		,'pro1_cont1.png',1),
	('Graphics processing, smooth gaming experience',
		'Acer has equipped its Acer Nitro 5 Eagle AN515-57-54MV laptop with a powerful Intel Core i5-11400H processor with 6 cores.
		12 threads for 2.7 GHz - 4.5 GHz with 12MB cache.'
		,'pro1_cont2.png',1),
	('Using Windows 11 operating system, experience with impressive battery capacity',
		'Acer Nitro 5 Eagle AN515-57-54MV is the first laptop in the nitro 5 series gaming laptop line to be equipped with an operating system.
		The latest Windows 11 with a new interface and modern sound gives users the most convenient and best experience. Special
		Especially, with a gaming laptop, this will be the best version of Windows for gamers. This will be a complete difference
		completely new compared to other laptops in the same segment available on the market.',
		'pro1_cont3.png',1),
	('Equipped with Full Size keyboard, RGB support, modern connectivity variety',
		'Acer Nitro 5 Eagle is keyboard is equipped with Full Size with deep key travel, high sensitivity and good elasticity, so it
		for an extremely responsive and smooth typing experience. In particular, with the key cluster used when playing games, the prominent WASD cluster helps the game
		players easily locate and identify when playing games.',
		'pro1_cont4.png',1),

	('Strong design, trendy style leading the trend',
		'The laptop G513IH-HN015T is designed by Asus to bring together the quintessence and beauty of its gaming computer. Impressive sporty style 
		with color is the highlight, each angle of the device is very attractive, including the base. Powerful dimensions 35.4 x 25.9 x 2.59 cm. Solid 
		2.1 kg weight.',
		'pro2_cont1.jpg',2),
	('The keyboard is responsive, the trackpad is bigger',
		'ASUS G513IH-HN015T is a laptop designed to use a regular keyboard, not equipped with a separate group of unnecessary number keys, a keyboard
		with Very beautiful LED lights with stunning lighting effects. Play entertaining games in very convenient lighting conditions or at night.
		Key response click extremely well to fight the game more secure.',
		'pro2_cont2.jpg',2),
	('Accurate speed display and excellent quality',
		'ASUS G513IH-HN015T laptop uses a 15.6-inch screen to help enjoy gaming much better than other small screen versions. Full HD resolution 
		1920 x 1080 frame. Easily immerse yourself in the most impressive and attractive movie sky.'
		,'pro2_cont3.jpg',2),
	('Great gaming performance from AMD Ryzen 7 processors and graphics cards from NVIDIA',
		'ASUS G513IH-HN015T laptop is equipped with AMD Ryzen 7 generation specifically AMD Ryzen 7 4800H. Powerful processor with powerful overclocking
		with 2.9 GHz base clock and can be overclocked up to 4.2 GHz. 8MB of CPU cache with 8 cores and 16 data processing threads. Extreme gaming 
		capabilities, and support for graphics-related tasks such as rendering video extremely well.'
		,'pro2_cont4.jpg',2),
	('Powerful Expanded Storage - Smooth Work Multi-tasking',
		'Asus G513IH-HN015T laptop is equipped with 8GB RAM for smooth and efficient multi-tasking, and enough RAM for games to work well. The modern 
		generation of DDR4 RAM delivers stronger performance and claims outstanding speed with a RAM Bus of up to 3200MHz. Easy to upgrade with 2 RAM 
		slots and the machine supports up to 32GB of RAM.'
		,'pro2_cont5.jpg',2),
	('Clear sound - Longer battery life',
		'ASUS G513IH-HN015T laptop is equipped with a quality speaker system for the clearest, most attractive sound. Equipped with two-way AI noise 
		cancellation to effectively remove noise from input and output audio. Bigger volume with stronger bass and greater dynamic range than older 
		versions.'
		,'pro2_cont6.jpg',2),
	('Secure operating system - Fast expansion connection',
		'ASUS G513IH-HN015T laptop comes pre-installed with extremely secure 64-bit Windows 10 Home operating system with many safety updates. 
		Expanded connection with HDMI output port; 1 USB Type C port, DisplayPort, Power Delivery, 3 USB 3.2 ports; 1 LAN port 1 Gb/s.'
		,'pro2_cont7.jpg',2),

	('Modern design with outstanding brand identity MSI logo',
		'MSI Modern 15 A10M 667VN has an extremely light weight of 1.6 kg and a thickness of only 1.86 cm. The lines on the laptop are delicately and 
		elegantly designed, but still exude a strong MSI-like look, giving you a lot of inspiration to work more efficiently.'
		,'pro3_cont1.jpg',3),
	('Great performance with Intel Core i5-10210U',
		'The MSI laptop is equipped with a powerful 10th generation Intel Core i5-10210U processor with 4 cores, 8 threads and a maximum clock speed 
		of up to 4.20GHz, which helps this computer handle any application well. Also very energy efficient.'
		,'pro3_cont2.jpg',3),
	('Full HD resolution with 15.6 inch screen',
		'The MSI Modern 15 A10M belongs to the compact laptop line thanks to its ultra-thin screen border, up to 90% screen-to-body ratio, creating a 
		truly spacious and immersive feeling. Combination of anti-glare non-touch IPS panel with HD webcam utility.'
		,'pro3_cont3.jpg',3),
	('Various and convenient connection ports',
		'This MSI laptop is equipped with 3 USB 3.2 ports and one USB Type C / DisplayPort, making it easy to connect to peripherals combined with HDMI 
		output and microSD card reader, quickly opening photos. images and display them on the big screen so that we can review the memorable moments.'
		,'pro3_cont4.jpg',3),
	('3-cell battery for up to 9 hours of battery life',
		'The laptop is equipped with a 52Wh lithium-polymer battery with a usage time of up to more than 9 hours of work. This battery life can well 
		meet the needs or travel of users, you do not need to plug in the charger many times a day to interrupt your work.'
		,'pro3_cont5.jpg',3),
	('Stable wireless connection',
		'MSI A10M 667VN has a modern WiFi 802.11ax (Wifi 6) wireless connection, this is the wifi generation for stable and fast connectivity. There 
		is also a Bluetooth 5.1 wireless connection that is very convenient for users to serve the needs of work as well as entertainment.'
		,'pro3_cont6.jpg',3),

	('Outstanding design, 15.6 inch Full HD screen',
		'The MSI Thin GF65 10UE 286VN laptop has a very eye-catching design with red and black color gamut, compact and lightweight 35.9 x 25.4 x 2.17 
		cm and weighs only 1.8kg. Users can carry it with them everywhere easily without feeling entangled or heavy, serving the needs of users well.'
		,'pro4_cont1.jpg',4),
	('NVIDIA GeForce RTX 3060 6GB GDDR6, 16GB Ram, 512GB SSD M.2 NVMe',
		'MSI Thin GF65 10UE 286VN laptop uses NVIDIA GeForce RTX 3060 6GB GDDR6 graphics chip to help users perform tasks faster and smoother. In 
		addition, gamers can have a great experience when playing high-resolution graphic games.'
		,'pro4_cont2.jpg',4),
	('Using Intel Core i5-10500H chip, Led-enabled keyboard, pre-installed with Windows 10 Home SL',
		'The MSI Thin GF65 10UE 286VN laptop uses Intel Core i5-10500H chip, which is the 10th generation Intel Core chip. With 6 cores and 12 threads,
		the highest clock speed reaches 4.5GHz and the cache memory up to 12MB helps users The service is done easily and quickly, the smoothest as 
		well as providing the best gaming experience.'
		,'pro4_cont3.jpg',4),
	('3-cell 51Wh battery, modern connectivity ports',
		'The use of a 3-cell 51Wh battery for the MSI Thin GF65 10UE 286VN laptop makes it possible for users to comfortably use the computer to listen 
		to music, play games, entertain or work for more than 6 hours continuously. customary. Moreover, after only 2 hours of charging, the laptop is 
		fully charged so that users can continue their work.'
		,'pro4_cont4.jpg',4),

	('Possessing an elegant and modern appearance, combined with a 14-inch wide-angle screen for an immersive visual experience',
		'Acer Aspire 5 laptop A514-54-51VT (NX.A23SV.004) possesses an elegant and modern appearance with bright silver color, and also has a fairly 
		compact size, weighing only 1.5kg and only 31.89 x 20,698 x 1,496 cm, so you can easily bring it anywhere to work without any problems.'
		,'pro5_cont1.png',5),
	('Owning a new generation Intel Core i5 processor, combined with 8GB RAM to perform all tasks smoothly',
		'Not only that, Acer Aspire 5 A514-54-51VT laptop (NX.A23SV.004) also owns a modern new generation Intel Core i5 processor, clocked at 4.2GHz, 
		accompanied by 4 cores of 8 ultra-high processing flow, allowing you to exploit all the features of your computer with powerful processing 
		speed.'
		,'pro5_cont2.png',5),
	('Equipped with a powerful Intel Iris Xe Graphics chip, combined with a high 512GB SSD hard drive',
		'In particular, the Acer Aspire 5 A514-54-51VT laptop (NX.A23SV.004) is also equipped with a powerful Intel Iris Xe Graphics chip, allowing 
		users to use office software, support Support playing basic graphic games, simple video and image editing.'
		,'pro5_cont4.png',5),
	('Diversify convenient connection ports, and support pre-installed popular Windows 10 operating system',
		'Moreover, the laptop Acer Aspire 5 A514-54-51VT (NX.A23SV.004) also diversified convenient connection ports, including: 2 USB 3.2 ports, 1 
		Thunderbolt 4 port with 1 video output port HDMI. It also supports more types of fast wireless connections including WiFi 802.11ax (Wifi 6) 
		and Bluetooth 5.1.'
		,'pro5_cont4.png',5),
	('Possessing a battery of up to 40Wh for long-lasting use, and owning a very good keyboard quality',
		'Acer Aspire 5 laptop A514-54-51VT (NX.A23SV.004) owns a battery of up to 40Wh, allowing users to use the machine continuously, with a maximum
		time of up to 11 hours for office operations. basic, so you can work, study and play with complete peace of mind anywhere without having to 
		worry about not having a place to charge the battery.'
		,'pro5_cont5.png',5),

	('Elegant streamlined design, screen for wider viewing angle',
		'Upgraded compared to its predecessors, the Dell Latitude 3520 70251603 laptop is designed to be compact with dimensions of 36.1 x 24.1 x 1.81
		cm and a weight of only 1.8kg. With a luxurious look, and a sturdy shell, thin bezels and polished edges bring a sense of luxury to the user.'
		,'pro6_cont1.png',6),
	('Power comes from 11th Gen Intel Core CPUs, improving usage efficiency',
		'Dell Latitude 3520 70251603 laptop is equipped with 11th generation Intel Core i3-1115G4 CPU with 2 cores and 4 threads, clocked at 4.1GHz, 
		performance increased by 20% compared to its predecessors, and the ability to support graphics double.'
		,'pro6_cont2.png',6),
	('Anti-glare screen, diverse connectivity',
		'Dell Latitude 3520 70251603 laptop screen has a screen resolution of 1080p standard for you to watch movies, display text clearly, can 
		multitask the screen. Besides, the screen is also equipped with Anti Glare anti-glare layer to improve contrast, creating wide and 
		multi-dimensional viewing angles and brighter vision with a full HD screen.'
		,'pro6_cont3.png',6),
	('Using Fedora operating system, using time up to 6 hours',
		'Fedora operating system is used on Dell Latitude 3520 70251603 Laptop with high availability, completely free, multi-features, GNOME 3 
		interface, impressive performance. Fedora operating system is a good environment for web programmers supporting java, php... In addition, it 
		also supports available tools for programming.'
		,'pro6_cont4.png',6);
insert into configs(name)
values
	('Cpu Gen'),
	('Chip CPU'),
	('Chip GPU'),
	('RAM'),
	('Dislay'),
	('Storage'),
	('Storage port number'),
	('Type M.2 support'),
	('Output port'),
	('Connector'),
	('Wireless Connectivity'),
	('Keyboart'),
	('Operating system'),
	('Size'),
	('Battery'),
	('Mass');
insert into configdetails(Contentc,Config_Id,Product_Id)
values
	('Core i5 , Intel Core 11th Gen',1,1),
	('Intel Core i5-11400H ( 2.7 GHz - 4.5 GHz / 12MB / 6 core, 12 thread ) i5-11400H',2,1),
	('RTX 3050 4GB GDDR6 / Intel UHD Graphics',3,1),
	('1 x 8GB DDR4 3200MHz ( 2 Slots / Max support 32GB )',4,1),
	('15.6" ( 1920 x 1080 ) Full HD IPS 144Hz, HD webcam',5,1),
	('512GB SSD M.2 NVMe /',6,1),
	('1 x 2.5" SATA , 2 x M.2 NVMe',7,1),
	('M.2 NVMe',8,1),
	('1 x HDMI',9,1),
	('1 x USB Type C / DisplayPort , 3 x USB 3.2 , 1 x 3.5 mm , LAN 1 Gb/s',10,1),
	('WiFi 802.11ax (Wifi 6) , Bluetooth 5.1',11,1),
	('normal , with number pad , RGB',12,1),
	('Windows 11',13,1),
	('36.34 x 25.5 x 2.39 cm',14,1),
	('4 cell 57 Wh , Instant battery',15,1),
	('2.2 kg',16,1),

	('Ryzen 7',1,2),
	('AMD Ryzen 7 4800H ( 2.9 GHz - 4.2 GHz / 8MB / 8 core, 16 thread ) Ryzen 7 4800H',2,2),
	('GTX 1650 4GB GDDR6 / AMD Radeon Graphics',3,2),
	('1 x 8GB DDR4 3200MHz ( 2 slots / Max support 32GB )',4,2),
	('15.6" ( 1920 x 1080 ) Full HD IPS 144Hz',5,2),
	('512GB SSD M.2 NVMe /',6,2),
	('2 x M.2 NVMe',7,2),
	('M.2 NVMe',8,2),
	('1 x HDMI',9,2),
	('1 x USB Type C / DisplayPort / Power Delivery , 3 x USB 3.2 , 1 x 3.5 mm , LAN 1 Gb/s',10,2),
	('WiFi 802.11ax (Wifi 6) , Bluetooth 5.1',11,2),
	('normal , No number keyboart , RGB',12,2),
	('Windows 10 Home 64-bit Windows 10',13,2),
	('35.4 x 25.9 x 2.59 cm',14,2),
	('4 cell 56 Wh',15,2),
	('2.1 kg',16,2),

	('Core i5 , Intel Core 10th Gen',1,3),
	('Intel Core i5-10210U ( 2.1 GHz - 4.2 GHz / 6MB / 4 core, 8 thread ) i5-10210U',2,3),
	('Onboard Intel UHD Graphics',3,3),
	('1 x 8GB DDR4 3200MHz ( 2 slots / Max support 64GB )',4,3),
	('15.6" ( 1920 x 1080 ) Full HD IPS, HD webcam',5,3),
	('512GB SSD M.2 NVMe /',6,3),
	('1 x M.2 SATA/NVMe',7,3),
	('M.2 SATA/NVMe',8,3),
	('1 x HDMI',9,3),
	('1 x USB Type C / DisplayPort , 3 x USB 3.2 , 1 x micro SD card slot , Audio combo',10,3),
	('WiFi 802.11ax (Wifi 6) , Bluetooth 5.1',11,3),
	('normal , LED',12,3),
	('Windows 10 Home 64-bit Windows 10',13,3),
	('35.7 x 23.4 x 1.89 cm',14,3),
	('3 cell 52 Wh',15,3),
	('1.6 kg',16,3),

	('Core i5 , Intel Core 10th Gen',1,4),
	('Intel Core i5-10500H ( 2.5 GHz - 4.5 GHz / 12MB / 6 core, 12 thread ) i5-10500H',2,4),
	('RTX 3060 6GB GDDR6 / Intel UHD Graphics',3,4),
	('2 x 8GB DDR4 3200MHz ( 2 slots / Max support 64GB )',4,4),
	('15.6" ( 1920 x 1080 ) Full HD IPS 144Hz, HD webcam',5,4),
	('512GB SSD M.2 NVMe /',6,4),
	('2 x M.2 NVMe',7,4),
	('M.2 NVMe',8,4),
	('1 x HDMI',9,4),
	('2 x USB 3.2 Type C , 2 x USB 3.2 , Audio combo , LAN 1 Gb/s',10,4),
	('WiFi 802.11ax (Wifi 6) , Bluetooth',11,4),
	('normal , LED',12,4),
	('Windows 10 Home SL 64-bit Windows 10',13,4),
	('35.9 x 25.4 x 2.17 cm',14,4),
	('3 cell 51 Wh',15,4),
	('1.8 kg',16,4),

	('Core i5 , Intel Core 11th Gen',1,5),
	('Intel Core i5-1135G7 ( 2.4 GHz - 4.2 GHz / 8MB / 4 cores, 8 threads ) i5-1135G7',2,5),
	('Onboard Intel Iris Xe Graphics',3,5),
	('8GB (4GB + 4GB Onboard) LPDDR4X 2666MHz',4,5),
	('14" ( 1920 x 1080 ) Full HD IPS , HD webcam',5,5),
	('512GB SSD M.2 NVMe /',6,5),
	('1 x M.2 NVMe',7,5),
	('M.2 NVMe',8,5),
	('1 x HDMI',9,5),
	(', 2 x USB 3.2 , 1 x Thunderbolt 4 , Audio combo',10,5),
	('WiFi 802.11ax (Wifi 6) , Bluetooth 5.1',11,5),
	('Normal , No number keyboart',12,5),
	('Windows 10 Home 64-bit Windows 10',13,5),
	('31.89 x 20.698 x 1.496',14,5),
	('40 Wh ',15,5),
	('1.5 kg',16,5),

	('Core i3 , Intel Core 11th gen',1,6),
	('Intel Core i3-1115G4 ( 3.0 GHz - 4.10 GHz / 6MB / 2 cores, 4 threads ) i3-1115G4',2,6),
	('Onboard Intel UHD Graphics',3,6),
	('1 x 4GB DDR4 3200MHz ( 2 slots / support max 32GB )',4,6),
	('15.6" ( 1366 x 768 ) HD TN no touch , HD webcam',5,6),
	('256GB SSD M.2 NVMe',6,6),
	('1 x M.2 NVMe',7,6),
	('M.2 NVMe',8,6),
	('1 x HDMI',9,6),
	('1 x USB Type C / DisplayPort , 2 x USB 3.2 , 1 x USB 2.0 , 1 x micro SD card slot, LAN 1 Gb/s',10,6),
	('WiFi 802.11ax (Wifi 6) , Bluetooth',11,6),
	('normal , with number pad',12,6),
	('Fedora Fedora',13,6),
	('36.1 x 24.1 x 1.81 cm',14,6),
	('3 cell 41 Wh ',15,6),
	('1.8 kg',16,6);
insert into favorites(createDate,Product_Id,Username_Id)
values
	('2023-2-14',1,'binhbv'),
	('2023-2-14',2,'binhbv'),
	('2023-2-14',3,'binhbv'),
	('2023-2-14',4,'binhbv'),
	('2023-2-14',5,'binhbv');
insert into reviews(name,email,reviews,rate,createDate,Product_Id)
values
	('Alex','alex@gmail.com','Acer specializes in designing products with high configuration that play great games, powerful 
		configurations that can replace PCs in the process of playing games without getting hot and dropping frames, good quality 
		worth choosing',
		5,'2022-12-13',1),
	('Harley','harley@gmail.com','Games are reproduced smoothly, with fast frame rates and minimal screen tearing',
		4,'2022-10-07',1),
	('Bill','alex@gmail.com','Just bought 2 days, the machine is configured ok, the keyboard is responsive, especially the movie
		 screen is very nice',
		 3, '2022-11-05',1),
	('Gate','alex@gmail.com','Good machine, enthusiastic staff Playing games should plug in the charger to open the device to 
	full performance. Go to settings to set 144hz smoother',
		2,'2022-10-24',1);