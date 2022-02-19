<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
<meta charset="UTF-8">
<title>Lotto</title>
</head>

<body style="padding: 15px;">
		
		<form class="pure-form" method="post" action="./add">
			<fieldset>
				<button type="submit" class="pure-button pure-button-primary">
					電腦選號
				</button>
			</fieldset>
		</form>
		
		<!-- ******  Homework  ****** -->
		<form class="pure-form" method="post" action="./data">
			<fieldset>
				<button type="submit" class="pure-button pure-button-primary">
					統計每一號碼的出現次數
				</button>
			</fieldset>
		</form>
		球號出現次數統計: <p/>
		<c:forEach varStatus="status" var="data" items="${ lottosData }">
				
				${ data.key }(${ data.value }) 
				
		</c:forEach>
		
		
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th>index</th>
					<th>lotto</th>
					<th>update</th>
					<th>delete</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach varStatus="status" var="lotto" items="${ lottos }">
					
					<tr>
						<td>${ status.index }</td>
						<td>${ lotto }</td>
						<td>
							<button type="submit" onclick="window.location.href='./update/${status.index}'" class="pure-button pure-button-primary">
								update
							</button>
						</td>	
						<td>
							<button type="submit" onclick="window.location.href='./delete/${status.index}'" class="pure-button pure-button-primary">
								delete
							</button>
						</td>
					</tr>
					
				</c:forEach>
			</tbody>
			
		</table>
		
	
</body>
</html>