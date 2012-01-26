var editing = Ext.create('Ext.grid.plugin.CellEditing', {
    clicksToEdit: 2,
});

Ext.define('PoupaNiquel.controller.Payees', {
    extend: 'Ext.app.Controller',

    models: ['Generic'],
    stores: ['Payees'],
    views:  ['payees.Grid', 'common.MdiWindow'],
    
    refs: [{
    	ref: 'payeesGrid',
    	selector: 'payeesGrid'
    }],
    
    init: function() {
    	this.control({
    		'payeesGrid button[action=add]': {
  	    	    click: this.add
  	        },
  	        'payeesGrid button[action=delete]': {
	    	    click: this.delete
	        },
    	});
    },
    
    showPanel: function() {
    	var viewport = Ext.ComponentManager.get('viewportCenter'),
    	    panel = Ext.ComponentManager.get('payeesPanel'),
    	    store = this.getPayeesStore();
    	
    	if (panel == null) {
       	    panel = Ext.create('widget.mdiWindow', {
	    		id: 'payeesPanel',
	            title: 'Payees',
	            items: [{
	            	xtype: 'payeesGrid',
	            	store: store,
	            	plugins: [editing],
	            }]
	    	});
       	    
       	    editing.on('edit', function(editor, e, eOpts) {
       	    	store.sync();
       	    });
       	    
       	    viewport.add(panel);
    	}
    	panel.show();
    },
    
    add: function(button) {
    	this.getPayeesGrid().getStore().insert(0, this.getGenericModel().create());
    },
    
    delete: function() {
    	var grid = this.getPayeesGrid(),
	        record = grid.getSelectionModel().getSelection()[0],
            store = grid.getStore();
        
        if (record) {
        	store.remove(record);
            store.sync();
        }
    }
    
});