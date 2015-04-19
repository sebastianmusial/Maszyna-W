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
	initMemoryPreview();
	
	$.get("LanguageAccessor", {lang: "pl"}, function(language) {
		MW.Language = language;
		retranslateRegisters();
		retranslateSignals();
		for(key in MW.Language.UI){
			$("#" + key).html(MW.Language.UI[key]);
		}
	});
	
	var settingsInitialized = initSettings();
	initRunner();
	initInteractions();
	restoreState();
	
	// Delayed so the browser can manage DOM changes before showing content.
	setTimeout(function() {
		Mappings.Dom.View.containerCentralUnit.animate({opacity: 1.0}, 0);
		setTimeout(function() {
			$.when(settingsInitialized).then(initMemoryLeft);
		}, 25);
	}, 25);
	
	$("#test-memory").click(function() {
		var i = 0;
		var update = function() {
			var start = i;
			for(; i < start + 8; ++i) {
				MW.Memory[i].value = i;
				MW.Memory[i].text = "stp " + i;
			}
		}
		var updateRecursively = function() {
			update();
			if(i < 512)
				setTimeout(updateRecursively, 500);
		}
		setTimeout(updateRecursively, 500);
	});
});
