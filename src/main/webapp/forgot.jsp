<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<form action="/btweb/forgot" method="post">
			<label for="email">Email:</label> 
			<input type="email" id="email" name="email" class="input-field" required><br> 
			${alert}<br>
			<button class="btn btn-primary" type="submit">Lấy lại mật khẩu</button><br>
			<a href="/btweb/login">Quay lại Login</a>
		</form>
</body>
</html>