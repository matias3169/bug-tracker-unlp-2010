function mandatorio(id, obj)
{
	if(obj.value=="")
		document.getElementById(id).style.color = '#FF0000';
	else
		document.getElementById(id).style.color = '#00AA00';
}

function enviarLogin()
{
	alert("enviado");
}
