<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Đăng kí</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #C1D8C3;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .main {
            background-color: #F5EFE6;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            padding: 20px;
            width: 300px;
        }

        .main h2 {
            color: #6A9C89;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        select {
            width: 100%;
            margin-bottom: 15px;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        button[type="submit"] {
            padding: 15px;
            border-radius: 10px;
            border: none;
            background-color: #6A9C89;
            color: white;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
    </style>
</head>

<body>
    <div class="main">
        <h2>Register</h2>
        <form action="register" method="post">
            <label for="first">Tên đăng nhập: </label>
            <input type="text" id="username" name="username" required />

            <label for="last">Họ tên: </label>
            <input type="text" id="fullname" name="fullname" required />

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required />

            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required />

            <label for="repassword">Nhập lại mật khẩu:</label>
            <input type="password" id="repassword" name="repassword" required />

            <label for="mobile">Số điện thoại:</label>
            <input type="text" id="phone" name="phone" maxlength="10" required />

            

            <button type="submit">
                Submit
            </button>
            
            <p>Đã có tài khoản? <a href="${pageContext.request.contextPath }/login">Đăng nhập</a></p>
        </form>
    </div>
</body>

</html>