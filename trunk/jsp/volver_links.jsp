<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:set var="scriptActual" value="${pageContext.request.servletPath}" />

<c:if test="${scriptActual ne '/index.jsp'}" >
	<c:choose>
	    <c:when test="${scriptActual=='/sistema.jsp'}">
	        <c:set var="nulo" value="${sessionScope.linkVolver.vaciar()}" />
	    </c:when>
	    <c:otherwise>
	        <c:set var="paginaActual" value="${scriptActual.substring(1)}${\"?\"}${pageContext.request.queryString}" />
			<c:set var="nulo" value="${sessionScope.linkVolver.visita(paginaActual)}" />
			<c:set var="paginaAnterior" value="${sessionScope.linkVolver.paginaAnterior()}" />
			<br><br>
			<table cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td>
						
						<a href="<c:out value="${paginaAnterior}"/>">
							<img src="iconos/atras.png"  class="icono_chico" alt="Volver" 
								title="<c:out value="${paginaAnterior}"/>"></a>
						&nbsp;&nbsp;&nbsp;
					</td>
					<td>
						
						<a href="sistema.jsp">
							<img src="iconos/home.png"  class="icono_chico" alt="P&aacute;gina principal" 
								title="P&aacute;gina principal"></a>
						&nbsp;&nbsp;&nbsp;
					</td>
					<td>
						
						<a href="logout.jsp">
							<img src="iconos/logout.png"  class="icono_chico" alt="Salir" 
								title="Salir del sistema">
						</a>
					</td>
				</tr>
			</table>
	    </c:otherwise>
	</c:choose>
</c:if>