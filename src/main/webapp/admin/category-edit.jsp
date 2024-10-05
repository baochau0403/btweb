<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<form action= "<c:url value='/admin/category/update>'</c:url>"method="post">
<label for="text" id="categoryid" name="categoryid" hidden="hidden" value="${cate.categoryid}"><br>
  <label for="fname"> Category Name:</label><br>
  <input type="text" id="categoryname" name="categoryname" value="${cate.categoryid}" ><br>
  <label for="lname">Images:</label><br>
  <input type="file" id="images" name="images">
	<p>Status</p>
  <input type="radio" id="ston" name="status" value="1">
  <label for="html">Đang hoạt động</label><br>
  <input type="radio" id="stoff" name="status" value="0">
  <label for="css">Khóa</label><br>

<input type ="submit" value="Update">
</form>