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
    	var id = 'transactionsTabPanel';
    	if (Ext.ComponentManager.get(id) == null) {
	    	tabpanel = Ext.create('Ext.tab.Panel', {
	    		xtype: 'tabpanel',
	    		id: id,
	    		activeTab: 0,
	    		height:'100%',
	    	});

	    	this.getAccountsStore().each(function(record) {
	    		tabpanel.add([{
	    			xtype: 'transactionpanel',
	    			title: record.data.name,
//	    			style: 'padding: 15px;',
	    		}]);
	    	});

	    	tabpanel.setActiveTab(0);
	    	viewport = Ext.ComponentManager.get('viewportCenter');
	    	viewport.add(tabpanel);
    	}
    }
});