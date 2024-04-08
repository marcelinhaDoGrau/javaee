/**
 * 
 * Validação de Formulário
 */

 function validar(){
	 //alert('teste')
	 let nome = frmContato.nome.value
	 let fone = frmContato.fone.value
	 
	 if (nome === ""){
		 alert("Preencha o campo nome!")
		 frmContato.nome.focus()
		 return false
	 }else if(fone === ""){
		 alert("Preenchao campo fone!")
		 frmContato.fone.focus()
		 return false
	 }else{
		 document.forms["frmContato"].submit()
	 }
 }