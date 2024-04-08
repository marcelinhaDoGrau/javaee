/**
 * Confirmação de exclusão de um contato
 */

 function confirmar(Idcon){
	 let resposta = confirm("Deseja realmente excluir este contato?")
	 if(resposta===true){
		 //alert(Idcon)
		 window.location.href = "delete?idcon=" +Idcon
	 }
 }