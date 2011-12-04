<%@ include file="config.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" >
	<title>..::<%= BTUNLP_Titulo %>::..</title>
	<link rel="stylesheet" href="estilos.css" type="text/css" media="screen" />
    <script src="javascripts.js" type="text/javascript"></script>
    
    <c:if test="${(pageContext.request.servletPath ne '/index.jsp') and (sessionScope.user eq null)}">
    		<meta http-equiv="REFRESH" content="0;url=index.jsp">
    </c:if>

</head>

