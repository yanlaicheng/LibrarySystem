<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%> 
<%@ page import="com.yan.LibrarySystem.po.Book" %>
<%@ page import="com.yan.LibrarySystem.po.BookType" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
 
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的bookType信息
    List<BookType> bookTypeList = (List<BookType>)request.getAttribute("bookTypeList");
    Book book = (Book)request.getAttribute("book");

    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login';</script>");
    }
%>
<HTML><HEAD><TITLE>修改图书</TITLE>
<STYLE type=text/css>
BODY {
	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>
 <script src="<%=basePath %>resources/js/calendar.js"></script> 
</HEAD>
<BODY background="<%=basePath %>resources/images/adminBg.jpg"> 
<TABLE align="center" height="100%" cellSpacing=0 cellPadding=0 width="80%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top > 
 <sf:form method="post" modelAttribute="book" enctype="multipart/form-data" > 
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>图书条形码:</td>
    <td width=70%><sf:input path="barcode" readOnly="true" size="20"/> 
     <sf:errors path="barcode" cssStyle="color:red" /></td>
  </tr>

  <tr>
    <td width=30%>图书名称:</td>
    <td width=70%><sf:input path="bookName" size="20"/> 
     <sf:errors path="bookName" cssStyle="color:red" /></td>
  </tr>

  <tr>
    <td width=30%>图书所在类别:</td>
    <td width=70%>
      <select name="bookType.bookTypeId">
      <%
        for(BookType bookType:bookTypeList) {
          String selected = "";
          if(bookType.getBookTypeId() == book.getBookType().getBookTypeId())
            selected = "selected";
      %>
          <option value='<%=bookType.getBookTypeId() %>' <%=selected %>><%=bookType.getBookTypeName() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>图书价格:</td>
    <td width=70%><sf:input path="price" size="8"/>元
     <sf:errors path="price" cssStyle="color:red" /></td>
  </tr>

  <tr>
    <td width=30%>库存:</td>
    <td width=70%><sf:input path="count" size="8"/> 
     <sf:errors path="count" cssStyle="color:red" /></td>
  </tr>

  <tr>
    <td width=30%>出版社:</td>
    <td width=70%><sf:input path="publish" size="20"/> 
     <sf:errors path="publish" cssStyle="color:red" /></td>
  </tr>
  
   <tr>
    <td width=30%>出版日期:</td>
    <td width=70%><sf:input path="publishDate" readonly="true" onclick="setDay(this);"/> 
     <sf:errors path="publishDate" cssStyle="color:red" /> </td>
  </tr>
  

  <tr>
    <td width=30%>图书图片:</td>
    <td width=70%><img src="<%=basePath %><%=book.getPhotoBook() %>" width="200px" border="0px"/><br/>
    <sf:hidden path="photoBook"/>
    <input id="photoBookFile" name="photoBookFile" type="file" size="50" /></td>
  </tr>
  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='保存' >
        &nbsp;&nbsp;
        <input type="reset" value='重写' />
      </td>
    </tr>

</table>
</sf:form>
   </TD></TR>
  </TBODY>
</TABLE>
</BODY>
</HTML>
