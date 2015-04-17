/**
 * Running JS functions defined in other files
 * in the right order, but first read language
 * and architecture info.
 */

$(document).ready(function() {
	Mappings.Dom.View.containerCentralUnit.animate({opacity: 0.0}, 0);
	
	MW.initView();
	initRegisters();
	initSignals();
	
	$.get("LanguageAccessor", {lang: "pl"}, function(language) {
		MW.Language = language;
		retranslateRegisters();
		retranslateSignals();
		for(key in MW.Language.UI){
			$("#" + key).html(MW.Language.UI[key]);
		}
	});

	initSettings();
	initRunner();
	initInteractions();
	restoreState();
	
	// Delayed so the browser can manage DOM changes before showing content.
	setTimeout(function() {
		Mappings.Dom.View.containerCentralUnit.animate({opacity: 1.0}, 0);
	}, 25);
});
