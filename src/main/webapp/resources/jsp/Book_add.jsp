<%@ page language="java" import="java.util.*"  contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.yan.LibrarySystem.po.BookType" %> 
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的bookType信息
    List<BookType> bookTypeList = (List<BookType>)request.getAttribute("bookTypeList");
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login';</script>");
    }
%>
<HTML><HEAD><TITLE>添加图书</TITLE> 
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
<!--  background="<%=basePath %>resources/images/adminBg.jpg" -->
<BODY>

<TABLE align="center" height="100%" cellSpacing=0 cellPadding=0 width="80%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=top >
     <sf:form method="post" modelAttribute="book" onsubmit="return checkForm();" enctype="multipart/form-data">    
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr>
    <td width=30%>图书条形码:</td>
    <td width=70%> <sf:input path="barcode" size="20"/> 
     <sf:errors path="barcode" cssStyle="color:red" />
    </td>
  </tr>

  <tr>
    <td width=30%>图书名称:</td>
    <td width=70%><sf:input path="bookName" size="20"/> 
     <sf:errors path="bookName" cssStyle="color:red" /></td>
  </tr>

  <tr>
    <td width=30%>图书所在类别:</td>
    <td width=70%>
      <sf:select path="bookType.bookTypeId" items="${bookTypeList}" itemLabel="bookTypeName"  
        itemValue="bookTypeId"></sf:select>    
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
    <td width=70%><input id="photoBookFile" name="photoBookFile" type="file" size="50" /></td>
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
