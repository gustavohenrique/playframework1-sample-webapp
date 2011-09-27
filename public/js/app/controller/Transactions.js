Ext.define('PoupaNiquel.controller.Transactions', {
    extend: 'Ext.app.Controller',

    views: ['FilterPanel', 'TransactionGrid'],
    models: ['Account', 'Transaction'],
    stores: ['Accounts', 'Transactions'],
    
    init: function() {
    },
    
    openWindow: function() {
    	
    	if (Ext.ComponentManager.get('transactionsWindow') == null) {
    		
    		var tabs = new Array();
    		
    		this.getAccountsStore().each(function(record) {
	    		store = Ext.create('PoupaNiquel.store.Transactions');
	    		store.proxy.extraParams.accountId = record.data.id;
	    		store.load({params:{start:0, limit:5}});
	    		
	    		tab = Ext.widget('panel', {
	    		    layout: 'border',
	    		    title: record.data.name,
	    		    items: [{
	    		    	region: 'west', xtype: 'filterpanel', title: 'Filter',
	    		    }, {
	    		    	region: 'center', xtype: 'transactiongrid', title: 'Transactions', store: store
	    		    }]
	    		});
	    		
	    		tabs.push(tab);
	    	});
	    	
	    	tabPanel = Ext.widget('tabpanel', {
	    		activeTab: 0,
	    		frame: false,
	    		items: tabs,
	    	});

	    	win = Ext.create('widget.window', {
	    		id: 'transactionsWindow',
	    		constrain: true,
	            height: '70%',
	            width: '90%',
	            x: 20,
	            y: 20,
	            title: 'Transactions',
	            closable: true,
	            plain: true,
	            layout: 'fit',
	            items: tabPanel
	    	});
	    	
	    	viewport = Ext.ComponentManager.get('viewportCenter');
	    	viewport.add(win);
	    	win.show();
    	}
    }
});