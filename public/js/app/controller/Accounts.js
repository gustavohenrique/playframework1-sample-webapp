//var editing = Ext.create('Ext.grid.plugin.editing');
var editing = Ext.create('Ext.grid.plugin.CellEditing');

Ext.define('PoupaNiquel.controller.Accounts', {
    extend: 'Ext.app.Controller',

    models: ['Account'],
    stores: ['Accounts'],
    views:  ['accounts.Grid', 'common.MdiWindow'],
    
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
	            	plugins: [editing],
	            }]
	    	});
       	    viewport.add(panel);
       	    panel.show();
    	}
    },
    
    add: function(button) {
    	//var account = Ext.create('PoupaNiquel.model.Account');
    	this.getAccountsGrid().getStore().insert(0, this.getAccountModel().create());
    },
    
    delete: function(button) {
    	var grid = this.getAccountsGrid(),
	        record = grid.getSelectionModel().getSelection()[0],
            store = grid.getStore();
        
        if (record) {
        	store.remove(record);
            store.sync();
        }
    }
    
});