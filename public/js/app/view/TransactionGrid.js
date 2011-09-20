Ext.define('PoupaNiquel.view.TransactionGrid' ,{
	extend: 'Ext.grid.Panel',
    alias: 'widget.transactiongrid',
    
    border: true,
    
    columns: [{
    	xtype: 'rownumberer',
    	width: 30,
    },{
        text     : 'Date',
        sortable : true,
        dataIndex: 'transactionDate',
        renderer : Ext.util.Format.dateRenderer('d/m/Y'),
    }, {
    	text     : 'Description',
    	flex     : 1,
        sortable : false,
        dataIndex: 'description'
    }, {
    	text     : 'Amount',
        sortable : false,
        dataIndex: 'amount'
    }, {
    	text     : 'Balance',
        sortable : false,
        dataIndex: 'balance'
    }],
   
    initComponent: function() {
    	this.dockedItems = [{
            xtype: 'pagingtoolbar',
            store: 'Transactions',
            dock: 'bottom',
            beforePageText: 'Page ',
            displayMsg: 'Displaying {0} - {1} of {2}',
            emptyMsg: "Nothing to display",
            displayInfo: true
        }];
        return this.callParent(arguments);
    }
});