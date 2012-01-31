Ext.define('PoupaNiquel.controller.Common', {
    extend: 'Ext.app.Controller',

    models: ['Payee', 'Category', 'Account', 'Transaction'],
    stores: ['Payees','Categories', 'Accounts', 'Transactions'],
    views : ['common.MdiWindow'],
    
    refs: [{
    	ref: 'myGrid',
    	selector: this.myGridSelector
    }],
    
    init: function() {
    	var addButton = this.myGridSelector + ' button[action=add]',
    	    delButton = this.myGridSelector + ' button[action=delete]';
    	
    	this.control({
    		addButton: {click: this.add},
    		delButton: {click: this.delete},
    	});
    },
    
    showPanel: function() {
    	var viewport = Ext.ComponentManager.get('viewportCenter'),
    	    panel = Ext.ComponentManager.get(this.myPanelName),
    	    store = this.getStore(this.myStoreName);
    	
    	if (panel == null) {
    		var grid = this.myGridPanel;
    		
    		if (grid == undefined) {
    			grid = Ext.create('PoupaNiquel.view.common.DataGrid', {
	       	    	alias  : 'widget.' + this.myGridSelector,
	            	store  : store,
	            	plugins: Ext.create('Ext.grid.plugin.CellEditing'),
    			});
       	    };
    		
       	    panel = Ext.create('widget.mdiWindow', {
       	    	id   : this.myPanelName,
       	    	title: this.myTitle,
       	    	items: grid
       	    });
       	    
       	    viewport.add(panel);
    	}
    	panel.show();
    },
    
    add: function(button) {
    	this.getMyGrid().getStore().insert(0, this.getModel(this.myModelName).create());
    },
    
    delete: function() {
    	var grid = this.getMyGrid(),
	        record = grid.getSelectionModel().getSelection()[0],
            store = grid.getStore();
        
        if (record) {
        	store.remove(record);
        }
    }
     
});