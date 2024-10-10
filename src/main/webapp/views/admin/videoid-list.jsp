<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<a href="<c:url value='/admin/videoid/add'></c:url>">Add video</a>
<div>
    <a href="<c:url value='/admin/videoid/details?id=${vid.videoId }'/>">Danh sách video</a>
</div>
<hr style="border: none; height: 2px; background-color: green;" />
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


<input type="file" onchange="chooseFile(this)" id="poster" name="poster" value="${vid.poster}">


<label for="fname" style="display: inline-block; width: 100px;">VideoID:</label>
<input type="text" id="VideoId" name="VideoId" value="${vid.videoId}" style="display: inline-block;"><br>

<label for="fname" style="display: inline-block; width: 100px;">Video Title:</label>
<input type="text" id="title" name="title" value="${vid.title}" style="display: inline-block;"><br>
<label for="fname" style="display: inline-block; width: 100px;">Views count:</label>
<input type="text" id="Views" name="Views" value="${vid.Views}" style="display: inline-block;"><br>
<label for="category" style="display: inline-block; width: 100px;">Category:</label>
<select id="category" name="category" style="display: inline-block;">
    <c:forEach items="${categories}" var="category">
        <option value="${category.categoryId}">${category.categoryname}</option>
    </c:forEach>
</select><br>
<label for="fname" style="display: inline-block; width: 100px;">Description:</label>
<input type="text" id="Description" name="Description" value="${vid.Description}" style="display: inline-block;width: 300px;height: 100px;"><br>

<p>Active:</p>


  <input type="radio" id="ston" name="active" value="1" ${vid.active==1?'checked':''  } >


  <label for="html">Đang hoạt động</label><br>


  <input type="radio" id="stoff" name="active" value="0" ${vid.active!=1?'checked':'' }>


  <label for="css">Khóa</label><br>





</form>
<hr style="border: none; height: 2px; background-color: green;" />
<div>
   <input type="submit" value="create">
   <input type="submit" value="Update">
    <button onclick="window.location.href='<c:url value='/admin/videoid/delete'/>'">Delete</button>
    <button type="reset">Reset</button>
</div>
	<button onclick="window.location.href='<c:url value='/admin/videoid/tool'/>'">Tools</button>
<div>
	<a href="<c:url value='/admin/videoid/print'></c:url>">
    <span style="text-decoration: none; display: inline-block;">&#8226;</span> Print
</a>
</div>
<div>
<a href="<c:url value='/admin/videoid/save'></c:url>">
    <span style="text-decoration: none; display: inline-block;">&#8226;</span> Save as PDF
</a>
</div>
<div>
<a href="<c:url value='/admin/videoid/export'></c:url>">
    <span style="text-decoration: none; display: inline-block;">&#8226;</span> Export to Excel
</a>
</div>
<table border="1" width=100%>
<tr>

<th>STT</th>
<th>VideoID</th>
<th>Poster</th>
<th>video title</th>
<th>description</th>
<th>views</th>
<th>Category</th>
<th>active</th>
<th>Action</th>
</tr>
<c:forEach items="${listvid}" var="vid" varStatus="STT">
<tr>
<td>${STT.index+1 }</td>
<td>${vid.videoId }</td>
<c:if test="${vid.poster.substring(0 , 5)=='https'}">
    <c:url value="${vid.poster}" var="imgUrl"></c:url>
</c:if>
<c:if test="${vid.poster.substring(0 , 5)!='https'}">
    <c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>
</c:if>
<td><img height="150" width="200" src="${imgUrl}" /></td>
<td>${vid.title }</td>
<td>${vid.description }</td>
<td>${vid.views }</td>
<td>${vid.category }</td>
<td>
    <c:choose>
        <c:when test="${vid.active == 1}">
            Đang hoạt động
        </c:when>
        <c:otherwise>
            Khóa
        </c:otherwise>
    </c:choose>
</td>
<td><a
href="<c:url value='/admin/videoid/edit?videoId=${vid.videoId }'/>">Sửa</a>
| <a
href="<c:url value='/admin/videoid/delete?videoId=${vid.videoId }'/>">Xóa</a>
</td>
</tr>
</c:forEach>
</table>