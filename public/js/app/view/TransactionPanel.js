Ext.define('PoupaNiquel.view.TransactionPanel' ,{
	extend: 'Ext.panel.Panel',
    alias: 'widget.transactionpanel',
    
    layout: 'border',
    height:'100%',

    items: [{
    	region: 'west',
		xtype: 'filterpanel',
		title: 'Filter',
	}, {
		region: 'center',
		xtype: 'transactiongrid',
		title: 'Transactions',
	}],

    initComponent: function() {
        return this.callParent(arguments);
    }
});