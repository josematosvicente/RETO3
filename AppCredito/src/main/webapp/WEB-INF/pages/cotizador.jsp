<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<body id="mainPrincipal" class="mainBody">
	<form id="formulario" name="formulario" method="post">




		<div id="contenido" class="fondo-home">
			<h1>
				Simula tu Crédito <a href="" id="report_CRONOGRAMA"
					onclick="makeReportLinkCRONOGRAMA(this)" target=""></a>
			</h1>
				<div class="form-group">
					<label for="txtMonto">Monto del Credito</label> <input type="number"
						class="form-control" id="txtMonto" name="txtMonto" placeholder="Monto del Credito" value="4000">
				</div>
				<div class="form-group">
					<label for="txtCuotas">Numero de Cuotas</label> <input type="number"
						class="form-control" id="txtCuotas" name="txtCuotas" placeholder="Numero de Cuotas" value="12">
				</div>
				<div class="form-group">
					<label for="txtTasa">Tasa Anual</label> <input type="number"
						class="form-control" id="txtTasa" name="txtTasa" placeholder="Tasa Anual" value="7">
				</div>
				<div class="form-group">
					<label for="txtFecha">Fecha de desembolso</label> <input type="date"
						class="form-control" id="txtFecha" name="txtFecha"
						placeholder="Fecha de desembolso"  value="2018-03-08">
				</div>
				<div class="form-group">
					<label for="cmbPeriodoGracia">Periodo de Gracia</label> <select
						class="form-control" id="cmbPeriodoGracia" name="cmbPeriodoGracia">
						<option value="0">Sin capitalizacion</option>
						<option value="1">Con Capitalizacion</option>
					</select>
				</div>

				<input id="btnSimular" type="button" value="Simular"
					class="ui-button ui-widget ui-state-default ui-corner-all"
					role="button" />
		</div>



		<div id="error-row" class="custom-error">
			<ul></ul>
		</div>
	</form>


	<div id="dialogCronograma" style="display: none"
		title="CRONOGRAMA DE PAGO">
		<div id="contenido3">
			<form id="formulario4" name="formulario4" method="post">
				<div>
					<table id="tableCrononograma" style="width: 100% !important;">
					</table>
				</div>
			</form>
		</div>
	</div>

	<div id="dialogMail" style="display: none" title="INGRESE SU CORREO">
		<div id="contenido3">
			<form id="formulario4" name="formulario4" method="post">
				<div>
					<input type="email" id="txtCorreo" tabindex="3" placeholder="Ingrese su correo electrónico" style="width: 100% !important" class="ui-corner-all">
				</div>
			</form>
		</div>
	</div>

</body>