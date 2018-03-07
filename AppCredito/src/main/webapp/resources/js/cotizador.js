jQuery(document).ready(function($) {
	inicializarComponentes();
	inicializarTablaCronograma();
	
	
	$("#formulario").validate({
        rules: {
            txtMonto: "required",
            txtCuotas: "required",
            txtTasa: "required",
            txtFecha: "required"
        },
        messages: {
        	txtMonto: "Porfavor, especifique un monto",
        	txtCuotas: "Porfavor, especifique el numero de cuotas",
        	txtTasa: "Porfavor, especifique la Tasa",
        	txtFecha: "Porfavor, especifique la fecha de Desembolso"

        }
    })
});




var report_CRONOGRAMA_basicLink='report/LISTADO_CRONOGRAMA';
var report_CRONOGRAMA= [];
function addCRONOGRAMA(value,position){
report_CRONOGRAMA[position]=value;
}
function cleanCRONOGRAMA(){
report_CRONOGRAMA=[];
}
function makeReportLinkCRONOGRAMA(){
if(typeof(functionCRONOGRAMA)=="function"){
functionCRONOGRAMA();
}
cantidad = report_CRONOGRAMA.length;
newLink = '';
for (i = 0; i < cantidad; i++) {
 newLink=newLink+'&param__'+i+'='+report_CRONOGRAMA[i];
}
newLink= report_CRONOGRAMA_basicLink + '?'+newLink.substr(1);
$('#report_CRONOGRAMA').attr('href', newLink);
}

var idcronograma='';

function functionCRONOGRAMA(){
	cleanCRONOGRAMA();
	addCRONOGRAMA(idcronograma, 1);
}



function guardarDatosRegistro(){
	var parametros = [];
      	parametros[1]=$('#txtMonto').val();
      	parametros[2]=$('#txtTasa').val();
      	parametros[3]=$('#txtCuotas').val();
      	parametros[4]=$('#txtFecha').val();
      	parametros[5]={async: false,callback : respuestaRegistro, errorHandler : error};
	dwrService.sendAjax.apply(this, parametros);
}


function respuestaRegistro(list){
	var arr = getJsonStringToArray(list);
	idcronograma=arr[0][0];
	$('#tableCrononograma').dataTable().fnClearTable();
	if(list != "[]"){
		$('#tableCrononograma').dataTable().fnAddData(arr);	
	}
	$('#tableCrononograma').dataTable().fnDraw();	
}

function inicializarTablaCronograma(){
	$('#tableCrononograma').dataTable({		
		language: { 
		    'lengthMenu': 'Mostrar _MENU_ registros por p&aacute;gina', 
		    'info': 'Visualizaci&oacute;n de _START_ hasta _END_ resultados de _TOTAL_', 
		    'infoEmpty': 'Visualizaci&oacute;n de 0 hasta 0 resultados de 0', 
		    'loadingRecords': 'Cargando...', 
		    'processing': 'Procesando...', 
		    'zeroRecords': 'No se encontraron registros'
		},
		'ordering': false,
		'info': false,
		'searching': false,
		'responsive': true,
		'lengthChange': false,
        columns: [
			{ title: "id"},
            { title: "N.cuo"},
            { title: "cuota"},
            { title: "amort."},
            { title: "interes"},
            { title: "capital"},
            { title: "fecha"}
        ],
  		"pageLength": 10
    });
}

function getJsonStringToArray(jsonString){
	var obj = JSON.parse(jsonString);
	var arrRows = [];
	$.each( obj, function( key, value ) {
	    var arrColumn = [];
	    $.each( value, function( ky, val ) {
	        arrColumn.push(val);
	    });
	    arrRows.push(arrColumn);
	});
	return arrRows;
}


function enviarMail(){
	idcronograma = idcronograma;
	var parametros = [];
      	parametros[1]=idcronograma;//idcronograma
      	parametros[2]=$('#txtCorreo').val();//para
      	parametros[3]={async: false,callback : respuestaEnviarMail, errorHandler : error};
	dwrService.sendMail.apply(this, parametros);
}


function respuestaEnviarMail(list){
	console.log("respuesta de Mail");
}

function inicializarComponentes(){
	$("#btnSimular").button()
    .click(function( event ) {
    	//enviarMail();
    	cuadroCronograma();
    });
}

function error(){
	$("div.container span").html('Se ha producido un error en la selecci&oacute; de la lista');
	document.getElementById('lbl_bienvenida').innerHTML = "";
}

var dialogCronograma;
function cuadroCronograma(){
	var validado = $("#formulario").valid();
	if(validado){
	var wWidth = $(window).width();
    var dWidth = wWidth * 0.8;
	dialogCronograma =$("#dialogCronograma").dialog({
		autoOpen: false,
		width: dWidth,
		modal:true,
        buttons: {
        	
        	"Enviar por Mail": function(){cuadroMail()},
        	"Exportar": function(){$( "#report_CRONOGRAMA" ).trigger( "click" );var href = $('#report_CRONOGRAMA').attr('href');
            window.location.href = href;}
        },
        close : function(){
        } 
	});
	$("#dialogCronograma").dialog("open");
 	guardarDatosRegistro();
	}
}

var dialogMail;
function cuadroMail(){
	dialogMail =$("#dialogMail").dialog({
		autoOpen: false,
		modal:true,
        buttons: {
        	"Aceptar": function(){enviarMail()},
        	"Cancelar": function(){$(this).dialog("close")}},
        close : function(){
        } 
	});
	$("#dialogMail").dialog("open");
}
