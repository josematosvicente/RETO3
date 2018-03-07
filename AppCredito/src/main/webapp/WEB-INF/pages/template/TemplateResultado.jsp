<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 		<meta http-equiv="Content-Type" content="text/html; charset=windows-1252"> -->
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
		<link href="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/css/dm_credito.css" type="text/css" rel="stylesheet" />
		
		<link href="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/css/tablas_cotizador.css" type="text/css" rel="stylesheet" />
		<link href="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/css/easyui.css" rel="stylesheet" type="text/css">
    	<link href="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/css/icon.css" rel="stylesheet" type="text/css">
    	<link href="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/css/jquery-ui-1.11.14.css" rel="stylesheet" type="text/css">
    	<link href="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
    	<link href="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/css/dm_cotizador2.css" type="text/css" rel="stylesheet" />
		
		<link rel="shortcut icon" href="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/ico/favicon.ico"/>

		<script src="${pageContext.request.contextPath}/dwr/engine.js" type='text/javascript'></script>
		<script src="${pageContext.request.contextPath}/dwr/util.js" type='text/javascript'></script>
		<script src="${pageContext.request.contextPath}/dwr/interface/dwrService.js" type='text/javascript'></script>
		
		<script src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/jquery-1.11.14.js"></script>
		<script src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/jquery-ui-1.11.14.js"></script>
		<script src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/jquery-ui-1.11.14.min.js"></script>
		<script src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/jquery.alphanum.js"></script>
		<script src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/hashtable.js"></script>
		<script src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/jquery.numberformatter-1.2.3.js"></script>
		<script src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/jquery.validate.js"></script>

		<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
		<script type="text/javascript" src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/map/jquery.ui.map.js"></script>
		<script type="text/javascript" src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/map/jquery.ui.map.extensions.js"></script>
		<script type="text/javascript" src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/map/jquery.ui.map.microdata.js"></script>
		<script type="text/javascript" src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/map/jquery.ui.map.microformat.js"></script>
		<script type="text/javascript" src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/map/jquery.ui.map.overlays.js"></script>
		<script type="text/javascript" src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/map/jquery.ui.map.rdfa.js"></script>
		<script type="text/javascript" src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/map/jquery.ui.map.services.js"></script>
		
		<link href="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/css/chardinjs.css" rel="stylesheet">
		<script src="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/js/chardinjs.js"></script>
		
		<link href="<%=org.com.dm.util.Constants.RUTA_RECURSOS %>/css/dm_credito.css" type="text/css" rel="stylesheet" />
		
</head>
    <body>
        <table border="0" align="center" width="500px !important" id="table">
            <tr>
                <td height="30" >
<%--                     <tiles:insertAttribute name="header" /> --%>
                </td>
            </tr>
            <tr>
                <td width="350" >
                    <tiles:insertAttribute name="body" />
                </td>
            </tr>
            <tr class="ul-p">
                <td >
                    <tiles:insertAttribute name="footer" />
                </td>
            </tr>
        </table>
    </body>
</html>