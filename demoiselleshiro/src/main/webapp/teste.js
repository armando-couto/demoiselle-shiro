
function setCursor(node, pos) {

	var node = (typeof node == "string" || node instanceof String) ? document
			.getElementById(node) : node;

	if (!node) {
		return false;
	} else if (node.createTextRange) {
		var textRange = node.createTextRange();
		textRange.collapse(true);
		textRange.moveEnd(pos);
		textRange.moveStart(pos);
		textRange.select();
		return true;
	} else if (node.setSelectionRange) {
		node.setSelectionRange(pos, pos);
		return true;
	}

	return false;
}

jQuery(document).ready(function() {

	// jQuery("#meuTexto").blur(function(){
	// jQuery(this).val(jQuery(this).val().toUpperCase());
	// });
	// jQuery("input[name!='meuTexto4']").blur(function(){
	// jQuery(this).val(jQuery(this).val().toUpperCase());
	// });
	
	jQuery("input[name!='meuTexto4']").focus(function() {
		setCursor(this, 0);
	});
	
//	jQuery("input[name!='meuTexto4']").blur(function() {
////		jQuery(this).
//		alert("AAA")
//		setCursor(this, 0);
//	});

	// jQuery("#teste").focus(function() {
	// if (jQuery(this).val() == "") {
	// // jQuery(this).val("__.___.___,__");
	// }
	// });
	//
	// jQuery("#teste").keypress(function(event) {
	//
	// var mask = "99.999.999,99";
	// var maskValidInputSize = 0;
	//
	// var maskl = "";
	// for ( var k = 0; k < mask.length; k++) {
	// if (mask.charAt(k) == "9") {
	// maskl += mask.charAt(k);
	// }
	// }
	//
	// var valor = jQuery(this).val();
	//
	// for ( var k = 0; k < valor.length; k++) {
	// valor = valor.replace("_", "");
	// valor = valor.replace(",", "");
	// valor = valor.replace(".", "");
	// }
	//
	// // alert(event.which);
	//
	// if (event.which == 8 || event.which == 0 || event.which == 13) {
	// return true;
	// } else {
	// if ((event.which > 47) && (event.which < 58)) {
	// var inputValue = String.fromCharCode(event.which);
	//
	// if (valor.length < maskl.length) {
	// valor += inputValue;
	// }
	//
	// var arrValue = valor.split("");
	// arrValue = arrValue.reverse();
	// var revertedValue = arrValue.join("");
	// var arrMask = mask.split("", mask.length);
	// arrMask = arrMask.reverse();
	// var revertedMask = arrMask.join("");
	//
	// var x = 0;
	// var xv = 0;
	//							
	// while (x <= mask.length) {
	// // jQuery("#teste1").val(revertedMask.charAt(x) +
	// // jQuery("#teste1").val());
	// if (revertedMask.charAt(x) == "9") {
	// // if (typeof revertedValue.charAt(xv) != undefined) {
	// if (xv < valor.length) {
	// arrMask[x] = arrValue[xv];
	// } else {
	// arrMask[x] = "";
	// }
	//
	// xv++;
	// }
	// x++;
	// }
	//
	// jQuery("#teste1").val(arrMask.join(""));
	// valor = arrMask.reverse().join("");
	//
	// jQuery(this).val(valor);
	//
	// return false;
	// }else{
	// return false;
	// }
	// }
	// });

});