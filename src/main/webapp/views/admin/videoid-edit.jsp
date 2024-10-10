<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div>
	<label>UpLoad</label>
</div>

<form action="<c:url value='/admin/videoid/update'></c:url>" method="post"


enctype="multipart/form-data">



<label for="lname">Poster:</label><br>


<c:if test="${vid.poster.substring(0 , 5)=='https'}">


<c:url value="${vid.poster}" var="imgUrl"></c:url>


</c:if>


<c:if test="${vid.poster.substring(0 , 5)!='https'}">


<c:url value="/image?fname=${vid.poster }" var="imgUrl"></c:url>


</c:if>


<img id="poster" height="150" width="200" src="${imgUrl}" />


<input type="file" onchange="chooseFile(this)" id="images" name="images" value="${vid.poster}">
<div>
<label for="fname" style="display: inline-block; width: 100px;">VideoID:</label>
<input type="text" id="VideoId" name="VideoId" value="${vid.videoId}" style="display: inline-block;"><br>

<label for="fname" style="display: inline-block; width: 100px;">Video Title:</label>
<input type="text" id="title" name="title" value="${vid.title}" style="display: inline-block;"><br>

<label for="fname" style="display: inline-block; width: 100px;">Views count:</label>
<input type="text" id="views" name="views" value="${vid.views}" style="display: inline-block;"><br>
<label for="category" style="display: inline-block; width: 100px;">Category:</label>
<select name="category">
    <c:forEach var="category" items="${categories}">
        <option value="${category.categoryId}">${category.categoryname}</option>
    </c:forEach>
</select>
<div>
<label for="fname" style="display: inline-block; width: 100px;">Description:</label>
<input type="text" id="description" name="description" value="${vid.description}" style="display: inline-block;width: 300px;height: 100px;"><br>
</div>
<p>Active:</p>


  <input type="radio" id="ston" name="active" value="1" ${vid.active==1?'checked':''  } >


  <label for="html">Đang hoạt động</label><br>


  <input type="radio" id="stoff" name="active" value="0" ${vid.active!=1?'checked':'' }>


  <label for="css">Khóa</label><br>

</div>




<input type="submit" value="Update">


</form>