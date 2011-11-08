var rowEditing = Ext.create('Ext.grid.plugin.RowEditing');

Ext.define('PoupaNiquel.controller.Accounts', {
    extend: 'Ext.app.Controller',

    models: ['Account'],
    stores: ['Accounts'],
    views: ['accounts.Grid', 'common.MdiWindow'],
    
    refs: [{
    	ref: 'accountsGrid',
    	selector: 'accountsGrid'
    }],
    
    init: function() {
    	this.control({
    		'accountsGrid button[action=add]': {
  	    	    click: this.add
  	        },
  	        'accountsGrid button[action=delete]': {
	    	    click: this.delete
	        },
    	})
    },
    
    showPanel: function() {
    	var viewport = Ext.ComponentManager.get('viewportCenter'),
    	    panel = Ext.ComponentManager.get('accountsPanel');
    	
    	if (panel == null) {
       	    panel = Ext.create('widget.mdiWindow', {
	    		id: 'accountsPanel',
	            title: 'Accounts',
	            items: [{
	            	xtype: 'accountsGrid',
	            	store: this.getAccountsStore(),
	            	plugins: [rowEditing],
	            }]
	    	});
       	    viewport.add(panel);
       	    panel.show();
    	}
    },
    
    add: function(button) {
    	this.getAccountsStore().insert(0, Ext.create('PoupaNiquel.model.Account'));
        rowEditing.startEdit(0, 0);
    },
    
    delete: function(button) {
    	var grid = button.up('accountsGrid'),
	        record = grid.getSelectionModel().getSelection(),
            store = grid.getStore();
        
        if (record) {
        	store.remove(record);
            store.sync();
        }
    }
    
});