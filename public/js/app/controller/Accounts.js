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
    	
    },
    
    showPanel: function() {
    	var viewport = Ext.ComponentManager.get('viewportCenter');
    	
    	if (Ext.ComponentQuery.query('#accountsPanel').length) {
    		Ext.ComponentManager.get('accountsPanel').show();
    	}
    	else {
       	    win = Ext.create('widget.mdiWindow', {
	    		id: 'accountsPanel',
	            title: 'Accounts',
	            items: [{
	            	xtype: 'accountsGrid'
	            }]
	    	});
       	    viewport.add(win);
       	    win.show();
    	}
    }
    
});