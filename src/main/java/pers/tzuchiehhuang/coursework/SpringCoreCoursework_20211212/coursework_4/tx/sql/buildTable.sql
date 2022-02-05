lock tables `book` write;
insert into `book`(`bname`, `price`) 
			values('Java', 150), ('Python', 100), ('C#', 200),
				  ('Ruby', 400), ('C++', 250), ('JavaScript', 350);
unlock tables;

lock tables `stock` write;
insert into `stock`(`bid`, `amount`) 
			values(1, 10), (2, 10), (3, 10),
				  (4, 10), (5, 10), (6, 10);
unlock tables;

lock tables `wallet` write;
insert into `wallet`(`wname`, `money`) 
			values('Vicent', 2000), ('Lisa', 600), ('Jack', 1200),
				  ('Bob', 800), ('Peter', 400), ('Amy', 1500);
unlock tables;