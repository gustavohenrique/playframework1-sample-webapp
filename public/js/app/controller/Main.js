Ext.Loader.setConfig({enabled:true});
Ext.define('PoupaNiquel.controller.Main', {
    extend: 'Ext.app.Controller',

    views: ['TopPanel', 'TransactionPanel', 'FilterPanel', 'TransactionGrid'],
    stores: ['Accounts', 'Transactions'],
    models: ['Account', 'Transaction'],
   
    
    init: function() {
    	this.control({
	      'button[action=transactions]': {
	        click: this.openTransactionsPanel
	      },
    	})
    },
    
    openTransactionsPanel: function() {

    	if (Ext.ComponentManager.get('transactionsTabPanel') == null) {
    		tabpanelItems = new Array();
    		
    		accountStore = this.getAccountsStore();
    		
	    	accountStore.each(function(record) {
	    		transactionStore = Ext.create('PoupaNiquel.store.Transactions');
	    		transactionStore.proxy.extraParams.accountId = record.data.id;
	    		transactionStore.load();
	    		
	    		transactionPanel = Ext.widget('transactionpanel');
	    		transactionPanel.setTitle(record.data.name);
	    		transactionPanel.add({
    		    	region: 'west', xtype: 'filterpanel', title: 'Filter',
    				buttons: [{ text: 'Clean'}, { text: 'Filter' }],
    			});
	    		
	    		transactionPanel.add({
    				region: 'center', xtype: 'transactiongrid', title: 'Transactions', store: transactionStore
	    		});
	    		
	    		tabpanelItems.push(transactionPanel);
	    	});
	    	
	    	tabpanel = Ext.create('Ext.tab.Panel', {
	    		xtype: 'tabpanel',
	    		id: 'transactionsTabPanel',
	    		activeTab: 0,
	    		height:'100%',
	    		frame: false,
	    		items: tabpanelItems,
	    	});

	    	viewport = Ext.ComponentManager.get('viewportCenter');
	    	viewport.add(tabpanel);
    	}
    }
});