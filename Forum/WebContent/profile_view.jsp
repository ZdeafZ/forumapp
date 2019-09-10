<%@ page import="demo.forum.classes.User" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>The Cool Cats Forum</title>
</head>
<body>
<h1>Welcome to your profile</h1>
<h2><%= ((User)request.getAttribute("user")).getUsername() %></h2>
Profile picture: <br/>
<img src="<%= ((User)request.getAttribute("user")).getProfilePicturePath() %>" alt="Profile Picture"><br/>
Description: <br/>
<% //change this garbage at some point cause its a dumb check.
	if(!((User)request.getAttribute("user")).getDescription().isEmpty())
	{
	System.out.println(((User)request.getAttribute("user")).getDescription().isEmpty());%>
<div><%=((User)request.getAttribute("user")).getDescription()%></div>
<%	}
	else
	{%>
	<div>This user has no description.<br/></div>
<%	}%>
<a href="./profile_edit.html">Edit your profile</a><br/>
<a href="./forum_index">Back to the threads</a>
</body>
</html>