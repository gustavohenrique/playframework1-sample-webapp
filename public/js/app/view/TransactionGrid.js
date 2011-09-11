Ext.define('PoupaNiquel.view.TransactionGrid' ,{
	extend: 'Ext.grid.Panel',
    alias: 'widget.transactiongrid',
    
    store: new Ext.data.SimpleStore({ fields: ['date', 'description','amount','balance'],  data : [ [ "2011-01-21", "Teste2", "500", "5500" ],  [ "2011-01-22", "Teste3", "-100", "5400" ] ] }),
    
    stateful: true,
    stateId: 'stateGrid',
    columns: [{
        text     : 'Date',
        sortable : false,
        dataIndex: 'date',
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
   
    
    bbar: Ext.create('Ext.PagingToolbar', {
        store: new Ext.data.SimpleStore({ fields: ['date', 'description','amount','balance'],  data : [ [ "2011-01-21", "Teste2", "500", "5500" ],  [ "2011-01-22", "Teste3", "-100", "5400" ] ] }),
        displayInfo: true,
        beforePageText: 'Page ',
        displayMsg: 'Displaying {0} - {1} of {2}',
        emptyMsg: "Nothing to display",
        displayInfo: true,
    }),

    
    initComponent: function() {
        return this.callParent(arguments);
    }
});