var editing = Ext.create('Ext.grid.plugin.CellEditing', {
    clicksToEdit: 1,
});

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
	            	plugins: [editing],
	            }]
	    	});
       	    
       	    editing.on('edit', function(editor, e, eOpts) {
       	    	console.debug(eOpts);
       	    	store.sync();
       	    });
       	    
       	    viewport.add(panel);
       	    panel.show();
    	}
    },
    
    add: function(button) {
    	//var category = Ext.create('PoupaNiquel.model.Category');
    	this.getCategoriesGrid().getStore().insert(0, this.getCategoryModel().create());
        //editing.startEdit(0, 0);
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