Ext.define('PoupaNiquel.controller.Accounts', {
    extend: 'Ext.app.Controller',

    models: ['Account'],
    stores: ['Accounts'],
    views: ['accounts.Grid'],
    
    refs: [{
    	ref: 'accountsGrid',
    	selector: 'accountsGrid'
    }],
    
    init: function() {
    	
    },
    
    showPanel: function() {
    	var viewport = Ext.ComponentManager.get('viewportCenter'),
    	    win = Ext.create('widget.panel', {
	    		id: 'accountsPanel',
	    		constrain: true,
	            height: '100%',
	            width: '100%',
	            title: 'Accounts',
	            closable: true,
	            plain: true,
	            layout: 'fit',
	            items: [{
	            	xtype: 'accountsGrid'
	            }]
	    	});
    	viewport.add(win);
    	win.show();
    }
    
});