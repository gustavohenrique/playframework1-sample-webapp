Ext.Loader.setConfig({enabled:true});
Ext.define('PoupaNiquel.controller.Main', {
    extend: 'Ext.app.Controller',

    views: ['main.MainToolbar'],
    
    init: function() {
    	this.control({
	        'mainToolbar button[action=showPanel]': {
	            click: this.showPanel
	        },
    	})
    },
    
    showPanel: function(button) {
    	var panels = Ext.ComponentQuery.query('mdiWindow');
    	
    	if (panels.length) {
    		for (var i=0; i < panels.length; i++) {
    			var item = panels[i];
    		    if (item.id != this.id) item.close();
    		};
    	}
    	this.getController(button.controller).showPanel();
    },
    
});