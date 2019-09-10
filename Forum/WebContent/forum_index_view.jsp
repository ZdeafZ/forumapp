<%@ page	
	import="demo.forum.classes.Thread,demo.forum.classes.Post,java.util.ArrayList"
 	language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>The Cool Cats Forum</title>
</head>
<body>
	<div>
	
	<%=(String)session.getAttribute("username")%> is welcome to the cool cats club!<br/>
	<button onClick="location.href='./logout'">LOG OUT</button>
	<button onClick="location.href='./profile'">PROFILE</button>
	
	<h1>Your active threads:</h1>
	
	<% 	ArrayList<Thread> myThreadList = (ArrayList<Thread>) request.getAttribute("my_thread_list");
	  	if(myThreadList.isEmpty()) {%>
	  	No active threads made by you! <br/>
	<%	}
		else {
			for(int i = 0; i < myThreadList.size(); ++i) {%>
			<%=	myThreadList.get(i).getID()%>. <a href='./thread?id=<%=myThreadList.get(i).getID()%>'><%=myThreadList.get(i).getThreadName() %></a>
			<br/>
			<%	}%>
	<%	}%>
	<h1>General active threads:</h1>
	
	<% 	ArrayList<Thread> threadList = (ArrayList<Thread>) request.getAttribute("thread_list");
	  	if(myThreadList.isEmpty()) {%>
	  	No active threads in the forum! <br/>
	<%	}
		else {
			for(int i = 0; i < threadList.size(); ++i) {%>
			<%=	threadList.get(i).getID()%>. <a href='./thread?id=<%=threadList.get(i).getID()%>'><%=threadList.get(i).getThreadName() %></a>
				- started by: <%=threadList.get(i).getThreadUser() %>
			<br/>
			<%	}%>
	<%	}%>
	<br/>
	<button onClick="location.href='./thread_post.html'">POST THREAD</button>
	</div>
</body>
</html>