Ext.define('PoupaNiquel.controller.Common', {
    extend: 'Ext.app.Controller',

    models: ['Payee',  'Category',   'Account',  'Transaction'],
    stores: ['Payees', 'Categories', 'Accounts', 'Transactions', 'combobox.Payees', 'combobox.Categories', 'combobox.Accounts'],
    views : ['common.MdiWindow', 'transactions.FilterComboBox', 'transactions.Grid', 'accounts.Grid'],
    
    refs: [{
    	ref: 'myGrid',
    	selector: this.myGridSelector
    }],
    
    showPanel: function() {
    	var viewport = Ext.ComponentManager.get('viewportCenter'),
    	    panel = Ext.ComponentManager.get(this.myPanelName),
    	    store = this.getStore(this.myStoreName);
    	
    	if (panel == null) {
    		var grid = this.myGridPanel;
    		
    		if (grid == undefined) {
    			grid = Ext.create('PoupaNiquel.view.common.DataGrid', {
    				id     : this.myGridSelector,
	       	    	alias  : 'widget.' + this.myGridSelector,
	            	store  : store,
	            	plugins: Ext.create('Ext.grid.plugin.CellEditing', {
	            		listeners: {
	            			'edit': function() {
	            				store.sync();
	            			}
	            		}
	            	}),
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
    	button.up('grid').getStore().insert(0, this.getModel(this.myModelName).create());
    },
    
    delete: function(button) {
    	var grid = button.up('grid'),
    	    store = grid.getStore(),
	        record = grid.getSelectionModel().getSelection()[0];
        
        if (record) {
        	if (confirm('Are you sure?')) {
        		store.remove(record);
        		store.sync();
        	}
        }
    }
     
});