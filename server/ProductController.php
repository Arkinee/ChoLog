<?php
	require 'function.php';

	const JWT_SECRET_KEY = "TEST_KEYTEST_KEYTEST_KEYTEST_KEYTEST_KEYTEST_KEYTEST_KEYTEST_KEYTEST_KEYTEST_KEYTEST_KEYTEST_KEYTEST_KEY";

	$res = (Object)Array();
	header('Content-Type: json');
	$req = json_decode(file_get_contents("php://input"));
	try {
		addAccessLogs($accessLogs, $req);
		switch ($handler) {
			case "category":
				http_response_code(200);
				$res->isSuccess = true;
				$res->code = 300;
				$res->message = "카테고리 목록 출력";
				$res->categoryList = category();
				echo json_encode($res, JSON_NUMERIC_CHECK);
				break;

			case "productList":
				http_response_code(200);
				$res->isSuccess = true;
				$res->code = 100;
				$res->message = "상품 목록 출력";
				$res->popularList = popular();
				$res->recommendList = recommend();
				$res->onlineList = online();
//				$res->offlineList = offline();
				echo json_encode($res, JSON_NUMERIC_CHECK);
				break;
		}
	} catch (\Exception $e) {
		return getSQLErrorException($errorLogs, $e, $req);
	}
