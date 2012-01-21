var editing = Ext.create('Ext.grid.plugin.CellEditing');

Ext.define('PoupaNiquel.controller.Categories', {
    extend: 'Ext.app.Controller',

    models: ['Category'],
    stores: ['Categories'],
    views:  ['categories.Grid', 'common.MdiWindow'],
    
    refs: [{
    	ref: 'categoriesGrid',
    	selector: 'grid'
    }],
    
    init: function() {
    	this.control({
    		'grid button[action=add]': {
  	    	    click: this.add
  	        },
  	        'grid button[action=delete]': {
	    	    click: function() { console.log('dfsfsd');}
	        },
    	});
    	console.log(this);
    },
    
    showPanel: function() {
    	var viewport = Ext.ComponentManager.get('viewportCenter'),
    	    panel = Ext.ComponentManager.get('categoriesPanel');
    	
    	if (panel == null) {
       	    panel = Ext.create('widget.mdiWindow', {
	    		id: 'categoriesPanel',
	            title: 'Categories',
	            items: [{
	            	xtype: 'categoriesGrid',
	            	store: this.getCategoriesStore(),
	            	plugins: [editing],
	            }]
	    	});
       	    viewport.add(panel);
       	    panel.show();
    	}
    },
    
    add: function(button) {
    	var grid = button.up('categoriesGrid'),
    	    account = Ext.create('PoupaNiquel.model.Account');
    	
    	grid.getStore().insert(0, account);
        editing.startEdit(0, 0);
    },
    
    delete: function() {
    	alert('dsfs');
//    	var grid = button.up('categoriesGrid'),
//	        record = grid.getSelectionModel().getSelection()[0],
//            store = grid.getStore();
//        
//        if (record) {
//        	store.remove(record);
//            store.sync();
//        }
    }
    
});