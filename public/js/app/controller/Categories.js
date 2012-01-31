Ext.define('PoupaNiquel.controller.Categories', {
    extend: 'Ext.app.Controller',

    models: ['Category'],
    stores: ['Categories'],
    views:  ['categories.Grid', 'common.MdiWindow'],
    
    refs: [{
    	ref: 'categoriesGrid',
    	selector: 'categoriesGrid'
    }],
    
    init: function() {
    	this.control({
    		'categoriesGrid button[action=add]': {
  	    	    click: this.add
  	        },
  	        'categoriesGrid button[action=delete]': {
	    	    click: this.delete
	        },
    	});
    },
    
    showPanel: function() {
    	var viewport = Ext.ComponentManager.get('viewportCenter'),
    	    panel = Ext.ComponentManager.get('categoriesPanel'),
    	    store = this.getCategoriesStore();
    	
    	if (panel == null) {
       	    panel = Ext.create('widget.mdiWindow', {
	    		id: 'categoriesPanel',
	            title: 'Categories',
	            items: [{
	            	xtype: 'categoriesGrid',
	            	store: store,
	            	plugins: Ext.create('Ext.grid.plugin.CellEditing'),
	            }]
	    	});
       	    viewport.add(panel);
    	}
    	
    	panel.show();
    },
    
    add: function(button) {
    	this.getCategoriesGrid().getStore().insert(0, this.getCategoryModel().create());
    },
    
    delete: function() {
    	var grid = this.getCategoriesGrid(),
	        record = grid.getSelectionModel().getSelection()[0],
            store = grid.getStore();
        
        if (record) {
        	store.remove(record);
            store.sync();
        }
    }
    
});