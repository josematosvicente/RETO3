<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

		<style>.ui-dialog {	top: 20px !important;}</style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    	<link href="https://code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">
    	<link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
<%--     	<link rel="shortcut icon" href="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/ico/favicon.ico"/>		 --%>
		<script src="${pageContext.request.contextPath}/dwr/engine.js" type='text/javascript'></script>
		<script src="${pageContext.request.contextPath}/dwr/util.js" type='text/javascript'></script>
		<script src="${pageContext.request.contextPath}/dwr/interface/dwrService.js" type='text/javascript'></script>
		<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

		
		
		<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
		
		<script src="${pageContext.request.contextPath}/resources/js/cotizador.js"></script>
		<link href="${pageContext.request.contextPath}/resources/css/dm_credito.css" type="text/css" rel="stylesheet" />
		
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.min.js"></script>
		<link href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker3.min.css" rel="stylesheet"/>
		
		<script type='text/javascript' src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
		<link href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.bootstrap.min.css" type="text/css" rel="stylesheet" />
		<script src="//cdn.datatables.net/responsive/2.2.1/js/dataTables.responsive.js"></script>
		<script src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
		
				
</head>
    <body>
        	<div style="width:70%; margin: auto;">
                    <tiles:insertAttribute name="body" />
             </div>
    </body>
</html>