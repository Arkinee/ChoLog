<?php

//READ
	function category()
	{
		$pdo = pdoSqlConnect();
		$query = "select category from CompanyInfo group by category order by count(category) desc ;";

		$st = $pdo->prepare($query);
		//    $st->execute([$param,$param]);
		$st->execute();
		$st->setFetchMode(PDO::FETCH_ASSOC);
		$res = $st->fetchAll();

		$st = null;
		$pdo = null;

		return $res;
	}

	function popular()
	{
		$pdo = pdoSqlConnect();
		$query = "select CompanyInfo.companyName, productName, price, CompanyInfo.category, CompanyInfo.logo
					from ProductInfo
         			join CompanyInfo on ProductInfo.companyName = CompanyInfo.companyName
					order by rand() limit 5;";
		$st = $pdo->prepare($query);
		//    $st->execute([$param,$param]);
		$st->execute();
		$st->setFetchMode(PDO::FETCH_ASSOC);
		$res = $st->fetchAll();

		$st = null;
		$pdo = null;
		return $res;
	}

	function recommend()
	{
		$pdo = pdoSqlConnect();
		$query = "select CompanyInfo.companyName, productName, price, CompanyInfo.category, CompanyInfo.logo
					from ProductInfo
         			join CompanyInfo on ProductInfo.companyName = CompanyInfo.companyName
					order by rand() limit 5;";
		$st = $pdo->prepare($query);
		//    $st->execute([$param,$param]);
		$st->execute();
		$st->setFetchMode(PDO::FETCH_ASSOC);
		$res = $st->fetchAll();

		$st = null;
		$pdo = null;
		return $res;
	}

	function online()
	{
		$pdo = pdoSqlConnect();
		$query = "select CompanyInfo.companyName, productName, price, CompanyInfo.category, CompanyInfo.logo
						from ProductInfo
				         join CompanyInfo on ProductInfo.companyName = CompanyInfo.companyName
					where  CompanyInfo.type = '온라인'
					order by rand();";
		$st = $pdo->prepare($query);
		//    $st->execute([$param,$param]);
		$st->execute();
		$st->setFetchMode(PDO::FETCH_ASSOC);
		$res = $st->fetchAll();

		$st = null;
		$pdo = null;
		return $res;
	}