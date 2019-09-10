<%@ page import="demo.forum.classes.Thread,demo.forum.classes.Post,java.util.ArrayList" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>The Cool Cats Forum</title>
</head>
<body> 
<%
	if(request.getAttribute("thread") != null)
	{
		Thread thread = (Thread) request.getAttribute("thread");
%>
	<h1><%= thread.getThreadName() %></h1>
	<div><%= "Made by: "+ thread.getThreadUser() %> <br/></div>
	<div><%= thread.getThreadText()%><br/></div>
<% 
	ArrayList<Post> list = (ArrayList<Post>) request.getAttribute("post_list");
	for(int i = 0; i < list.size(); ++i){
%>
	<div>
		-------------------------------<br/>
		<%= list.get(i).getID() %>. made by: <b><%=list.get(i).getUsername()%></b><br/>
		<%= list.get(i).getText()%> <br/>
		-------------------------------<br/>
	</div>
<%		
	}
	
%>
<form id="postForm" name="postForm" method="post" action="post">
	<textarea rows="3" cols="50" name="post_text" form="postForm"></textarea> <br/>
	<input id="threadID" name="threadID" type="hidden" value="<%=request.getParameter("id")%>">
	<input id="postCount" name="postCount" type="hidden" value = <%=list.size()%>>
    <input type="submit" value="Post"/>
<%
	}
%>
</form>
</body>
</html>